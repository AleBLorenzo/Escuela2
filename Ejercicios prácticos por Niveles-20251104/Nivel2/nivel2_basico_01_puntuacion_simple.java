// Nivel 2 - Ejercicio Básico 1: Gestor Simple de Puntuaciones
// Dificultad: ⭐ Básico
// Tema: Sistema de puntuaciones de arcade

/*
ENUNCIADO:
Estás desarrollando un juego de arcade retro. Necesitas un sistema simple para 
guardar y leer las puntuaciones más altas. Cada puntuación tiene:
- Puntos (int)
- Nivel alcanzado (int)

Crea dos métodos:
1. guardarPuntuacion(String archivo, int puntos, int nivel)
   - Guarda UNA puntuación en formato binario
   
2. leerPuntuacion(String archivo)
   - Lee la puntuación y la muestra en consola
   - Formato: "Puntuación: X puntos - Nivel: Y"

EJEMPLO DE USO:
guardarPuntuacion("highscore.dat", 15000, 7);
leerPuntuacion("highscore.dat");
// Salida: Puntuación: 15000 puntos - Nivel: 7

NOTAS:
- Usa DataOutputStream para escritura
- Usa DataInputStream para lectura
- El archivo debe contener exactamente 8 bytes (4 + 4)
*/

import java.io.*;

public class GestorPuntuaciones {
    
    /**
     * Guarda una puntuación en un archivo binario
     * @param archivo nombre del archivo
     * @param puntos puntos obtenidos
     * @param nivel nivel alcanzado
     */
    public static void guardarPuntuacion(String archivo, int puntos, int nivel) 
            throws IOException {
        
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(archivo))) {
            
            // Escribir puntos y nivel como enteros de 4 bytes cada uno
            dos.writeInt(puntos);
            dos.writeInt(nivel);
            
            System.out.println("✓ Puntuación guardada correctamente");
        }
    }
    
    /**
     * Lee y muestra una puntuación desde un archivo binario
     * @param archivo nombre del archivo
     */
    public static void leerPuntuacion(String archivo) throws IOException {
        
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(archivo))) {
            
            // Leer en el mismo orden que se escribió
            int puntos = dis.readInt();
            int nivel = dis.readInt();
            
            // Mostrar resultado
            System.out.println("Puntuación: " + puntos + " puntos - Nivel: " + nivel);
        }
    }
    
    /**
     * Método main para probar el gestor
     */
    public static void main(String[] args) {
        String archivo = "highscore.dat";
        
        try {
            // Prueba 1: Guardar puntuación inicial
            System.out.println("=== Prueba 1: Guardar puntuación ===");
            guardarPuntuacion(archivo, 15000, 7);
            leerPuntuacion(archivo);
            System.out.println();
            
            // Prueba 2: Actualizar con mejor puntuación
            System.out.println("=== Prueba 2: Nueva mejor puntuación ===");
            guardarPuntuacion(archivo, 25000, 12);
            leerPuntuacion(archivo);
            System.out.println();
            
            // Prueba 3: Verificar que solo hay una puntuación
            System.out.println("=== Prueba 3: Verificar tamaño del archivo ===");
            File f = new File(archivo);
            System.out.println("Tamaño del archivo: " + f.length() + " bytes (esperado: 8)");
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

/*
SALIDA ESPERADA:
=== Prueba 1: Guardar puntuación ===
✓ Puntuación guardada correctamente
Puntuación: 15000 puntos - Nivel: 7

=== Prueba 2: Nueva mejor puntuación ===
✓ Puntuación guardada correctamente
Puntuación: 25000 puntos - Nivel: 12

=== Prueba 3: Verificar tamaño del archivo ===
Tamaño del archivo: 8 bytes (esperado: 8)

EXPLICACIÓN TÉCNICA:
1. DataOutputStream.writeInt() escribe 4 bytes en formato big-endian
2. El archivo resultante tiene exactamente 8 bytes (2 × 4 bytes)
3. La lectura debe hacerse en el MISMO ORDEN que la escritura
4. Cada vez que guardamos, sobrescribimos el archivo anterior

CONTENIDO HEXADECIMAL del archivo highscore.dat (25000, 12):
00 00 61 A8  00 00 00 0C
│        │   │        │
│        │   │        └─ Nivel: 12 (0x0000000C)
│        └─────────────── Puntos: 25000 (0x000061A8)

CONCEPTOS CLAVE:
- DataOutputStream simplifica la escritura de tipos primitivos
- No necesitas manipular bytes individuales
- El orden de lectura/escritura es CRÍTICO
- FileOutputStream sobrescribe el archivo por defecto

EJERCICIO PARA PENSAR:
¿Qué pasaría si intentas leer con readDouble() en lugar de dos readInt()?
Respuesta: Leerías los 8 bytes como un único double, obteniendo un valor sin sentido.
*/
