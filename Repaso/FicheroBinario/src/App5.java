
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class App5 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Menu");
            System.out.println("1-Añadir");
            System.out.println("2-leer");
            System.out.println("3-Modificar");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    añadirdatos(sc);
                    break;
                case 2:
                    leer(sc);
                    break;
                    case 3:
                    Modificar(sc);
                    break;
                default:
                    throw new AssertionError();
            }
        }

    }

    private static void añadirdatos(Scanner sc) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("FicheroBinario/src/datos.dat",true))) {

            System.out.println("introduce el nuemro de cuenta");
            long NumeroCuenta = sc.nextLong();
            sc.nextLine();

            System.out.println("introduce el nombre");
            String NombreTitular = sc.nextLine();

            System.out.println("introduce el saldo");
            double SaldoActual = sc.nextDouble();
            sc.nextLine();

            System.out.println("introduce si eesta activo o no");
            boolean Activo = sc.nextBoolean();
            sc.nextLine();

            dos.writeLong(NumeroCuenta);
            dos.writeUTF(NombreTitular);
            dos.writeDouble(SaldoActual);
            dos.writeBoolean(Activo);
            dos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void leer(Scanner sc) throws FileNotFoundException, IOException {
        try (DataInputStream dos = new DataInputStream(new FileInputStream("FicheroBinario/src/datos.dat"))) {

            while (true) {

                long num = dos.readLong();
                String nombre = dos.readUTF();
                double saldo = dos.readDouble();
                boolean activo = dos.readBoolean();
                
                Cuenta c = new Cuenta(activo ,nombre ,num, saldo);
                System.out.println(c.toString());
            }

        } catch (EOFException e) {
            System.out.println("Fin de la lista");
        }
    }

     private static void Modificar(Scanner sc) throws FileNotFoundException, IOException {
    
             File ficehro = new File ("FicheroBinario/src/datos.dat");
            File nuevofichero = new File ("FicheroBinario/src/datos_temp.dat");
            boolean encotrar = false;

                try (DataInputStream dos = new DataInputStream(new FileInputStream("FicheroBinario/src/datos.dat"));
        DataOutputStream doa = new DataOutputStream(new FileOutputStream("FicheroBinario/src/datos_temp.dat"))) {


            System.out.println("Introdece el nuemro de cuanta a modificar");
            long nuemroamodificar = sc.nextLong();
            sc.nextLine();

            System.out.println("Introdece el saldo a editar");
            double nuevosaldo = sc.nextDouble();
            sc.nextLine();

            while (true) {

                long nuemrocuenta;
                System.out.println(nuemrocuenta = dos.readLong());
                String nombre;
                System.out.println(nombre = dos.readUTF());
                double saldo ;
                System.out.println(saldo =dos.readDouble());
                boolean actividad ;
                System.out.println(actividad =dos.readBoolean());


                if (nuemroamodificar == nuemrocuenta ) {

                    double saldoFinal = nuevosaldo;
                    
                    doa.writeLong(nuemrocuenta);
                    doa.writeUTF(nombre);
                    doa.writeDouble(saldoFinal);
                    doa.writeBoolean(actividad);

                   encotrar = true;
                    
                }else {
                    
                    doa.writeLong(nuemrocuenta);
                    doa.writeUTF(nombre);
                    doa.writeDouble(saldo); 
                    doa.writeBoolean(actividad);
                }
   
 }
                 } catch (EOFException e) {
           
            }
                  
       
        if (encotrar) {
            File archivoDestino = new File("FicheroBinario/src/datos.dat");

            if (ficehro.delete()) {
                if ( nuevofichero.renameTo(archivoDestino)) {
                    System.out.println("Archivo actualizado correctamente.");
                } else {
                    System.err.println("Error: No se pudo renombrar el archivo temporal.");
                }
            } else {
                System.err.println("Error: No se pudo eliminar el archivo original.");
            }
        } else {
          
            nuevofichero.delete();
        }
    }

}
