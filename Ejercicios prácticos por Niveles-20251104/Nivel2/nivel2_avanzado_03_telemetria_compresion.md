# Nivel 2 - Ejercicio Avanzado 3: Sistema de Telemetría de Carreras con Compresión Delta

**Dificultad:** ⭐⭐⭐ Avanzado  
**Tema:** F1 Racing - Telemetría en tiempo real con compresión inteligente

## Contexto

Los equipos de Fórmula 1 recopilan cientos de sensores en tiempo real durante las carreras: velocidad, RPM, temperatura del motor, presión de neumáticos, posición GPS, etc. Los datos se transmiten constantemente desde el coche al pit, generando **gigabytes de información por carrera**.

Tu tarea es crear un sistema de telemetría que:
1. Capture datos de sensores en tiempo real
2. Comprima los datos usando **Delta Encoding** (diferencias)
3. Reconstruya los datos originales desde el formato comprimido
4. Analice el rendimiento del piloto

## ¿Qué es Delta Encoding?

En lugar de guardar valores absolutos, guardas las **diferencias** con respecto al valor anterior.

### Ejemplo Simple

**Datos originales de velocidad (km/h):**
```
[250, 252, 254, 256, 255, 253, 251, 249]
```

**Delta encoding (diferencias):**
```
[250, +2, +2, +2, -1, -2, -2, -2]
```

**Ventaja:** Los deltas suelen ser números pequeños que requieren menos bytes.

### Optimización con Tipos Variables

- Si delta cabe en un `byte` (-127 a 127): usa 1 byte
- Si necesita un `short` (-32767 a 32767): usa 1 byte de flag + 2 bytes
- Si necesita un `int`: usa 1 byte de flag + 4 bytes

## Especificaciones del Sistema

### Datos de Telemetría (Muestra)

Cada **frame** de telemetría contiene (60 veces por segundo):

| Campo | Tipo | Unidad | Rango Típico |
|-------|------|--------|--------------|
| Timestamp | long | ms | 0 - Long.MAX |
| Velocidad | short | km/h | 0 - 400 |
| RPM | short | revoluciones/min | 0 - 20000 |
| Marcha | byte | 1-8 | 1 - 8 |
| Acelerador | byte | % | 0 - 100 |
| Freno | byte | % | 0 - 100 |
| TempMotor | short | °C | 50 - 120 |
| TempFrenoDel | short | °C | 20 - 800 |
| TempFrenoTras | short | °C | 20 - 800 |
| PosicionX | float | metros | variable |
| PosicionY | float | metros | variable |
| DRS | boolean | activo | true/false |

**Tamaño sin comprimir:** 
- Timestamp: 8 bytes
- 6 shorts: 12 bytes
- 3 bytes: 3 bytes
- 2 floats: 8 bytes
- 1 boolean: 1 byte
- **TOTAL: 32 bytes por frame**

A 60 frames/segundo, una carrera de 90 minutos genera:
- 60 × 60 × 90 = 324,000 frames
- 324,000 × 32 = **10.4 MB** sin comprimir

## Formato de Archivo Comprimido

### Header (24 bytes)

```
Offset | Campo              | Tipo  | Bytes | Descripción
-------|-------------------|-------|-------|---------------------------
0      | Magic             | int   | 4     | 0x54454C46 ("TELF")
4      | Version           | byte  | 1     | Versión del formato (1)
5      | CompressionType   | byte  | 1     | 1=Delta, 2=RLE, 3=Mixto
6      | SamplingRate      | short | 2     | Muestras por segundo (60)
8      | NumFrames         | int   | 4     | Total de frames en archivo
12     | StartTimestamp    | long  | 8     | Timestamp del primer frame
20     | Reserved          | int   | 4     | Reservado
```

### Frame Base (Primer Frame - Sin Comprimir: 32 bytes)

El primer frame se guarda completo sin compresión, sirviendo como referencia:

```
long timestamp
short velocidad
short rpm
byte marcha
byte acelerador
byte freno
short tempMotor
short tempFrenoDel
short tempFrenoTras
float posX
float posY
boolean drs
```

### Frames Comprimidos (Deltas)

Para cada frame subsecuente, guardamos solo las diferencias:

#### Formato de Delta por Campo

**Para campos numéricos pequeños (byte/short):**
```
[Flag: 1 byte]
  0x00 = sin cambio (0 bytes de datos)
  0x01 = delta de 1 byte (1 byte de datos)
  0x02 = delta de 2 bytes (2 bytes de datos)
  0x03 = valor absoluto (tamaño completo)
```

**Para campos grandes (float/double/long):**
```
[Flag: 1 byte]
  0x00 = sin cambio
  0x01 = valor absoluto nuevo
```

#### Ejemplo de Frame Comprimido

