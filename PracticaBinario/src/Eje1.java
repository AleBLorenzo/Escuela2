import java.io.File;
import java.io.FileInputStream;

public class Eje1 {


    public static void main(String[] args) throws Exception {

        final String RUTA = "ejercicio1_basico_ENUNCIADO.md";

        infoBasica(RUTA);


    }

    private static void infoBasica(String RUTA) {
        //Creamos la entrada de datos con un FileInputStream especial para la
        //la lectura de cualquier archivo

        try (FileInputStream rd = new FileInputStream(new File(RUTA))) {

            //Con este bucle copiamos cada byte a una variable para almacenarlo
            int totalBytes = 0;
            int bytes = 0;

            while ((bytes = rd.read()) != -1) {

                totalBytes++;

            }

            //creamos un conversor para pasarlo a MB

            double convertidorMb = Math.round(totalBytes) / 1000.0;

            //Imprimimos la info q nos da cada variable

            System.out.println("Total de Bytes des archivo :" + totalBytes + " bytes " + "(" + convertidorMb + " KB)");

        } catch (Exception e) {
        }
    }
}
