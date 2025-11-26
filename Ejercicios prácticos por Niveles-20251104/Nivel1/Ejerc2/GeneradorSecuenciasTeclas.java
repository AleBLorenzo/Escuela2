import java.io.*;
import java.util.*;

/**
 * EJERCICIO BÁSICO 2: Generador de Secuencias de Teclas
 * 
 * Este programa crea archivos binarios que representan secuencias de pulsaciones
 * de teclas para un sistema de replay de videojuegos.
 * 
 * Formato del archivo:
 * - Header (8 bytes): Magic "KEYS" + versión + reservados
 * - Metadata (8 bytes): cantidad de pulsaciones + duración
 * - Secuencia (N bytes): códigos de teclas
 * - Footer (1 byte): checksum
 */
public class GeneradorSecuenciasTeclas {
    
    // Mapeo de teclas a códigos
    private static final Map<Character, Integer> CODIGOS_TECLAS = new HashMap<>();
    private static final Map<Character, String> NOMBRES_TECLAS = new HashMap<>();
    
    static {
        // Inicializar mapeos
        CODIGOS_TECLAS.put('W', 87);   NOMBRES_TECLAS.put('W', "Arriba");
        CODIGOS_TECLAS.put('A', 65);   NOMBRES_TECLAS.put('A', "Izquierda");
        CODIGOS_TECLAS.put('S', 83);   NOMBRES_TECLAS.put('S', "Abajo");
        CODIGOS_TECLAS.put('D', 68);   NOMBRES_TECLAS.put('D', "Derecha");
        CODIGOS_TECLAS.put('J', 74);   NOMBRES_TECLAS.put('J', "Puño");
        CODIGOS_TECLAS.put('K', 75);   NOMBRES_TECLAS.put('K', "Patada");
        CODIGOS_TECLAS.put('L', 76);   NOMBRES_TECLAS.put('L', "Bloqueo");
        CODIGOS_TECLAS.put(' ', 32);   NOMBRES_TECLAS.put(' ', "Salto");
        CODIGOS_TECLAS.put('X', 16);   NOMBRES_TECLAS.put('X', "Correr"); // Shift -> X
        CODIGOS_TECLAS.put('P', 13);   NOMBRES_TECLAS.put('P', "Pausa");  // Enter -> P
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Cabecera
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║     GENERADOR DE SECUENCIAS DE TECLAS - REPLAY SYSTEM         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // Solicitar datos
        System.out.print("Nombre del archivo de salida: ");
        String nombreArchivo = scanner.nextLine().trim();
        
        System.out.print("Secuencia de teclas (ej: WWAASSJJK): ");
        String secuencia = scanner.nextLine().trim().toUpperCase();
        
        System.out.print("Duración total (ms): ");
        int duracion = scanner.nextInt();
        
        System.out.println();
        
        try {
            crearArchivoReplay(nombreArchivo, secuencia, duracion);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
        
        scanner.close();
    }
    
    /**
     * Crea un archivo de replay con la secuencia de teclas.
     */
    public static void crearArchivoReplay(String nombreArchivo, String secuencia, int duracion) 
            throws IOException {
        
        System.out.println("--- PROCESANDO SECUENCIA ---");
        
        // Procesar secuencia y convertir a bytes
        List<Byte> bytesValidos = new ArrayList<>();
        
        for (char c : secuencia.toCharArray()) {
            if (CODIGOS_TECLAS.containsKey(c)) {
                int codigo = CODIGOS_TECLAS.get(c);
                bytesValidos.add((byte) codigo);
                System.out.printf("Tecla '%c' (%s) → 0x%02X%n", 
                                c, NOMBRES_TECLAS.get(c), codigo);
            } else {
                System.out.printf("⚠ Advertencia: Tecla '%c' no válida (ignorada)%n", c);
            }
        }
        
        System.out.println();
        
        // Validar que hay al menos una tecla
        if (bytesValidos.isEmpty()) {
            System.err.println("Error: No hay teclas válidas en la secuencia");
            return;
        }
        
        int cantidadPulsaciones = bytesValidos.size();
        
        // Crear archivo
        System.out.println("--- ESCRIBIENDO ARCHIVO ---");
        
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo)) {
            
            // 1. HEADER (8 bytes)
            // Magic number "KEYS"
            fos.write(0x4B); // 'K'
            fos.write(0x45); // 'E'
            fos.write(0x59); // 'Y'
            fos.write(0x53); // 'S'
            
            // Versión
            fos.write(0x01);
            
            // Reservados (3 bytes)
            fos.write(0x00);
            fos.write(0x00);
            fos.write(0x00);
            
            System.out.println("✓ Header escrito (8 bytes)");
            
            // 2. METADATA (8 bytes)
            // Cantidad de pulsaciones (4 bytes, big-endian)
            escribirInt(fos, cantidadPulsaciones);
            
            // Duración (4 bytes, big-endian)
            escribirInt(fos, duracion);
            
            System.out.println("✓ Metadata escrita (8 bytes)");
            
            // 3. SECUENCIA
            int suma = 0;
            for (byte b : bytesValidos) {
                fos.write(b);
                suma += (b & 0xFF); // Sumar para checksum
            }
            
            System.out.printf("✓ Secuencia escrita (%d bytes)%n", cantidadPulsaciones);
            
            // 4. CHECKSUM (1 byte)
            byte checksum = (byte) (suma & 0xFF);
            fos.write(checksum);
            
            System.out.printf("✓ Checksum escrito (1 byte): 0x%02X%n", checksum & 0xFF);
            System.out.println();
            
            fos.flush();
        }
        