Si solo cambian velocidad (+2) y acelerador (+5):
```
Timestamp: [0x00] (sin cambio)
Velocidad: [0x01] [0x02] (delta = +2)
RPM: [0x00] (sin cambio)
Marcha: [0x00] (sin cambio)
Acelerador: [0x01] [0x05] (delta = +5)
Freno: [0x00] (sin cambio)
TempMotor: [0x00]
TempFrenoDel: [0x00]
TempFrenoTras: [0x00]
PosX: [0x01] [float bytes] (cambió posición)
PosY: [0x01] [float bytes]
DRS: [0x00]

Total: 12 flags + 2 datos + 8 datos de floats = 22 bytes
(vs 32 bytes sin comprimir = 31% de ahorro)
```

## Funcionalidades Requeridas

### 1. Captura de Datos (Simulación)

#### `generarTelemetriaNormal(int numFrames)`
```java
public static List<TelemetriaFrame> generarTelemetriaNormal(int numFrames)
```
- Genera telemetría realista de una vuelta normal
- Velocidad varía entre 80-320 km/h
- RPM correlacionado con velocidad
- Marchas suben/bajan gradualmente
- Acelerador y freno inversamente relacionados
- Temperaturas aumentan gradualmente

#### `generarTelemetriaOvertake(int numFrames)`
```java
public static List<TelemetriaFrame> generarTelemetriaOvertake(int numFrames)
```
- Simula un adelantamiento agresivo
- Cambios bruscos de velocidad y marchas
- Uso intenso de frenos
- Temperaturas más altas

#### `generarTelemetriaAccidente(int startFrame, int numFrames)`
```java
public static List<TelemetriaFrame> generarTelemetriaAccidente(
        int startFrame, int numFrames)
```
- Simula telemetría antes, durante y después de un accidente
- Velocidad alta que baja bruscamente a 0
- RPM cae a idle
- Valores erráticos en el momento del impacto

### 2. Compresión

#### `comprimirTelemetria(List<TelemetriaFrame> frames, String archivoSalida)`
```java
public static void comprimirTelemetria(List<TelemetriaFrame> frames, 
                                       String archivoSalida)
```
- Escribe header con metadatos
- Escribe primer frame completo (base)
- Para cada frame subsecuente:
  - Calcula deltas con frame anterior
  - Escribe solo lo que cambió
  - Usa flags para indicar tipo de cambio

#### `calcularDelta(TelemetriaFrame actual, TelemetriaFrame anterior)`
```java
private static Delta calcularDelta(TelemetriaFrame actual, 
                                    TelemetriaFrame anterior)
```
- Compara campo por campo
- Calcula diferencias numéricas
- Determina el tipo de delta óptimo (byte/short/full)

### 3. Descompresión

#### `descomprimirTelemetria(String archivoComprimido)`
```java
public static List<TelemetriaFrame> descomprimirTelemetria(
        String archivoComprimido)
```
- Lee header y valida formato
- Lee frame base completo
- Para cada frame comprimido:
  - Lee flags y deltas
  - Reconstruye el frame aplicando deltas al anterior
  - Valida valores dentro de rangos esperados

#### `aplicarDelta(TelemetriaFrame anterior, Delta delta)`
```java
private static TelemetriaFrame aplicarDelta(TelemetriaFrame anterior, 
                                             Delta delta)
```
- Copia el frame anterior
- Aplica los cambios especificados en el delta
- Devuelve el nuevo frame reconstruido

### 4. Análisis y Estadísticas

#### `analizarCompresion(String archivoOriginal, String archivoComprimido)`
```java
public static void analizarCompresion(String archivoOriginal, 
                                      String archivoComprimido)
```
Muestra:
- Tamaño original vs comprimido
- Ratio de compresión
- Ahorro en bytes y porcentaje
- Bytes promedio por frame
- Campos que más comprimen
- Campos que menos comprimen

#### `validarIntegridad(List<TelemetriaFrame> original, 
                       List<TelemetriaFrame> descomprimido)`
```java
public static boolean validarIntegridad(List<TelemetriaFrame> original, 
                                        List<TelemetriaFrame> descomprimido)
```
- Compara frame por frame
- Verifica que todos los valores coincidan
- Tolera diferencias mínimas en floats (±0.001)
- Devuelve true si los datos son idénticos

#### `analizarRendimiento(List<TelemetriaFrame> frames)`
```java
public static void analizarRendimiento(List<TelemetriaFrame> frames)
```
Calcula y muestra:
- Velocidad máxima alcanzada
- Velocidad promedio
- RPM máximo y promedio
- Uso de frenos (% del tiempo)
- Uso de acelerador (% del tiempo)
- Temperatura máxima del motor
- Tiempo con DRS activo
- Detección de momentos críticos (frenadas fuertes, aceleraciones, etc.)

### 5. Visualización en Consola

#### `graficarVelocidad(List<TelemetriaFrame> frames, int ancho)`
```java
public static void graficarVelocidad(List<TelemetriaFrame> frames, int ancho)
```
Genera un gráfico ASCII de velocidad:
```
Velocidad (km/h)
320 |                    ╱╲
    |                  ╱    ╲
