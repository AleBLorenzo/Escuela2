import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class CopiarFichero {
    public static void main(String[] args) throws IOException {

        try {

            if (args.length < 2) {
                System.out.println("Fichero copiado correctamente");
                return;
            }

            String nombreOrigen = args[0];
            String nombreDestino = args[1];

            File ficheroOrigen = new File(nombreOrigen);
            ficheroOrigen.mkdirs();

            File fichero = new File(ficheroOrigen + "//fichero.txt");

            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Esto es un fichero de texto");
            bw.close();

            File ficheroDestino = new File(nombreDestino);

            if (ficheroDestino.exists()) {
                System.out.println("La carpeta ya existe");
            } else {
                System.out.println("La carpeta no existe se va a crear una nueva");
                ficheroDestino.mkdirs();

            }

            Files.copy(fichero.toPath(), new File(ficheroDestino + "//fichero.txt").toPath());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
