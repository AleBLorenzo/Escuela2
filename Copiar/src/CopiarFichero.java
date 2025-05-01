import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class CopiarFichero {
    public static void main(String[] args) throws IOException {

        try {
            File ficheroOrigen = new File("origen");
            ficheroOrigen.mkdirs();

            File fichero = new File(ficheroOrigen + "//fichero.txt");

            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Esto es un fichero de texto");
            bw.close();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce el nombre de la Carpeta destino: ");
            String nombreFichero = scanner.nextLine();

            File ficheroDestino = new File(nombreFichero);

            if (ficheroDestino.exists()) {
                System.out.println("La carpeta ya existe");
            } else {
                System.out.println("La carpeta no existe se va a crear una nueva");
                ficheroDestino.mkdirs();

            }

            Files.copy(fichero.toPath(), new File(ficheroDestino + "//fichero.txt").toPath());

            scanner.close();
            System.out.println("Fichero copiado correctamente");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
