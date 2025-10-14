package Fichero3.src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Eje12 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Usuario> lista = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Fichero3/src/Users.dat"))) {
            lista = (List<Usuario>) ois.readObject();

        } catch (Exception e) {
             System.out.println("Error: "+ e );

        }
        int opcion = 0;

        while (true) {
            System.out.println("-----MENU-----");
            System.out.println("1-Registrar nuevo usuario");
            System.out.println("2-Autenticar usuario");
            System.out.println("3-Cambiar contraseña ");
            System.out.println("4-Activar/desactivar usuario (solo ADMIN)");
            System.out.println("5-Listar usuarios por rol");
            System.out.println("6-Estadísticas de usuarios activos/inactivos");
            System.out.println("7-salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    añadir(sc, lista);
                    break;
                case 2:
                    autentificar(sc, lista);
                    break;
                case 3:
                    contrasena(sc, lista);
                    break;
                case 4:
                    ActivarD(sc, lista);
                    break;
                case 5:
                    Listar(sc,lista);
                    break;
                case 6:
                    Estadisticas(lista);
                    break;
                case 7:
                    sc.close();
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }

    public static void añadir(Scanner sc, List<Usuario> lista) {

        System.out.println("Escriba el nombre de usuario: ");
        String user = sc.nextLine();

        for (Usuario Usuario : lista) {

            if (Usuario.getUsername().equalsIgnoreCase(user)) {

                System.out.println("El user ya existe");
                return;
            }
        }

        System.out.println("Escriba la contraseña: ");
        String contrasena = sc.nextLine();

           if (contrasena.length() < 6) {
            System.out.println("La contraseña debe tener al menos 6 caracteres.");
            return;
        }

        System.out.println("Escriba el email : ");
        String email = sc.nextLine();

        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {

            System.out.println(" El email no tiene un formato válido.");
            return;
        }

        System.out.println("Escriba la fecha de registro (YYYY-MM-DD): ");
        LocalDate fechaR = LocalDate.parse(sc.nextLine());


         Boolean activo = false;
        
         System.out.println("Escriba el rol (: ADMIN, USER, GUEST): ");
            String tipo = sc.nextLine().toUpperCase();

            Rol rol;
            rol =Rol.valueOf(tipo);

       Usuario nuevo = new Usuario(activo, email, fechaR, contrasena , rol, user);
     
      
        lista.add(nuevo);

        System.out.println("Usuario Añadido");

        try (ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("Fichero3/src/Users.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
             System.out.println("Error: "+ e );
        }

    }

    public static void autentificar(Scanner sc, List<Usuario> lista) {

        System.out.println("Escribe el nombre de usuario");
        String nombre = sc.nextLine();

              System.out.println("Escribe la contraseña");
        String contrasena = sc.nextLine();

        for (Usuario V : lista) {
            if (V.getUsername().toLowerCase().contains(nombre)  && V.getPasswordHash().equals(String.valueOf(contrasena.hashCode()))) {
               
              V.setActivo(true);
                 System.out.println("Usuario autentificado");
                  System.out.println(V);   
            }
          
        }

        
        try (ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("Fichero3/src/Users.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
             System.out.println("Error: "+ e );
        }

    }

    public static void contrasena(Scanner sc, List<Usuario> lista) {

        System.out.println("Escribe el nombre de usuario");
        String nombre = sc.nextLine();

              System.out.println("Escribe la contraseña");
        String contrasena = sc.nextLine();

        for (Usuario V : lista) {
            if (V.getUsername().toLowerCase().contains(nombre)  && V.getPasswordHash().equals(String.valueOf(contrasena.hashCode()))) {
               
             System.out.println("Escribe la nueva contraseña");
             String nueva = sc.nextLine();

                if (nueva.length() < 6) {
            System.out.println("La contraseña debe tener al menos 6 caracteres.");
            return;
        }

        V.setPasswordHash(String.valueOf(nueva.hashCode()));
              System.out.println("Contraseña cambiada correctamente");
            }

        }

        try (ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("Fichero3/src/Users.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
             System.out.println("Error: "+ e );
        }

    }

    public static void ActivarD(Scanner sc, List<Usuario> lista) {

        System.out.println("Escribe el user ADMIN");
        String userA = sc.nextLine();

         System.out.println("Escribe la contraseña");
        String contraA = sc.nextLine();

          Usuario admin = null;
        for (Usuario V : lista) {
            if (V.getUsername().toLowerCase().contains(userA)  && V.getPasswordHash().equals(String.valueOf(contraA.hashCode()))) {
                admin = V;
            } else{
                System.out.println("Admin no disponible");
            }

        }
      
        
        System.out.println("Escribe el user a modificar");
        String userM = sc.nextLine();

         for (Usuario V : lista) {
            if (V.getUsername().toLowerCase().contains(userM) ) {
               V.setActivo(!V.isActivo());
               System.out.println("Estado actualizado de "+ (V.isActivo() ? "activo" : "inactivo"));
            } else{
                System.out.println("usuario no disponible");
            }

        }



        try (ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("Fichero3/src/Users.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
             System.out.println("Error: "+ e );
        }

    }

    public static void Listar(Scanner sc,List<Usuario> lista) {
        System.out.print("Rol a listar (ADMIN, USER, GUEST): ");
        String rolT = sc.nextLine().toUpperCase();

        


        System.out.println("---LISTA---");

        
        for (Usuario V : lista) {

            if (V.getRol().name().equalsIgnoreCase(rolT)) {
                System.out.println(V);
            }

        }

        try (ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("Fichero3/src/Users.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
             System.out.println("Error: "+ e );
        }

    }

    public static void Estadisticas(List<Usuario> lista) {

        System.out.println("---ESTADISTICA---");

        int contadorP = 0;
        int contadorD = 0;

        for (Usuario V : lista) {

            if (V.isActivo()) {

                contadorD++;

            } else {
                contadorP++;

            }

        }

        System.out.println(" inactivos: " + contadorP + " activos: " + contadorD);

        try (ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream("Fichero3/src/Users.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
             System.out.println("Error: "+ e );
        }
    }
}
