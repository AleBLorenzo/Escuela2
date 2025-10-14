package Fichero3.src;

import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.swing.text.Position;

public class Eje9 {

     private static final String ruta = "Fichero3/src/empleados.dat";
    private static final int tam = 82;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (true) {
            System.out.println("-----MENU-----");
            System.out.println("1-Dar de alta empleado");
            System.out.println("2-Buscar empleado por ID");
            System.out.println("3-Modificar salario de un empleado");
            System.out.println("4-Listar empleados por departamento");
            System.out.println("5-Calcular masa salarial total");
            System.out.println("6-Encontrar empleado con mayor/menor salario");
            System.out.println("7-salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    añadir(sc);
                    break;
                case 2:
                    Buscar(sc);
                    break;
                case 3:
                    ModificarS(sc);
                    break;
                case 4:
                    ListaD(sc);
                    break;
                case 5:
                    CalcularSalarioT(sc);
                    break;
                case 6:
                    SalarioMN(sc);
                    break;
                case 7:
                    sc.close();
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }

    public static void añadir(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "rw")) {

            System.out.println("Introduzca los datos: ");

            System.out.println("Escriba el nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Escriba el apellido: ");
            String apellido = sc.nextLine();


            System.out.println("Escriba el Departamento (Informatica, Administracion, Ventas, Produccion): ");
            String departamento = sc.nextLine();
            String departamentoV = null ;

            if (!validarDepartamento(departamento)) {
                System.out.println("Departamento no válido.");
                return;
            }

            System.out.println("Escriba el salario: ");
            Double salario = sc.nextDouble();

            int ID = obtenerSiguienteId(rs);

            rs.seek(rs.length());

            rs.writeInt(ID);
            rs.writeBytes(String.format("%-25.25s", nombre));
            rs.writeBytes(String.format("%-25.25s", apellido));
            rs.writeBytes(String.format("%-20.20s", departamentoV));
            rs.writeDouble(salario);

              System.out.println("Empleado añadido correctamente con ID: " + ID);

        } catch (Exception e) {
             System.out.println("Error al añadir empleado: " + e.getMessage());
        }

    }

    public static void Buscar(Scanner sc) {

   
            System.out.println("Introduce el id a Buscar : ");
            int idN = sc.nextInt();

                 try (RandomAccessFile rs = new RandomAccessFile(ruta, "r")) {

            rs.seek(0);

            while (rs.getFilePointer() < rs.length()) {
                int id = rs.readInt();
                String nom = leerCadena(rs, 25);
                String ape = leerCadena(rs, 25);
                String dep = leerCadena(rs, 20);
                double sal = rs.readDouble();

                if (id == idN) {

            System.out.println("Empleado encontrado:");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nom);
            System.out.println("Apellido: " + ape);
            System.out.println("Departamento: " + dep);
            System.out.println("Salario actual: " + sal);
                    break;    
        } else{
            System.out.println("Empleado no encontrado");
        }
    }
        } catch (Exception e) {
             System.err.println("Error al buscar: " + e.getMessage());
        }

    }

    public static void ModificarS(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "rw")) {

            System.out.print("ID a modificar: ");
            int registro = sc.nextInt();
            sc.nextLine();

          
            long posInicio = buscarEmpleadoPorId(rs, registro);

            if (posInicio == -1) {
                System.out.println("❌ Empleado no encontrado.");
                return;
            }

            rs.seek(posInicio + 4 + 25 + 25 + 20);
            double salarioActual = rs.readDouble();

            System.out.println("Salario actual: " + salarioActual);
            System.out.print("Nuevo salario: ");
            double nuevo = sc.nextDouble();

            rs.seek(posInicio + 4 + 25 + 25 + 20);
            rs.writeDouble(nuevo);
            System.out.println("Salario modificado correctamente.");

        } catch (Exception e) {
               System.err.println("Error al modificar: " + e.getMessage());
        }

    }

    public static void ListaD(Scanner sc) {

           System.out.print("Introduce el departamento: ");
        String deptoB = sc.nextLine().trim();

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "r")) {
         

            while (rs.getFilePointer() < rs.length()) {

                int id = rs.readInt();
                String nom = leerCadena(rs, 25);
                String ape = leerCadena(rs, 25);
                String dep = leerCadena(rs, 20);
                double sal = rs.readDouble();

                if (dep.equalsIgnoreCase(deptoB)) {

                      System.out.println("Empleado encontrado:");
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nom);
                    System.out.println("Apellido: " + ape);
                    System.out.println("Departamento: " + dep);
                    System.out.println("Salario actual: " + sal);
                       
                } else {
                    System.out.println("Empleado no encotrado en ese departamento ");
                }
        }

           

        } catch (Exception e) {

            System.err.println("Error al listar: " + e.getMessage());
        }

    }

    public static void CalcularSalarioT(Scanner sc) {
 try (RandomAccessFile rs = new RandomAccessFile(ruta, "r")) {
            double total = 0;

            while (rs.getFilePointer() < rs.length()) {
                rs.readInt();
                rs.skipBytes(25 + 25 + 20);
                total += rs.readDouble();
            }

            System.out.println("Masa salarial total: " + total + " euros");

        } catch (Exception e) {
            System.err.println("Error al calcular masa salarial: " + e.getMessage());
        }
    }

    public static void SalarioMN(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile(ruta, "r")) {
            double salMax = Double.MIN_VALUE;
            double salMin = Double.MAX_VALUE;

            String nomMax = "", apeMax = "", depMax = "";
            String nomMin = "", apeMin = "", depMin = "";
            int idMax = 0, idMin = 0;

            while (rs.getFilePointer() < rs.length()) {
                int id = rs.readInt();
                String nom = leerCadena(rs, 25);
                String ape = leerCadena(rs, 25);
                String dep = leerCadena(rs, 20);
                double sal = rs.readDouble();

                if (sal > salMax) {
                    salMax = sal; idMax = id;
                    nomMax = nom; apeMax = ape; depMax = dep;
                }
                if (sal < salMin) {
                    salMin = sal; idMin = id;
                    nomMin = nom; apeMin = ape; depMin = dep;
                }
            }

 
        System.out.println("\nEmpleado con Menor salario:");
        System.out.println("ID: " + idMax);
        System.out.println("Nombre: " + nomMax);
        System.out.println("Apellido: " + apeMax);
        System.out.println("Departamento: " + depMax);
        System.out.println("Salario: " + salMax + " euros");

        System.out.println("\nEmpleado con Mayor salario:");
        System.out.println("ID: " + idMin);
        System.out.println("Nombre: " + nomMin);
        System.out.println("Apellido: " + apeMin);
        System.out.println("Departamento: " + depMin);
        System.out.println("Salario: " + salMin + " euros");
            
        } catch (Exception e) {
              System.err.println("Error al calcular salarios extremos: " + e.getMessage());
        }
    }

      private static boolean validarDepartamento(String dept) {
        String[] validos = { "Informatica", "Administracion", "Ventas", "Produccion" };
        for (String d : validos) {
            if (d.equalsIgnoreCase(dept.trim())) return true;
        }
        return false;
    }

       private static int obtenerSiguienteId(RandomAccessFile rs) throws Exception {
        long tam1 = rs.length();
        return (int) (tam1 / tam) + 1;
    }

     private static long buscarEmpleadoPorId(RandomAccessFile rs, int idBuscado) throws Exception {
        rs.seek(0);
        while (rs.getFilePointer() < rs.length()) {
            long pos = rs.getFilePointer();
            int id = rs.readInt();
            rs.skipBytes(25 + 25 + 20 + 8);
            if (id == idBuscado) return pos;
        }
        return -1;
    }

    public static String leerCadena(RandomAccessFile rs, int longitud) throws Exception {
        byte[] buffer = new byte[longitud];
        rs.readFully(buffer);
        return new String(buffer).trim();
    }

}
