package Fichero3.src;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Eje9 {

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

        try (RandomAccessFile rs = new RandomAccessFile("Fichero3/src/empleados.dat", "rw")) {


          String[] departamentosValidos = {"Informatica", "Administracion", "Ventas", "Produccion"};


            System.out.println("Introduzca los datos: ");

            System.out.println("Escriba el nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Escriba el apellido: ");
            String apellido = sc.nextLine();


            System.out.println("Escriba el Departamento (Informatica, Administracion, Ventas, Produccion): ");
            String departamento = sc.nextLine();
            String departamentoV = null ;

            for (String dep : departamentosValidos) {
                if (dep.equalsIgnoreCase(departamento)) {
                      departamentoV  = dep;
                       
                } else if(departamentoV != null){
                    System.out.println("Departamento guardado");
                   
                } else {
                    System.out.println("No valido");
                    break;
                }
            }



            System.out.println("Escriba el salario: ");
            Double salario = sc.nextDouble();

            int ID = (int) (rs.length() / 82) + 1;

            rs.seek(rs.length());

            rs.writeInt(ID);
            rs.writeBytes(String.format("%-25.25s", nombre));
            rs.writeBytes(String.format("%-25.25s", apellido));
            rs.writeBytes(String.format("%-20.20s", departamentoV));
            rs.writeDouble(salario);

        } catch (Exception e) {
        }

    }

    public static void Buscar(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile("Fichero3/src/empleados.dat", "r")) {

            System.out.println("Introduce el id a Buscar : ");
            int idN = sc.nextInt();

            long salto = 82 * (idN - 1);

            rs.seek(salto);

            int id = rs.readInt();

            String nom = "";
            for (int i = 0; i < 25; i++) {
                nom += (char) rs.readByte();
                nom = nom.trim();
            }

            String ape = "";
            for (int i = 0; i < 25; i++) {
                ape += (char) rs.readByte();
                ape = ape.trim();
            }

            String dep = "";
            for (int i = 0; i < 20; i++) {
                dep += (char) rs.readByte();
                dep = dep.trim();
            }

            double sal = rs.readDouble();

            System.out.println("Empleado encontrado:");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nom);
            System.out.println("Apellido: " + ape);
            System.out.println("Departamento: " + dep);
            System.out.println("Salario actual: " + sal);

        } catch (Exception e) {
        }

    }

    public static void ModificarS(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile("Fichero3/src/empleados.dat", "rw")) {

            System.out.print("ID a modificar: ");
            int registro = sc.nextInt();
            sc.nextLine();

            int contador = 1;
            long posInicio = 0;

            while (rs.getFilePointer() < rs.length()) {
                posInicio = rs.getFilePointer();

                int id = rs.readInt();

                String nom = "";
                for (int i = 0; i < 25; i++) {
                    nom += (char) rs.readByte();
                    nom = nom.trim();
                }

                String ape = "";
                for (int i = 0; i < 25; i++) {
                    ape += (char) rs.readByte();
                    ape = ape.trim();
                }

                String dep = "";
                for (int i = 0; i < 20; i++) {
                    dep += (char) rs.readByte();
                    dep = dep.trim();
                }

                double sal = rs.readDouble();

                if (contador == registro) {

                    System.out.println("Empleado encontrado:");
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nom);
                    System.out.println("Apellido: " + ape);
                    System.out.println("Departamento: " + dep);
                    System.out.println("Salario actual: " + sal);

                    System.out.print("Nuevo Salario: ");
                    Double nuevo = sc.nextDouble();

                    rs.seek(posInicio + 4 + 25 + 25 + 20);
                    rs.writeDouble(nuevo);

                    System.out.println("Salario modificado correctamente");
                    return;
                }
                contador++;
            }

        } catch (Exception e) {
        }

    }

    public static void ListaD(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile("Fichero3/src/empleados.dat", "rw")) {
      

            
        System.out.print("Introduce el departamento a listar: ");
        String deptoB = sc.nextLine().trim();

        rs.seek(0);

        while (rs.getFilePointer() < rs.length()) { 

              int id = rs.readInt();

                String nom = "";
                for (int i = 0; i < 25; i++) {
                    nom += (char) rs.readByte();
                    nom = nom.trim();
                }

                String ape = "";
                for (int i = 0; i < 25; i++) {
                    ape += (char) rs.readByte();
                    ape = ape.trim();
                }

                String dep = "";
                for (int i = 0; i < 20; i++) {
                    dep += (char) rs.readByte();
                    dep = dep.trim();
                }

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
        }

    }

    public static void CalcularSalarioT(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile("Fichero3/src/empleados.dat", "rw")) {

            rs.seek(0);
          
            double total = 0 ;

            while (rs.getFilePointer() < rs.length()) { 
                 
                 int id = rs.readInt();

                String nom = "";
                for (int i = 0; i < 25; i++) {
                    nom += (char) rs.readByte();
                    nom = nom.trim();
                }

                String ape = "";
                for (int i = 0; i < 25; i++) {
                    ape += (char) rs.readByte();
                    ape = ape.trim();
                }

                String dep = "";
                for (int i = 0; i < 20; i++) {
                    dep += (char) rs.readByte();
                    dep = dep.trim();
                }

                double sal = rs.readDouble();


                total += sal;
                
            }

             System.out.println("La masa salarial total es: " + total + " euros");

        } catch (Exception e) {
        }
    }

    public static void SalarioMN(Scanner sc) {

        try (RandomAccessFile rs = new RandomAccessFile("Fichero3/src/empleados.dat", "rw")) {

             rs.seek(0);

            double salMax = Double.MAX_VALUE;
            double salMin = Double.MIN_VALUE;

            int idMax = 0;
            int idMin = 0;
            String nomMax = "";
            String nomMin = "";
            String apeMax = "";
            String apeMin = "";
            String depMax = "";
            String depMin = "";



            while (rs.getFilePointer() < rs.length()) { 
                 
                 int id = rs.readInt();

                String nom = "";
                for (int i = 0; i < 25; i++) {
                    nom += (char) rs.readByte();
                    nom = nom.trim();
                }

                String ape = "";
                for (int i = 0; i < 25; i++) {
                    ape += (char) rs.readByte();
                    ape = ape.trim();
                }

                String dep = "";
                for (int i = 0; i < 20; i++) {
                    dep += (char) rs.readByte();
                    dep = dep.trim();
                }

                double sal = rs.readDouble();


                if(sal < salMax){
                       salMax = sal;
                       idMax = id;
                       nomMax = nom;
                       apeMax = ape;
                       depMax = dep;
                }
                
                 if(sal > salMax){
                       salMin = sal;
                       idMin = id;
                       nomMin = nom;
                       apeMin = ape;
                       depMin = dep;
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
        }
    }
}
