package Fichero3.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Eje4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion;
        double tempM;
        double tempN;
        int humedad;
        String dia;

        while (true) {
            System.out.println("----Menu----");
            System.out.println("1-Añadir registro");
            System.out.println("2-Ver todos los registros");
            System.out.println("3-Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    try (DataOutputStream dis = new DataOutputStream(new FileOutputStream("src/temp.txt", true))) {

                        System.out.println("Escriba el dia: ");
                        dia = sc.nextLine();
                        dis.writeUTF(dia);

                        System.out.println("Escriba la temperatura Maxima: ");
                        tempM = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Escriba la temperatura Minima: ");
                        tempN = sc.nextDouble();
                        sc.nextLine();

                        if (tempM > tempN) {
                            dis.writeDouble(tempM);
                            dis.writeDouble(tempN);

                        } else {
                            System.out.println("La temperatura maxima tiene q ser mayor q la minima");

                        }

                        System.out.println("Escriba la humedad: ");
                        humedad = sc.nextInt();
                        sc.nextLine();
                        if (humedad >= 0 && humedad <= 100) {
                            dis.writeInt(humedad);
                        } else {
                            System.out.println("la humedad tiene q estar entre 0 y 100");
                        }

                        dis.flush();

                    } catch (Exception e) {
                    }

                    break;
                case 2:
                    try (DataInputStream dic = new DataInputStream(new FileInputStream("src/temp.txt"))) {

                        while (dic.available() > 0) {

                            System.out.println("=== REGISTRO TEMPERATURAS ===");
                            System.out.println("Fecha: " + dic.readUTF());
                            System.out.println("Temp. Máx: " + dic.readDouble() + "°C | " + "Temp. Mín: "
                                    + dic.readDouble() + "°C");
                            System.out.println("Humedad: " + dic.readInt() + "%");
                            System.out.println("------------------------");
                        }

                    } catch (Exception e) {
                    }

                    break;
                case 3:
                    System.out.println("Adios");
                    sc.close();
                    return;
                default:
                    System.out.println("Opción no válida");
            }

        }

    }

}
