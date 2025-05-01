import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;


public class CopiarFichero {
    public static void main(String[] args) throws IOException {

        File ficheroOrigen = new File("origen");
        ficheroOrigen.mkdirs();
       

        File fichero = new File(ficheroOrigen +"//fichero.txt");

        FileWriter fw = new FileWriter(fichero);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Esto es un fichero de texto");
        bw.close();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre de la Carpeta destino: ");
        String nombreFichero = scanner.nextLine(); 

        File ficheroDestino = new File(nombreFichero);
        ficheroDestino.mkdirs();

        Files.copy(fichero.toPath(), new File(ficheroDestino+"//fichero.txt").toPath());

        System.out.println("Fichero copiado correctamente");

    }
}
