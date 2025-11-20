# Nivel 2 - Ejercicio Básico 3: Lista de Puntuaciones

**Dificultad:** ⭐ Básico  
**Tema:** Sistema de ranking multijugador  
**Tiempo estimado:** 60 minutos

## Contexto

Necesitas crear un sistema de ranking para un juego multijugador donde múltiples jugadores pueden registrar sus puntuaciones. A diferencia de los ejercicios anteriores donde solo guardabas UN registro, ahora debes manejar MÚLTIPLES registros en el mismo archivo.

## Objetivo

Crear una clase `ListaPuntuaciones` que permita:
1. Añadir nuevas puntuaciones al final del archivo (sin perder las anteriores)
2. Leer y mostrar TODAS las puntuaciones del archivo
3. Contar cuántas puntuaciones hay registradas

## Especificaciones Técnicas

### Datos de Puntuación

Cada entrada contiene:
- **Nombre** (String): Nombre del jugador
- **Puntuación** (int): Puntos obtenidos

### Método 1: agregarPuntuacion

```java
public static void agregarPuntuacion(String archivo, String nombre, int puntos) 
        throws IOException
```

**Funcionalidad:**
- **AÑADE** una nueva puntuación al final del archivo (modo append)
- NO sobrescribe las puntuaciones anteriores
- Escribe en orden: nombre (String) → puntos (int)
- Muestra mensaje: "✓ Puntuación añadida: [nombre] - [puntos]"

**CRÍTICO:** Debes usar **modo append** para no perder datos anteriores:
```java
new FileOutputStream(archivo, true)  // ← El 'true' es modo append
```

### Método 2: mostrarTodas

```java
public static void mostrarTodas(String archivo) throws IOException
```

**Funcionalidad:**
- Lee TODAS las puntuaciones del archivo
- Muestra cada una con formato: `"N. Jugador: [nombre] - Puntos: [puntos]"`
- Si el archivo está vacío, muestra: "(No hay puntuaciones registradas)"
- Usa **EOFException** para detectar el final del archivo

**Patrón de lectura requerido:**
```java
while (true) {
    try {
        String nombre = dis.readUTF();
        int puntos = dis.readInt();
        // Procesar y mostrar
    } catch (EOFException e) {
        break;  // Fin del archivo alcanzado
    }
}
```

### Método 3: contarPuntuaciones

```java
public static int contarPuntuaciones(String archivo) throws IOException
```

**Funcionalidad:**
- Cuenta cuántas puntuaciones hay en el archivo
- Lee todos los registros pero solo cuenta, no los muestra
- Devuelve el número total
- Devuelve 0 si el archivo no existe

## Ejemplo de Uso

```java
// Añadir varias puntuaciones
agregarPuntuacion("scores.dat", "Ana", 1500);
agregarPuntuacion("scores.dat", "Bob", 2300);
agregarPuntuacion("scores.dat", "Carlos", 1800);

// Mostrar todas
mostrarTodas("scores.dat");

// Contar
int total = contarPuntuaciones("scores.dat");
System.out.println("Total: " + total + " puntuaciones");
```

**Salida esperada:**
```
✓ Puntuación añadida: Ana - 1500
✓ Puntuación añadida: Bob - 2300
✓ Puntuación añadida: Carlos - 1800

=== RANKING DE PUNTUACIONES ===
1. Jugador: Ana             - Puntos: 1,500
2. Jugador: Bob             - Puntos: 2,300
3. Jugador: Carlos          - Puntos: 1,800
================================

Total: 3 puntuaciones
```

## Casos de Prueba Obligatorios

Tu programa debe:

1. **Probar con archivo vacío:**
   - Llamar a `mostrarTodas()` en archivo que no existe
   - Llamar a `contarPuntuaciones()` en archivo que no existe
   - Resultado esperado: mensaje apropiado, sin errores

2. **Añadir 5+ puntuaciones:**
   ```
   Ana - 1500
   Bob - 2300
   Carlos - 1800
   Diana - 3100
   Elena - 2700
   ```

3. **Verificar que NO se sobrescriben:**
   - Añadir 3 puntuaciones
   - Mostrar todas (deben aparecer las 3)
   - Añadir 2 más
   - Mostrar todas (deben aparecer las 5)

4. **Contar correctamente:**
   - Verificar que `contarPuntuaciones()` devuelve el número correcto

5. **Simular sesión nueva:**
   - Cerrar el programa
   - Volver a abrirlo
   - Añadir más puntuaciones
   - Verificar que las anteriores siguen ahí

## Estructura del Archivo

El archivo contendrá múltiples registros uno tras otro:

```
[Nombre1][Puntos1][Nombre2][Puntos2]...[NombreN][PuntosN]
```

**Ejemplo con 3 registros:**
```
"Ana" (UTF-8) → 1500 → "Bob" (UTF-8) → 2300 → "Carlos" (UTF-8) → 1800
```

**NO hay:**
- Número de registros al inicio
- Separadores especiales
- Marcas de fin de registro

**La única forma de saber cuándo termina:** EOFException

## Patrón EOFException Explicado

### ¿Qué es EOFException?

Es una excepción que se lanza cuando intentas leer más allá del final del archivo. **Esto es NORMAL y ESPERADO**, no es un error.

### Patrón Correcto:

