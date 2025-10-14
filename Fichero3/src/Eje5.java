package Fichero3.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.*;
import java.util.Scanner;

public class Eje5 {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int opcion;

        while (true) {
            System.out.println("----MENU----");
            System.out.println("1) Añadir producto ");
            System.out.println("2) Listar productos");
            System.out.println("3) Buscar por código");
            System.out.println("4)Modificar stock");
            System.out.println("5) Salir");
            System.out.println("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    añadirProducto(sc);

                    break;
                case 2:
                    listarProductos();
                    break;

                case 3:
                    buscarPorCodigo(sc);
                    break;
                case 4:
                    modificarStock(sc);
                    break;
                case 5:
                    System.out.println("Ha salido");
                    sc.close();
                    return;
                default:
                    throw new AssertionError();
            }

        }

    }

    public static void añadirProducto(Scanner sc) {

        try (DataOutputStream si = new DataOutputStream(new FileOutputStream("src/BD.txt", true));
                DataInputStream no = new DataInputStream(new FileInputStream("src/BD.txt"))) {

            int codigo = 0;
            String nombre = null;
            float precio = 0.0f;
            int stock = 0;
            boolean dispo = false;

            System.out.println("Codigo: ");
            codigo = Integer.parseInt(sc.nextLine());

            System.out.println("Nombre: ");

            nombre = sc.nextLine();

            System.out.println("Precio: ");
            precio = Float.parseFloat(sc.nextLine());

            System.out.println("Stock: ");
            stock = Integer.parseInt(sc.nextLine());

            System.out.println("Disponibilidad (true/false): ");
            dispo = sc.nextBoolean();
            sc.nextLine();

            boolean codigoExiste = false;
            while (no.available() > 0) {
                int codTemp = no.readInt();
                no.readUTF();
                no.readFloat();
                no.readInt();
                no.readBoolean();
                if (codTemp == codigo) {
                    codigoExiste = true;
                    break;
                }
            }

            if (codigoExiste) {
                System.out.println("El código ya existe.");
                return;
            }

            if (precio > 0 && stock >= 0) {

                si.writeInt(codigo);

                if (!nombre.isEmpty()) {
                    si.writeUTF(nombre);
                } else {
                    System.out.println("El nombre no puede estar vacio");
                    return;
                }

                si.writeFloat(precio);
                si.writeInt(stock);
                si.writeBoolean(dispo);

                si.flush();

            } else {
                System.out.println("El precio tiene q ser mayor q 0 y el stock mayor o iugal");
            }

        } catch (Exception e) {
           System.out.println("Error: "+ e);
        }

    }

    public static void listarProductos() {

        try (DataInputStream no = new DataInputStream(new FileInputStream("src/BD.txt"))) {
            System.out.println("COD |      NOMBRE           |  PRECIO | STOCK | DISPONIBLE");
            System.out.println("----|-----------------------|---------|------|------------");

            while (no.available() > 0) {

                int codigoN = no.readInt();
                String nombreN = no.readUTF();
                float precioN = no.readFloat();
                int stockN = no.readInt();
                boolean dispoN = no.readBoolean();

                System.out.println(codigoN + "   |         " + nombreN + "         |    " + precioN
                        + "    |    " + stockN + "   |    " + dispoN);

            }

        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }

    }

    public static void buscarPorCodigo(Scanner sc) {

        try (DataInputStream no = new DataInputStream(new FileInputStream("src/BD.txt"))) {

            int codigoS;

            System.out.println("Escribe el codigo a buscar");

            codigoS = sc.nextInt();
            int codigoN = 0;
            String nombreN = null;
            float precioN = 0.0f;
            int stockN = 0;
            boolean dispoN = false;

            while (no.available() > 0) {

                codigoN = no.readInt();
                nombreN = no.readUTF();
                precioN = no.readFloat();
                stockN = no.readInt();
                dispoN = no.readBoolean();

            }
            if (codigoN == codigoS) {
                System.out.println("COD |      NOMBRE           |  PRECIO | STOCK | DISPONIBLE");
                System.out.println("----|-----------------------|---------|------|------------");
                System.out.printf("%-4d | %-20s | %7.2f | %5d | %-10b%n", codigoN, nombreN, precioN, stockN,
                        dispoN);
            } else {

                System.out.println("Codigo no encontrado");
            }

        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }
    }

    public static void modificarStock(Scanner sc) {

        try (DataOutputStream temp = new DataOutputStream(new FileOutputStream("src/temp.txt"));
                DataInputStream no = new DataInputStream(new FileInputStream("src/BD.txt"))) {

            int codigoS;
            System.out.print("Introduce el código del producto a modificar: ");
            codigoS = sc.nextInt();

            int codigoN = 0;
            String nombreN = null;
            float precioN = 0.0f;
            int stockN = 0;
            boolean disponibleN = false;

            while (no.available() > 0) {

                codigoN = no.readInt();
                nombreN = no.readUTF();
                precioN = no.readFloat();
                stockN = no.readInt();
                disponibleN = no.readBoolean();

                if (codigoN == codigoS) {
                    System.out.printf("Stock actual:" + stockN + " Nuevo stock: ");
                    int nuevoStock = sc.nextInt();
                    sc.nextLine();
                    if (nuevoStock >= 0) {
                        stockN = nuevoStock;

                    } else {
                        System.out.println("Stock tiene q ser igual o mayor q 0");
                    }

                }
                temp.writeInt(codigoN);
                temp.writeUTF(nombreN);
                temp.writeFloat(precioN);
                temp.writeInt(stockN);
                temp.writeBoolean(disponibleN);
            }

        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }

        File original = new File("src/BD.txt");
        File nuevo = new File("src/temp.txt");

        if (original.delete()) {
            nuevo.renameTo(original);
            System.out.println("Stock actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar el archivo.");
        }

    }
}