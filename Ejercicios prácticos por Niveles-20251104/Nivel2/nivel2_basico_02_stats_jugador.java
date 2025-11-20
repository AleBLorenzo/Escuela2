// Nivel 2 - Ejercicio Básico 2: Estadísticas de Jugador
// Dificultad: ⭐ Básico
// Tema: RPG - Sistema de estadísticas

/*
ENUNCIADO:
Estás creando un RPG y necesitas guardar las estadísticas de un jugador.
Cada jugador tiene:
- ID (int)
- Nombre (String)
- Nivel (int)
- Puntos de vida (int)
- Puntos de maná (int)
- Experiencia (long)

Crea dos métodos:
1. guardarJugador(String archivo, int id, String nombre, int nivel, 
                   int vida, int mana, long experiencia)
   - Guarda los datos del jugador en formato binario

2. mostrarJugador(String archivo)
   - Lee y muestra todos los datos del jugador
   - Formato libre pero claro

EJEMPLO DE USO:
guardarJugador("player.dat", 1001, "Aragorn", 45, 850, 320, 125000L);
mostrarJugador("player.dat");

NOTAS:
- Usa writeUTF() para el nombre
- Usa writeLong() para la experiencia (puede ser un número grande)
- El orden de lectura debe coincidir con el de escritura
*/

import java.io.*;

public class EstadisticasJugador {
    
