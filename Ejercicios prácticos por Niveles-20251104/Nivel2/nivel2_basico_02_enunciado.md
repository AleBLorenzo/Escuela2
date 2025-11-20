# Nivel 2 - Ejercicio Básico 2: Estadísticas de Jugador

**Dificultad:** ⭐ Básico  
**Tema:** RPG - Sistema de estadísticas de personaje  
**Tiempo estimado:** 45 minutos

## Contexto

Estás creando un juego de rol (RPG) y necesitas guardar las estadísticas de los personajes. Cada jugador tiene múltiples atributos de diferentes tipos que deben almacenarse en formato binario.

## Objetivo

Crear una clase `EstadisticasJugador` que permita:
1. Guardar todos los datos de un jugador en un archivo binario
2. Leer y mostrar todos los datos del jugador

## Especificaciones Técnicas

### Datos del Jugador

Cada jugador tiene los siguientes atributos:

| Campo | Tipo | Descripción |
|-------|------|-------------|
| ID | int | Identificador único (4 bytes) |
| Nombre | String | Nombre del personaje (UTF-8 variable) |
| Nivel | int | Nivel del personaje (4 bytes) |
| Vida | int | Puntos de vida (4 bytes) |
| Maná | int | Puntos de maná (4 bytes) |
| Experiencia | long | Puntos de experiencia (8 bytes) |

### Método 1: guardarJugador

```java
public static void guardarJugador(String archivo, int id, String nombre, 
        int nivel, int vida, int mana, long experiencia) throws IOException
```

**Funcionalidad:**
- Crea/sobrescribe el archivo especificado
- Escribe todos los campos **en este orden exacto:**
  1. ID (int)
  2. Nombre (String con writeUTF)
  3. Nivel (int)
  4. Vida (int)
  5. Maná (int)
  6. Experiencia (long)
- Muestra mensaje: "✓ Datos del jugador guardados correctamente"

**IMPORTANTE:** 
- Usa `writeUTF()` para el nombre (incluye longitud automáticamente)
- Usa `writeLong()` para la experiencia (8 bytes, no 4)

### Método 2: mostrarJugador

```java
public static void mostrarJugador(String archivo) throws IOException
```

**Funcionalidad:**
- Lee el archivo en el **mismo orden** que se escribió
- Muestra los datos en un formato claro y legible
- Sugerencia de formato:
  ```
  ╔════════════════════════════════════════╗
  ║        DATOS DEL PERSONAJE             ║
  ╠════════════════════════════════════════╣
  ║ ID:           1001                     ║
  ║ Nombre:       Aragorn                  ║
  ║ Nivel:        45                       ║
  ║ Vida:         850                      ║
  ║ Maná:         320                      ║
  ║ Experiencia:  125000                   ║
  ╚════════════════════════════════════════╝
  ```

## Ejemplo de Uso

```java
// Guardar un jugador
guardarJugador("player.dat", 1001, "Aragorn", 45, 850, 320, 125000L);

// Mostrar los datos
mostrarJugador("player.dat");
```

**Salida esperada:**
```
✓ Datos del jugador guardados correctamente
╔════════════════════════════════════════╗
║        DATOS DEL PERSONAJE             ║
╠════════════════════════════════════════╣
║ ID:           1001                     ║
║ Nombre:       Aragorn                  ║
║ Nivel:        45                       ║
║ Vida:         850                      ║
║ Maná:         320                      ║
║ Experiencia:  125000                   ║
╚════════════════════════════════════════╝
```

## Casos de Prueba Obligatorios

Tu programa debe probar con los siguientes personajes:

1. **Guerrero Novato:**
   - ID: 1001, Nombre: "Conan", Nivel: 5
   - Vida: 150, Maná: 50, Experiencia: 1250

2. **Mago Experimentado:**
   - ID: 2034, Nombre: "Gandalf", Nivel: 45
   - Vida: 450, Maná: 800, Experiencia: 125000

3. **Paladín Legendario:**
   - ID: 5678, Nombre: "Arthas", Nivel: 99
   - Vida: 2500, Maná: 1500, Experiencia: 9999999

4. **Nombre Muy Corto:**
   - ID: 100, Nombre: "Lu", Nivel: 1
   - Vida: 100, Maná: 100, Experiencia: 0

5. **Nombre con Caracteres Especiales:**
   - ID: 777, Nombre: "Señor Oscuro Ñoño", Nivel: 66
   - Vida: 1800, Maná: 900, Experiencia: 666666

## Cálculo del Tamaño del Archivo

El archivo tendrá un tamaño **variable** dependiendo del nombre:

