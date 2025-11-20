# Nivel 2 - Ejercicio Avanzado 1: Conversor de Formatos de Guardado

**Dificultad:** ⭐⭐⭐ Avanzado  
**Tema:** Minecraft - Conversor de formatos de guardado de mundos

## Contexto

Minecraft ha tenido varios formatos de guardado a lo largo de su historia. Imagina que necesitas crear un conversor entre dos formatos diferentes:
- **Formato Clásico (v1):** Registros de longitud variable con strings UTF
- **Formato Compacto (v2):** Registros de longitud fija con IDs numéricos

Tu tarea es crear un sistema que pueda:
1. Leer archivos en ambos formatos
2. Convertir entre formatos
3. Validar la integridad de los datos
4. Generar reportes de conversión

## Especificaciones de los Formatos

### Formato Clásico (v1) - Longitud Variable

**Header (8 bytes):**
- Magic number: `0x4D435346` (4 bytes) - "MCSF" en ASCII
- Version: 1 (int, 4 bytes)

**Cada bloque:**
- X (int, 4 bytes): Coordenada X
- Y (short, 2 bytes): Coordenada Y (altura)
- Z (int, 4 bytes): Coordenada Z
- Tipo (String, 2+n bytes): Nombre del bloque ("stone", "dirt", "diamond_ore", etc.)
- Metadata (byte, 1 byte): Datos adicionales (orientación, variante, etc.)
- Timestamp (long, 8 bytes): Cuando fue colocado

**Tamaño variable:** 19 + len(tipo) bytes por bloque

### Formato Compacto (v2) - Longitud Fija

**Header (12 bytes):**
- Magic number: `0x4D435346` (4 bytes) - "MCSF" en ASCII
- Version: 2 (int, 4 bytes)
- Num bloques (int, 4 bytes)

**Cada bloque (20 bytes exactos):**
- X (int, 4 bytes): Coordenada X
- Y (short, 2 bytes): Coordenada Y
- Z (int, 4 bytes): Coordenada Z
- TipoID (short, 2 bytes): ID numérico del bloque
- Metadata (byte, 1 byte)
- Timestamp (long, 8 bytes)

**Tamaño fijo:** 20 bytes por bloque

### Tabla de Conversión de IDs

```
ID   | Tipo Nombre      | Descripción
-----|------------------|------------------
0    | air              | Aire (vacío)
1    | stone            | Piedra
2    | dirt             | Tierra
3    | grass            | Césped
4    | cobblestone      | Piedra labrada
5    | wood             | Madera
6    | sand             | Arena
7    | gravel           | Grava
8    | gold_ore         | Mena de oro
9    | iron_ore         | Mena de hierro
10   | coal_ore         | Mena de carbón
11   | diamond_ore      | Mena de diamante
12   | redstone_ore     | Mena de redstone
13   | water            | Agua
14   | lava             | Lava
15   | glass            | Vidrio
16   | wool             | Lana
17   | tnt              | TNT
18   | obsidian         | Obsidiana
19   | bedrock          | Lecho de roca
```

## Funcionalidades Requeridas

### 1. Gestión de Diccionario

#### `inicializarDiccionario()`
```java
private static Map<String, Short> nombreAId = new HashMap<>();
private static Map<Short, String> idANombre = new HashMap<>();

public static void inicializarDiccionario()
```
- Carga todos los pares nombre↔ID de la tabla
- Mantiene dos mapas para conversión bidireccional

#### `obtenerID(String nombre)`
```java
public static short obtenerID(String nombre)
```
- Convierte nombre de bloque a ID
- Devuelve -1 si el bloque no existe

#### `obtenerNombre(short id)`
```java
public static String obtenerNombre(short id)
```
- Convierte ID a nombre de bloque
- Devuelve "unknown" si el ID no existe

### 2. Lectura de Archivos

#### `leerFormatoV1(String archivo)`
```java
public static List<Bloque> leerFormatoV1(String archivo)
```
- Lee un archivo en formato v1 (longitud variable)
- Valida el magic number y versión
- Devuelve lista de objetos Bloque
- Lanza excepción si el formato es incorrecto

#### `leerFormatoV2(String archivo)`
```java
public static List<Bloque> leerFormatoV2(String archivo)
```
- Lee un archivo en formato v2 (longitud fija)
- Valida el magic number y versión
- Lee el contador de bloques del header
- Devuelve lista de objetos Bloque

### 3. Escritura de Archivos

#### `guardarFormatoV1(String archivo, List<Bloque> bloques)`
```java
public static void guardarFormatoV1(String archivo, List<Bloque> bloques)
```
- Escribe header con magic number y versión 1
- Escribe cada bloque con strings UTF
- Registros de longitud variable

