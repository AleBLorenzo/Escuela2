import java.io.File;
import java.io.FileInputStream;

public class Eje1 {

    public static void main(String[] args) throws Exception {

        final String RUTA = "ejercicio1_basico_ENUNCIADO.md";

        infoBasica(RUTA);

    }

    private static void infoBasica(String RUTA) {

        // Creamos la entrada de datos con un FileInputStream especial para la
        // la lectura de cualquier archivo.
        try (FileInputStream rd = new FileInputStream(new File(RUTA))) {

            // Con este bucle copiamos cada byte a una variable para almacenarlo.
            int totalBytes = 0;
            int valortotal = 0;

            int[] bytes = new int[256];

            while ((totalBytes = rd.read()) != -1) {

                valortotal++;
                bytes[totalBytes]++;

            }

             int byteMasFrecuente = 0;
        int frecuenciaMasAlta = bytes[0];
        int byteMenosFrecuente = -1;
        int frecuenciaMasBaja = Integer.MAX_VALUE;
        int bytesUnicos = 0;
        
        for (int i = 0; i < 256; i++) {
            if (bytes[i] > 0) {
                bytesUnicos++;
                
                if (bytes[i] > frecuenciaMasAlta) {
                    frecuenciaMasAlta = bytes[i];
                    byteMasFrecuente = i;
                }
                
                if (bytes[i] < frecuenciaMasBaja) {
                    frecuenciaMasBaja = bytes[i];
                    byteMenosFrecuente = i;
                }
            }
        }


            // creamos un conversor para pasarlo a KB.
            float convertidorKb = (float) (Math.round(valortotal) / 1024.0);
            float convertidorMb = (float) (Math.round(convertidorKb) / 1024.0);

            System.out.println(byteMasFrecuente +"  "+frecuenciaMasAlta);
            System.out.println(byteMenosFrecuente);
            System.out.println(bytesUnicos);

            // Imprimimos la info q nos da cada variable.
            System.out.println("Total de Bytes des archivo :" + valortotal + " bytes " + "(" + convertidorKb + " KB)"
                    + "(" + convertidorMb + " MB)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
