# 📝 EJERCICIO AVANZADO 2: Extractor de Recursos (.PAK Archive)

## 🎯 Objetivo

Crear un programa avanzado que **extraiga recursos individuales de archivos .PAK empaquetados**, similar a cómo funcionan los archivos de assets de videojuegos AAA.

Este ejercicio te enseñará a:
- Parsear formatos binarios complejos con múltiples secciones
- Trabajar con tablas de índices y offsets
- Extraer archivos embebidos de un contenedor
- Implementar sistemas de caché y lookup eficientes
- Manejar archivos comprimidos dentro de archivos
- Validar integridad con hashes
- Reconstruir jerarquías de directorios

---

## 📋 Descripción del Ejercicio

Los juegos modernos empaquetan miles de recursos (texturas, modelos, sonidos, scripts) en archivos .PAK para:
- **Reducir cantidad de archivos** (miles → uno o pocos)
- **Mejorar velocidad de carga** (menos operaciones de disco)
- **Proteger assets** contra extracción casual
- **Facilitar distribución** (un solo archivo vs carpetas complejas)

Tu programa debe implementar un **extractor profesional** que:
1. Lea el header y tabla de índices del .PAK
2. Permita listar todos los recursos contenidos
3. Extraiga recursos específicos o todos
4. Reconstruya la estructura de directorios
5. Valide integridad con checksums
6. Soporte compresión (DEFLATE/GZIP)

---

## 🎮 Contexto: Asset Pipeline de Videojuegos

**Formato .PAK real usado en:**
- Unreal Engine (Epic Games)
- Source Engine (Valve)
- CryEngine (Crytek)
- Engines custom de AAA studios

**Características típicas:**
- Archivos de 1-10 GB
- Miles de recursos internos
- Estructura jerárquica (directorios virtuales)
- Compresión por recurso
- Metadatos (tamaños, tipos, timestamps)

---

## 📥 Formato del Archivo .PAK

### **Estructura General:**

```
[HEADER - 64 bytes]
Magic: "GPAK" (4 bytes) - Game PAK
Version: uint32
Creation timestamp: uint64
Total entries: uint32
Index table offset: uint64
Index table size: uint32
Flags: uint32
  bit 0: Compression enabled
  bit 1: Encryption enabled
  bit 2: Directory structure
  bit 3-31: Reserved
Reserved: (32 bytes)

[RESOURCE DATA SECTION]
Recursos concatenados uno tras otro:

  [RESOURCE 1]
  Resource header (32 bytes):
    Size uncompressed: uint32
    Size compressed: uint32
    Compression type: uint8 (0=none, 1=DEFLATE, 2=GZIP)
    Flags: uint8
    Checksum CRC32: uint32
    Reserved: (18 bytes)
  
  Resource data: (size_compressed bytes)

  [RESOURCE 2]
  ...

  [RESOURCE N]
  ...

[INDEX TABLE]
Ubicada al final del archivo (offset indicado en header):

  Index entry count: uint32
  
  Para cada entrada:
    [ENTRY - 256 bytes]
    Filename: char[200] (null-terminated)
    Directory: char[100] (null-terminated, ej: "textures/characters/")
    Offset: uint64 (posición en el archivo)
    Size uncompressed: uint32
    Size compressed: uint32
    Timestamp: uint64
    File type: char[8] (ej: "PNG", "WAV", "LUA")
    Checksum: uint32
    Flags: uint32

[FOOTER - 32 bytes]
Magic end: "KEND" (4 bytes)
Index offset: uint64
Total entries: uint32
Checksum: uint64
```

---

## 📤 Funcionalidades del Extractor

### **1. Listar Contenido:**