280 |                ╱        ╲
    |              ╱            ╲
240 |            ╱                ╲
    |          ╱                    ╲
200 |        ╱                        ╲
    |      ╱                            ╲
160 |    ╱                                ╲
    |  ╱                                    ╲
120 |╱                                        ╲
    +--------------------------------------------
    0s                                       10s
```

#### `mostrarHeatmap(List<TelemetriaFrame> frames)`
```java
public static void mostrarHeatmap(List<TelemetriaFrame> frames)
```
Muestra un mapa de calor de temperaturas por zona del coche.

## Clase Auxiliar: TelemetriaFrame

```java
class TelemetriaFrame {
    private long timestamp;
    private short velocidad;      // km/h
    private short rpm;            // revoluciones/min
    private byte marcha;          // 1-8
    private byte acelerador;      // 0-100%
    private byte freno;           // 0-100%
    private short tempMotor;      // °C
    private short tempFrenoDel;   // °C
    private short tempFrenoTras;  // °C
    private float posX;           // metros
    private float posY;           // metros
    private boolean drs;          // DRS activo
    
    // Métodos útiles
    public double getVelocidadMS() {
        return velocidad / 3.6;  // km/h a m/s
    }
    
    public boolean esFrenada() {
        return freno > 50;
    }
    
    public boolean esAceleracion() {
        return acelerador > 80 && freno < 10;
    }
    
    public int getTempPromedioFrenos() {
        return (tempFrenoDel + tempFrenoTras) / 2;
    }
}
```

## Clase Auxiliar: Delta

```java
class Delta {
    // Flags para cada campo
    private byte timestampFlag;
    private byte velocidadFlag;
    private byte rpmFlag;
    // ... etc
    
    // Valores de delta (solo si flag != 0x00)
    private long timestampDelta;
    private short velocidadDelta;
    private short rpmDelta;
    // ... etc
    
    // Constantes
    public static final byte SIN_CAMBIO = 0x00;
    public static final byte DELTA_1_BYTE = 0x01;
    public static final byte DELTA_2_BYTES = 0x02;
    public static final byte VALOR_ABSOLUTO = 0x03;
}
```

## Escenario de Prueba

Simula una vuelta completa de F1:

```
Sector 1 (0-20s): Recta principal
- Velocidad: 100 → 320 km/h (aceleración)
- DRS activo a partir de 300 km/h
- Marchas: 3 → 4 → 5 → 6 → 7 → 8

Sector 2 (20-35s): Curvas técnicas
- Velocidad: 320 → 120 → 180 → 100 km/h
- Frenadas fuertes (freno 100%)
- Marchas: 8 → 5 → 3 → 6 → 4
- Temperaturas de frenos suben a 600°C

Sector 3 (35-50s): Chicane y recta final
- Velocidad: 100 → 80 → 280 km/h
- Marchas: 4 → 2 → 3 → 7
- Aceleración fuerte saliendo

Total: 50 segundos × 60 fps = 3000 frames
```

## Casos de Prueba Obligatorios

1. **Generar 3000 frames** de telemetría realista
2. **Comprimir** los datos usando delta encoding
3. **Descomprimir** y verificar integridad (100% igual)
4. **Analizar compresión:**
   - Mostrar ratio de compresión
   - Identificar qué campos comprimen mejor
5. **Analizar rendimiento** del piloto
6. **Generar gráfico** de velocidad en consola
7. **Simular accidente** (100 frames) y comprimir
8. **Comparar ratios** de compresión: vuelta normal vs accidente

## Cálculos Esperados

### Compresión Esperada

**Vuelta normal (valores cambian gradualmente):**
- Original: 3000 × 32 = 96,000 bytes
- Comprimido: ~25,000-35,000 bytes
- Ratio: 60-70% de compresión

**Accidente (valores erráticos):**
- Original: 100 × 32 = 3,200 bytes
- Comprimido: ~2,000-2,500 bytes
- Ratio: 20-40% de compresión (menos eficiente)

### Análisis de Rendimiento

Para la vuelta de ejemplo:
- Velocidad máxima: 320 km/h
- Velocidad promedio: ~180 km/h
- Tiempo con DRS: ~8 segundos (16%)
- Frenadas fuertes: 4 eventos
- Temperatura máxima frenos: 650°C

## Salida Esperada

```
=== SISTEMA DE TELEMETRÍA F1 ===

