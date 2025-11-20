
import java.io.File;
import java.io.FileInputStream;

public class App {

    private static final String RUTA = "README.md";

    public static void main(String[] args) throws Exception {

        File fichero = new File(RUTA);
        // Creamos el FileInputStream para leer el Archivo en bytes
        try (FileInputStream in = new FileInputStream(fichero)) {

            int[] frecuencias = new int[256];
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

   
            while ((lectura = in.read()) != -1) {

                frecuencias[lectura]++;
                cantidadtotal++;


                
                if (lectura == 0 ) {
                    bytenulo++;
                }
                if (lectura >=32 && lectura <= 126 ) {
                    BytesASCII++;
                }
                if (lectura >= 128 ) {
                    BytesMSB++;
                }


                
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

                      //    String.format("%02X", frecuencias);

            }
            float cantidadKB = (int) cantidadtotal / 1024;
            System.out.println("Archivo: " + fichero.getName());
            System.out.println("Tamaño: " + cantidadtotal + " bytes (" + cantidadKB + "KB)");

            System.out.println("\n--- DISTRIBUCIÓN DE VALORES ---");
            System.out.println("Byte más frecuente: " + ByteFrecuente + "(aparece " + frecuenciaMasAlta + ")");
            System.out.println("Byte menos frecuente:  " + ByteMenosFrecuente + "(aparece " + frecuenciaMasBaja + ")");
            System.out.println("Bytes únicos diferentes:  " + ByteUnico);

            System.out.println("\n--- DISTRIBUCIÓN DE VALORES ---");
            System.out.println("Bytes nulos (0x00): " + bytenulo );
            System.out.println("Bytes ASCII imprimibles (32-126):  " + BytesASCII );
            System.out.println("Bytes con MSB activado (128-255):  " + BytesMSB);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}
