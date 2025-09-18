import java.io.File;
import java.util.Scanner;

public class p2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca la ruta del fichero");
        String rutFichero = sc.next();

        File fichero = new File(rutFichero);

        if (fichero.exists()) {
            fichero.delete();
            System.out.println("El fichero se ha eliminado");

        } else {

            System.out.println("El fichero no existe");
        }
    }
}
