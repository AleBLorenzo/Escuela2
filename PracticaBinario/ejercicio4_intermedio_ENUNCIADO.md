# 📝 EJERCICIO INTERMEDIO 1: Divisor de Archivos (File Splitter)

## 🎯 Objetivo

Crear un programa que **divida un archivo grande en múltiples archivos más pequeños** (chunks), útil para transferir archivos grandes o distribuir assets de videojuegos.

Este ejercicio te enseñará a:
- Dividir archivos en partes de tamaño específico
- Crear múltiples archivos de salida
- Calcular offsets y tamaños
- Generar nombres de archivo secuenciales
- Crear archivos de metadata

---

## 📋 Descripción del Ejercicio

Imagina que desarrollas un juego grande y necesitas distribuir texturas, música y videos. Algunos archivos son muy grandes para subirlos a ciertos servidores o para descargar de una sola vez.

Tu programa debe:
1. **Dividir un archivo grande** en múltiples chunks de tamaño específico
2. **Numerar los chunks** secuencialmente (parte001, parte002, etc.)
3. **Crear un archivo de metadata** (.split.info) con información de reconstrucción
4. **Añadir headers a cada chunk** para validación

---

## 🎮 Contexto: Sistema de Distribución de Assets

**Casos de uso reales:**
- Dividir un archivo de 500 MB en chunks de 50 MB para subirlo a servicios con límite
- Distribuir texturas de un juego en paquetes descargables
- Crear sistema de actualización por partes (descargar solo lo necesario)
- Streaming de assets durante la carga del juego

---

## 📥 Entrada

El programa debe solicitar o recibir:

1. **Archivo de entrada:** Path al archivo grande a dividir
2. **Tamaño del chunk:** Tamaño máximo de cada parte en bytes, KB o MB
   - Ejemplos: `1048576` (1 MB), `10MB`, `512KB`
3. **Directorio de salida:** Donde guardar los chunks (opcional, por defecto: `.`)
4. **Nombre base:** Prefijo para los archivos (opcional, por defecto: nombre del archivo original)

---

## 📤 Salida Esperada

### **Ejemplo: Dividir archivo de texturas de 15 MB**

```
╔════════════════════════════════════════════════════════════════╗
║              DIVISOR DE ARCHIVOS - FILE SPLITTER               ║
╚════════════════════════════════════════════════════════════════╝

Archivo a dividir: texturas_hd.pak
Tamaño del archivo: 15,728,640 bytes (15.00 MB)
Tamaño del chunk: 5,242,880 bytes (5.00 MB)
Directorio de salida: ./assets_split/
Nombre base: texturas_hd

--- CALCULANDO DIVISIÓN ---
Se crearán 3 chunks:
  Chunk 1: 5.00 MB
  Chunk 2: 5.00 MB
  Chunk 3: 5.00 MB (último chunk)

--- DIVIDIENDO ARCHIVO ---
[Chunk 1/3] texturas_hd.part001 ... ████████████████ 100% (5.00 MB)
[Chunk 2/3] texturas_hd.part002 ... ████████████████ 100% (5.00 MB)
[Chunk 3/3] texturas_hd.part003 ... ████████████████ 100% (5.00 MB)

--- CREANDO ARCHIVO DE METADATA ---
✓ Archivo de metadata creado: texturas_hd.split.info

--- RESUMEN ---
✓ División completada exitosamente
✓ Archivo original: texturas_hd.pak (15.00 MB)
✓ Chunks creados: 3
✓ Total escrito: 15,728,640 bytes
✓ Directorio: ./assets_split/

Archivos generados:
  ./assets_split/texturas_hd.part001 (5.00 MB)
  ./assets_split/texturas_hd.part002 (5.00 MB)
  ./assets_split/texturas_hd.part003 (5.00 MB)
  ./assets_split/texturas_hd.split.info (metadata)

Para reconstruir el archivo:
  java FileMerger ./assets_split/texturas_hd.split.info
```

---

## 🔧 Especificaciones Técnicas

### **Estructura de cada chunk:**

Cada archivo .partXXX debe tener:

