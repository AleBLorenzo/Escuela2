package Fichero3.src;

import TareaPRG7.src.Producto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Eje10 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Vehiculo> lista = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Fichero3/src/confecionario.dat"))) {
               lista = (List<Vehiculo>) ois.readObject();
        
        } catch (Exception e) {
             System.out.println("Error: "+ e );
           
        }
        int opcion = 0;

        while (true) {
            System.out.println("-----MENU-----");
            System.out.println("1-Añadir vehículos a la colección");
            System.out.println("2-Cargar colección desde archivo");
            System.out.println("3-Buscar vehículos por marca");
            System.out.println("4-Filtrar por tipo de combustible");
            System.out.println("5-Mostrar vehículos ordenados por precio");
            System.out.println("6-salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    añadir(sc, lista);
                    break;
                case 2:
                    Lista(sc , lista);
                    break;
                case 3:
                    Buscar(sc, lista);
                    break;
                case 4:
                  TipoC(sc, lista);
                    break;
                case 5:
                   OrdenadoP(lista);
                    break;
                case 6:
                    sc.close();
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }

    public static void añadir(Scanner sc, List lista) {

      

            System.out.println("Escriba una Marca: ");
            String Marca = sc.nextLine();

            System.out.println("Escriba una Modelo: ");
            String Modelo = sc.nextLine();

            System.out.println("Escriba el Año (año > 1900): ");
            int Ano = sc.nextInt();
            

            if (Ano > 1900) {
            } else {
                System.out.println("Escriba un año mayor q 1900");
                return;
            }

            System.out.println("Escriba el Precio: ");
            Double Precio = sc.nextDouble();
            sc.nextLine();

            System.out.println("Escriba el Combustible (GASOLINA, DIESEL, HIBRIDO, ELECTRICO): ");
            String tipo = sc.nextLine().toUpperCase();

            Combustible combustible;
            combustible = Combustible.valueOf(tipo);

            Vehiculo nuevo = new Vehiculo(Ano, combustible, Marca, Modelo, Precio);

            lista.add(nuevo);

            System.out.println("Coche Añadido");

              try (ObjectOutputStream ob = new ObjectOutputStream( new FileOutputStream("Fichero3/src/confecionario.dat"))) {
            ob.writeObject(lista);

        } catch (Exception e) {
            System.out.println("Error: "+ e );
        }

    }

    public static void Lista(Scanner sc, List<Vehiculo> lista) {

           if (lista.isEmpty()) {
        System.out.println("No hay vehículos registrados.");
        return;
    }

             System.out.println("\n--- LISTA DE VEHÍCULOS ---");

            for (Vehiculo v : lista) {
            System.out.println(v);
        } 
       
      
    }

    public static void Buscar(Scanner sc, List<Vehiculo> lista) {

       System.out.println("Escribe una Marca a buscar");
       String Marca = sc.nextLine();

       for (Vehiculo V : lista) {
        if (V.getMarca().equalsIgnoreCase(Marca)) {
            System.out.println(V);
            
        } else {
            System.out.println("No se encontro");
        }
           
       }

    }

    public static void TipoC(Scanner sc, List<Vehiculo> lista) {

       System.out.println("Escribe el Tipo de combustible a buscar (GASOLINA, DIESEL, HIBRIDO, ELECTRICO) ");
       String tipo = sc.nextLine().toUpperCase();

       Combustible comb = Combustible.valueOf(tipo);

       for (Vehiculo V : lista) {
        if (V.getCombustible().equals(comb)) {
            System.out.println(V);
            
        } else {
            System.out.println("No se encontro carro con ese combustible");
        }
           
       }

    }

    public static void OrdenadoP( List<Vehiculo> lista) {

        lista.sort(Comparator.comparingDouble(Vehiculo::getPrecio));

        System.out.println("Comparados por precio: ");
        for (Vehiculo V : lista) {
            System.out.println(V);
            
        }

      
    }

 

}
