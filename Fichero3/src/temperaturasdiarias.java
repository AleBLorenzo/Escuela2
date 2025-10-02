import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class temperaturasdiarias {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion;
        double temp ;
        String dia;

        try (DataOutputStream dis = new DataOutputStream(new FileOutputStream("Fichero3/src/temp.txt", true));
                DataInputStream dic = new DataInputStream(new FileInputStream("Fichero3/src/temp.txt"))) {

            while (true) {
                System.out.println("----Menu----");
                System.out.println("1-Añadir registro");
                System.out.println("2-Ver todos los registros");
                System.out.println("3-Salir");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        dia = sc.nextLine();
                        dis.writeUTF(dia);
                        temp = sc.nextDouble();
                        dis.writeDouble(temp);
                        temp = sc.nextDouble();
                        dis.writeDouble(temp);
                        opcion = sc.nextInt();
                        dis.writeInt(opcion);

                        dis.flush();

                        break;
                    case 2:

                        while (dic.available() > 0) {

                            System.out.println(dic.readUTF());
                            System.out.println(dic.readDouble());
                            System.out.println(dic.readDouble());
                            System.out.println(dic.readInt());
                        }

                        break;
                    case 3:
                        System.out.println("Adios");
                        sc.close();
                        break;
                    default:
                        System.out.println("Opción no válida");
                }

            }

        }

        catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}