1. **Header (16 bytes):**
   - Bytes 0-3: Magic number "SPLT" (0x53 0x50 0x4C 0x54)
   - Byte 4: Versión (0x01)
   - Bytes 5-8: Número de chunk (int, 1-based)
   - Bytes 9-12: Total de chunks (int)
   - Bytes 13-15: Reservados (0x00)

2. **Datos del chunk:**
   - N bytes del archivo original

3. **Footer (4 bytes):**
   - Checksum CRC32 o suma simple de los datos

### **Archivo de metadata (.split.info):**

Archivo de texto con formato:
```
[SPLIT_INFO]
original_filename=texturas_hd.pak
original_size=15728640
chunk_size=5242880
total_chunks=3
created_date=2025-11-01T16:30:00Z
checksum_type=CRC32
chunks_directory=./assets_split/

[CHUNK_001]
filename=texturas_hd.part001
size=5242880
offset=0
checksum=A1B2C3D4

[CHUNK_002]
filename=texturas_hd.part002
size=5242880
offset=5242880
checksum=E5F6G7H8

[CHUNK_003]
filename=texturas_hd.part003
size=5242880
offset=10485760
checksum=I9J0K1L2
```

---

## 💡 Algoritmo Sugerido

```
1. Validar archivo de entrada (existe, no está vacío)
2. Parsear tamaño de chunk (convertir KB/MB a bytes)
3. Calcular cantidad de chunks necesarios:
   totalChunks = ceil(tamañoArchivo / tamañoChunk)
4. Crear directorio de salida si no existe
5. Abrir archivo de entrada
6. Para cada chunk:
   a. Crear archivo .partXXX
   b. Escribir header
   c. Leer hasta tamañoChunk bytes del archivo original
   d. Escribir datos al chunk
   e. Calcular checksum
   f. Escribir footer con checksum
   g. Cerrar chunk
   h. Actualizar progreso
7. Crear archivo .split.info con metadata
8. Mostrar resumen
```

---

## 🎓 Conceptos Clave

### **1. Cálculo de chunks:**
```java
long tamañoArchivo = archivo.length();
int tamañoChunk = 5 * 1024 * 1024; // 5 MB
int totalChunks = (int) Math.ceil((double) tamañoArchivo / tamañoChunk);
```

### **2. Nombres de archivo con padding:**
```java
// texturas_hd.part001, texturas_hd.part002, etc.
String nombreChunk = String.format("%s.part%03d", nombreBase, numeroChunk);
```

### **3. Crear directorios:**
```java
File directorio = new File(directorioSalida);
if (!directorio.exists()) {
    directorio.mkdirs();
}
```

### **4. Leer cantidad específica de bytes:**
```java
// Leer máximo tamañoChunk bytes
byte[] buffer = new byte[Math.min(BUFFER_SIZE, bytesRestantes)];
int bytesLeidos = fis.read(buffer);
```

### **5. Checksum simple (CRC32):**
```java
import java.util.zip.CRC32;

CRC32 crc = new CRC32();
crc.update(buffer, 0, bytesLeidos);
long checksum = crc.getValue();
```

### **6. Parsear tamaños (10MB, 512KB, etc.):**
```java
public static long parsearTamaño(String tamaño) {
    tamaño = tamaño.trim().toUpperCase();
    
    if (tamaño.endsWith("KB")) {
        return Long.parseLong(tamaño.replace("KB", "")) * 1024;
    } else if (tamaño.endsWith("MB")) {
        return Long.parseLong(tamaño.replace("MB", "")) * 1024 * 1024;
    } else if (tamaño.endsWith("GB")) {
        return Long.parseLong(tamaño.replace("GB", "")) * 1024 * 1024 * 1024;
    } else {
        return Long.parseLong(tamaño); // Bytes directamente
    }
}
```

---

## 🧪 Casos de Prueba

### **Caso 1: Archivo pequeño (1 chunk)**
- Archivo: 2 MB
- Chunk size: 5 MB
- Resultado: 1 solo chunk con todo el contenido

