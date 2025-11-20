# Nivel 2 - Ejercicio Intermedio 2: Base de Datos de Campeones (Registros de Longitud Fija)

**Dificultad:** ⭐⭐ Intermedio  
**Tema:** League of Legends - Sistema de campeones

## Contexto

Estás desarrollando una herramienta para gestionar información sobre campeones de League of Legends. A diferencia del ejercicio anterior, este sistema usa **registros de longitud FIJA** para permitir acceso directo por índice.

## ¿Qué son los Registros de Longitud Fija?

En lugar de usar strings con longitud variable (writeUTF), todos los strings tendrán **siempre el mismo tamaño** rellenando con espacios o caracteres nulos.

**Ventajas:**
- Acceso directo: puedes calcular `posición = índice × tamaño_registro`
- No necesitas leer todos los registros anteriores
- Más eficiente para búsquedas

**Desventajas:**
- Desperdicio de espacio para textos cortos
- Tamaño máximo fijo para strings

## Especificaciones del Registro

Cada campeón tiene **EXACTAMENTE 50 bytes:**

| Campo | Tipo | Bytes | Descripción |
|-------|------|-------|-------------|
| ID | int | 4 | Identificador único |
| Nombre | char[20] | 40 | Nombre (20 caracteres × 2 bytes c/u) |
| Rol | char | 2 | 'T'=Top, 'J'=Jungle, 'M'=Mid, 'A'=ADC, 'S'=Support |
| Nivel | byte | 1 | Nivel de dificultad (1-3) |
| Ataque | byte | 1 | Poder de ataque (0-10) |
| Defensa | byte | 1 | Poder defensivo (0-10) |
| Magia | byte | 1 | Poder mágico (0-10) |

**Total por registro:** 50 bytes exactos

## Estructura del Archivo

```
[Header: 4 bytes = número de campeones]
[Campeón 0: 50 bytes]
[Campeón 1: 50 bytes]
[Campeón 2: 50 bytes]
...
[Campeón N-1: 50 bytes]
```

## Manejo de Strings con Padding

Para mantener longitud fija en strings:

### Escritura de Nombre (20 caracteres siempre)
```java
String nombre = "Ahri";  // 4 caracteres

// Opción A: Padding manual con espacios
String nombrePadded = String.format("%-20s", nombre);  // "Ahri                "

// Opción B: Truncar si es demasiado largo
if (nombre.length() > 20) {
    nombre = nombre.substring(0, 20);
}

// Escribir exactamente 20 caracteres
for (int i = 0; i < 20; i++) {
    if (i < nombre.length()) {
        dos.writeChar(nombre.charAt(i));
    } else {
        dos.writeChar(' ');  // Rellenar con espacios
    }
}
```

### Lectura de Nombre
```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 20; i++) {
    sb.append(dis.readChar());
}
String nombre = sb.toString().trim();  // Eliminar espacios al final
```

## Funcionalidades Requeridas

Implementa la clase `DatabaseCampeones` con:

### 1. `inicializarArchivo(String archivo)`
```java
public static void inicializarArchivo(String archivo)
```
- Crea un archivo nuevo
- Escribe el header con contador = 0
- Prepara el archivo para recibir campeones

### 2. `agregarCampeon(...)`
```java
public static void agregarCampeon(String archivo, int id, String nombre, 
        char rol, byte nivel, byte ataque, byte defensa, byte magia)
```
- Lee el contador actual del header
- Añade el campeón al final
- Actualiza el contador en el header
- El nombre debe ser truncado/rellenado a 20 caracteres

### 3. `leerCampeon(String archivo, int indice)`
```java
public static void leerCampeon(String archivo, int indice)
```
- Lee el campeón en la posición `indice` (0-based)
- Calcula: posición = 4 + (indice × 50)
- Usa `FileInputStream.skip()` o lee y descarta bytes
- Muestra toda la información del campeón

### 4. `listarTodos(String archivo)`
```java
public static void listarTodos(String archivo)
```
- Lee el contador del header
- Lista todos los campeones usando un bucle for (no while infinito)
- Muestra estadísticas: promedio de ataque, defensa, magia

### 5. `buscarPorRol(String archivo, char rol)`
```java
public static void buscarPorRol(String archivo, char rol)
```
- Filtra campeones por rol ('T', 'J', 'M', 'A', 'S')
- Muestra todos los que coincidan

### 6. `campeonMasFuerte(String archivo)`
```java
public static void campeonMasFuerte(String archivo)
```
- Calcula "poder total" = ataque + defensa + magia
- Encuentra el campeón con mayor poder total
- En caso de empate, muestra el primero encontrado

## Datos de Prueba

| ID | Nombre | Rol | Nivel | Ataque | Defensa | Magia |
|----|--------|-----|-------|--------|---------|-------|
| 1 | Ahri | M | 2 | 3 | 4 | 8 |
| 2 | Garen | T | 1 | 7 | 8 | 1 |
| 3 | Lee Sin | J | 3 | 8 | 5 | 3 |
| 4 | Lux | M | 2 | 2 | 4 | 9 |
| 5 | Jinx | A | 2 | 9 | 2 | 4 |
| 6 | Thresh | S | 3 | 3 | 6 | 6 |
| 7 | Yasuo | M | 3 | 8 | 4 | 4 |
| 8 | Vayne | A | 3 | 10 | 2 | 1 |
| 9 | Darius | T | 1 | 9 | 6 | 1 |
| 10 | Leona | S | 1 | 4 | 9 | 3 |

