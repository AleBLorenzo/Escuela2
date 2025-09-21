import java.util.ArrayList;
import java.util.Scanner;

public class App1 {

    public static void main(String[] args) throws Exception {

        int entero;
        String Dato;

        Scanner sc = new Scanner(System.in);

        ArrayList BD = new ArrayList<>();

        while (true) {

            System.out.println("--- GESTOR DE TAREAS ---");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Mostrar tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Buscar tarea");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción:");

            entero = sc.nextInt();
            Dato = sc.next();

            switch (entero) {

                case 1:

                    System.out.println("Descripción de la tarea: ");

                    System.out.print(Dato);
                    System.out.println("Tarea '" + Dato + "' añadida.");
                    BD.add(Dato);

                    break;

                case 2:
                    System.out.println("--- TAREAS PENDIENTES ---");

                    for (int i = 0; i < BD.size(); i++) {
                        System.out.println(BD.get(i));
                    }

                    break;
                case 3:
                    System.out.println("Número de tarea a completar: ");
                    int selec = sc.nextInt();

                    for (int i = 0; i < BD.size(); i++) {
                        if (selec == i) {
                            System.out.println("Tarea '" + BD.get(i) + "' completada y eliminada. ");
                            BD.remove(i);
                        } else {
                            System.out.println("opcion no encontrada");
                        }
                    }

                    break;
                case 4:
                    System.out.println("Palabra clave para buscar: ");
                    String palab = sc.next();
                    String palabS = palab.trim();

                    for (int i = 0; i < BD.size(); i++) {

                        if (BD.contains(palabS)) {
                            System.out.println(palabS);
                        } else {
                            System.out.println("no se encuentra");
                        }

                    }

                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    break;
            }

        }
    }
}