    /**
     * Guarda las estadísticas de un jugador en formato binario
     */
    public static void guardarJugador(String archivo, int id, String nombre, 
            int nivel, int vida, int mana, long experiencia) throws IOException {
        
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(archivo))) {
            
            // Escribir todos los campos en orden
            dos.writeInt(id);
            dos.writeUTF(nombre);        // UTF incluye longitud automáticamente
            dos.writeInt(nivel);
            dos.writeInt(vida);
            dos.writeInt(mana);
            dos.writeLong(experiencia);   // long = 8 bytes
            
            System.out.println("✓ Datos del jugador guardados correctamente");
        }
    }
    
    /**
     * Lee y muestra las estadísticas de un jugador
     */
    public static void mostrarJugador(String archivo) throws IOException {
        
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(archivo))) {
            
            // Leer en el MISMO ORDEN que se escribió
            int id = dis.readInt();
            String nombre = dis.readUTF();
            int nivel = dis.readInt();
            int vida = dis.readInt();
            int mana = dis.readInt();
            long experiencia = dis.readLong();
            
            // Mostrar en formato bonito
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║        DATOS DEL PERSONAJE             ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.printf("║ ID:           %-24d ║%n", id);
            System.out.printf("║ Nombre:       %-24s ║%n", nombre);
            System.out.printf("║ Nivel:        %-24d ║%n", nivel);
            System.out.printf("║ Vida:         %-24d ║%n", vida);
            System.out.printf("║ Maná:         %-24d ║%n", mana);
            System.out.printf("║ Experiencia:  %-24d ║%n", experiencia);
            System.out.println("╚════════════════════════════════════════╝");
        }
    }
    
    /**
     * Método auxiliar para calcular el tamaño del archivo
     */
    public static void analizarArchivo(String archivo) throws IOException {
        File f = new File(archivo);
        System.out.println("\n--- Análisis del Archivo ---");
        System.out.println("Tamaño total: " + f.length() + " bytes");
        System.out.println("Desglose esperado:");
        System.out.println("  - ID: 4 bytes (int)");
        System.out.println("  - Nombre: 2 + n bytes (UTF-8)");
        System.out.println("  - Nivel: 4 bytes (int)");
        System.out.println("  - Vida: 4 bytes (int)");
        System.out.println("  - Maná: 4 bytes (int)");
        System.out.println("  - Experiencia: 8 bytes (long)");
        System.out.println("  - Total: 26 + longitud(nombre) bytes");
    }
    
    /**
     * Método main con múltiples pruebas
     */
    public static void main(String[] args) {
        String archivo = "player.dat";
        
        try {
            // Prueba 1: Guerrero de nivel bajo
            System.out.println("=== PRUEBA 1: Guerrero Novato ===");
            guardarJugador(archivo, 1001, "Conan", 5, 150, 50, 1250L);
            mostrarJugador(archivo);
            analizarArchivo(archivo);
            System.out.println();
            
            // Prueba 2: Mago de nivel medio
            System.out.println("=== PRUEBA 2: Mago Experimentado ===");
            guardarJugador(archivo, 2034, "Gandalf", 45, 450, 800, 125000L);
            mostrarJugador(archivo);
            analizarArchivo(archivo);
            System.out.println();
            
            // Prueba 3: Paladín de alto nivel
            System.out.println("=== PRUEBA 3: Paladín Legendario ===");
            guardarJugador(archivo, 5678, "Arthas", 99, 2500, 1500, 9999999L);
            mostrarJugador(archivo);
            analizarArchivo(archivo);
            System.out.println();
            
            // Prueba 4: Nombre muy corto
            System.out.println("=== PRUEBA 4: Nombre Corto ===");
            guardarJugador(archivo, 100, "Lu", 1, 100, 100, 0L);
            mostrarJugador(archivo);
            analizarArchivo(archivo);
            System.out.println();
            
            // Prueba 5: Nombre largo con caracteres especiales
            System.out.println("=== PRUEBA 5: Nombre con Caracteres Especiales ===");
            guardarJugador(archivo, 777, "Señor Oscuro Ñoño", 66, 1800, 900, 666666L);
            mostrarJugador(archivo);
            analizarArchivo(archivo);
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

/*
SALIDA ESPERADA (extracto):
=== PRUEBA 2: Mago Experimentado ===
✓ Datos del jugador guardados correctamente
╔════════════════════════════════════════╗
║        DATOS DEL PERSONAJE             ║
╠════════════════════════════════════════╣
║ ID:           2034                     ║
║ Nombre:       Gandalf                  ║
║ Nivel:        45                       ║
║ Vida:         450                      ║
║ Maná:         800                      ║
║ Experiencia:  125000                   ║
╚════════════════════════════════════════╝

--- Análisis del Archivo ---
Tamaño total: 33 bytes
Desglose esperado:
  - ID: 4 bytes (int)
  - Nombre: 2 + n bytes (UTF-8)
  - Nivel: 4 bytes (int)
  - Vida: 4 bytes (int)
  - Maná: 4 bytes (int)
  - Experiencia: 8 bytes (long)
  - Total: 26 + longitud(nombre) bytes

EXPLICACIÓN TÉCNICA:

1. FORMATO writeUTF():
   - Primero escribe 2 bytes con la longitud del string
   - Luego escribe los caracteres en UTF-8 modificado
   - Para "Gandalf" (7 caracteres): 2 + 7 = 9 bytes
   - Contenido hex: 00 07 47 61 6E 64 61 6C 66
                    │  │  └─────────────────┘
                    │  │    "Gandalf" en ASCII
                    │  └─ Longitud: 7
                    └─── Byte alto de longitud

2. TAMAÑO TOTAL para "Gandalf":
   - ID: 4 bytes
   - Nombre: 2 + 7 = 9 bytes
   - Nivel: 4 bytes
   - Vida: 4 bytes
   - Maná: 4 bytes
   - Experiencia: 8 bytes
   - TOTAL: 33 bytes

3. CARACTERES ESPECIALES:
   Para "Señor Oscuro Ñoño" (18 caracteres visuales):
   - La 'Ñ' y 'ñ' ocupan 2 bytes cada una en UTF-8
   - Longitud real: 18 + 3 (extra por Ñ, ñ, ñ) = 21 bytes de datos
   - Total con header: 2 + 21 = 23 bytes para el string
   - Archivo completo: 26 + 21 = 47 bytes

CONTENIDO HEXADECIMAL (ejemplo para Conan, nivel 5):
00 00 03 E9  00 05 43 6F 6E 61 6E  00 00 00 05  00 00 00 96  00 00 00 32  00 00 00 00 00 00 04 E2
│           │  │  └──────────────┘ │           │           │           │                       │
│           │  │       "Conan"     │           │           │           │                       │
│           │  │                   │           │           │           │                       └─ Exp: 1250
│           │  │                   │           │           │           └─ Maná: 50
│           │  │                   │           │           └─ Vida: 150
│           │  │                   │           └─ Nivel: 5
│           │  └─ Longitud: 5
│           └─ Parte del ID
└─ ID: 1001 (0x000003E9)

CONCEPTOS CLAVE:
- writeUTF() maneja automáticamente la longitud del string
- Los strings ocupan espacio variable (longitud fija)
- writeLong() escribe 8 bytes (vs 4 de writeInt)
- El orden de lectura/escritura es FUNDAMENTAL
- UTF-8 puede ocupar más de 1 byte por carácter

ERRORES COMUNES A EVITAR:
1. Leer los campos en orden diferente al de escritura
2. Olvidar que writeUTF incluye 2 bytes de longitud
3. Confundir int (4 bytes) con long (8 bytes)
4. Asumir que cada carácter ocupa 1 byte en UTF-8

EJERCICIO PARA PENSAR:
Si guardas 100 jugadores con el método actual, ¿cómo leerías solo el jugador #50?
Respuesta: Con este método NO PUEDES acceder directamente. Necesitarías:
- Opción A: Leer secuencialmente los primeros 49 jugadores
- Opción B: Usar registros de longitud fija (próximo ejercicio)
- Opción C: Usar RandomAccessFile (nivel posterior)
*/
