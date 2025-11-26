
import java.io.File;
import java.io.FileInputStream;

public class App {

    private static final String RUTA = "README.md";

    public static void main(String[] args) throws Exception {

        File fichero = new File(RUTA);
        // Creamos el FileInputStream para leer el Archivo en bytes
        try (FileInputStream in = new FileInputStream(fichero)) {

            int[] frecuencias = new int[256];
            byte[] primeros64byte = new byte[64];
            int bytesGuardados = 0;
            int lectura;
            int cantidadtotal = 0;
            int contadorByteFrecuente = 0;
            int ByteFrecuente = 0;
            int contadorByteMenosFrecuente = -1;
            int ByteMenosFrecuente = 0;
            int ByteUnico = 0;
            int frecuenciaMasAlta = frecuencias[0];
            int frecuenciaMasBaja = Integer.MAX_VALUE;
            int bytenulo = 0;
            int BytesASCII = 0;
            int BytesMSB = 0;

            int byteAnterior = -1;
            int contadorSecuencia = 0;
            int secuenciasDetectadas = 0;
            int mejorSecuenciaLongitud = 0;
            long mejorSecuenciaOffset = 0;
            int mejorSecuenciaByte = 0;
            long offset = 0;

            while ((lectura = in.read()) != -1) {

                frecuencias[lectura]++;
                cantidadtotal++;

                if (bytesGuardados < 64) {
                    primeros64byte[bytesGuardados++] = (byte) lectura;

                }

                if (lectura == 0) {
                    bytenulo++;
                }
                if (lectura >= 32 && lectura <= 126) {
                    BytesASCII++;
                }
                if (lectura >= 128) {
                    BytesMSB++;
                }

                // Detectar secuencias repetitivas
                if (lectura == byteAnterior) {
                    contadorSecuencia++;

                    // Si encontramos 4+ bytes iguales consecutivos
                    if (contadorSecuencia == 4) {
                        secuenciasDetectadas++;

                        // Guardar la mejor secuencia
                        if (contadorSecuencia > mejorSecuenciaLongitud) {
                            mejorSecuenciaLongitud = contadorSecuencia;
                            mejorSecuenciaOffset = offset - contadorSecuencia;
                            mejorSecuenciaByte = lectura;
                        }
                    } else if (contadorSecuencia > 4) {
                        // Actualizar mejor secuencia si es más larga
                        if (contadorSecuencia > mejorSecuenciaLongitud) {
                            mejorSecuenciaLongitud = contadorSecuencia;
                            mejorSecuenciaOffset = offset - contadorSecuencia;
                            mejorSecuenciaByte = lectura;
                        }
                    }
                } else {
                    contadorSecuencia = 1;
                }

                byteAnterior = lectura;
                offset++;
            }

            for (int i = 0; i < frecuencias.length; i++) {

                if (frecuencias[i] > 0) {
                    ByteUnico++;

                    if (frecuencias[i] > frecuenciaMasAlta) {
                        frecuenciaMasAlta = frecuencias[i];
                        ByteFrecuente = i;

                    }

                    if (frecuencias[i] < frecuenciaMasBaja) {
                        frecuenciaMasBaja = frecuencias[i];
                        ByteMenosFrecuente = i;

                    }
                }

            }

            float cantidadKB = (int) cantidadtotal / 1024;
            System.out.println("Archivo: " + fichero.getName());
            System.out.println("Tamaño: " + cantidadtotal + " bytes (" + cantidadKB + "KB)");

            System.out.println("\n--- DISTRIBUCIÓN DE VALORES ---");
            System.out.println("Byte más frecuente: " + ByteFrecuente + "(aparece " + frecuenciaMasAlta + ")");
            System.out.println("Byte menos frecuente:  " + ByteMenosFrecuente + "(aparece " + frecuenciaMasBaja + ")");
            System.out.println("Bytes únicos diferentes:  " + ByteUnico);

            System.out.println("\n--- DISTRIBUCIÓN DE VALORES ---");
            System.out.println("Bytes nulos (0x00): " + bytenulo);
            System.out.println("Bytes ASCII imprimibles (32-126):  " + BytesASCII);
            System.out.println("Bytes con MSB activado (128-255):  " + BytesMSB);

            System.out.println("\n--- PATRONES DETECTADOS ---");
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

            System.out.println("\n--- PRIMEROS 64 BYTES (HEX) ---");
            for (int i = 0; i < bytesGuardados; i += 16) {

                System.out.printf("%08X  ", i);

                for (int j = 0; j < 16 && (i + j) < bytesGuardados; j++) {

                    System.out.printf("%02X ", primeros64byte[i + j] & 0xFF);
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}
