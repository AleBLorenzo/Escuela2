import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class Contador {

    public static void main(String[] args) {

        FileReader si = null;
        BufferedReader lee = null;

        try {

            si = new FileReader("Fichero2/src/fichero.txt");
            lee = new BufferedReader(si);

            int contador = 0;
            String linea;

            while ((linea = lee.readLine()) != null) {

                    String[] palabras = linea.split(" ");
                    contador += palabras.length;

            }

            System.out.print("El Total de Palabras es " + contador);

        } catch (Exception e) {
            System.out.println("Error al abrir el fichero");

        } finally {
            try {
                si.close();
                lee.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
            }
        }

    }
}
