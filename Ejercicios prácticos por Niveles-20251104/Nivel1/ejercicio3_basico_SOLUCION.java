import java.io.*;

/**
 * EJERCICIO BÁSICO 3: Cifrador/Descifrador XOR de Savegames
 * 
 * Este programa cifra y descifra archivos usando el operador XOR.
 * Es perfecto para proteger archivos de guardado de videojuegos contra
 * edición manual casual.
 * 
 * Uso:
 *   java CifradorXOR c <entrada> <salida> <clave>  // Cifrar
 *   java CifradorXOR d <entrada> <salida> <clave>  // Descifrar
 * 
 * Ejemplo:
 *   java CifradorXOR c savegame.dat savegame.enc 167
 *   java CifradorXOR d savegame.enc savegame_restored.dat 167
 */
public class CifradorXOR {
    
    private static final int BUFFER_SIZE = 8192; // 8 KB
    private static final int BYTES_MUESTRA = 16; // Para mostrar en verificación
    
    public static void main(String[] args) {
        // Validar argumentos
        if (args.length < 4) {
            mostrarAyuda();
            return;
        }
        
        String modo = args[0].toLowerCase();
        String archivoEntrada = args[1];
        String archivoSalida = args[2];
        int clave;
        
        // Validar y parsear clave
        try {
            clave = Integer.parseInt(args[3]);
            if (clave < 1 || clave > 255) {
                System.err.println("Error: La clave debe estar entre 1 y 255");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: La clave debe ser un número entre 1 y 255");
            return;
        }
        
        // Validar modo
        boolean esCifrar;
        if (modo.equals("c") || modo.equals("cifrar")) {
            esCifrar = true;
        } else if (modo.equals("d") || modo.equals("descifrar")) {
            esCifrar = false;
        } else {
            System.err.println("Error: Modo debe ser 'c' (cifrar) o 'd' (descifrar)");
            return;
        }
        
        // Procesar archivo
        try {
            procesarArchivo(esCifrar, archivoEntrada, archivoSalida, (byte) clave);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al procesar archivo: " + e.getMessage());
        }
    }
    
    /**
     * Procesa el archivo aplicando XOR (cifrado o descifrado).
     */
    public static void procesarArchivo(boolean esCifrar, String archivoEntrada, 
                                      String archivoSalida, byte clave) throws IOException {
        
        File entrada = new File(archivoEntrada);
        
        // Verificar que el archivo de entrada existe
        if (!entrada.exists()) {
            throw new FileNotFoundException(archivoEntrada);
        }
        
        long tamañoArchivo = entrada.length();
        
        // Cabecera
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║         CIFRADOR/DESCIFRADOR XOR - PROTECTOR DE SAVES          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // Información
        System.out.println("Modo: " + (esCifrar ? "CIFRAR" : "DESCIFRAR"));
        System.out.println("Archivo entrada: " + archivoEntrada);
        System.out.println("Archivo salida: " + archivoSalida);
        System.out.printf("Clave XOR: 0x%02X (%d)%n", clave & 0xFF, clave & 0xFF);
        System.out.println();
        
        // Arrays para guardar primeros bytes (para verificación)
        byte[] primerosBytesOriginales = new byte[BYTES_MUESTRA];
        byte[] primerosBytesProcesados = new byte[BYTES_MUESTRA];
        int bytesMuestraGuardados = 0;
        
        System.out.println("--- PROCESANDO ---");
        System.out.println("Leyendo archivo de entrada...");
        System.out.printf("Tamaño: %d bytes (%s)%n", tamañoArchivo, formatearTamaño(tamañoArchivo));
        System.out.println();
        System.out.println("Aplicando " + (esCifrar ? "cifrado" : "descifrado") + " XOR...");
        
        // Procesar archivo
        long bytesProcessados = 0;
        long ultimoProgreso = 0;
        
        try (FileInputStream fis = new FileInputStream(entrada);
             FileOutputStream fos = new FileOutputStream(archivoSalida)) {
            
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesLeidos;
            
            while ((bytesLeidos = fis.read(buffer)) != -1) {
                // Guardar primeros bytes originales (antes de XOR)
                if (bytesMuestraGuardados < BYTES_MUESTRA && bytesLeidos > 0) {
                    int porCopiar = Math.min(BYTES_MUESTRA - bytesMuestraGuardados, bytesLeidos);
                    System.arraycopy(buffer, 0, primerosBytesOriginales, 
                                   bytesMuestraGuardados, porCopiar);
                }
                
                // Aplicar XOR a cada byte
                for (int i = 0; i < bytesLeidos; i++) {
                    buffer[i] ^= clave;
                }
                
                // Guardar primeros bytes procesados (después de XOR)
                if (bytesMuestraGuardados < BYTES_MUESTRA && bytesLeidos > 0) {
                    int porCopiar = Math.min(BYTES_MUESTRA - bytesMuestraGuardados, bytesLeidos);
                    System.arraycopy(buffer, 0, primerosBytesProcesados, 
                                   bytesMuestraGuardados, porCopiar);
                    bytesMuestraGuardados += porCopiar;
                }
                
                // Escribir bytes procesados
                fos.write(buffer, 0, bytesLeidos);
                
                // Actualizar progreso
                bytesProcessados += bytesLeidos;
                long progresoActual = (bytesProcessados * 100) / tamañoArchivo;
                
                if (progresoActual != ultimoProgreso) {
                    mostrarBarraProgreso(progresoActual);
                    ultimoProgreso = progresoActual;
                }
            }
            
            fos.flush();
        }
        
        System.out.println();
        System.out.println();
        
        // Mostrar resultados
        System.out.println("--- RESULTADO ---");
        System.out.println("✓ Archivo " + (esCifrar ? "cifrado" : "descifrado") + " correctamente");
        System.out.println("✓ Bytes procesados: " + bytesProcessados);
        System.out.println("✓ Archivo de salida: " + archivoSalida);
        System.out.println();
        
        // Mostrar verificación
        System.out.println("--- VERIFICACIÓN ---");
        
        if (esCifrar) {
            System.out.println("Primeros " + bytesMuestraGuardados + " bytes ORIGINALES:");
            imprimirBytesHex(primerosBytesOriginales, bytesMuestraGuardados);
            System.out.println();
            System.out.println("Primeros " + bytesMuestraGuardados + " bytes CIFRADOS:");
            imprimirBytesHex(primerosBytesProcesados, bytesMuestraGuardados);
            System.out.println();
            System.out.printf("¡Cifrado completado! Para descifrar usa la misma clave: 0x%02X%n", 
                            clave & 0xFF);
        } else {
            System.out.println("Primeros " + bytesMuestraGuardados + " bytes CIFRADOS:");
            imprimirBytesHex(primerosBytesOriginales, bytesMuestraGuardados);
            System.out.println();
            System.out.println("Primeros " + bytesMuestraGuardados + " bytes DESCIFRADOS:");
            imprimirBytesHex(primerosBytesProcesados, bytesMuestraGuardados);
            System.out.println();
            System.out.println("¡Descifrado completado! El archivo original ha sido restaurado.");
        }
    }
    
    /**
     * Muestra la barra de progreso.
     */
    private static void mostrarBarraProgreso(long progreso) {
        int anchoBarra = 40;
        int lleno = (int) ((progreso * anchoBarra) / 100);
        
        System.out.print("\r[");
        for (int i = 0; i < anchoBarra; i++) {
            if (i < lleno) {
                System.out.print("█");
            } else {
                System.out.print(" ");
            }
        }
        System.out.printf("] %d%%", progreso);
    }
    
    /**
     * Imprime bytes en formato hexadecimal.
     */
    private static void imprimirBytesHex(byte[] bytes, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            System.out.printf("%02X ", bytes[i] & 0xFF);
            if ((i + 1) % 16 == 0 && i < cantidad - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }
    
    /**
     * Formatea el tamaño en bytes.
     */
    private static String formatearTamaño(long bytes) {
        if (bytes < 1024) {
            return bytes + " bytes";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
        } else {
            return String.format("%.2f GB", bytes / (1024.0 * 1024.0 * 1024.0));
        }
    }
    
    /**
     * Muestra ayuda de uso.
     */
    private static void mostrarAyuda() {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║         CIFRADOR/DESCIFRADOR XOR - PROTECTOR DE SAVES          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Uso:");
        System.out.println("  java CifradorXOR <modo> <entrada> <salida> <clave>");
        System.out.println();
        System.out.println("Parámetros:");
        System.out.println("  modo    : 'c' o 'cifrar' para cifrar");
        System.out.println("            'd' o 'descifrar' para descifrar");
        System.out.println("  entrada : archivo de entrada");
        System.out.println("  salida  : archivo de salida");
        System.out.println("  clave   : número entre 1 y 255");
        System.out.println();
        System.out.println("Ejemplos:");
        System.out.println("  java CifradorXOR c savegame.dat savegame.enc 167");
        System.out.println("  java CifradorXOR d savegame.enc savegame_restored.dat 167");
        System.out.println();
        System.out.println("Nota: La misma clave se usa para cifrar y descifrar (cifrado simétrico)");
    }
}