## Casos de Prueba Obligatorios

1. **Inicializar archivo** vacío
2. **Agregar los 10 campeones** de la lista
3. **Leer directamente el campeón en índice 0** (Ahri)
4. **Leer directamente el campeón en índice 4** (Jinx) - sin leer los anteriores
5. **Leer directamente el campeón en índice 9** (Leona)
6. **Listar todos** los campeones
7. **Buscar campeones Mid** (debe encontrar 3: Ahri, Lux, Yasuo)
8. **Encontrar el campeón más fuerte**
9. **Verificar el tamaño del archivo** = 4 + (10 × 50) = 504 bytes

## Cálculo de Posición

Para leer el campeón en índice N:
```
Posición_byte = 4 (header) + N × 50 (tamaño_registro)

Ejemplos:
- Índice 0: posición = 4 + 0×50 = 4
- Índice 4: posición = 4 + 4×50 = 204
- Índice 9: posición = 4 + 9×50 = 454
```

## Implementación de Skip

Para ir directamente a un registro:

```java
try (FileInputStream fis = new FileInputStream(archivo);
     DataInputStream dis = new DataInputStream(fis)) {
    
    // Saltar header
    dis.skipBytes(4);
    
    // Saltar N registros
    dis.skipBytes(indice * 50);
    
    // Ahora leer el registro deseado
    int id = dis.readInt();
    // ... resto del registro
}
```

## Salida Esperada (extracto)

```
=== INICIALIZAR ARCHIVO ===
✓ Archivo inicializado con contador = 0

=== AGREGAR CAMPEONES ===
✓ Campeón añadido: Ahri (ID: 1)
✓ Campeón añadido: Garen (ID: 2)
...
✓ Total: 10 campeones en la base de datos

=== ACCESO DIRECTO ===
Leyendo campeón en índice 0:
  ID: 1 | Nombre: Ahri | Rol: Mid | Nivel: 2
  Stats: ATK=3, DEF=4, MAG=8 | Poder Total: 15

Leyendo campeón en índice 4:
  ID: 5 | Nombre: Jinx | Rol: ADC | Nivel: 2
  Stats: ATK=9, DEF=2, MAG=4 | Poder Total: 15

=== LISTADO COMPLETO ===
Total de campeones: 10
1. Ahri          | Mid     | ATK:3  DEF:4  MAG:8  | Total:15
2. Garen         | Top     | ATK:7  DEF:8  MAG:1  | Total:16
...

Estadísticas generales:
- Promedio Ataque: 6.3
- Promedio Defensa: 5.0
- Promedio Magia: 4.0

=== BÚSQUEDA POR ROL ===
Campeones Mid (M):
- Ahri (Nivel 2) - Poder: 15
- Lux (Nivel 2) - Poder: 15
- Yasuo (Nivel 3) - Poder: 16
Total: 3 campeones

=== CAMPEÓN MÁS FUERTE ===
🏆 Lee Sin (ID: 3) - Poder Total: 16
```

## Estructura Hexadecimal del Archivo

```
Header (4 bytes):
00 00 00 0A          ← Contador: 10 campeones

Campeón 0 - Ahri (50 bytes):
00 00 00 01          ← ID: 1
00 41 00 68 00 72 00 69 00 20 00 20 ... (20 chars)  ← "Ahri" + padding
00 4D                ← Rol: 'M'
02                   ← Nivel: 2
03                   ← Ataque: 3
04                   ← Defensa: 4
08                   ← Magia: 8

Campeón 1 - Garen (50 bytes):
00 00 00 02          ← ID: 2
...
```

## Ventajas de Este Enfoque

1. **Acceso O(1):** Puedes ir directamente al registro N
2. **Modificación in-place:** Puedes actualizar un registro sin reescribir todo
3. **Tamaño predecible:** Siempre sabes el tamaño del archivo

## Pistas Importantes

1. **writeChar()** escribe 2 bytes (formato Unicode UTF-16)
2. Para rellenar strings: `String.format("%-20s", nombre)`
3. Para leer nombres: acumula 20 chars y haz `.trim()`
4. El header es CRÍTICO: siempre actualízalo cuando agregues campeones
5. skipBytes() es más eficiente que leer y descartar

## Retos Adicionales (Opcional)

1. **`modificarStats(int indice, byte atk, byte def, byte mag)`** - Actualiza solo las stats sin reescribir todo
2. **`intercambiar(int indice1, int indice2)`** - Intercambia dos campeones de posición
3. **`ordenarPorPoder()`** - Ordena campeones por poder total (in-place)
4. **Comparar rendimiento:** Mide el tiempo de acceso directo vs secuencial

## Criterios de Evaluación

- ✅ Registros de exactamente 50 bytes cada uno
- ✅ Acceso directo funciona correctamente (sin leer registros anteriores)
- ✅ Header actualizado correctamente
- ✅ Strings con padding correcto (20 caracteres)
- ✅ Cálculos de posición correctos

¡Este ejercicio te prepara para trabajar con bases de datos binarias reales! 🎯
