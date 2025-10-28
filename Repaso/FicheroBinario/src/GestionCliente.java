
import java.io.EOFException;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GestionCliente {

    private static final String RUTA = "FicheroBinario/src/clientes.dat";

    static Scanner sc = new Scanner(System.in);

    final static int TAMNOMBRE = 40;
    final static int Registro = 4 + (TAMNOMBRE + 2) + 8 + 1;

    public static void main(String[] args) throws EOFException {

        while (true) {

            System.out.println("MENU");
            System.out.println("1-Añadir Cliente");
            System.out.println("2-Consultar Cliente por Posicion");
            System.out.println("3-Modificar Saldo del Cliente");
            System.out.println("4-Listar Clientes");
            System.out.println("5-Salir");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    Añadir(RUTA, sc);
                    break;

                case 2:
                    Consultar(RUTA, sc);
                    break;

                case 3:
                    Modificar(RUTA, sc);
                    break;

                case 4:
                    Listar(RUTA);
                    break;

                case 5:
                    sc.close();
                    return;

                default:
                    throw new AssertionError();
            }
        }

    }

    private static void Añadir(String RUTA, Scanner sc) {

        System.out.println("Escriba los datos para añadir los clientes\n");
        System.out.println("ID: ");
        int idN = sc.nextInt();
        sc.nextLine();
        boolean existe = ComprobarID(RUTA, sc, idN);

        if (!existe) {

            System.out.println("Nombre Completo: ");
            String nombreN = sc.nextLine();

            System.out.println("Saldo (Con coma): ");
            double saldoN = sc.nextDouble();
            sc.nextLine();

            System.out.println("¿Es VIP?: ");
            boolean clienteVIPN = sc.nextBoolean();
            sc.nextLine();

            try (RandomAccessFile wr = new RandomAccessFile(new File(RUTA), "rw")) {

                wr.seek(wr.length());
                wr.writeInt(idN);
                wr.writeUTF(nombreN);
                wr.writeDouble(saldoN);
                wr.writeBoolean(clienteVIPN);

            } catch (Exception e) {
                System.out.println("ERROR: " + e);
            }

        }

    }

    private static void Listar(String RUTA) {

        System.out.println("CONSULTA");

        try (RandomAccessFile rd = new RandomAccessFile(new File(RUTA), "r")) {

            for (int i = 0; i < rd.length(); i++) {

                System.out.println("\nCLIENTE\n");
                System.out.println(rd.readInt());
                System.out.println(rd.readUTF());
                System.out.println(rd.readDouble());
                System.out.println(rd.readBoolean());

            }

        } catch (Exception e) {
            System.out.println("Fin de la Lista");
        }

    }

    private static void Consultar(String RUTA, Scanner sc) {

        int id = 0;
        String nombre = null;
        double saldo = 0;
        boolean Vip = false;

        System.out.println("Escriba la posicion q quiere consultar ( > 0 )");
        int posicion = sc.nextInt();
        sc.nextLine();

        int registro = 1;

        try (RandomAccessFile rd = new RandomAccessFile(new File(RUTA), "r")) {

            System.out.println("\nCLIENTE (" + posicion + ")" + "\n");

            while (rd.getFilePointer() < rd.length()) {

                id = rd.readInt();
                nombre = rd.readUTF();
                saldo = rd.readDouble();
                Vip = rd.readBoolean();

                if (registro == posicion) {

                    System.out.println(id);
                    System.out.println(nombre);
                    System.out.println(saldo);
                    System.out.println(Vip);
                    System.out.println("");

                }

                registro++;

            }

        } catch (Exception e) {
            System.out.println("Fin de la Lista");
        }

    }

    private static void Modificar(String RUTA, Scanner sc) {

        int id = 0;
        String nombre = null;
        double saldo = 0;
        boolean Vip = false;

        System.out.println("Escriba la posicion q quiere Modificar ( > 0 )");
        int posicion = sc.nextInt();
        sc.nextLine();

        System.out.println("Escriba el nuevo saldo (con coma)");
        double saldoN = sc.nextDouble();
        sc.nextLine();

        int registro = 1;

        try (RandomAccessFile rd = new RandomAccessFile(new File(RUTA), "rw")) {

            System.out.println("\nCLIENTE (" + posicion + ")" + "\n");

            while (rd.getFilePointer() < rd.length()) {

                long inicioRegistro = rd.getFilePointer();

                id = rd.readInt();
                nombre = rd.readUTF();
                saldo = rd.readDouble();
                Vip = rd.readBoolean();

                if (registro == posicion) {

                    rd.seek(inicioRegistro + 4 + (2 + nombre.getBytes("UTF-8").length));

                    rd.writeDouble(saldoN);

                    System.out.println("Saldo Actualizado");
                    break;

                }

                registro++;

            }

        } catch (Exception e) {
            System.out.println("Fin de la Lista");
        }

    }

    private static boolean ComprobarID(String RUTA, Scanner sc, int idN) {

        try (RandomAccessFile rd = new RandomAccessFile(new File(RUTA), "r")) {

            rd.seek(0);

            while (rd.getFilePointer() < rd.length()) {

                int id = rd.readInt();
                String nombre = rd.readUTF();
                double saldo = rd.readDouble();
                boolean vip = rd.readBoolean();

                if (id == idN) {

                    return true;
                }

            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;

    }

}
