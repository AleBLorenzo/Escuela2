import java.io.*;

/**
 * EJERCICIO BÁSICO 1: Analizador de Archivos Binarios
 * 
 * Este programa analiza un archivo binario y genera estadísticas detalladas
 * sobre su contenido, incluyendo frecuencias de bytes, patrones y distribución.
 * 
 * Uso: java AnalizadorArchivoBinario <nombre_archivo>
 */
public class AnalizadorArchivoBinario {
    
    /**
     * Punto de entrada del programa.
     */
    public static void main(String[] args) {
        // Validar argumentos
        if (args.length < 1) {
            System.out.println("Uso: java AnalizadorArchivoBinario <nombre_archivo>");
            System.out.println("Ejemplo: java AnalizadorArchivoBinario config.bin");
            return;
        }
        
        String nombreArchivo = args[0];
        
        try {
            analizarArchivo(nombreArchivo);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Analiza un archivo binario y muestra estadísticas completas.
     */
    public static void analizarArchivo(String nombreArchivo) throws IOException {
        File file = new File(nombreArchivo);
        
        // Verificar que el archivo existe
        if (!file.exists()) {
            throw new FileNotFoundException(nombreArchivo);
        }
        
        long tamañoArchivo = file.length();
        
        // Arrays para estadísticas
        int[] frecuencias = new int[256];  // Contador para cada byte posible (0-255)
        byte[] primeros64Bytes = new byte[64]; // Para mostrar al final
        int bytesGuardados = 0; // Cantidad de bytes guardados en primeros64Bytes
        
        // Contadores
        long totalBytes = 0;
        long bytesNulos = 0;
        long bytesASCII = 0;
        long bytesMSB = 0;
        
        // Para detectar secuencias
        int byteAnterior = -1;
        int contadorSecuencia = 0;
        int secuenciasDetectadas = 0;
        int mejorSecuenciaLongitud = 0;
        long mejorSecuenciaOffset = 0;
        int mejorSecuenciaByte = 0;
        
        // Leer archivo
        try (FileInputStream fis = new FileInputStream(file)) {
            int byteLeido;
            long offset = 0;
            
            while ((byteLeido = fis.read()) != -1) {
                // Guardar primeros 64 bytes para mostrarlos al final
                if (bytesGuardados < 64) {
                    primeros64Bytes[bytesGuardados++] = (byte) byteLeido;
                }
                
                // Actualizar frecuencias
                frecuencias[byteLeido]++;
                
                // Actualizar contadores
                totalBytes++;
                
                if (byteLeido == 0) {
                    bytesNulos++;
                }
                
                if (byteLeido >= 32 && byteLeido <= 126) {
                    bytesASCII++;
                }
                
                if (byteLeido >= 128) {
                    bytesMSB++;
                }
                
                // Detectar secuencias repetitivas
                if (byteLeido == byteAnterior) {
                    contadorSecuencia++;
                    
                    // Si encontramos 4+ bytes iguales consecutivos
                    if (contadorSecuencia == 4) {
                        secuenciasDetectadas++;
                        
                        // Guardar la mejor secuencia
                        if (contadorSecuencia > mejorSecuenciaLongitud) {
                            mejorSecuenciaLongitud = contadorSecuencia;
                            mejorSecuenciaOffset = offset - contadorSecuencia;
                            mejorSecuenciaByte = byteLeido;
                        }
                    } else if (contadorSecuencia > 4) {
                        // Actualizar mejor secuencia si es más larga
                        if (contadorSecuencia > mejorSecuenciaLongitud) {
                            mejorSecuenciaLongitud = contadorSecuencia;
                            mejorSecuenciaOffset = offset - contadorSecuencia;
                            mejorSecuenciaByte = byteLeido;
                        }
                    }
                } else {
                    contadorSecuencia = 1;
                }
                
                byteAnterior = byteLeido;
                offset++;
            }
        }
        
        // Calcular byte más y menos frecuente
        int byteMasFrecuente = 0;
        int frecuenciaMasAlta = frecuencias[0];
        int byteMenosFrecuente = -1;
        int frecuenciaMasBaja = Integer.MAX_VALUE;
        int bytesUnicos = 0;
        
        for (int i = 0; i < 256; i++) {
            if (frecuencias[i] > 0) {
                bytesUnicos++;
                
                if (frecuencias[i] > frecuenciaMasAlta) {
                    frecuenciaMasAlta = frecuencias[i];
                    byteMasFrecuente = i;
                }
                
                if (frecuencias[i] < frecuenciaMasBaja) {
                    frecuenciaMasBaja = frecuencias[i];
                    byteMenosFrecuente = i;
                }
            }
        }
        
        // Mostrar reporte
        imprimirReporte(
            nombreArchivo,
            tamañoArchivo,
            totalBytes,
            byteMasFrecuente,
            frecuenciaMasAlta,
            byteMenosFrecuente,
            frecuenciaMasBaja,
            bytesUnicos,
            bytesNulos,
            bytesASCII,
            bytesMSB,
            secuenciasDetectadas,
            mejorSecuenciaLongitud,
            mejorSecuenciaOffset,
            mejorSecuenciaByte,
            primeros64Bytes,
            bytesGuardados
        );
    }
    
    /**
     * Imprime el reporte de análisis con formato bonito.
     */
    private static void imprimirReporte(
            String nombreArchivo,
            long tamañoArchivo,
            long totalBytes,
            int byteMasFrecuente,
            int frecuenciaMasAlta,
            int byteMenosFrecuente,
            int frecuenciaMasBaja,
            int bytesUnicos,
            long bytesNulos,
            long bytesASCII,
            long bytesMSB,
            int secuenciasDetectadas,
            int mejorSecuenciaLongitud,
            long mejorSecuenciaOffset,
            int mejorSecuenciaByte,
            byte[] primeros64Bytes,
            int cantidadBytes) {
        
        // Cabecera
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║          ANÁLISIS DE ARCHIVO BINARIO                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // Información básica
        System.out.println("Archivo: " + nombreArchivo);
        System.out.printf("Tamaño: %d bytes (%s)%n", tamañoArchivo, formatearTamaño(tamañoArchivo));
        System.out.println();
        
        // Estadísticas de bytes
        System.out.println("--- ESTADÍSTICAS DE BYTES ---");
        double porcentajeMasFrecuente = (frecuenciaMasAlta * 100.0) / totalBytes;
        System.out.printf("Byte más frecuente: 0x%02X (aparece %d veces, %.2f%%)%n",
                         byteMasFrecuente, frecuenciaMasAlta, porcentajeMasFrecuente);
        
        if (byteMenosFrecuente != -1) {
            double porcentajeMenosFrecuente = (frecuenciaMasBaja * 100.0) / totalBytes;
            System.out.printf("Byte menos frecuente: 0x%02X (aparece %d vez/veces, %.2f%%)%n",
                             byteMenosFrecuente, frecuenciaMasBaja, porcentajeMenosFrecuente);
        }
        
        System.out.println("Bytes únicos diferentes: " + bytesUnicos);
        System.out.println();
        
        // Distribución de valores
        System.out.println("--- DISTRIBUCIÓN DE VALORES ---");
        double porcentajeNulos = (bytesNulos * 100.0) / totalBytes;
        System.out.printf("Bytes nulos (0x00): %d (%.2f%%)%n", bytesNulos, porcentajeNulos);
        
        double porcentajeASCII = (bytesASCII * 100.0) / totalBytes;
        System.out.printf("Bytes ASCII imprimibles (32-126): %d (%.2f%%)%n",
                         bytesASCII, porcentajeASCII);
        
        double porcentajeMSB = (bytesMSB * 100.0) / totalBytes;
        System.out.printf("Bytes con MSB activado (128-255): %d (%.2f%%)%n",
                         bytesMSB, porcentajeMSB);
        System.out.println();
        
        // Patrones detectados
        System.out.println("--- PATRONES DETECTADOS ---");
        if (secuenciasDetectadas > 0) {
            System.out.println("✓ Se detectaron " + secuenciasDetectadas +
                             " secuencias de 4+ bytes consecutivos iguales");
            if (mejorSecuenciaLongitud > 0) {
                System.out.printf("  Ejemplo: %d bytes 0x%02X consecutivos en offset 0x%04X%n",
                                mejorSecuenciaLongitud, mejorSecuenciaByte, mejorSecuenciaOffset);
            }
        } else {
            System.out.println("✗ No se detectaron secuencias repetitivas (4+ bytes iguales)");
        }
        System.out.println();
        
        // Mostrar primeros bytes en hexadecimal
        if (cantidadBytes > 0) {
            System.out.println("--- PRIMEROS " + Math.min(cantidadBytes, 64) + " BYTES (HEX) ---");
            imprimirHexDump(primeros64Bytes, cantidadBytes);
            System.out.println();
        }
        
        System.out.println("Análisis completado.");
    }
    
    /**
     * Imprime bytes en formato hexadecimal (similar a hex dump).
     */
    private static void imprimirHexDump(byte[] bytes, int cantidad) {
        int bytesPerLine = 16;
        
        for (int i = 0; i < cantidad; i += bytesPerLine) {
            // Imprimir offset
            System.out.printf("%08X  ", i);
            
            // Imprimir bytes en hexadecimal
            for (int j = 0; j < bytesPerLine && (i + j) < cantidad; j++) {
                System.out.printf("%02X ", bytes[i + j] & 0xFF);
            }
            
            System.out.println();
        }
    }
    
    /**
     * Formatea el tamaño en bytes a una representación legible.
     */
    private static String formatearTamaño(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
        } else {
            return String.format("%.2f GB", bytes / (1024.0 * 1024.0 * 1024.0));
        }
    }
}
