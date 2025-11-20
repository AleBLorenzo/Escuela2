// Nivel 2 - Ejercicio Básico 3: Lista de Puntuaciones
// Dificultad: ⭐ Básico
// Tema: Sistema de ranking multijugador

/*
ENUNCIADO:
Crea un sistema para gestionar MÚLTIPLES puntuaciones de diferentes jugadores.
Cada entrada tiene:
- Nombre del jugador (String)
- Puntuación (int)

Crea tres métodos:
1. agregarPuntuacion(String archivo, String nombre, int puntos)
   - AÑADE una nueva puntuación al final del archivo (modo append)
   
2. mostrarTodas(String archivo)
   - Lee y muestra TODAS las puntuaciones del archivo
   - Usa EOFException para detectar el final
   - Formato: "Jugador: [nombre] - Puntos: [puntos]"
   
3. contarPuntuaciones(String archivo)
   - Cuenta cuántas puntuaciones hay en el archivo
   - Devuelve el número total

EJEMPLO DE USO:
agregarPuntuacion("scores.dat", "Ana", 1500);
agregarPuntuacion("scores.dat", "Bob", 2300);
agregarPuntuacion("scores.dat", "Carlos", 1800);
mostrarTodas("scores.dat");
int total = contarPuntuaciones("scores.dat");
System.out.println("Total: " + total + " puntuaciones");

NOTAS:
- Usa FileOutputStream con parámetro 'true' para modo append
- Usa un bucle while(true) con try-catch para EOFException
- No guardes el número de registros en el archivo
*/

import java.io.*;

public class ListaPuntuaciones {
    
