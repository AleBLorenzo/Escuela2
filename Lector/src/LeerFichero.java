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

            File fichero = new File("fichero.txt");

            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Esto es un fichero de texto fg4");
            bw.close();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Sleecione q quiere ver del archivo: ");
            System.out.println("1. Leer el fichero completo");
            System.out.println("2. Cantidad total de caracteres");
            System.out.println("3. Cantiadd total de palabras");
            String seleccion = scanner.nextLine();

            switch (seleccion) {
                case "1":
                    FileReader fr = new FileReader(fichero);
                    BufferedReader br = new BufferedReader(fr);

                    int c;
                    while ((c = br.read()) != -1) {
                        System.out.print((char) c);
                    }
                    fr.close();
                    break;

                case "2":
                    FileInputStream fis = new FileInputStream(fichero);
                    InputStreamReader dis = new InputStreamReader(fis);

                    int totalCaracteres = 0;
                    while ((c = dis.read()) != -1) {
                        totalCaracteres++;
                    }


                    System.out.println("Total de caracteres " + totalCaracteres);
                    break;

                case "3":
                    String contenido = Files.readString(fichero.toPath());
                    String[] palabras = contenido.split(" ");
                    System.out.println("Total de palabras " + palabras.length);
                    break;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
