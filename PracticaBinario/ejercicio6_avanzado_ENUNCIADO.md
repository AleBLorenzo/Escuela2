# 📝 EJERCICIO AVANZADO 1: Procesador y Compresor de Replays

## 🎯 Objetivo

Crear un programa avanzado que **lea, analice, comprima y optimice archivos de replay** de videojuegos, eliminando frames redundantes y aplicando técnicas de compresión especializadas.

Este ejercicio te enseñará a:
- Analizar estructuras binarias complejas
- Detectar y eliminar redundancias en datos secuenciales
- Aplicar algoritmos de compresión diferencial
- Optimizar archivos binarios para tamaño y velocidad
- Implementar formatos binarios versionados
- Generar estadísticas detalladas de optimización

---

## 📋 Descripción del Ejercicio

Los archivos de replay en juegos competitivos pueden ser **muy grandes** si se guardan todos los frames sin optimización. Un replay de 10 minutos a 60 FPS son 36,000 frames. Si cada frame ocupa 100 bytes, ¡son 3.6 MB solo para un replay!

Tu programa debe:
1. **Leer un archivo de replay** en formato extendido (basado en ejercicio básico 2)
2. **Analizar frames** para detectar redundancias
3. **Aplicar compresión delta** (solo guardar cambios)
4. **Eliminar frames duplicados consecutivos**
5. **Comprimir secuencias repetitivas** con RLE (Run-Length Encoding)
6. **Generar reporte** de optimización
7. **Crear replay optimizado** que ocupe menos espacio

---

## 🎮 Contexto: Sistema de Replays Competitivos

**Problema real:**
- Un torneo online genera miles de replays
- Cada replay sin optimizar: 5-10 MB
- 1000 replays = 5-10 GB de almacenamiento
- Con optimización inteligente: 500 MB - 1 GB (reducción 90%)

**Casos de uso:**
- Plataformas de streaming de eSports
- Sistemas de análisis de partidas
- Guardado en la nube de replays
- Compartir replays por redes lentas

---

## 📥 Formato de Entrada

### **Replay extendido (sin optimizar):**

```
[HEADER - 32 bytes]
Magic: "REPL" (4 bytes)
Versión: 0x02 (1 byte)
FPS: 60 (1 byte)
Duración (frames): uint32
Timestamp Unix: uint64
ID jugador 1: uint32
ID jugador 2: uint32
Mapa ID: uint16
Modo de juego: uint8
Reservado: (7 bytes)

[FRAME DATA - variable]
Para cada frame:
  Frame number: uint32 (4 bytes)
  Timestamp: uint32 (4 bytes, ms desde inicio)
  
  Player 1 state (20 bytes):
    Position X: float (4 bytes)
    Position Y: float (4 bytes)
    Velocidad X: float (4 bytes)
    Velocidad Y: float (4 bytes)
    Estado: uint32 (4 bytes) - bits: saltar, agachado, atacando, etc.
  
  Player 2 state (20 bytes): (misma estructura)
  
  Inputs (2 bytes):
    P1 input: uint8 (W=0x01, A=0x02, S=0x04, D=0x08, etc.)
    P2 input: uint8
  
  Events (variable):
    Event count: uint8 (0-255)
    Para cada event:
      Event type: uint8
      Event data: variable según tipo

[FOOTER - 16 bytes]
Total frames: uint32
Checksum: uint64
Magic end: "ENDE" (4 bytes)
```

---

## 📤 Formato de Salida Optimizado

### **Replay comprimido:**

```
[HEADER - 32 bytes]
Magic: "RPLC" (4 bytes) - Compressed
Versión: 0x03 (1 byte)
Compression flags: uint8
  bit 0: Delta compression
  bit 1: RLE compression
  bit 2: Frame skipping
  bit 3-7: Reserved
FPS original: 60 (1 byte)
... (resto igual pero con metadatos de compresión)

[KEYFRAME 0 - frame completo]
Frame 0 con todos los datos

[DELTA FRAME 1]
Solo cambios respecto a frame anterior:
  Changed fields bitmask: uint32
  Solo los campos que cambiaron

[DELTA FRAME 2]
...

[RLE SEQUENCE]
Si hay N frames idénticos:
  RLE marker: 0xFF
  Frame de referencia: uint32
  Repeat count: uint16

[KEYFRAME cada 60 frames]
Frame completo para permitir seek

[FOOTER - 32 bytes]
Original size: uint32
Compressed size: uint32
Compression ratio: float
Keyframe count: uint16
... estadísticas
```

