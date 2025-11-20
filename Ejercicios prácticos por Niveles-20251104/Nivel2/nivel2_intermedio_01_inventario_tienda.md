# Nivel 2 - Ejercicio Intermedio 1: Sistema de Inventario de Tienda

**Dificultad:** ⭐⭐ Intermedio  
**Tema:** Gestión de tienda de videojuegos

## Contexto

Eres el desarrollador de un sistema de inventario para una tienda de videojuegos retro. Necesitas gestionar el catálogo de juegos disponibles con información detallada.

## Especificaciones

### Estructura de cada Juego

Cada juego en el inventario tiene los siguientes datos:
- **ID** (int): Identificador único del juego
- **Título** (String): Nombre del juego
- **Plataforma** (String): Consola o sistema (ej: "NES", "SNES", "PS1")
- **Año** (short): Año de lanzamiento (1970-2024)
- **Precio** (float): Precio en euros
- **Stock** (int): Unidades disponibles
- **Usado** (boolean): true si es de segunda mano, false si es nuevo

### Funcionalidades Requeridas

Implementa la clase `InventarioTienda` con los siguientes métodos:

#### 1. `agregarJuego(...)`
```java
public static void agregarJuego(String archivo, int id, String titulo, 
        String plataforma, short año, float precio, int stock, boolean usado)
```
- Añade un nuevo juego al final del archivo
- Usa modo append para no perder los juegos anteriores
- Muestra confirmación tras agregar

#### 2. `listarInventario(String archivo)`
```java
public static void listarInventario(String archivo)
```
- Muestra TODOS los juegos del inventario
- Formato sugerido:
  ```
  ID: 1001 | The Legend of Zelda | NES | 1986 | 89.99€ | Stock: 3 | USADO
  ID: 1002 | Super Mario Bros.   | NES | 1985 | 120.00€ | Stock: 5 | NUEVO
  ```
- Usa EOFException para detectar el final

#### 3. `buscarPorId(String archivo, int id)`
```java
public static void buscarPorId(String archivo, int id)
```
- Busca un juego específico por su ID
- Si lo encuentra, muestra toda su información
- Si no existe, muestra mensaje apropiado

#### 4. `calcularValorTotal(String archivo)`
```java
public static double calcularValorTotal(String archivo)
```
- Calcula el valor total del inventario
- Fórmula: suma de (precio × stock) para cada juego
- Devuelve el total y lo muestra formateado

#### 5. `juegosPlataforma(String archivo, String plataforma)`
```java
public static void juegosPlataforma(String archivo, String plataforma)
```
- Lista todos los juegos de una plataforma específica
- Ejemplo: mostrar solo juegos de "SNES"
- Muestra cuántos juegos encontró

## Datos de Prueba

Usa estos juegos para tus pruebas:

| ID | Título | Plataforma | Año | Precio | Stock | Usado |
|----|--------|------------|-----|--------|-------|-------|
| 1001 | The Legend of Zelda | NES | 1986 | 89.99 | 3 | true |
| 1002 | Super Mario Bros. | NES | 1985 | 120.00 | 5 | false |
| 1003 | Chrono Trigger | SNES | 1995 | 150.00 | 2 | true |
| 1004 | Final Fantasy VII | PS1 | 1997 | 75.50 | 8 | false |
| 1005 | Super Metroid | SNES | 1994 | 180.00 | 1 | true |
| 1006 | Castlevania SOTN | PS1 | 1997 | 95.00 | 4 | false |

## Casos de Prueba Obligatorios

Tu programa debe ejecutar y validar:

1. **Agregar todos los juegos** del listado
2. **Listar el inventario completo**
3. **Buscar el juego con ID 1003** (debe encontrarlo)
4. **Buscar el juego con ID 9999** (no debe existir)
5. **Calcular el valor total** del inventario
6. **Listar solo juegos de SNES** (debe mostrar 2)
7. **Listar solo juegos de NES** (debe mostrar 2)

## Consideraciones Técnicas

### Tipos de Datos
- Usa `short` para el año (rango -32,768 a 32,767)
- Usa `float` para precio (4 bytes, suficiente para precios)
- Usa `boolean` para indicar si es usado (1 byte)

### Cálculo de Tamaño por Registro
Cada juego ocupa:
- ID: 4 bytes (int)
- Título: 2 + n bytes (String UTF)
- Plataforma: 2 + m bytes (String UTF)
- Año: 2 bytes (short)
- Precio: 4 bytes (float)
- Stock: 4 bytes (int)
- Usado: 1 byte (boolean)

**Total:** 19 + len(titulo) + len(plataforma) bytes por juego

### Formato de Lectura
El orden DEBE ser exactamente:
```java
int id = dis.readInt();
String titulo = dis.readUTF();
String plataforma = dis.readUTF();
short año = dis.readShort();
float precio = dis.readFloat();
int stock = dis.readInt();
boolean usado = dis.readBoolean();
```

## Salida Esperada (extracto)

```
=== AGREGAR JUEGOS AL INVENTARIO ===
✓ Juego añadido: The Legend of Zelda
✓ Juego añadido: Super Mario Bros.
...

=== INVENTARIO COMPLETO ===
ID: 1001 | The Legend of Zelda | NES  | 1986 | 89.99€  | Stock: 3 | USADO
ID: 1002 | Super Mario Bros.   | NES  | 1985 | 120.00€ | Stock: 5 | NUEVO
...
Total de juegos: 6

=== BUSCAR JUEGO POR ID ===
Buscando juego con ID: 1003
✓ Encontrado: Chrono Trigger (SNES, 1995) - 150.00€ x 2 unidades [USADO]

Buscando juego con ID: 9999
✗ No se encontró ningún juego con ID: 9999

=== VALOR TOTAL DEL INVENTARIO ===
Valor total: 2,194.98€

=== JUEGOS POR PLATAFORMA ===
Plataforma: SNES
- Chrono Trigger (1995) - 150.00€ x 2 = 300.00€
- Super Metroid (1994) - 180.00€ x 1 = 180.00€
Total: 2 juegos de SNES
```

## Retos Adicionales (Opcional)

Si terminas antes de tiempo, implementa:

1. **`juegoMasCaro()`** - Encuentra y muestra el juego más caro
2. **`stockBajo(int minimo)`** - Lista juegos con stock menor al mínimo
3. **`actualizarPrecio(int id, float nuevoPrecio)`** - Actualiza el precio de un juego
   - ⚠️ Esto es DIFÍCIL con DataStreams (requiere reescribir todo el archivo)
4. **`estadisticasPorAño()`** - Muestra cuántos juegos hay por década

## Pistas

1. Para agregar en modo append: `new FileOutputStream(archivo, true)`
2. Para buscar por ID, lee secuencialmente hasta encontrar o llegar al final
3. Para calcular valor total, acumula `precio * stock` en cada iteración
4. Para filtrar por plataforma, usa `equalsIgnoreCase()` para comparar strings
5. Recuerda que `short` se lee con `readShort()` y `float` con `readFloat()`

## Criterios de Evaluación

- ✅ **Funcionalidad:** Todos los métodos funcionan correctamente
- ✅ **Manejo de errores:** IOException y EOFException manejados apropiadamente
- ✅ **Lectura correcta:** Tipos de datos leídos en el orden correcto
- ✅ **Formato de salida:** Información clara y bien formateada
- ✅ **Código limpio:** Nombres descriptivos, código organizado

## Archivos a Entregar

- `InventarioTienda.java` - Clase principal con todos los métodos
- `inventario.dat` - Archivo binario generado (opcional)

¡Buena suerte! 🎮