```java
try (DataInputStream dis = new DataInputStream(
        new FileInputStream(archivo))) {
    
    int contador = 0;
    
    while (true) {  // Bucle "infinito"
        try {
            // Intentar leer siguiente registro
            String nombre = dis.readUTF();
            int puntos = dis.readInt();
            
            contador++;
            System.out.println(contador + ". " + nombre + " - " + puntos);
            
        } catch (EOFException e) {
            // Fin del archivo - ESTO ES ESPERADO
            break;  // Salir del while
        }
    }
    
} catch (FileNotFoundException e) {
    System.out.println("(Archivo no existe)");
}
```

### ¿Por qué este patrón?

- DataInputStream **no tiene** un método `hasNext()` confiable
- `available()` no funciona bien con DataInputStream
- EOFException es el mecanismo **oficial** para detectar el final

## Pistas Importantes

1. **Modo Append:**
   ```java
   // ❌ INCORRECTO (sobrescribe)
   new FileOutputStream(archivo)
   
   // ✅ CORRECTO (añade al final)
   new FileOutputStream(archivo, true)
   ```

2. **EOFException NO es un error:**
   ```java
   } catch (EOFException e) {
       // Esto es NORMAL, significa "fin del archivo"
       break;  // Simplemente sal del bucle
   }
   ```

3. **Orden de lectura:**
   ```java
   // Mismo orden que escritura
   String nombre = dis.readUTF();
   int puntos = dis.readInt();
   ```

4. **Manejo de archivo inexistente:**
   ```java
   try {
       // ... código de lectura ...
   } catch (FileNotFoundException e) {
       // Archivo no existe, retorna 0 o mensaje apropiado
   }
   ```

5. **Formato de números con comas:**
   ```java
   System.out.printf("Puntos: %,d%n", puntos);  // 1,500
   ```

## Método Auxiliar Opcional

Puedes crear este método para encontrar la mejor puntuación:

```java
public static void mostrarMejorPuntuacion(String archivo) throws IOException {
    try (DataInputStream dis = new DataInputStream(
            new FileInputStream(archivo))) {
        
        String mejorNombre = "";
        int mejorPuntos = -1;
        
        while (true) {
            try {
                String nombre = dis.readUTF();
                int puntos = dis.readInt();
                
                if (puntos > mejorPuntos) {
                    mejorNombre = nombre;
                    mejorPuntos = puntos;
                }
                
            } catch (EOFException e) {
                break;
            }
        }
        
        if (mejorPuntos >= 0) {
            System.out.println("🏆 Mejor: " + mejorNombre + 
                    " - " + mejorPuntos + " puntos");
        }
        
    } catch (FileNotFoundException e) {
        System.out.println("No hay puntuaciones");
    }
}
```

## Preguntas para Reflexionar

1. ¿Qué pasaría si olvidas el `true` en FileOutputStream?
2. ¿Por qué no podemos saber cuántos registros hay sin leer todo el archivo?
3. ¿Es EOFException realmente un error o parte del flujo normal?
4. ¿Cómo podrías modificar el sistema para saber el número de registros sin leer todos?

## Errores Comunes a Evitar

❌ **Olvidar el modo append:**
```java
// Esto SOBRESCRIBE el archivo cada vez
new FileOutputStream(archivo)
```

❌ **No capturar EOFException:**
```java
while (true) {
    String nombre = dis.readUTF();  // ¡Crash al final!
}
```

❌ **Capturar IOException en lugar de EOFException:**
```java
} catch (IOException e) {  // Demasiado genérico
    break;
}
// Mejor:
} catch (EOFException e) {  // Específico para fin de archivo
    break;
}
```

❌ **Leer en orden incorrecto:**
```java
int puntos = dis.readInt();      // ¡ERROR!
String nombre = dis.readUTF();   // Orden incorrecto
```

## Criterios de Evaluación

- ✅ **Modo append funciona:** Puntuaciones se añaden sin perder anteriores
- ✅ **EOFException manejada:** Detecta fin de archivo correctamente
- ✅ **Lectura completa:** `mostrarTodas()` lee todos los registros
- ✅ **Conteo correcto:** `contarPuntuaciones()` devuelve número exacto
- ✅ **Archivo inexistente:** Maneja gracefully cuando no existe
- ✅ **Formato de salida:** Claro y numerado

## Extensiones Opcionales

Si terminas antes, intenta:

1. **Mejor puntuación:** Implementa `mostrarMejorPuntuacion()`
2. **Buscar por nombre:** Crea método que busque un jugador específico
3. **Promedio:** Calcula la puntuación promedio de todos
4. **Top N:** Muestra solo las N mejores puntuaciones (requiere ordenar en memoria)
5. **Eliminar duplicados:** Detecta si un jugador ya tiene puntuación

## Entregables

- Archivo `ListaPuntuaciones.java` con:
  - Método `agregarPuntuacion()` con modo append
  - Método `mostrarTodas()` con patrón EOFException
  - Método `contarPuntuaciones()`
  - Método `main()` con casos de prueba completos
  - (Opcional) Métodos auxiliares adicionales

## Concepto Importante: Registros de Longitud Variable

Este ejercicio usa **registros de longitud variable** porque los nombres tienen tamaños diferentes:

- "Ana" (3 chars) → Registro más pequeño
- "Fernando" (8 chars) → Registro más grande

**Consecuencia:** No puedes calcular directamente la posición del registro N. Debes leer secuencialmente desde el inicio.

En el siguiente nivel aprenderás sobre **registros de longitud fija** que permiten acceso directo.

¡Éxito con el ranking! 🏆