---

## 🔧 Técnicas de Optimización a Implementar

### **1. Delta Compression (Compresión Diferencial)**

Solo guarda los cambios entre frames consecutivos:

```java
// Frame anterior
PlayerState prevState = {x: 100.0, y: 200.0, vx: 5.0, vy: 0.0};

// Frame actual
PlayerState currState = {x: 105.0, y: 200.0, vx: 5.0, vy: 0.0};

// Delta (solo guardar):
// - x cambió: +5.0
// - y, vx, vy: sin cambios (no guardar)

// Bitmask de campos que cambiaron:
uint32 mask = 0x01; // Solo bit 0 activado (x cambió)

// Guardar:
mask (4 bytes) + delta_x (4 bytes) = 8 bytes
// vs frame completo: 20 bytes
// Ahorro: 60%
```

### **2. Run-Length Encoding (RLE)**

Detecta frames idénticos consecutivos:

```java
// Si frames 100-149 son idénticos:
// En lugar de guardar 50 frames × 46 bytes = 2300 bytes
// Guardar:
//   - Frame 100 completo: 46 bytes
//   - RLE marker: 1 byte
//   - Frame referencia: 4 bytes
//   - Count: 2 bytes (49 repeticiones)
// Total: 53 bytes
// Ahorro: 97.7%
```

### **3. Frame Skipping**

Si dos frames son casi idénticos (cambios < threshold), saltar el intermedio:

```java
if (distanciaEntreFram es(frame1, frame3) < THRESHOLD) {
    // Saltar frame2
    // En reproducción, interpolar entre frame1 y frame3
}
```

### **4. Keyframes**

Insertar frames completos cada N frames para:
- Permitir seek/salto rápido
- Limitar propagación de errores
- Balance entre compresión y accesibilidad

```java
// Cada 60 frames (1 segundo a 60 FPS), guardar keyframe completo
if (frameNumber % 60 == 0) {
    escribirKeyframe(frame);
} else {
    escribirDeltaFrame(frame, framePrevio);
}
```

---

## 💡 Algoritmo Sugerido

```
FASE 1: ANÁLISIS
1. Leer archivo replay original
2. Cargar todos los frames en memoria (o procesar en chunks)
3. Analizar patrones:
   - Frames consecutivos idénticos
   - Campos que cambian frecuentemente vs estáticos
   - Secuencias repetitivas
4. Generar estadísticas de compresibilidad

FASE 2: COMPRESIÓN
1. Escribir header optimizado
2. Escribir keyframe 0 (completo)
3. Para cada frame subsiguiente:
   a. Comparar con frame anterior
   b. Si idéntico:
      - Incrementar contador RLE
      - Continuar
   c. Si contador RLE > 0:
      - Escribir secuencia RLE
      - Reset contador
   d. Si es keyframe (cada N frames):
      - Escribir frame completo
   e. Si no:
      - Calcular delta
      - Escribir solo cambios
4. Escribir footer con estadísticas

FASE 3: VALIDACIÓN
1. Leer replay comprimido
2. Descomprimir en memoria
3. Comparar con original
4. Verificar que son funcionalmente equivalentes
```

---

## 🎓 Conceptos Avanzados

### **1. Bitmask para campos cambiados:**

