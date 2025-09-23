import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class addCont {

    public static void main(String[] args) {

        FileWriter histo = null;
        BufferedWriter Escrito = null;
        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("Ingresa una frase: ");
            String frace = sc.nextLine();

            histo = new FileWriter("Fichero2/src/historial.txt", true);
            Escrito = new BufferedWriter(histo);

            Escrito.write(frace);
            Escrito.newLine();
            Escrito.flush();

        } catch (Exception e) {

            System.out.println(e);
        } finally {
            try {

                histo.close();
                Escrito.close();
                sc.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