#### `guardarFormatoV2(String archivo, List<Bloque> bloques)`
```java
public static void guardarFormatoV2(String archivo, List<Bloque> bloques)
```
- Escribe header con magic number, versión 2, y contador
- Escribe cada bloque con IDs numéricos
- Registros de longitud fija (20 bytes)

### 4. Conversión Entre Formatos

#### `convertirV1aV2(String archivoOrigen, String archivoDestino)`
```java
public static void convertirV1aV2(String archivoOrigen, String archivoDestino)
```
- Lee archivo v1
- Convierte nombres a IDs usando el diccionario
- Guarda en formato v2
- Genera reporte de conversión

#### `convertirV2aV1(String archivoOrigen, String archivoDestino)`
```java
public static void convertirV2aV1(String archivoOrigen, String archivoDestino)
```
- Lee archivo v2
- Convierte IDs a nombres usando el diccionario
- Guarda en formato v1
- Genera reporte de conversión

### 5. Validación y Análisis

#### `validarFormato(String archivo)`
```java
public static String validarFormato(String archivo)
```
- Lee los primeros 8 bytes
- Verifica magic number (0x4D435346)
- Identifica versión (1 o 2)
- Devuelve "v1", "v2", o "inválido"

#### `compararArchivos(String archivo1, String archivo2)`
```java
public static void compararArchivos(String archivo1, String archivo2)
```
- Carga ambos archivos
- Compara si tienen los mismos bloques (ignorando el formato)
- Muestra diferencias si las hay

#### `generarReporteConversion(String archivoOriginal, String archivoConvertido)`
```java
public static void generarReporteConversion(String archivoOriginal, 
                                            String archivoConvertido)
```
Muestra:
- Tamaño original vs convertido
- Reducción/aumento de espacio en %
- Número de bloques
- Bloques por tipo
- Tiempo de conversión

### 6. Optimizaciones

#### `comprimirV2(String archivo)`
```java
public static void comprimirV2(String archivo)
```
- Lee archivo v2
- Elimina bloques duplicados (misma posición)
- Ordena por coordenadas para mejor compresión
- Reescribe el archivo optimizado

#### `detectarBloquesDuplicados(List<Bloque> bloques)`
```java
public static Map<String, Integer> detectarBloquesDuplicados(List<Bloque> bloques)
```
- Analiza la lista de bloques
- Encuentra bloques en la misma posición
- Devuelve mapa con coordenada → cantidad de duplicados

## Clase Auxiliar: Bloque

```java
class Bloque {
    private int x;
    private short y;
    private int z;
    private String tipo;      // Para v1
    private short tipoId;     // Para v2
    private byte metadata;
    private long timestamp;
    
    // Constructor, getters, setters
    
    public String getPosicion() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        // Compara posición y tipo (ignorando timestamp)
    }
}
```

## Datos de Prueba

Crea un archivo v1 con al menos 50 bloques que incluyan:
- 10 bloques de "stone" en diferentes posiciones
- 5 bloques de "diamond_ore"
- 15 bloques de "dirt"
- 20 bloques variados de otros tipos

Ejemplo de bloques:
```
(0, 64, 0) - grass - metadata: 0
(1, 64, 0) - dirt - metadata: 0
(2, 64, 0) - stone - metadata: 0
(0, 65, 0) - diamond_ore - metadata: 0
...
```

## Casos de Prueba Obligatorios

Tu programa debe:

1. **Crear un archivo v1** con 50+ bloques
2. **Validar que es formato v1** correctamente
3. **Convertir v1 → v2** exitosamente
4. **Validar que el resultado es v2**
5. **Comparar contenido** de ambos archivos (debe ser idéntico)
6. **Generar reporte de conversión** con estadísticas
7. **Convertir v2 → v1** (round-trip)
8. **Verificar que el round-trip** recupera los datos originales
9. **Mostrar reducción de tamaño** v1 vs v2
10. **Detectar y eliminar duplicados** si los hay

## Consideraciones Técnicas

### 1. Validación de Magic Number

```java
int magicNumber = dis.readInt();
if (magicNumber != 0x4D435346) {
    throw new IOException("Archivo no válido: magic number incorrecto");
}
```

### 2. Cálculo de Reducción de Espacio

Para "diamond_ore" (12 caracteres):
- V1: 19 + 12 = 31 bytes
- V2: 20 bytes
- Reducción: (31-20)/31 × 100 = 35.5%

Para "tnt" (3 caracteres):
- V1: 19 + 3 = 22 bytes
- V2: 20 bytes
- Reducción: (22-20)/22 × 100 = 9.1%