```java
class PlayerState {
    float x, y, vx, vy;
    int estado;
}

// Calcular qué cambió
int calcularMask(PlayerState prev, PlayerState curr) {
    int mask = 0;
    if (Math.abs(curr.x - prev.x) > EPSILON) mask |= 0x01;
    if (Math.abs(curr.y - prev.y) > EPSILON) mask |= 0x02;
    if (Math.abs(curr.vx - prev.vx) > EPSILON) mask |= 0x04;
    if (Math.abs(curr.vy - prev.vy) > EPSILON) mask |= 0x08;
    if (curr.estado != prev.estado) mask |= 0x10;
    return mask;
}

// Escribir solo campos que cambiaron
void escribirDelta(DataOutputStream dos, PlayerState curr, 
                   PlayerState prev, int mask) throws IOException {
    dos.writeInt(mask);
    if ((mask & 0x01) != 0) dos.writeFloat(curr.x);
    if ((mask & 0x02) != 0) dos.writeFloat(curr.y);
    if ((mask & 0x04) != 0) dos.writeFloat(curr.vx);
    if ((mask & 0x08) != 0) dos.writeFloat(curr.vy);
    if ((mask & 0x10) != 0) dos.writeInt(curr.estado);
}
```

### **2. Detección de frames idénticos:**

```java
boolean framesIdenticos(Frame f1, Frame f2) {
    // Comparar todos los campos relevantes
    return Math.abs(f1.p1.x - f2.p1.x) < 0.01f &&
           Math.abs(f1.p1.y - f2.p1.y) < 0.01f &&
           // ... más comparaciones
           f1.p1.estado == f2.p1.estado &&
           f1.p1Input == f2.p1Input;
}
```

### **3. Cuantización de floats:**

```java
// Reducir precisión de floats para mejor compresión
short cuantizarPosicion(float pos) {
    // Posición en rango [0, 1000], precisión 0.1
    return (short) (pos * 10);
}

float descuantizarPosicion(short pos) {
    return pos / 10.0f;
}

// Ahorro: 4 bytes (float) → 2 bytes (short)
```

---

## 📊 Reporte de Optimización Esperado

```
╔════════════════════════════════════════════════════════════════╗
║        PROCESADOR DE REPLAYS - REPORTE DE OPTIMIZACIÓN        ║
╚════════════════════════════════════════════════════════════════╝

Archivo original: torneo_semifinal_match3.replay
Archivo optimizado: torneo_semifinal_match3.rplc

--- INFORMACIÓN GENERAL ---
Duración: 8:45 (525 segundos)
FPS: 60
Total frames: 31,500

--- ANÁLISIS DE REDUNDANCIA ---
Frames completamente idénticos consecutivos: 8,420 (26.7%)
  Secuencias RLE detectadas: 142
  Secuencia más larga: 89 frames (1.48s de pausa)

Campos estáticos (nunca cambian):
  Mapa ID: 100%
  Player IDs: 100%

Campos de baja variación (< 10% cambios):
  Velocidad Y: 8.5%
  Estado "agachado": 3.2%

Campos de alta variación (> 80% cambios):
  Posición X: 95.3%
  Posición Y: 92.1%
  Inputs: 88.7%

--- TÉCNICAS APLICADAS ---
✓ Delta compression: 22,658 frames (71.9%)
✓ RLE compression: 142 secuencias (8,420 frames)
✓ Keyframes: 525 (cada 60 frames)
✓ Cuantización de floats: Todas las posiciones

--- RESULTADOS ---
Tamaño original: 1,449,000 bytes (1.38 MB)
  Header: 32 bytes
  Frames: 1,448,880 bytes (46 bytes × 31,500)
  Footer: 16 bytes

Tamaño optimizado: 235,678 bytes (230.15 KB)
  Header: 32 bytes
  Keyframes: 24,150 bytes (46 bytes × 525)
  Delta frames: 201,234 bytes (~9 bytes avg)
  RLE sequences: 2,130 bytes (15 bytes × 142)
  Footer: 32 bytes

Reducción: 1,213,322 bytes (83.7%)
Ratio de compresión: 6.15:1

--- DESGLOSE POR TÉCNICA ---
Delta compression: ~820 KB ahorrados (56.6% del ahorro)
RLE compression: ~380 KB ahorrados (26.2% del ahorro)
Cuantización: ~188 KB ahorrados (13.0% del ahorro)
Header optimization: ~13 KB ahorrados (0.9% del ahorro)

--- MÉTRICAS DE CALIDAD ---
✓ Validación: PASSED
✓ Frames verificados: 31,500/31,500
✓ Pérdida de precisión: < 0.1 unidades (aceptable)
✓ Integridad: 100%

--- PERFORMANCE ---
Tiempo de procesamiento: 2.34 segundos
Velocidad: 13,461 frames/s
Memoria pico: 45 MB

Optimización completada exitosamente.
```

