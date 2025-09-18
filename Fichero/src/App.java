import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("Que ruta quiere comprobar");

        String Solicitud = sc.nextLine();

        File Directorio = new File(Solicitud);

        if (Directorio.exists()) {
            System.out.println("El fichero existe");
             
           String [] Dire =  Directorio.list();

            for(String archivos : Dire){
                System.out.println(archivos);

            }
        } else {
            System.out.println("no existe");
        }
    }
}
