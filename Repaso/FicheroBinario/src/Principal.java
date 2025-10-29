
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    private static final String RUTAUSER = "FicheroBinario/src/usuarios.dat";
    private static final String RUTAPELICULA = "FicheroBinario/src/peliculas.dat";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("MENU");
            System.out.println("1- Registrar Usuario");
            System.out.println("2- Agregar pelicula");
            System.out.println("3- Listar Pelicuals");
            System.out.println("Menu");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Registrar(RUTAUSER, sc);
                    break;
                case 2:
                    Agregar(RUTAUSER, RUTAPELICULA, sc);
                    break;
                case 3:
                    listar(RUTAPELICULA, sc);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private static void Registrar(String RUTAUSER, Scanner sc) {

        System.out.println("Introduce un nombre de usuario ");
        String nombre = sc.nextLine();

        System.out.println("Introduce el rol (ADMIN o USER) ");
        String rol = sc.nextLine();

        System.out.println("Esta Activo o no ? (true/false)");
        boolean activo = sc.nextBoolean();
        sc.nextLine();

        try (ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream(RUTAUSER, true))) {

            Rol rolesA;
            Rol rolesU;

            if (rol.equalsIgnoreCase("ADMIN")) {

                rolesA = Rol.ADMIN;
                Usuario nuevo = new Usuario(activo, rolesA, nombre);
                op.writeObject(nuevo);

            } else {

                rolesU = Rol.USER;
                Usuario nuevo = new Usuario(activo, rolesU, nombre);
                op.writeObject(nuevo);

            }

        } catch (Exception e) {
        }

    }

    private static void Agregar(String RUTAUSER, String RUTAPELICULA, Scanner sc) {

        System.out.println("Introduce un nombre de Admin ");
        String nombre = sc.nextLine();

        try (ObjectInputStream op = new ObjectInputStream(new FileInputStream(RUTAUSER))) {

            Usuario user = (Usuario) op.readObject();

            if (user.getUsername().contains(nombre)) {
                System.out.println("Usuerio Corecto");
            } else {
                System.out.println("USer incorecto");
                return;
            }

        } catch (Exception e) {
        }

        try (ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream(RUTAPELICULA, true))) {
            System.out.println("Introduce un nombre de la Pelicula ");
            String nombreP = sc.nextLine();

            System.out.println("Introduce un director ");
            String director = sc.nextLine();

            System.out.println("Introduce la fecha ");
            String fecha = sc.nextLine();

            System.out.println("Esta Disponible ? (true/false)");
            boolean disponible = sc.nextBoolean();
            sc.nextLine();

            Pelicula nueva = new Pelicula(fecha, director, disponible, nombreP);

            op.writeObject(nueva);

        } catch (Exception e) {
        }
    }

    private static void listar(String RUTAPELICULA, Scanner sc) {

        System.out.println("Peliculas ");

        try (ObjectInputStream op = new ObjectInputStream(new FileInputStream(RUTAPELICULA))) {

            ArrayList<Pelicula> lista = (ArrayList<Pelicula>) op.readObject();
        for (Pelicula p : lista) {
            System.out.println(p);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}