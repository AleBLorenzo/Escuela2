import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LeerFichero {
    public static void main(String[] args) throws IOException {

        try {

            if (args.length < 1) {
                System.out.println("Lectura Correcta");
                return;
            }
            boolean v = false;
            String nombreFichero;

            if (args.length == 2 && args[0].equals("-v")) {
                v = true;
                nombreFichero = args[1];
            } else {
                nombreFichero = args[0];
            }

            File fichero = new File(nombreFichero);

            if (fichero.exists()) {
                System.out.println("El fichero ya existe");
            } else {
                System.out.println("El fichero no existe  " + nombreFichero);

            }

            BufferedReader br = new BufferedReader(new FileReader(fichero));

            String linea;
            int totalCaracteres = 0;
            int totalPalabras = 0;
            int totalLineas = 0;

            if ((linea = br.readLine()) != null) {
                System.out.println(linea);
                totalLineas++;

                if (linea.length() > 0) {
                    totalCaracteres += linea.length();
                }

                if (!linea.isEmpty()) {
                    totalPalabras += linea.split("\\s+").length;
                }

                if (v == false) {
                    System.out.println("\n--- Estadísticas ---");
                    System.out.println("Total de caracteres: " + totalCaracteres);
                    System.out.println("Total de palabras: " + totalPalabras);
                    System.out.println("Total de líneas: " + totalLineas);
                }

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
