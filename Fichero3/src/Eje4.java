
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Eje4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion;
        float tempM;
        float tempN;
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

                        System.out.println("Escriba la fecha (DD/MM/AAAA): ");
                        dia = sc.nextLine();

                        System.out.println("Escriba la temperatura Maxima (ejemplo: 22,4): ");
                        tempM = sc.nextFloat();
                        sc.nextLine();
                        System.out.println("Escriba la temperatura Minima(ejemplo: 20,5): ");
                        tempN = sc.nextFloat();
                        sc.nextLine();

                        if (tempM > tempN) {
                        } else {
                            System.out.println("La temperatura maxima tiene q ser mayor q la minima");
                            break;

                        }

                        System.out.println("Escriba la humedad (0 <= humedad <= 100): ");
                        humedad = sc.nextInt();
                        sc.nextLine();
                        if (humedad >= 0 && humedad <= 100) {
                        } else {
                            System.out.println("la humedad tiene q estar entre 0 y 100");
                            break;
                        }
                        dis.writeUTF(dia);
                        dis.writeFloat(tempM);
                        dis.writeFloat(tempN);
                        dis.writeInt(humedad);

                        dis.flush();

                    } catch (Exception e) {
                         System.out.println(e);
                    }

                    break;
                case 2:
                    try (DataInputStream dic = new DataInputStream(new FileInputStream("src/temp.txt"))) {

                        while (dic.available() > 0) {

                            System.out.println("=== REGISTRO TEMPERATURAS ===");
                            System.out.println("Fecha: " + dic.readUTF());
                            System.out.println("Temp. Máx: " + dic.readFloat() + "°C | " + "Temp. Mín: "
                                    + dic.readFloat() + "°C");
                            System.out.println("Humedad: " + dic.readInt() + "%");
                            System.out.println("------------------------");
                        }

                    } catch (Exception e) {
                        System.out.println(e);
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