    /**
     * Añade una nueva puntuación al final del archivo
     */
    public static void agregarPuntuacion(String archivo, String nombre, int puntos) 
            throws IOException {
        
        // Modo APPEND: true como segundo parámetro
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(archivo, true))) {  // ← true = append
            
            dos.writeUTF(nombre);
            dos.writeInt(puntos);
            
            System.out.println("✓ Puntuación añadida: " + nombre + " - " + puntos);
        }
    }
    
    /**
     * Muestra todas las puntuaciones usando EOFException
     */
    public static void mostrarTodas(String archivo) throws IOException {
        
        System.out.println("\n=== RANKING DE PUNTUACIONES ===");
        
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(archivo))) {
            
            int contador = 0;
            
            // Bucle infinito: se rompe cuando llegamos al final
            while (true) {
                try {
                    String nombre = dis.readUTF();
                    int puntos = dis.readInt();
                    
                    contador++;
                    System.out.printf("%d. Jugador: %-15s - Puntos: %,d%n", 
                            contador, nombre, puntos);
                    
                } catch (EOFException e) {
                    // Fin del archivo alcanzado - esto es ESPERADO
                    break;
                }
            }
            
            if (contador == 0) {
                System.out.println("(No hay puntuaciones registradas)");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("(No hay puntuaciones registradas - archivo no existe)");
        }
        
        System.out.println("================================\n");
    }
    
    /**
     * Cuenta el número de puntuaciones en el archivo
     */
    public static int contarPuntuaciones(String archivo) throws IOException {
        
        int contador = 0;
        
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(archivo))) {
            
            while (true) {
                try {
                    dis.readUTF();  // Leer nombre (pero no lo usamos)
                    dis.readInt();  // Leer puntos (pero no lo usamos)
                    contador++;
                    
                } catch (EOFException e) {
                    break;  // Fin del archivo
                }
            }
            
        } catch (FileNotFoundException e) {
            return 0;  // Si el archivo no existe, hay 0 puntuaciones
        }
        
        return contador;
    }
    
    /**
     * Método auxiliar: encuentra la puntuación más alta
     */
    public static void mostrarMejorPuntuacion(String archivo) throws IOException {
        
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(archivo))) {
            
            String mejorNombre = "";
            int mejorPuntos = -1;
            boolean hayDatos = false;
            
            while (true) {
                try {
                    String nombre = dis.readUTF();
                    int puntos = dis.readInt();
                    hayDatos = true;
                    
                    if (puntos > mejorPuntos) {
                        mejorNombre = nombre;
                        mejorPuntos = puntos;
                    }
                    
                } catch (EOFException e) {
                    break;
                }
            }
            
            if (hayDatos) {
                System.out.println("🏆 Mejor puntuación: " + mejorNombre + 
                        " con " + String.format("%,d", mejorPuntos) + " puntos");
            } else {
                System.out.println("No hay puntuaciones para analizar");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("No hay puntuaciones para analizar");
        }
    }
    
    /**
     * Método main con pruebas completas
     */
    public static void main(String[] args) {
        String archivo = "scores.dat";
        
        try {
            // Limpiar archivo anterior si existe
            File f = new File(archivo);
            if (f.exists()) {
                f.delete();
                System.out.println("(Archivo anterior eliminado)\n");
            }
            
            // Prueba 1: Mostrar archivo vacío
            System.out.println("=== PRUEBA 1: Archivo Vacío ===");
            mostrarTodas(archivo);
            System.out.println("Total de puntuaciones: " + contarPuntuaciones(archivo));
            System.out.println();
            
            // Prueba 2: Añadir primera puntuación
            System.out.println("=== PRUEBA 2: Primera Puntuación ===");
            agregarPuntuacion(archivo, "Ana", 1500);
            mostrarTodas(archivo);
            System.out.println();
            
            // Prueba 3: Añadir más puntuaciones
            System.out.println("=== PRUEBA 3: Añadir Más Puntuaciones ===");
            agregarPuntuacion(archivo, "Bob", 2300);
            agregarPuntuacion(archivo, "Carlos", 1800);
            agregarPuntuacion(archivo, "Diana", 3100);
            agregarPuntuacion(archivo, "Elena", 2700);
            mostrarTodas(archivo);
            System.out.println();
            
            // Prueba 4: Contar puntuaciones
            System.out.println("=== PRUEBA 4: Estadísticas ===");
            int total = contarPuntuaciones(archivo);
            System.out.println("Total de puntuaciones registradas: " + total);
            mostrarMejorPuntuacion(archivo);
            System.out.println();
            
            // Prueba 5: Añadir puntuaciones en otra sesión
            System.out.println("=== PRUEBA 5: Simular Nueva Sesión ===");
            agregarPuntuacion(archivo, "Fernando", 2900);
            agregarPuntuacion(archivo, "Gloria", 1600);
            mostrarTodas(archivo);
            System.out.println("Total actualizado: " + contarPuntuaciones(archivo));
            mostrarMejorPuntuacion(archivo);
            System.out.println();
            
            // Prueba 6: Análisis del archivo
            System.out.println("=== PRUEBA 6: Análisis del Archivo ===");
            long tamaño = f.length();
            int numPuntuaciones = contarPuntuaciones(archivo);
            double promedioPorRegistro = (double) tamaño / numPuntuaciones;
            System.out.println("Tamaño del archivo: " + tamaño + " bytes");
            System.out.println("Número de puntuaciones: " + numPuntuaciones);
            System.out.printf("Promedio por registro: %.2f bytes%n", promedioPorRegistro);
            System.out.println("Nota: El tamaño varía según la longitud de los nombres");
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

/*
SALIDA ESPERADA:
=== PRUEBA 1: Archivo Vacío ===

=== RANKING DE PUNTUACIONES ===
(No hay puntuaciones registradas - archivo no existe)
================================

Total de puntuaciones: 0

=== PRUEBA 2: Primera Puntuación ===
✓ Puntuación añadida: Ana - 1500

=== RANKING DE PUNTUACIONES ===
1. Jugador: Ana             - Puntos: 1,500
================================

=== PRUEBA 3: Añadir Más Puntuaciones ===
✓ Puntuación añadida: Bob - 2300
✓ Puntuación añadida: Carlos - 1800
✓ Puntuación añadida: Diana - 3100
✓ Puntuación añadida: Elena - 2700

=== RANKING DE PUNTUACIONES ===
1. Jugador: Ana             - Puntos: 1,500
2. Jugador: Bob             - Puntos: 2,300
3. Jugador: Carlos          - Puntos: 1,800
4. Jugador: Diana           - Puntos: 3,100
5. Jugador: Elena           - Puntos: 2,700
================================

=== PRUEBA 4: Estadísticas ===
Total de puntuaciones registradas: 5
🏆 Mejor puntuación: Diana con 3,100 puntos

=== PRUEBA 5: Simular Nueva Sesión ===
✓ Puntuación añadida: Fernando - 2900
✓ Puntuación añadida: Gloria - 1600

=== RANKING DE PUNTUACIONES ===
1. Jugador: Ana             - Puntos: 1,500
2. Jugador: Bob             - Puntos: 2,300
3. Jugador: Carlos          - Puntos: 1,800
4. Jugador: Diana           - Puntos: 3,100
5. Jugador: Elena           - Puntos: 2,700
6. Jugador: Fernando        - Puntos: 2,900
7. Jugador: Gloria          - Puntos: 1,600
================================

Total actualizado: 7
🏆 Mejor puntuación: Diana con 3,100 puntos

=== PRUEBA 6: Análisis del Archivo ===
Tamaño del archivo: 101 bytes
Número de puntuaciones: 7
Promedio por registro: 14.43 bytes
Nota: El tamaño varía según la longitud de los nombres

EXPLICACIÓN TÉCNICA:

1. MODO APPEND:
   new FileOutputStream(archivo, true)
                                  └─── TRUE = añadir al final
                                       FALSE (default) = sobrescribir
   
   Sin el 'true', cada llamada a agregarPuntuacion() sobrescribiría el archivo.

2. PATRÓN EOFException:
   try (DataInputStream dis = ...) {
       while (true) {                    // Bucle "infinito"
           try {
               String data = dis.readUTF();
               // procesar...
           } catch (EOFException e) {
               break;                     // Salir del while
           }
       }
   }
   
   ¿Por qué este patrón?
   - DataInputStream no tiene un método hasNext() o available() confiable
   - EOFException es la forma OFICIAL de detectar el final
   - Es un control de flujo normal, no un error

3. ESTRUCTURA DEL ARCHIVO:
   [Nombre1][Puntos1][Nombre2][Puntos2]...[NombreN][PuntosN]
   
   Ejemplo con 3 registros ("Ana", 1500), ("Bob", 2300), ("Di", 3100):
   
   00 03 41 6E 61  00 00 05 DC  00 03 42 6F 62  00 00 08 FC  00 02 44 69  00 00 0C 1C
   │  │  └──────┘  │           │  │  └──────┘  │           │  │  └────┘  │         │
   │  │   "Ana"    │           │  │   "Bob"    │           │  │   "Di"   │         │
   │  └ Len:3      │           │  └ Len:3      │           │  └ Len:2    │         │
   │               │           │               │           │             │         │
   │               └ 1500      │               └ 2300      │             └ 3100    │
   └─────────────────────────────────────────────────────────────────────────────┘
        Registro 1                 Registro 2                 Registro 3

4. TAMAÑO VARIABLE:
   Cada registro ocupa: 2 + len(nombre) + 4 bytes
   
   "Ana" (3 chars):  2 + 3 + 4 = 9 bytes
   "Bob" (3 chars):  2 + 3 + 4 = 9 bytes
   "Di" (2 chars):   2 + 2 + 4 = 8 bytes
   "Diana" (5):      2 + 5 + 4 = 11 bytes
   
   Por eso el tamaño total varía.

5. CÁLCULO DE POSICIÓN:
   ¿Cómo leer directamente el registro #3?
   RESPUESTA: NO SE PUEDE con registros de longitud variable.
   
   Para acceso directo necesitas:
   - Registros de longitud fija (padding de strings)
   - O un índice separado con las posiciones
   - O RandomAccessFile con búsqueda secuencial

CONTENIDO HEXADECIMAL REAL (3 primeros registros):
00 03 41 6E 61 00 00 05 DC 00 03 42 6F 62 00 00 08 FC 00 06 43 61 72 6C 6F 73 00 00 07 08

Desglose:
- 00 03: longitud "Ana" (3)
- 41 6E 61: "Ana" en ASCII
- 00 00 05 DC: 1500 en int (big-endian)
- 00 03: longitud "Bob" (3)
- 42 6F 62: "Bob"
- 00 00 08 FC: 2300
- 00 06: longitud "Carlos" (6)
- 43 61 72 6C 6F 73: "Carlos"
- 00 00 07 08: 1800

CONCEPTOS CLAVE:
- FileOutputStream(archivo, true) → modo append
- EOFException es ESPERADA, no un error
- while(true) + try-catch(EOFException) → patrón estándar
- Registros de longitud variable → acceso secuencial obligatorio
- readUTF() maneja automáticamente la longitud

ERRORES COMUNES:
1. Olvidar el 'true' en FileOutputStream → sobrescribe en lugar de añadir
2. No capturar EOFException → programa termina con error
3. Intentar usar available() para detectar final → NO confiable
4. Capturar IOException en lugar de EOFException específicamente

COMPARACIÓN CON ALTERNATIVAS:

Opción A (actual): Sin contador
- Ventaja: Simple, no necesita actualizar contador
- Desventaja: Debes leer todo para contar

Opción B: Guardar contador al inicio
- Ventaja: Sabes cuántos registros hay sin leer todos
- Desventaja: Más complejo, debes actualizar el contador en cada append

Opción C: ArrayList + ObjectOutputStream
- Ventaja: Muy simple en código
- Desventaja: Debes cargar/guardar TODO el archivo cada vez

EJERCICIO PARA PENSAR:
Si el archivo tiene 1,000,000 de puntuaciones, ¿cuál es la eficiencia de:
a) contarPuntuaciones() → O(n) - debe leer todo
b) mostrarMejorPuntuacion() → O(n) - debe leer todo
c) agregarPuntuacion() → O(1) - append directo

¿Cómo mejorarías esto para búsquedas frecuentes?
Respuesta: Mantener un índice en memoria o archivo separado.
*/