        // Calcular tamaño total
        File file = new File(nombreArchivo);
        long tamañoTotal = file.length();
        
        System.out.println("Archivo creado exitosamente: " + nombreArchivo);
        System.out.println("Tamaño total: " + tamañoTotal + " bytes");
        System.out.println();
        
        // Mostrar estructura del archivo
        mostrarEstructura(nombreArchivo, cantidadPulsaciones, duracion, bytesValidos);
        
        System.out.println();
        System.out.println("¡Replay guardado! Úsalo para reproducir el combo.");
    }
    
    /**
     * Escribe un int como 4 bytes en big-endian.
     */
    private static void escribirInt(FileOutputStream fos, int valor) throws IOException {
        fos.write((valor >> 24) & 0xFF); // Byte más significativo
        fos.write((valor >> 16) & 0xFF);
        fos.write((valor >> 8) & 0xFF);
        fos.write(valor & 0xFF);         // Byte menos significativo
    }
    
    /**
     * Muestra una tabla con la estructura del archivo creado.
     */
    private static void mostrarEstructura(String nombreArchivo, int cantidad, int duracion, 
                                        List<Byte> secuencia) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    ESTRUCTURA DEL ARCHIVO                      ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Offset  | Contenido          | Descripción                    ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        
        // Header
        System.out.println("║ 0x0000  | 4B 45 59 53        | Magic: \"KEYS\"                  ║");
        System.out.println("║ 0x0004  | 01                 | Versión: 1                     ║");
        System.out.println("║ 0x0005  | 00 00 00           | Reservado                      ║");
        
        // Metadata
        System.out.printf("║ 0x0008  | %02X %02X %02X %02X        | Cantidad: %d pulsaciones       ║%n",
                         (cantidad >> 24) & 0xFF, (cantidad >> 16) & 0xFF,
                         (cantidad >> 8) & 0xFF, cantidad & 0xFF, cantidad);
        
        System.out.printf("║ 0x000C  | %02X %02X %02X %02X        | Duración: %d ms              ║%n",
                         (duracion >> 24) & 0xFF, (duracion >> 16) & 0xFF,
                         (duracion >> 8) & 0xFF, duracion & 0xFF, duracion);
        
        // Secuencia (mostrar solo primeros bytes)
        StringBuilder hexSecuencia = new StringBuilder();
        int maxMostrar = Math.min(4, secuencia.size());
        for (int i = 0; i < maxMostrar; i++) {
            hexSecuencia.append(String.format("%02X ", secuencia.get(i) & 0xFF));
        }
        if (secuencia.size() > 4) {
            hexSecuencia.append("...");
        }
        
        System.out.printf("║ 0x0010  | %-19s| Secuencia de teclas            ║%n", hexSecuencia.toString());
        
        // Checksum
        int suma = 0;
        for (byte b : secuencia) {
            suma += (b & 0xFF);
        }
        byte checksum = (byte) (suma & 0xFF);
        int offsetChecksum = 0x10 + secuencia.size();
    
        
        System.out.printf("║ 0x%04X  | %02X                 | Checksum: 0x%02X                 ║%n",
                         offsetChecksum, checksum & 0xFF, checksum & 0xFF);
        
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
}
