# 📝 EJERCICIO INTERMEDIO 2: Unificador de Archivos (File Merger)

## 🎯 Objetivo

Crear un programa que **reconstruya un archivo original a partir de múltiples chunks** creados por el ejercicio anterior, leyendo el archivo de metadata para validar la integridad.

Este ejercicio te enseñará a:
- Leer y parsear archivos de configuración/metadata
- Combinar múltiples archivos en uno solo
- Validar headers y checksums
- Verificar integridad de datos
- Manejar errores y archivos corruptos

---

## 📋 Descripción del Ejercicio

Tu programa debe:
1. **Leer el archivo .split.info** para obtener información de reconstrucción
2. **Validar que todos los chunks existan** y sean del tamaño esperado
3. **Leer cada chunk** validando su header
4. **Verificar checksums** para detectar corrupción
5. **Reconstruir el archivo original** combinando todos los chunks en orden
6. **Validar el archivo final** comparando tamaño y checksum

---

## 🎮 Contexto: Sistema de Instalación de Juegos

**Casos de uso reales:**
- Instalador de juegos que descarga assets en partes
- Sistema de actualizaciones que aplica parches divididos
- Recuperación de archivos distribuidos en múltiples medios (DVDs, USBs)
- Validación de integridad antes de usar archivos críticos

---

## 📥 Entrada

El programa debe recibir:

1. **Archivo de metadata:** Path al archivo .split.info
   - Contiene toda la información necesaria para reconstruir

2. **Directorio de salida (opcional):** Donde guardar el archivo reconstruido
   - Por defecto: mismo directorio que los chunks

3. **Modo de validación (opcional):**
   - `strict` - Falla si cualquier checksum no coincide
   - `permissive` - Continúa con warnings si hay errores menores

---

## 📤 Salida Esperada

### **Ejemplo: Reconstruir archivo de texturas**

```
╔════════════════════════════════════════════════════════════════╗
║             UNIFICADOR DE ARCHIVOS - FILE MERGER               ║
╚════════════════════════════════════════════════════════════════╝

Leyendo archivo de metadata: texturas_hd.split.info

--- INFORMACIÓN DE DIVISIÓN ---
Archivo original: texturas_hd.pak
Tamaño original: 15,728,640 bytes (15.00 MB)
Total de chunks: 3
Tamaño por chunk: 5,242,880 bytes (5.00 MB)
Fecha de división: 2025-11-01T16:30:00Z

--- VALIDANDO CHUNKS ---
[✓] Chunk 1/3: texturas_hd.part001 (5.00 MB) - OK
[✓] Chunk 2/3: texturas_hd.part002 (5.00 MB) - OK
[✓] Chunk 3/3: texturas_hd.part003 (5.00 MB) - OK

Todos los chunks encontrados y verificados.

--- RECONSTRUYENDO ARCHIVO ---
[Chunk 1/3] Leyendo header... ✓
            Validando datos... ✓
            Checksum: A1B2C3D4 ✓
            Escribiendo... ████████████████ 100% (5.00 MB)

[Chunk 2/3] Leyendo header... ✓
            Validando datos... ✓
            Checksum: E5F6G7H8 ✓
            Escribiendo... ████████████████ 100% (5.00 MB)

[Chunk 3/3] Leyendo header... ✓
            Validando datos... ✓
            Checksum: I9J0K1L2 ✓
            Escribiendo... ████████████████ 100% (5.00 MB)

--- VERIFICACIÓN FINAL ---
✓ Archivo reconstruido: texturas_hd.pak
✓ Tamaño esperado: 15,728,640 bytes
✓ Tamaño real: 15,728,640 bytes
✓ Integridad: 100%

--- RESUMEN ---
✓ Reconstrucción completada exitosamente
✓ Chunks procesados: 3/3
✓ Bytes escritos: 15,728,640
✓ Errores: 0
✓ Warnings: 0

El archivo texturas_hd.pak ha sido reconstruido correctamente.
Puedes eliminar los chunks si ya no los necesitas.
```

### **Ejemplo con errores:**

```
--- VALIDANDO CHUNKS ---
[✓] Chunk 1/3: texturas_hd.part001 (5.00 MB) - OK
[✗] Chunk 2/3: texturas_hd.part002 (4.98 MB) - TAMAÑO INCORRECTO
    Esperado: 5,242,880 bytes
    Real: 5,221,000 bytes
    Diferencia: -21,880 bytes
[✓] Chunk 3/3: texturas_hd.part003 (5.00 MB) - OK

--- ERRORES ENCONTRADOS ---
✗ Error crítico en chunk 2: Tamaño incorrecto
  Este chunk puede estar corrupto o incompleto.

¿Deseas continuar de todos modos? (s/n): n

Reconstrucción cancelada.
El archivo no se ha creado debido a errores de validación.

Sugerencias:
- Vuelve a descargar o copiar el chunk 2
- Verifica que no se interrumpió la descarga
- Si el problema persiste, vuelve a generar los chunks desde el archivo original
```