```
java PakExtractor list archivo.pak

╔════════════════════════════════════════════════════════════════╗
║            EXTRACTOR DE RECURSOS .PAK - LIST MODE              ║
╚════════════════════════════════════════════════════════════════╝

Archivo: game_assets.pak
Versión: 1
Creado: 2025-10-15T10:30:00Z
Total recursos: 1,247

--- ESTRUCTURA DE DIRECTORIOS ---
textures/
  characters/
    hero_diffuse.png (2.5 MB, comprimido: 1.2 MB)
    hero_normal.png (2.5 MB, comprimido: 2.1 MB)
    villain_diffuse.png (2.3 MB, comprimido: 1.1 MB)
  environment/
    ground_01.png (4.0 MB, comprimido: 1.5 MB)
    sky_01.hdr (8.2 MB, comprimido: 6.1 MB)
sounds/
  music/
    menu_theme.ogg (5.1 MB, sin comprimir)
    battle_01.ogg (6.3 MB, sin comprimir)
  sfx/
    explosion.wav (512 KB, comprimido: 256 KB)
    jump.wav (128 KB, comprimido: 64 KB)
scripts/
  ai/
    enemy_patrol.lua (24 KB, sin comprimir)
    boss_behavior.lua (45 KB, sin comprimir)
  ...

--- ESTADÍSTICAS ---
Total archivos: 1,247
Tamaño sin comprimir: 2.4 GB
Tamaño comprimido: 1.1 GB
Ratio de compresión: 2.18:1
Archivos comprimidos: 892 (71.5%)
Archivos sin comprimir: 355 (28.5%)

--- TIPOS DE ARCHIVO ---
PNG: 523 archivos (1.2 GB)
OGG: 234 archivos (758 MB)
WAV: 156 archivos (234 MB)
LUA: 89 archivos (12 MB)
JSON: 67 archivos (8 MB)
...
```

### **2. Extraer Archivo Específico:**

```
java PakExtractor extract archivo.pak "textures/characters/hero_diffuse.png"

╔════════════════════════════════════════════════════════════════╗
║           EXTRACTOR DE RECURSOS .PAK - EXTRACT MODE            ║
╚════════════════════════════════════════════════════════════════╝

Archivo PAK: game_assets.pak
Recurso: textures/characters/hero_diffuse.png

--- INFORMACIÓN DEL RECURSO ---
Directorio: textures/characters/
Nombre: hero_diffuse.png
Tipo: PNG
Tamaño original: 2,621,440 bytes (2.50 MB)
Tamaño comprimido: 1,258,291 bytes (1.20 MB)
Compresión: DEFLATE
Ratio: 2.08:1
Offset en PAK: 0x00AB4520
Timestamp: 2025-10-10T15:20:00Z
Checksum: 0xA1B2C3D4

--- EXTRAYENDO ---
Leyendo datos comprimidos... ✓
Descomprimiendo (DEFLATE)... ✓
Validando checksum... ✓
Creando estructura de directorios... ✓
Escribiendo archivo... ✓

Archivo extraído: ./extracted/textures/characters/hero_diffuse.png
Verificación: OK (checksum coincide)

Extracción completada en 0.23 segundos.
```

### **3. Extraer Todo:**

```
java PakExtractor extractall archivo.pak ./output/

╔════════════════════════════════════════════════════════════════╗
║          EXTRACTOR DE RECURSOS .PAK - EXTRACT ALL MODE         ║
╚════════════════════════════════════════════════════════════════╝

Archivo PAK: game_assets.pak
Destino: ./output/
Total recursos: 1,247

--- EXTRAYENDO ---
[  1/1247] textures/characters/hero_diffuse.png ... ✓
[  2/1247] textures/characters/hero_normal.png ... ✓
[  3/1247] textures/characters/villain_diffuse.png ... ✓
...
[Progress] ████████████████████████████ 45% (561/1247)
           Velocidad: 85 MB/s
           Tiempo restante: 12 segundos
...
[1247/1247] scripts/ui/credits.lua ... ✓

--- RESUMEN ---
✓ Archivos extraídos: 1,247
✓ Bytes escritos: 2,415,820,800 (2.25 GB)
✓ Directorios creados: 45
✓ Errores: 0
✓ Warnings: 2
  ⚠ Archivo "old/deprecated.png" tiene checksum no verificable (formato antiguo)
  ⚠ Archivo "temp/cache.dat" tamaño 0 bytes

Tiempo total: 28.5 segundos
Velocidad promedio: 82.3 MB/s

Extracción completada exitosamente.
Archivos disponibles en: ./output/
```

### **4. Buscar en el PAK:**

```
java PakExtractor search archivo.pak "*.lua"

Buscando archivos que coincidan con "*.lua" en game_assets.pak...

Resultados (89 archivos):
  scripts/ai/enemy_patrol.lua (24 KB)
  scripts/ai/boss_behavior.lua (45 KB)
  scripts/ui/menu.lua (12 KB)
  scripts/ui/hud.lua (18 KB)
  scripts/gameplay/inventory.lua (31 KB)
  ...

Total encontrados: 89 archivos (2.1 MB sin comprimir)
```