---

## 🧪 Casos de Prueba

### **Caso 1: Replay con muchas pausas**
- Jugador pausa el juego varias veces
- Muchos frames idénticos consecutivos
- Resultado esperado: > 80% compresión con RLE

### **Caso 2: Acción constante**
- Partida sin pausas, movimiento continuo
- Pocos frames idénticos
- Resultado esperado: 40-60% compresión con delta

### **Caso 3: Replay corto (< 30 segundos)**
- Pocos frames, overhead de keyframes significativo
- Resultado esperado: 30-40% compresión

### **Caso 4: Replay muy largo (> 30 minutos)**
- Muchos frames, alta compresibilidad
- Resultado esperado: > 85% compresión

### **Caso 5: Comparación con ZIP**
- Comprimir replay original con ZIP
- Comprimir replay original con tu algoritmo
- Comparar ratios y tiempos

---

## 🚀 Desafíos Adicionales (Opcional)

### **1. Descompresor:**
Implementa el proceso inverso:
- Lee replay comprimido
- Reconstruye todos los frames
- Verifica integridad

### **2. Predicción de movimiento:**
En lugar de delta simple, predice el siguiente frame:
```java
// Si velocidad es constante, predecir posición
float posPredecida = posActual + velocidad * deltaTime;
float delta = posReal - posPredecida;
// Guardar solo el delta de predicción (más pequeño)
```

### **3. Compresión adaptativa:**
Analiza el replay y elige automáticamente:
- Intervalo de keyframes óptimo
- Threshold de cuantización
- Técnicas según características del replay

### **4. Streaming:**
Diseña formato que permita:
- Reproducir mientras se descarga
- Seek a timestamp específico sin descomprimir todo

### **5. Metadata enriquecida:**
Añade índice de eventos importantes:
- Kills, objetivos, momentos épicos
- Permite saltar directamente a puntos de interés

### **6. Multi-threading:**
Procesa frames en paralelo:
- Thread 1: Lee frames originales
- Thread 2: Calcula deltas
- Thread 3: Aplica RLE
- Thread 4: Escribe salida

---

## 📚 Consideraciones de Diseño

### **Balance compresión vs accesibilidad:**
- Más keyframes = archivo más grande, seek más rápido
- Menos keyframes = archivo más pequeño, seek más lento
- Recomendado: 1 keyframe por segundo

### **Pérdida aceptable:**
- Posiciones: ±0.1 unidades aceptable (cuantización)
- Velocidades: ±0.01 aceptable
- Estados binarios: sin pérdida

### **Versionado:**
Diseña para forward compatibility:
- Versión en header
- Flags de características opcionales
- Lector debe poder ignorar datos desconocidos

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✅ Leer replays en formato original correctamente
- ✅ Aplicar al menos 2 técnicas de compresión (delta + RLE)
- ✅ Lograr > 60% de reducción en archivos típicos
- ✅ Preservar la funcionalidad del replay (pérdida mínima aceptable)
- ✅ Generar reporte detallado de optimización
- ✅ Validar integridad del replay comprimido
- ✅ Manejar replays de cualquier duración (sin límite de memoria)
- ✅ Procesar 10,000+ frames/segundo

---

## 🔗 Recursos

- RFC 1951 (DEFLATE): Ideas de compresión
- Algoritmos de video codec (H.264): Inspiración para delta frames
- `NIVEL1_TEORIA.md`: Manejo de buffers
- `HERRAMIENTA_HEX_DUMP.md`: Inspeccionar archivos binarios

---

**Tiempo estimado:** 3-5 horas

**Dificultad:** ⭐⭐⭐⭐⭐ Avanzada

**¡Optimiza esos replays!** 🎮📊🔥
