
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== GENERADOR DE TABLAS ===\n" + //
                    "\n" + //
                    "1. Tabla simple\n" + //
                    "2. Tabla extendida\n" + //
                    "3. Rango personalizado\n" + //
                    "4. Tabla Pitágoras\n" + //
                    "5. Salir\n" + //
                    "\n");

            System.out.print("Elige opción: ");

            int opción = sc.nextInt();
            sc.nextLine();

            switch (opción) {

                case 1:

                    System.out.print("Número base: ");
                    int numeroBase = sc.nextInt();
                    sc.nextLine();

                    System.out.println("=== TABLA DEL " + numeroBase + " ===");

                    for (int i = 0; i < 11; i++) {
                        int resutado = numeroBase * i;
                        System.out.println(numeroBase + " x " + i + " = " + resutado);
                    }
                    break;

                case 2:

                    System.out.print("Número base: ");
                    int numeroBase1 = sc.nextInt();
                    sc.nextLine();

                    System.out.println("=== TABLA DEL " + numeroBase1 + " ===");

                    for (int i = 0; i < 21; i++) {
                        int resutado = numeroBase1 * i;
                        System.out.println(numeroBase1 + " x " + i + " = " + resutado);
                    }

                    break;

                case 3:

                    System.out.print("Número base: ");
                    int numeroBase2 = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Inicio: ");
                    int inicio = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Fin: ");
                    int fin = sc.nextInt();
                    sc.nextLine();

                    System.out.println("=== TABLA DEL " + numeroBase2 + " ===");

                    for (int i = inicio; i < (fin + 1); i++) {
                        int resutado = numeroBase2 * i;
                        System.out.println(numeroBase2 + " x " + i + " = " + resutado);
                    }
                    break;

                case 4:

                System.out.println("=== TABLA DE PITÁGORAS (10x10) ===");

                System.out.print("    |   1    2    3    4    5    6    7    8    9   10 \n" + //
                                        "----+-------------------------------------------------\n");
                for (int i = 1; i < 11; i++) {
                        
                        System.out.printf(" %2d |", i);
                    for (int j = 1; j < 11; j++) {

                        int resutado = i*j;
                    
                      System.out.printf(" %3d ", resutado);
                      
                    }
                    System.out.println("");
                }

                    break;

                case 5:
                    System.out.println("¡Hasta pronto, matemático!");
                    return;
                default:

                System.out.println("Introduce un caracter valido");
                break;

            }

        }

    }
}