---

## 🔧 Especificaciones Técnicas

### **1. Parsear Header:**

```java
// Leer header (64 bytes)
byte[] headerBytes = new byte[64];
fis.read(headerBytes);

// Validar magic number
String magic = new String(headerBytes, 0, 4, StandardCharsets.US_ASCII);
if (!"GPAK".equals(magic)) {
    throw new IOException("No es un archivo PAK válido");
}

// Leer campos con ByteBuffer
ByteBuffer bb = ByteBuffer.wrap(headerBytes);
bb.position(4); // Saltar magic
int version = bb.getInt();
long timestamp = bb.getLong();
int totalEntries = bb.getInt();
long indexOffset = bb.getLong();
int indexSize = bb.getInt();
int flags = bb.getInt();
```

### **2. Leer Tabla de Índices:**

```java
// Ir al offset de la tabla de índices
RandomAccessFile raf = new RandomAccessFile(archivoLanzamiento, "r");
raf.seek(indexOffset);

// Leer cantidad de entradas
int entryCount = raf.readInt();

// Leer cada entrada
List<PakEntry> entries = new ArrayList<>();
for (int i = 0; i < entryCount; i++) {
    byte[] entryBytes = new byte[256];
    raf.read(entryBytes);
    
    PakEntry entry = parsearEntry(entryBytes);
    entries.add(entry);
}
```

### **3. Extraer Recurso con Descompresión:**

```java
void extraerRecurso(PakEntry entry, File destino) throws IOException {
    // Leer datos comprimidos
    try (RandomAccessFile raf = new RandomAccessFile(archivoPak, "r")) {
        raf.seek(entry.offset);
        
        // Leer header del recurso
        int sizeUncompressed = raf.readInt();
        int sizeCompressed = raf.readInt();
        byte compressionType = raf.readByte();
        // ... resto del header
        
        // Leer datos
        byte[] datosComprimidos = new byte[sizeCompressed];
        raf.read(datosComprimidos);
        
        // Descomprimir si es necesario
        byte[] datosFinales;
        if (compressionType == 1) { // DEFLATE
            datosFinales = descomprimirDEFLATE(datosComprimidos);
        } else if (compressionType == 2) { // GZIP
            datosFinales = descomprimirGZIP(datosComprimidos);
        } else {
            datosFinales = datosComprimidos;
        }
        
        // Validar checksum
        CRC32 crc = new CRC32();
        crc.update(datosFinales);
        if ((int)crc.getValue() != entry.checksum) {
            System.err.println("⚠ WARNING: Checksum no coincide");
        }
        
        // Escribir archivo
        Files.write(destino.toPath(), datosFinales);
    }
}
```

### **4. Descompresión DEFLATE:**

```java
import java.util.zip.Inflater;

byte[] descomprimirDEFLATE(byte[] datosComprimidos) throws IOException {
    Inflater inflater = new Inflater();
    inflater.setInput(datosComprimidos);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buffer = new byte[8192];
    
    try {
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            baos.write(buffer, 0, count);
        }
    } catch (DataFormatException e) {
        throw new IOException("Error al descomprimir", e);
    } finally {
        inflater.end();
    }
    
    return baos.toByteArray();
}
```

### **5. Búsqueda con Wildcards:**

```java
boolean coincidePattern(String filename, String pattern) {
    // Convertir pattern con * y ? a regex
    String regex = pattern
        .replace(".", "\\.")
        .replace("*", ".*")
        .replace("?", ".");
    
    return filename.matches(regex);
}
```

---

## 🎓 Conceptos Avanzados

### **1. Índice Optimizado:**

Para búsquedas rápidas, construir mapa en memoria:

```java
class PakIndex {
    Map<String, PakEntry> byFullPath = new HashMap<>();
    Map<String, List<PakEntry>> byDirectory = new HashMap<>();
    Map<String, List<PakEntry>> byExtension = new HashMap<>();
    
    void indexar(List<PakEntry> entries) {
        for (PakEntry entry : entries) {
            String fullPath = entry.directory + entry.filename;
            byFullPath.put(fullPath, entry);
            
            byDirectory
                .computeIfAbsent(entry.directory, k -> new ArrayList<>())
                .add(entry);
            
            String ext = obtenerExtension(entry.filename);
            byExtension
                .computeIfAbsent(ext, k -> new ArrayList<>())
                .add(entry);
        }
    }
}
```

### **2. Extracción Lazy:**

