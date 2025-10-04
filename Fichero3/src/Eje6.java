package Fichero3.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Eje6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcion;
        String NIE = null;
        String nombre = null;
        float notaP = 0;
        float notaB = 0;
        float notaS = 0;
        float media = 0;
        String[] s;

        while (true) {

            System.out.println("----MENU----");
            System.out.println("1- Introducir calificaciones de nuevos estudiantes");
            System.out.println("2- Mostrar listado completo con medias individuales");
            System.out.println("3- Calcular estadísticas del grupo ");
            System.out.println("4- Buscar estudiante por NIE");
            System.out.println("5- Contar aprobados y suspensos por asignatura");
            System.out.println("6- Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:

                    try (DataOutputStream out = new DataOutputStream(new FileOutputStream("datos.txt", true))) {
                        System.out.println("NIE: ");
                        NIE = sc.nextLine();

                        System.out.println("Nombre Completo: ");
                        nombre = sc.nextLine();

                        System.out.println("Nota Programacion: ");
                        notaP = sc.nextFloat();
                        sc.nextLine();

                        System.out.println("Nota Base de Datos: ");
                        notaB = sc.nextFloat();
                        sc.nextLine();

                        System.out.println("Nota Sistemas: ");
                        notaS = sc.nextFloat();
                        sc.nextLine();

                        if (notaP < 0 || notaP > 10 && notaB < 0 || notaB > 10 && notaS < 0
                                || notaS > 10 && NIE.matches("\\d{8}[A-Z]")) {

                            out.writeUTF(NIE);
                            out.writeUTF(nombre);
                            out.writeFloat(notaP);
                            out.writeFloat(notaB);
                            out.writeFloat(notaS);

                            out.flush();

                        } else {
                            System.out.println("Nota o NIE introducidos mal");
                        }

                    } catch (Exception e) {
                    }

                    break;

                case 2:

                    try (DataInputStream in = new DataInputStream(new FileInputStream("datos.txt"))) {

                        while (in.available() > 0) {

                            NIE = in.readUTF();
                            nombre = in.readUTF();
                            notaP = in.readFloat();
                            notaB = in.readFloat();
                            notaS = in.readFloat();

                            media = (notaP + notaB + notaS) / 3;

                            System.out.println("Datos");
                            System.out.println("NIE: " + NIE);
                            System.out.println("Nombre: " + nombre);
                            System.out.println("Nota Programacion: " + notaP);
                            System.out.println("Nota Base de Datos: " + notaB);
                            System.out.println("Nota Sitemas: " + notaS);
                            System.out.println("Nota media: " + media);
                        }

                    } catch (Exception e) {
                    }

                    break;

                case 3:
                    try (DataInputStream in = new DataInputStream(new FileInputStream("datos.txt"))) {
                        float mediageneral = 0;
                        float notaMax = -1;
                        float notaMin = 11;
                        int catidad = 0;

                        while (in.available() > 0) {

                            NIE = in.readUTF();
                            nombre = in.readUTF();
                            notaP = in.readFloat();
                            notaB = in.readFloat();
                            notaS = in.readFloat();

                            media = (notaP + notaB + notaS) / 3;

                            catidad++;
                            media++;

                            mediageneral = media / catidad;

                            if (media > notaMax)
                                notaMax = media;
                            if (media < notaMin)
                                notaMin = media;

                            if (catidad > 0) {
                                System.out.printf(" Media general del grupo: " + mediageneral);
                                System.out.printf(" Nota más alta: " + notaMax);
                                System.out.printf(" Nota más baja: " + notaMin);
                            } else {
                                System.out.println(" No hay estudiantes registrados.");
                            }
                        }

                    } catch (Exception e) {
                    }

                    break;

                case 4:

                    try (DataInputStream in = new DataInputStream(new FileInputStream("datos.txt"))) {
                        System.out.print("Introduce NIE a buscar: ");
                        String buscar = sc.nextLine();

                        while (in.available() > 0) {

                            NIE = in.readUTF();
                            nombre = in.readUTF();
                            notaP = in.readFloat();
                            notaB = in.readFloat();
                            notaS = in.readFloat();

                            if (NIE.equalsIgnoreCase(buscar)) {

                                System.out.println("Datos");
                                System.out.println("NIE: " + NIE);
                                System.out.println("Nombre: " + nombre);
                                System.out.println("Nota Programacion: " + notaP);
                                System.out.println("Nota Base de Datos: " + notaB);
                                System.out.println("Nota Sitemas: " + notaS);
                                break;

                            }
                        }

                    } catch (Exception e) {
                    }

                    break;

                case 5:

                    try (DataInputStream in = new DataInputStream(new FileInputStream("datos.txt"))) {

                        int aprobP = 0;
                        int suspP = 0;
                        int aprobB = 0;
                        int suspB = 0;
                        int aprobS = 0;
                        int suspS = 0;
                        while (in.available() > 0) {

                            in.readUTF();
                            in.readUTF();
                            notaP = in.readFloat();
                            notaB = in.readFloat();
                            notaS = in.readFloat();

                            if (notaP >= 5)
                                aprobP++;
                            else
                                suspP++;
                            if (notaB >= 5)
                                aprobB++;
                            else
                                suspB++;
                            if (notaS >= 5)
                                aprobS++;
                            else
                                suspS++;
                        }
                        System.out.println(" Programación: Aprobados = " + aprobP + " Suspensos = " + suspP);
                        System.out.println(" Bases de Datos: Aprobados = " + aprobB + " Suspensos = " + suspB);
                        System.out.println(" Sistemas: Aprobados = " + aprobS + " Suspensos = " + suspS);

                    } catch (Exception e) {
                    }

                    break;

                case 6:
                    System.out.println("Adios");
                    sc.close();
                    return;

                default:
                    throw new AssertionError();
            }
        }

    }
}