---

## 🔧 Especificaciones Técnicas

### **Parsear archivo .split.info:**

```java
// Leer línea por línea
BufferedReader reader = new BufferedReader(new FileReader(archivoInfo));
String linea;

while ((linea = reader.readLine()) != null) {
    if (linea.startsWith("original_filename=")) {
        nombreOriginal = linea.substring("original_filename=".length());
    }
    else if (linea.startsWith("original_size=")) {
        tamañoOriginal = Long.parseLong(linea.substring("original_size=".length()));
    }
    // ... más campos
}
```

### **Leer y validar header de chunk:**

```java
// Leer header (16 bytes)
byte[] header = new byte[16];
fis.read(header);

// Validar magic number
if (header[0] != 'S' || header[1] != 'P' || 
    header[2] != 'L' || header[3] != 'T') {
    throw new IOException("Header inválido en chunk");
}

// Leer número de chunk (bytes 5-8)
int numeroChunk = ByteBuffer.wrap(header, 5, 4).getInt();
```

### **Verificar checksum:**

```java
import java.util.zip.CRC32;

// Leer datos del chunk
byte[] datos = new byte[tamañoChunk];
fis.read(datos);

// Calcular checksum
CRC32 crc = new CRC32();
crc.update(datos);
long checksumCalculado = crc.getValue();

// Leer checksum del footer (4 bytes)
byte[] footer = new byte[4];
fis.read(footer);
long checksumEsperado = ByteBuffer.wrap(footer).getInt() & 0xFFFFFFFFL;

// Comparar
if (checksumCalculado != checksumEsperado) {
    System.err.println("⚠ WARNING: Checksum no coincide");
    System.err.printf("  Esperado: %08X%n", checksumEsperado);
    System.err.printf("  Calculado: %08X%n", checksumCalculado);
}
```

---

## 💡 Algoritmo Sugerido

```
1. Leer y parsear archivo .split.info
2. Extraer información clave:
   - Nombre archivo original
   - Tamaño total esperado
   - Total de chunks
   - Nombres de chunks
   - Checksums esperados
3. Validar existencia de todos los chunks
4. Validar tamaño de cada chunk
5. Crear archivo de salida
6. Para cada chunk en orden:
   a. Abrir chunk
   b. Leer y validar header
   c. Leer datos (saltando header y footer)
   d. Calcular checksum de datos
   e. Comparar con checksum esperado
   f. Escribir datos al archivo de salida
   g. Cerrar chunk
   h. Actualizar progreso
7. Cerrar archivo de salida
8. Validar tamaño final
9. Mostrar resumen
10. (Opcional) Preguntar si eliminar chunks
```

---

## 🎓 Conceptos Clave

### **1. Parsear archivo de configuración:**
```java
Properties props = new Properties();
try (FileInputStream fis = new FileInputStream("archivo.split.info")) {
    props.load(fis);
}

String nombreOriginal = props.getProperty("original_filename");
long tamaño = Long.parseLong(props.getProperty("original_size"));
```

### **2. ByteBuffer para leer int de 4 bytes:**
```java
import java.nio.ByteBuffer;

byte[] bytes = new byte[4];
fis.read(bytes);
int valor = ByteBuffer.wrap(bytes).getInt();
```

### **3. Verificar existencia de archivos:**
```java
File chunk = new File(nombreChunk);
if (!chunk.exists()) {
    System.err.println("Error: Chunk no encontrado: " + nombreChunk);
    return false;
}

if (chunk.length() != tamañoEsperado) {
    System.err.println("Warning: Tamaño incorrecto en " + nombreChunk);
}
```

### **4. Saltar bytes (skip header):**
```java
// Saltar los primeros 16 bytes (header)
fis.skip(16);

// Leer datos
byte[] datos = new byte[tamañoDatos];
fis.read(datos);

// Saltar los últimos 4 bytes (footer) no es necesario si no lees más
```

### **5. Combinar archivos eficientemente:**
```java
// NO hacer:
List<byte[]> todosLosDatos = new ArrayList<>();
for (chunk : chunks) {
    todosLosDatos.add(leerChunkCompleto(chunk)); // ❌ Mucha memoria
}

// SÍ hacer:
for (chunk : chunks) {
    try (FileInputStream fisChunk = new FileInputStream(chunk)) {
        byte[] buffer = new byte[8192];
        int leidos;
        while ((leidos = fisChunk.read(buffer)) != -1) {
            fosDestino.write(buffer, 0, leidos);
        }
    }
}
```

---

## 🧪 Casos de Prueba

### **Caso 1: Reconstrucción exitosa**
- Todos los chunks presentes y correctos
- Checksums válidos
- Resultado: archivo idéntico al original

### **Caso 2: Chunk faltante**
- Chunk 2 de 5 no existe
- Resultado: Error, no puede reconstruir

### **Caso 3: Chunk corrupto (checksum incorrecto)**
- Chunk 3 tiene datos modificados
- Checksum no coincide
- Resultado: Warning o error según modo