No cargar toda la tabla de índices en memoria para PAKs gigantes:

```java
// En lugar de List<PakEntry>, usar iterator
class PakIterator implements Iterator<PakEntry> {
    RandomAccessFile raf;
    long currentOffset;
    int remaining;
    
    public PakEntry next() {
        // Leer entry desde disco on-demand
    }
}
```

### **3. Caché de Recursos:**

Para herramientas que extraen múltiples veces:

```java
class PakCache {
    Map<String, byte[]> cache = new LRUCache<>(50); // Max 50 MB
    
    byte[] obtenerRecurso(String path) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }
        
        byte[] data = extraerDesdePak(path);
        cache.put(path, data);
        return data;
    }
}
```

---

## 🧪 Casos de Prueba

### **Caso 1: PAK Pequeño (< 10 archivos)**
- Verificar parsing correcto
- Extraer todos sin problemas

### **Caso 2: PAK Grande (> 1000 archivos)**
- Verificar performance de indexación
- Búsquedas deben ser < 100ms

### **Caso 3: Recursos Comprimidos**
- Algunos con DEFLATE, otros sin comprimir
- Verificar descompresión correcta

### **Caso 4: Estructura de Directorios Profunda**
- `assets/textures/characters/player/skins/legendary/texture_01.png`
- Reconstruir jerarquía correctamente

### **Caso 5: Checksums Incorrectos**
- Modificar bytes en un recurso
- Detectar corrupción y reportar

### **Caso 6: PAK Corrupto**
- Header inválido
- Tabla de índices incompleta
- Manejar errores gracefully

---

## 🚀 Desafíos Adicionales (Opcional)

### **1. Visor Integrado:**
GUI simple que muestre:
- Árbol de directorios
- Vista previa de imágenes
- Reproductor de audio
- Visor de texto

### **2. Empaquetador (Crear PAK):**
Implementa el proceso inverso:
- Toma una carpeta
- Comprime recursos
- Genera archivo .PAK

### **3. Modificador:**
- Reemplazar recursos dentro del PAK
- Añadir nuevos recursos
- Eliminar recursos existentes
- Actualizar tabla de índices

### **4. Diff de PAKs:**
Compara dos PAKs y muestra:
- Archivos añadidos
- Archivos eliminados
- Archivos modificados

### **5. Cifrado:**
Soporta recursos cifrados:
- AES-256 para datos sensibles
- Clave embebida o solicitada

### **6. Multi-threading:**
Extrae múltiples recursos en paralelo:
- Thread pool para I/O
- Hasta 4-8 threads concurrentes

### **7. Modo streaming:**
Para herramientas que cargan recursos on-demand:
```java
InputStream getResourceStream(String path) {
    // Devuelve stream que lee directamente del PAK
    // Sin extraer el archivo completo
}
```

---

## 📚 Consideraciones de Diseño

### **Formato Extensible:**
- Versión en header para forward compatibility
- Flags para características opcionales
- Espacios reservados para futuras extensiones

### **Performance:**
- Tabla de índices al final (permite append)
- Índice en memoria para búsquedas O(1)
- Extracción con buffer grande (64-256 KB)

### **Integridad:**
- CRC32 por recurso (rápido, suficientemente bueno)
- SHA-256 opcional para alta seguridad
- Validación del header completo

---

## ✅ Criterios de Éxito

Tu programa debe:
- ✅ Parsear correctamente archivos .PAK con miles de recursos
- ✅ Listar todos los recursos con su información completa
- ✅ Extraer recursos individuales preservando estructura de directorios
- ✅ Soportar descompresión DEFLATE/GZIP
- ✅ Validar integridad con checksums
- ✅ Búsqueda eficiente (< 100ms para 10k recursos)
- ✅ Extraer todo el PAK en tiempo razonable (> 50 MB/s)
- ✅ Manejar PAKs de múltiples GB sin problemas de memoria
- ✅ Reportar errores de forma útil

---

## 🔗 Recursos

- Formatos PAK reales: Unreal Engine PAK, Quake PAK
- Java NIO para performance: MappedByteBuffer
- Compresión: java.util.zip package
- `HERRAMIENTA_HEX_DUMP.md`: Analizar PAKs

---

**Tiempo estimado:** 4-6 horas

**Dificultad:** ⭐⭐⭐⭐⭐ Avanzada

**¡Desempaqueta esos assets!** 🎮📦🔓
