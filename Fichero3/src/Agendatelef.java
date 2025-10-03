package Fichero3.src;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Agendatelef {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("----MENU----");
            System.out.println("1-Añadir contacto");
            System.out.println("2-Buscar contacto por posición");
            System.out.println("3-Modificar teléfono de un contacto existente");
            System.out.println("4-Listar todos los contactos");
            System.out.println("5-Marcar posición como vacía (Borrar)");
            System.out.println("6-Salir");
            System.out.println("\nSeleccione una opcion");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    anadir(sc);
                    break;
                case 2:
                    Buscar(sc);
                    break;
                case 3:
                    Modificar(sc);
                    break;
                case 4:
                    Lista();
                    break;
                case 5:
                    Borrar(sc);
                    break;
                case 6:

                    sc.close();
                    return;
                default:
                    throw new AssertionError();
            }

        }
    }

    public static void anadir(Scanner sc) {

        System.out.println("Escriba el nombre : ");
        String nombre = sc.nextLine();

        System.out.println("Escriba el numero : ");
        String numero = sc.nextLine();

        if (!numero.matches("\\d+")) {

            System.out.println("El teléfono solo puede contener números.");
            return;
        }

        System.out.println("Escriba el email : ");
        String email = sc.nextLine();

        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {

            System.out.println(" El email no tiene un formato válido.");
            return;
        }

        try (RandomAccessFile rs = new RandomAccessFile("src/Datos.txt", "rw")) {

            rs.seek(rs.length());

            rs.writeUTF(String.format("%-30.30s", nombre));
            rs.writeUTF(String.format("%-15.15s", numero));
            rs.writeUTF(String.format("%-35.35s", email));
        

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void Lista() {

        try (RandomAccessFile rs = new RandomAccessFile("src/Datos.txt", "r")) {

            int contador = 0;

            rs.seek(0);
            System.out.println("----Agenda----");
            while (rs.getFilePointer() < rs.length()) {

                contador++;
                String dato1 = rs.readUTF();
                String dato2 = rs.readUTF();
                String dato3 = rs.readUTF();

                System.out.println(contador + " - " + dato1 + dato2 + dato3);

            }

        } catch (Exception e) {

        }
    }

    public static void Buscar(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile("src/Datos.txt", "r")) {

            long salto = (2+30) + (15) + (35);

            System.out.println("Escriba la posicion numerica numero del contacto q quiere encontrar");

            int registro = sc.nextInt();
            sc.nextLine();

            rs.seek(salto * (registro - 1));

            String dato1 = rs.readUTF();
            String dato2 = rs.readUTF();
            String dato3 = rs.readUTF();

            System.out.println(registro + " - " + dato1 + dato2 + dato3);

        } catch (Exception e) {
        }

    }

    public static void Borrar(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile("src/Datos.txt", "rw")) {

            long salto = (30) + (15) + (30);

            System.out.println("Escriba la posicion numerica numero del contacto q quiere borrar");

            int registro = sc.nextInt();
            sc.nextLine();

            if (registro >= rs.length()) {
                System.out.println(" No existe contacto en esa posición.");
                return;
            }

            rs.seek(salto * (registro - 1));

            System.out.println("Contacto en posición " + registro + " marcado como borrado.");

        } catch (Exception e) {
        }

    }

    public static void Modificar(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile("src/Datos.txt", "rw")) {

            System.out.print("Posición a modificar: ");
            int registro = sc.nextInt();
            sc.nextLine();

            long salto = (30) + (15) + (30);

            rs.seek(salto * (registro - 1));

            System.out.print("Nuevo teléfono: ");

            String nuevo = sc.nextLine();

            if (!nuevo.matches("\\d+")) {
                System.out.println("Solo números.");
                return;
            }
            rs.seek(30);
            rs.writeUTF(String.format("%-15s", nuevo));

        } catch (Exception e) {
        }

    }
}
