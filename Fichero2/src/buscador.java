
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class buscador {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce la palabra q queres buscar");

        String palabra = sc.nextLine();

        System.out.println("\nTexto: \n");
        FileReader s = null;
        BufferedReader leer = null;

        try {

            s = new FileReader("Fichero2/src/libro.txt");
            leer = new BufferedReader(s);

            String a;
            int numeroLinea = 0;

            while ((a = leer.readLine()) != null) {

                numeroLinea++;

                if (a.contains(palabra)) {

                    System.out.println("se encotrno la palabra " + palabra + " en la linea " + numeroLinea);
                }
            }

            sc.close();
        } catch (Exception e) {

            System.out.println(e);
        } finally {

            try {

                s.close();
                leer.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
