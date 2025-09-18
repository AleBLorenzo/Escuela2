import java.io.File;
import java.util.Scanner;

public class App1 {
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

              System.out.println("Si quiere borrar un fichero escriba su nombre");

                String borrar = sc.nextLine();
                
                File ficheroBorrar = new File(Directorio, borrar);
                if(ficheroBorrar.exists()){
                    ficheroBorrar.delete();
                    System.out.println("El fichero se ha borrado");

            }
        }
        } else {
            System.out.println("no existe");
        }
    }
}