### 3. Orden de Bytes (Endianness)

Java usa **big-endian** por defecto:
```
Magic number 0x4D435346:
Byte 0: 0x4D ('M')
Byte 1: 0x43 ('C')
Byte 2: 0x53 ('S')
Byte 3: 0x46 ('F')
```

### 4. Detección de Formato

```java
public static String detectarFormato(String archivo) throws IOException {
    try (DataInputStream dis = new DataInputStream(
            new FileInputStream(archivo))) {
        
        int magic = dis.readInt();
        if (magic != 0x4D435346) {
            return "invalido";
        }
        
        int version = dis.readInt();
        return version == 1 ? "v1" : version == 2 ? "v2" : "desconocido";
    }
}
```

## Salida Esperada

```
=== SISTEMA DE CONVERSIÓN DE FORMATOS MINECRAFT ===

[1] Creando archivo de prueba en formato v1...
✓ 50 bloques generados en 'mundo_v1.dat'
  - stone: 10 bloques
  - diamond_ore: 5 bloques
  - dirt: 15 bloques
  - otros: 20 bloques
  Tamaño: 1,245 bytes

[2] Validando formato...
✓ Formato detectado: v1
  Magic number: OK (0x4D435346)
  Version: 1

[3] Convirtiendo v1 → v2...
✓ Conversión completada: 'mundo_v2.dat'

=== REPORTE DE CONVERSIÓN ===
Formato origen: v1 (longitud variable)
Formato destino: v2 (longitud fija)
Bloques procesados: 50
Bloques convertidos: 50
Errores: 0

Tamaño:
- Original (v1): 1,245 bytes
- Convertido (v2): 1,012 bytes (12 header + 50×20)
- Reducción: 18.71%

Bloques más comunes:
1. dirt (15) - Ahorro: 195 bytes
2. stone (10) - Ahorro: 110 bytes
3. diamond_ore (5) - Ahorro: 55 bytes

Tiempo de conversión: 12ms

[4] Verificando integridad...
✓ Ambos archivos contienen los mismos 50 bloques
✓ Todas las coordenadas coinciden
✓ Todos los tipos coinciden

[5] Probando conversión inversa v2 → v1...
✓ Round-trip completado: 'mundo_v1_recuperado.dat'
✓ Datos originales recuperados correctamente

[6] Comparando archivos...
✓ mundo_v1.dat == mundo_v1_recuperado.dat
  (Los datos son idénticos)

[7] Analizando duplicados...
✓ No se encontraron bloques duplicados en las mismas coordenadas

=== RESUMEN FINAL ===
✓ Conversión bidireccional exitosa
✓ Integridad de datos verificada
✓ Formato v2 es 18.71% más eficiente para este mundo
```

## Retos Adicionales (Opcional)

1. **Compresión adicional:** Implementa RLE (Run-Length Encoding) para bloques consecutivos del mismo tipo
2. **Formato v3:** Diseña un formato propio más eficiente con chunks
3. **Verificación CRC:** Añade checksum para detectar corrupción
4. **Índice espacial:** Crea un índice para búsquedas rápidas por coordenadas
5. **Multi-threading:** Convierte múltiples archivos en paralelo
6. **GUI de conversión:** Crea interfaz gráfica con Swing

## Pistas Importantes

1. El magic number te permite identificar el formato sin ambigüedad
2. En v2, el contador en el header te evita usar EOFException
3. Valida SIEMPRE los IDs al convertir (puede haber IDs desconocidos)
4. Para round-trip, timestamp debe preservarse exactamente
5. HashMap es O(1) para conversiones ID↔nombre

## Análisis de Eficiencia

**V1 (variable):**
- Ventaja: No necesita diccionario, nombres legibles
- Desventaja: Más espacio, más lento (strings)

**V2 (fijo):**
- Ventaja: Más compacto, acceso directo por índice
- Desventaja: Necesita diccionario, menos legible

**Cuándo usar cada uno:**
- V1: Desarrollo, debugging, interoperabilidad
- V2: Producción, grandes mundos, rendimiento crítico

## Criterios de Evaluación

- ✅ Conversión bidireccional sin pérdida de datos
- ✅ Validación correcta de formatos
- ✅ Magic numbers y versiones manejados apropiadamente
- ✅ Reportes detallados y precisos
- ✅ Manejo robusto de errores (archivos corruptos, IDs inválidos)
- ✅ Código bien estructurado con separación de responsabilidades

Este ejercicio simula trabajo real con formatos de archivo propietarios. ¡Éxito! 🎮📦
