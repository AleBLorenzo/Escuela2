import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class CopiarFichero {
    public static void main(String[] args) throws IOException {

        File ficheroOrigen = new File("origen");
        ficheroOrigen.mkdirs();
        File ficheroDestino = new File("destino");
        ficheroDestino.mkdirs();

        File fichero = new File("origen//fichero.txt");

        FileWriter fw = new FileWriter(fichero);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Esto es un fichero de texto");
        bw.close();

        Files.copy(fichero.toPath(), new File("destino//fichero.txt").toPath());

        System.out.println("Fichero copiado correctamente");

    }
}