### **Caso 4: Chunks en desorden**
- Los archivos están con nombres incorrectos o desordenados
- Resultado: Debe detectar y reordenar correctamente usando el header

### **Caso 5: Archivo .split.info corrupto**
- Metadata inválida o incompleta
- Resultado: Error claro indicando qué falta

---

## 🔍 Validaciones Importantes

### **Pre-validación (antes de empezar):**
1. ✅ Archivo .split.info existe y es legible
2. ✅ Puede parsear todas las líneas necesarias
3. ✅ Todos los chunks existen
4. ✅ Tamaños de chunks coinciden con metadata
5. ✅ Hay espacio suficiente en disco para el archivo final

### **Durante reconstrucción:**
1. ✅ Header de cada chunk es válido (magic number correcto)
2. ✅ Número de chunk coincide con el esperado
3. ✅ Checksum de datos coincide
4. ⚠️ Si hay discrepancias, registrar warning

### **Post-validación:**
1. ✅ Tamaño final coincide con el esperado
2. ✅ (Opcional) Calcular hash SHA-256 del archivo completo
3. ✅ Archivo se puede abrir/leer correctamente

---

## 🚀 Desafíos Adicionales (Opcional)

### **1. Modo interactivo de reparación:**
```
Chunk 2 corrupto. Opciones:
1. Saltar y continuar (archivo final estará incompleto)
2. Rellenar con ceros
3. Cancelar reconstrucción
4. Intentar recuperar datos parciales
```

### **2. Reconstrucción parcial:**
- Permitir reconstruir aunque falten algunos chunks
- Marcar regiones corruptas en el archivo final

### **3. Verificación criptográfica:**
- Calcular SHA-256 del archivo completo
- Comparar con hash almacenado en metadata

### **4. Interfaz de progreso mejorada:**
```
Reconstruyendo texturas_hd.pak
████████████████████░░░░░░░░ 65% (3/5 chunks)
Chunk actual: texturas_hd.part004
Velocidad: 85 MB/s
Tiempo restante: 12 segundos
```

### **5. Modo batch:**
- Procesar múltiples archivos .split.info en un directorio
- Reconstruir todos automáticamente

### **6. Logging detallado:**
- Crear archivo .log con todos los detalles de la reconstrucción
- Útil para debugging

---

## 📚 Manejo de Errores

### **Errores recuperables (warnings):**
- Checksum no coincide pero modo permissive
- Chunk ligeramente diferente en tamaño (< 1%)
- Timestamps diferentes a los esperados

### **Errores críticos (abortar):**
- Chunk faltante
- Header inválido (no es archivo chunk válido)
- Espacio insuficiente en disco
- Permisos insuficientes para escribir

### **Mensajes de error útiles:**
```
✗ Error: No se puede reconstruir el archivo

Problema: El chunk 3 de 5 está faltante
Archivo: texturas_hd.part003
Ubicación esperada: ./assets_split/texturas_hd.part003

Posibles causas:
- El archivo no se descargó completamente
- El archivo fue movido o eliminado
- Error en el nombre del archivo

Soluciones:
1. Verifica que todos los chunks estén en el directorio correcto
2. Vuelve a descargar/copiar el chunk faltante
3. Vuelve a generar todos los chunks desde el archivo original

¿Necesitas más información? (s/n):
```

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✅ Parsear correctamente el archivo .split.info
- ✅ Validar la existencia de todos los chunks antes de empezar
- ✅ Leer y validar headers de cada chunk
- ✅ Verificar checksums y reportar discrepancias
- ✅ Reconstruir el archivo combinando chunks en orden correcto
- ✅ Validar que el tamaño final es el esperado
- ✅ Manejar errores de forma clara y útil
- ✅ Mostrar progreso durante la reconstrucción
- ✅ El archivo reconstruido debe ser idéntico al original (validar con diff o checksum)

---

## 🔗 Archivos Relacionados

- **Ejercicio Intermedio 1:** Divisor de Archivos (genera los chunks que este ejercicio lee)
- Usa `HERRAMIENTA_HEX_DUMP.md` para inspeccionar chunks corruptos
- Consulta `NIVEL1_TEORIA.md` para I/O con buffers

---

## 🧩 Validación Final

Después de completar ambos ejercicios (Split y Merge), verifica:

```bash
# 1. Dividir archivo original
java FileSplitter archivo_grande.dat 10MB

# 2. Eliminar el original (para probar reconstrucción)
rm archivo_grande.dat

# 3. Reconstruir desde chunks
java FileMerger archivo_grande.split.info

# 4. Comparar original restaurado
diff archivo_grande.dat archivo_grande_backup.dat
# Deben ser idénticos!

# 5. También puedes comparar con checksums
md5sum archivo_grande.dat
md5sum archivo_grande_backup.dat
```

---

**Tiempo estimado:** 90-120 minutos

**Dificultad:** ⭐⭐⭐ Intermedia

**¡Reconstruye esos assets!** 🎮🔧
