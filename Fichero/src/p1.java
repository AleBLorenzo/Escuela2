import java.util.Scanner;
import java.io.File;

public class p1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca el nombre del fichero");
        String nomFichero = sc.nextLine();

        System.out.println("Introduzca la ruta");
        String Ruta = sc.nextLine();
        File Directorio = new File(Ruta, nomFichero);

        if (Directorio.exists()) {
            System.out.println("El fichero existe");

        } else {
            try {
                if (Directorio.createNewFile()) {
                    System.out.println("El fichero se ha creado correctamente");

                }
            } catch (Exception e) {
                System.out.println("No se ha podido crear el fichero");
            }

            System.out.println("El fichero no existe");
        }

    }

}
