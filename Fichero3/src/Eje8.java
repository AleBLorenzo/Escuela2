package Fichero3.src;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Eje8 {

     public static final String ruta = "Fichero3/src/butacas.dat";

    public static void main(String[] args) {

       

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "rw")) {
            if (rs.length() == 0) {

                char[] filas = { 'A', 'B', 'C', 'D', 'E' };
                double precioBase = 10.0;

                for (char f : filas) {
                     double precio = precioBase;
                    for (int i = 1; i <= 10; i++) {
                        rs.writeInt(i);
                        rs.writeChar(f);
                        rs.writeBoolean(false);
                        rs.writeDouble(precio);
                        precio += 0.5;
                    }
                }

            }
        } catch (Exception e) {
             System.out.println("Error al inicializar butacas: " + e.getMessage());
        }

        while (true) {
            System.out.println("-----MENU-----");
            System.out.println("1-Mostrar mapa de butacas (X=ocupada, O=libre)");
            System.out.println("2-Reservar butaca específica");
            System.out.println("3-Cancelar reserva");
            System.out.println("4-Consultar precio de una butaca");
            System.out.println("5-Estadísticas: butacas libres, ocupadas, recaudación total");
            System.out.println("6-salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Mapa(sc);
                    break;
                case 2:
                    Reserva(sc);
                    break;
                case 3:
                    Cancelar(sc);
                    break;
                case 4:
                    Precio(sc);
                    break;
                case 5:
                    Estadistica(sc);
                    break;
                case 6:
                      sc.close();
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }

    public static void Mapa(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "r")) {

            System.out.println("  1 2 3 4 5 6 7 8 9 10");
            for (char fila = 'A'; fila <= 'E'; fila++) {

                System.out.print(fila + ": ");

                for (int i = 1; i <= 10; i++) {

                    int num = rs.readInt();
                    char filas = rs.readChar();
                    boolean ocupada = rs.readBoolean();
                    rs.readDouble();

                    System.out.print(ocupada ? "X " : "O ");
                }
                System.out.println();
            }

        } catch (Exception e) {
             System.out.println("Error al inicializar butacas: " + e.getMessage());
        }

    }

    public static void Reserva(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "rw")) {

            System.out.print("Fila (A-E): ");
            char fila = sc.next().toUpperCase().charAt(0);
            System.out.print("Numero (1-10): ");
            int numero = sc.nextInt();

            int index = (fila - 'A') * 10 + (numero - 1);
            long pos = index * 15 ;
            rs.seek(pos);
              rs.readInt(); 
            rs.readChar(); 
            boolean ocupada = rs.readBoolean();

            if (ocupada) {
                System.out.println("La butaca ya esta ocupada");
            } else {
                rs.seek(pos+6);
                rs.writeBoolean(true);
                System.out.println("Butaca reservada correctamente");
            }

        } catch (Exception e) {
             System.out.println("Error al reservar butaca: " + e.getMessage());
        }

    }

    public static void Cancelar(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "rw")) {
            System.out.print("Fila (A-E): ");

             char fila = sc.next().toUpperCase().charAt(0);

            System.out.print("Numero (1-10): ");
            int numero = sc.nextInt();

            int index = (fila - 'A') * 10 + (numero - 1);
            long pos = index * 15;
            rs.seek(pos);
            boolean ocupada = rs.readBoolean();

            if (ocupada) {
                rs.seek(pos + 6);
                rs.writeBoolean(false);
                System.out.println("Reserva cancelada");

            } else {

                System.out.println("La butaca ya esta libre");
            }

        } catch (Exception e) {
            System.out.println("Error al cancelar reserva: " + e.getMessage());
        }

    }

    public static void Precio(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "rw")) {

            System.out.print("Fila (A-E): ");
            char fila = sc.next().charAt(0);
            System.out.print("Numero (1-10): ");
            int numero = sc.nextInt();

            int index = (fila - 'A') * 10 + (numero - 1);
            long pos = index * 15 ;
            rs.seek(pos);
            rs.readInt();
            rs.readChar();
            rs.readBoolean();
            double precio = rs.readDouble();

            System.out.println("Precio de la butaca: " + precio + " euros");

        } catch (Exception e) {
            System.out.println("Error al consultar precio: " + e.getMessage());
        }

    }

    public static void Estadistica(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "rw")) {

            rs.seek(0);
            int libres = 0;
            int ocupadas = 0;
            double recaudacion = 0;

            for (int i = 0; i < 50; i++) {
                rs.readInt();
                rs.readChar();
                boolean ocupado = rs.readBoolean();
                double precio = rs.readDouble();

                if (ocupado) {
                    ocupadas++;
                    recaudacion += precio;
                } else {
                    libres++;
                }
            }

            System.out.println("Butacas libres: " + libres);
            System.out.println("Butacas ocupadas: " + ocupadas);
            System.out.println("Recaudacion total: " + recaudacion + " euros");
            
        } catch (Exception e) {
             System.out.println("Error al calcular estadísticas: " + e.getMessage());
        }
    }
}