[1] Generando telemetría de vuelta...
✓ 3000 frames generados (50.00 segundos @ 60 fps)
  Sector 1: Recta principal (0-20s)
  Sector 2: Curvas técnicas (20-35s)
  Sector 3: Chicane y final (35-50s)

[2] Comprimiendo datos...
✓ Frame base guardado (32 bytes)
✓ 2999 frames delta procesados
✓ Archivo comprimido: telemetria.tlf

=== ANÁLISIS DE COMPRESIÓN ===
Tamaño original: 96,000 bytes (93.75 KB)
Tamaño comprimido: 28,456 bytes (27.79 KB)
Ratio de compresión: 70.37%
Ahorro: 67,544 bytes (65.96 KB)

Bytes por frame:
- Original: 32.00 bytes/frame
- Comprimido: 9.49 bytes/frame (promedio)

Campos mejor comprimidos:
1. Marcha: 95.2% compresión (cambia poco)
2. RPM: 82.1% compresión (correlacionado con velocidad)
3. Temp Motor: 78.5% compresión (cambios graduales)

Campos peor comprimidos:
1. PosX/PosY: 12.3% compresión (siempre cambian)
2. Timestamp: 0% compresión (siempre incrementa)

[3] Descomprimiendo...
✓ 3000 frames reconstruidos
✓ Validación de integridad: EXITOSA (100% idéntico)

[4] Análisis de rendimiento...

=== ESTADÍSTICAS DE VUELTA ===
Duración: 50.00 segundos
Frames totales: 3000

Velocidad:
- Máxima: 320 km/h (18.5s - Sector 1)
- Promedio: 182 km/h
- Mínima: 80 km/h (36.2s - Chicane)

Motor:
- RPM máximo: 19,800 (18.5s)
- RPM promedio: 12,400
- Temp. máxima: 105°C

Frenos:
- Uso: 28% del tiempo
- Frenada más fuerte: 100% (22.1s)
- Temp. máxima delantera: 680°C
- Temp. máxima trasera: 625°C

DRS:
- Activo: 8.2 segundos (16.4%)
- Desde 300 km/h en adelante

Eventos destacados:
⚠️  4 frenadas fuertes detectadas (>90%)
✓  3 aceleraciones completas (0-100%)
⚠️  Temperatura frenos crítica en 22.5s (>650°C)

[5] Visualización de velocidad...

Velocidad (km/h) - Vuelta completa
320 |              ╱─────╲                    ╱───╲
    |            ╱         ╲                ╱       ╲
280 |          ╱             ╲            ╱           ╲
    |        ╱                 ╲        ╱               ╲
240 |      ╱                     ╲    ╱                   ╲
    |    ╱                         ╲╱                       ╲
200 |  ╱                                                      ╲
    |╱                                                          ╲
160 |                                                             ╲
120 |                         ╱─╲     ╱╲
 80 |                        ╱    ╲╱╱   ╲
    +──────────────────────────────────────────────────────────────
    0s          10s         20s         30s         40s         50s
    │─ Sector 1 ─│───── Sector 2 ──────│──── Sector 3 ────│

[6] Comparando con telemetría de accidente...
✓ 100 frames de accidente generados y comprimidos
  - Tamaño original: 3,200 bytes
  - Comprimido: 2,150 bytes
  - Ratio: 32.8% (menos eficiente por valores erráticos)

✅ Sistema operativo correctamente
```

## Retos Adicionales (Opcional)

1. **Compresión RLE:** Implementa Run-Length Encoding para valores que no cambian
2. **Compresión híbrida:** Delta para valores graduales, RLE para constantes
3. **Predicción:** Predice valores futuros basándote en tendencias
4. **Detección de anomalías:** Identifica patrones anormales en la telemetría
5. **Exportar a CSV:** Convierte telemetría binaria a CSV para análisis externo
6. **Streaming:** Procesa telemetría en tiempo real (frame por frame)

## Pistas Importantes

1. Los valores graduales comprimen mejor que los erráticos
2. Timestamp casi nunca se comprime (siempre incrementa)
3. Posición (floats) es difícil de comprimir eficientemente
4. RPM y velocidad están altamente correlacionados
5. El primer frame DEBE ser completo para tener referencia

## Criterios de Evaluación

- ✅ Compresión delta implementada correctamente
- ✅ Descompresión recupera datos 100% idénticos
- ✅ Análisis de compresión preciso y detallado
- ✅ Generación de telemetría realista
- ✅ Análisis de rendimiento completo y útil
- ✅ Visualización en consola funcional
- ✅ Código eficiente y bien estructurado

Este ejercicio combina manejo de binarios, compresión de datos y análisis estadístico. ¡El desafío perfecto! 🏎️💨