### **Caso 2: División exacta**
- Archivo: 10 MB
- Chunk size: 5 MB
- Resultado: 2 chunks exactos de 5 MB cada uno

### **Caso 3: División con resto**
- Archivo: 12 MB
- Chunk size: 5 MB
- Resultado: 3 chunks (5MB, 5MB, 2MB)

### **Caso 4: Chunks muy pequeños**
- Archivo: 1 MB
- Chunk size: 100 KB
- Resultado: 10 chunks de 100 KB + 1 chunk de 24 KB

### **Caso 5: Archivo muy grande**
- Archivo: 500 MB
- Chunk size: 10 MB
- Resultado: 50 chunks
- Verificar: performance, uso de memoria

---

## 🔍 Validaciones Importantes

1. **Archivo de entrada:**
   - ✅ Existe
   - ✅ No está vacío
   - ✅ Es legible
   - ✅ No es un directorio

2. **Tamaño de chunk:**
   - ✅ Mayor que 0
   - ✅ No mayor que el archivo completo (warning, pero permitir)
   - ✅ Recomendado: entre 1 MB y 100 MB

3. **Directorio de salida:**
   - ✅ Si existe, está vacío o preguntar si sobrescribir
   - ✅ Tiene permisos de escritura

4. **Espacio en disco:**
   - ⚠️ Verificar que hay espacio suficiente (archivo original × 1.1 para headers)

---

## 🚀 Desafíos Adicionales (Opcional)

### **1. Compresión por chunk:**
- Comprimir cada chunk con GZIP antes de escribirlo
- Actualizar metadata con tamaño comprimido

### **2. Cifrado:**
- Cifrar cada chunk con clave diferente
- Guardar info de cifrado en metadata

### **3. Verificación de integridad:**
- Calcular hash SHA-256 del archivo original
- Incluir en metadata para verificación futura

### **4. Modo streaming:**
- Dividir sin cargar todo en memoria
- Soportar archivos de varios GB

### **5. Interfaz de progreso mejorada:**
- Mostrar velocidad (MB/s)
- Tiempo estimado restante
- Barra de progreso por chunk y global

### **6. Recuperación de errores:**
- Si falla en medio, poder resumir desde el último chunk completo
- Crear archivo .resume con estado

---

## 📚 Consideraciones de Diseño

### **Tamaño de buffer recomendado:**
- **8-32 KB** para chunks pequeños
- **64-128 KB** para chunks medianos
- **256 KB - 1 MB** para chunks grandes

### **Gestión de memoria:**
```java
// NO hacer esto con archivos grandes:
byte[] todoElArchivo = new byte[(int) archivo.length()]; // ❌

// SÍ hacer esto:
byte[] buffer = new byte[8192];
while (bytesRestantes > 0) {
    int aLeer = Math.min(buffer.length, bytesRestantes);
    int leidos = fis.read(buffer, 0, aLeer);
    // Procesar...
}
```

### **Nombres de archivo:**
- Usar padding con ceros: `part001`, `part002`, etc.
- Facilita ordenamiento alfabético
- Permite hasta 999 chunks con 3 dígitos

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✅ Dividir correctamente archivos de cualquier tamaño
- ✅ Crear headers válidos en cada chunk
- ✅ Generar archivo de metadata completo y parseable
- ✅ Calcular checksums correctamente
- ✅ Mostrar progreso durante la división
- ✅ Validar todas las entradas
- ✅ Manejar archivos grandes sin problemas de memoria
- ✅ Crear directorio de salida si no existe
- ✅ Los chunks generados deben poder unirse de nuevo (ejercicio siguiente)

---

## 🔗 Archivos Relacionados

- **Ejercicio Intermedio 2:** Unificador de Archivos (leerá estos chunks)
- Usa `HERRAMIENTA_HEX_DUMP.md` para verificar headers de chunks
- Consulta `NIVEL1_TEORIA.md` para I/O con buffers

---

**Tiempo estimado:** 90-120 minutos

**Dificultad:** ⭐⭐⭐ Intermedia

**¡Divide y vencerás!** 🎮📦
