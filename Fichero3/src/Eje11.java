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

public class Eje11 {

    public static void main (String[] args){

     Scanner sc = new Scanner(System.in);
        List<Libro> lista = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Fichero3/src/biblioteca.dat"))) {
               lista = (List<Libro>) ois.readObject();
        
        } catch (Exception e) {
           
        }
        int opcion = 0;

        while (true) {
            System.out.println("-----MENU-----");
            System.out.println("1-Registrar nuevos libros con sus autores");
            System.out.println("2-Prestar libro");
            System.out.println("3-Devolver libro ");
            System.out.println("4-Buscar libros por autor");
            System.out.println("5-Listar libros disponibles");
            System.out.println("6-Mostrar estadísticas");
            System.out.println("7-salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    añadir(sc, lista);
                    break;
                case 2:
                    Prestar(sc, lista);
                    break;
                case 3:
                   Devovler(sc, lista);
                    break;
                case 4:
                  Buscar(sc, lista);
                    break;
                case 5:
                   Listar(lista);
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

    public static void añadir(Scanner sc, List<Libro> lista) {

      

            System.out.println("Escriba un ISBN: ");
            String ISBN = sc.nextLine();

           for (Libro libro : lista) {

            if (libro.getISBN().equalsIgnoreCase(ISBN)) {

                System.out.println("El ISBN ya existe");
                return;
            }
        }

            System.out.println("Escriba un titulo: ");
            String titulo = sc.nextLine();

            System.out.println("Escriba nombre del autor: ");
            String nombre = sc.nextLine();

            
            System.out.println("Escriba apellido del autor: ");
            String apellido = sc.nextLine();
            
            System.out.println("Escriba nacionalidad del autor: ");
            String nacionalidad = sc.nextLine();
            
            System.out.println("Escriba fecha de nacimiento del autor (YYYY-MM-DD): ");
             LocalDate fechaN = LocalDate.parse(sc.nextLine());

            System.out.println("Escriba las paginas: ");
            int paginas = sc.nextInt();
            sc.nextLine();

             System.out.println("Escriba si esta disponible (true/false): ");
            Boolean disponible = sc.nextBoolean();
             sc.nextLine();

             System.out.println("Escriba fecha de publicidad (YYYY-MM-DD): ");
           LocalDate fechaP = LocalDate.parse(sc.nextLine());
           
              if (fechaP.isBefore(fechaN)) {
            System.out.println("La fecha de publicacion no puede ser anterior a la fecha de nacimiento del autor");
            return;
        }


            Libro nuevo = new Libro(ISBN,fechaP, new Autor(nombre, apellido, nacionalidad, fechaN), paginas, disponible,  titulo);

             lista.add(nuevo);

             System.out.println("Libro Añadido");

              try (ObjectOutputStream ob = new ObjectOutputStream( new FileOutputStream("Fichero3/src/biblioteca.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
        }

    }

    public static void Buscar(Scanner sc, List<Libro> lista) {

        System.out.println("Escribe el nombre del autor el libro a buscar");
       String nombre = sc.nextLine();
     

       for (Libro V : lista) {
        if (V.getAutor().getNombre().toLowerCase().contains(nombre) || V.getAutor().getApellidos().toLowerCase().contains(nombre)) {
            System.out.println("Libros del autor");
            System.out.println(V);
              
        } 
           
       }

              try (ObjectOutputStream ob = new ObjectOutputStream( new FileOutputStream("Fichero3/src/biblioteca.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
        }
      
    }

    public static void Prestar(Scanner sc, List<Libro> lista) {

       System.out.println("Escribe el ISBN el libro a prestar");
       String ISBNv = sc.nextLine();
     

       for (Libro V : lista) {
        if (V.getISBN().equalsIgnoreCase(ISBNv)) {
            if (V.isDisponible()) {
                System.out.println("Libro prestado a usted");
                V.setDisponible(false) ;
                
            } else{
                System.out.println("Libro no disponible");
            }
            
        } 
           
       }
       
              try (ObjectOutputStream ob = new ObjectOutputStream( new FileOutputStream("Fichero3/src/biblioteca.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
        }

    }

    public static void Devovler(Scanner sc, List<Libro> lista) {

     
       System.out.println("Escribe el ISBN el libro a devolver");
       String ISBNv = sc.nextLine();
     

       for (Libro V : lista) {
        if (V.getISBN().equalsIgnoreCase(ISBNv)) {
            if (V.isDisponible() == false) {
                System.out.println("Libro devuelto");
                V.setDisponible(true) ;
                
            } else{
                System.out.println("Libro ya devuento");
            }
            
        } 
           
       }
       
              try (ObjectOutputStream ob = new ObjectOutputStream( new FileOutputStream("Fichero3/src/biblioteca.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
        }

    }

    public static void Listar( List<Libro> lista) {

        System.out.println("---LISTA---");

      for (Libro V : lista) {

            if (V.isDisponible()) {
                
              System.out.println(V);
                
            } 
                
       }
       

       
              try (ObjectOutputStream ob = new ObjectOutputStream( new FileOutputStream("Fichero3/src/biblioteca.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
        }
      
    }

    public static void Estadisticas( List<Libro> lista) {

        System.out.println("---ESTADISTICA---");
        int contadorT = 0 ;
        int contadorP = 0 ;
        int contadorD = 0 ;

      for (Libro V : lista) {

        contadorT++;
            if (V.isDisponible() == true) {
                
               contadorD++;
                
            } else{
               contadorP++;
                  
            }
            
           
       }

       System.out.println("Total :"+contadorT+ " Prestados: "+ contadorP+" Disponibles: "+contadorD);

      
              try (ObjectOutputStream ob = new ObjectOutputStream( new FileOutputStream("Fichero3/src/biblioteca.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
        }
    }
}
 

