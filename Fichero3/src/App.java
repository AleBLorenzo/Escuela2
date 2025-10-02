package Fichero3.src;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int opcion;
        int codigoS;
        int codigo = 0;
        String nombre = null;
        Double precio = 0.0;
        int stock = 0;
        boolean dispo = false;

        try (DataOutputStream si = new DataOutputStream(new FileOutputStream("src/BD.txt", true));
                DataInputStream no = new DataInputStream(new FileInputStream("src/BD.txt"))) {

            while (true) {
                System.out.println("1) Añadir producto ");
                System.out.println("2) Listar productos");
                System.out.println("3) Buscar por código");
                System.out.println("4)Modificar stock");
                System.out.println("5) Salir");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:

                        System.out.println("Codigo: ");
                        codigo = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Nombre: ");
                        nombre = sc.nextLine();

                        System.out.println("Precio: ");
                        precio = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Stock: ");
                        stock = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Sisponibilidad (true/false): ");
                        dispo = sc.nextBoolean();
                        sc.nextLine();

                        if (precio > 0 && stock >= 0) {
                            si.writeInt(codigo);
                            si.writeUTF(nombre);
                            si.writeDouble(precio);
                            si.writeInt(stock);
                            si.writeBoolean(dispo);

                            si.flush();

                        }

                        break;
                    case 2:

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

                        break;
                    case 3:

                        System.out.println("Escribe el codigo a buscar");

                        codigoS = sc.nextInt();

                        while (no.available() > 0) {

                            int codigoN = no.readInt();
                            String nombreN = no.readUTF();
                            float precioN = no.readFloat();
                            int stockN = no.readInt();
                            boolean dispoN = no.readBoolean();

                            if (codigoN == codigoS) {
                                System.out.println(codigoN + "   |         " + nombreN + "         |    " + precioN
                                        + "    |    " + stockN + "   |    " + dispoN);
                            } else {

                                System.out.println("Codigo no encontrado");
                            }

                        }
                        break;
                    case 4:
                        System.out.print("Introduce el código del producto a modificar: ");
                        codigoS = sc.nextInt();

                        while (no.available() > 0) {

                            int codigoN = no.readInt();
                            String nombreN = no.readUTF();
                            double precioN = no.readDouble();
                            int stockN = no.readInt();
                            boolean disponibleN = no.readBoolean();

                            if (codigoN == codigoS) {
                                System.out.printf("Stock actual:" + stockN + " Nuevo stock: ");
                                int nuevoStock = sc.nextInt();
                                stock = nuevoStock;

                                si.writeInt(codigo);
                                si.writeUTF(nombre);
                                si.writeDouble(precio);
                                si.writeInt(stock);
                                si.writeBoolean(dispo);
                            }

                        }

                        break;
                    case 5:
                        System.out.println("Ha salido");
                        sc.close();
                        return;
                    default:
                        throw new AssertionError();
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