```
Tamaño total = 26 + longitud_del_nombre_en_bytes

Desglose:
- ID: 4 bytes
- Nombre: 2 (longitud) + n (caracteres) bytes
- Nivel: 4 bytes
- Vida: 4 bytes
- Maná: 4 bytes
- Experiencia: 8 bytes
```

**Ejemplo para "Gandalf" (7 caracteres ASCII):**
- Total: 26 + 7 = 33 bytes

**Ejemplo para "Señor Oscuro Ñoño" (18 caracteres, algunos multi-byte):**
- Los caracteres Ñ, ñ ocupan 2 bytes cada uno en UTF-8
- Total: 26 + 21 = 47 bytes aproximadamente

## Pistas

1. **Orden crítico:** Debes leer en el MISMO orden que escribiste:
   ```java
   int id = dis.readInt();
   String nombre = dis.readUTF();  // No readInt()!
   int nivel = dis.readInt();
   // ... etc
   ```

2. **writeUTF() incluye longitud:** No necesitas escribir la longitud del string manualmente

3. **long vs int:**
   ```java
   dos.writeLong(experiencia);  // 8 bytes
   // NO: dos.writeInt(experiencia) // Esto truncaría el valor
   ```

4. **Formato con printf:**
   ```java
   System.out.printf("║ ID:           %-24d ║%n", id);
   ```

5. **Try-with-resources:**
   ```java
   try (DataOutputStream dos = new DataOutputStream(
           new FileOutputStream(archivo))) {
       dos.writeInt(id);
       dos.writeUTF(nombre);
       // ...
   } // Se cierra automáticamente
   ```

## Método Auxiliar Opcional

Puedes crear este método para analizar el archivo:

```java
public static void analizarArchivo(String archivo) throws IOException {
    File f = new File(archivo);
    System.out.println("\n--- Análisis del Archivo ---");
    System.out.println("Tamaño total: " + f.length() + " bytes");
    System.out.println("Desglose esperado:");
    System.out.println("  - ID: 4 bytes");
    System.out.println("  - Nombre: 2 + n bytes (UTF-8)");
    System.out.println("  - Nivel: 4 bytes");
    System.out.println("  - Vida: 4 bytes");
    System.out.println("  - Maná: 4 bytes");
    System.out.println("  - Experiencia: 8 bytes");
    System.out.println("  - Total: 26 + longitud(nombre) bytes");
}
```

## Preguntas para Reflexionar

1. ¿Por qué el archivo de "Lu" es más pequeño que el de "Gandalf"?
2. ¿Qué sucede si intentas leer el nivel antes del nombre?
3. ¿Por qué algunos caracteres como 'Ñ' ocupan más de 1 byte?
4. ¿Podrías guardar múltiples jugadores en el mismo archivo? ¿Cómo?

## Errores Comunes a Evitar

❌ **Leer en orden incorrecto:**
```java
String nombre = dis.readUTF();
int id = dis.readInt();  // ¡ERROR! ID va primero
```

❌ **Usar writeInt para experiencia:**
```java
dos.writeInt((int)experiencia);  // ¡ERROR! Pierdes datos
```

❌ **Olvidar el orden:**
```java
// Escritura
dos.writeInt(id);
dos.writeUTF(nombre);

// Lectura
int id = dis.readInt();
int nivel = dis.readInt();  // ¡ERROR! Esperabas String
```

## Criterios de Evaluación

- ✅ **Tipos de datos correctos:** int, String, long usados apropiadamente
- ✅ **Orden consistente:** Lectura en el mismo orden que escritura
- ✅ **writeUTF/readUTF:** Usado correctamente para strings
- ✅ **writeLong/readLong:** Usado para experiencia
- ✅ **Manejo de excepciones:** IOException manejada
- ✅ **Formato de salida:** Claro y profesional
- ✅ **Casos de prueba:** Al menos 3 personajes diferentes probados

## Extensiones Opcionales

Si terminas antes, intenta:

1. **Método de comparación:** Compara dos jugadores y dice cuál es más poderoso
2. **Validación:** Verifica que los valores sean válidos (vida > 0, nivel >= 1, etc.)
3. **Actualización:** Crea un método que solo actualice la vida y el maná (más difícil)
4. **Lista de jugadores:** Guarda múltiples jugadores en el mismo archivo

## Entregables

- Archivo `EstadisticasJugador.java` con:
  - Método `guardarJugador()` implementado
  - Método `mostrarJugador()` implementado
  - Método `main()` con todos los casos de prueba
  - (Opcional) Método `analizarArchivo()`

¡Que la fuerza te acompañe! ⚔️
