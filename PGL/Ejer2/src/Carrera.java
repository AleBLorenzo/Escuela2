
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Carrera {

    static Scanner sc = new Scanner(System.in);

    static int numeroC;
    static PuestoAvituallamiento puesto;
    static List<Corredor> listaCoredores;
    static ExecutorService corredores;
    static int capacidad;
    static String nombreC;
    public static int metros;
    static List<Future<Long>> resultados;
    public BlockingQueue<String> compartida = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // --- Preparación de objetos compartidos ---

        JuezSalida Juez = new JuezSalida();

        // TAREA 1.2: Crea los corredores y los hilos. Asegúrate de pasar
        // la instancia del Podio a cada corredor.

        // --- INICIA TU CÓDIGO AQUÍ (Creación de corredores y hilos) ---

        int opcion;

        while (true) {

            System.out.println("Menu Configuracion\n"
                    + "1- Añadir Datos\n"
                    + "2- Mostrar datos Añadidos\n"
                    + "3- Iniciar Carrera\n"
                    + "4- Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:

                    ConfigurarCoredores();
                    DistanciaCarrera();
                    Capacidad();
                    NombreCorredores(puesto, Juez);

                    break;

                case 2:

                    Estadisticas();

                    break;

                case 3:

                    InicioCarrera(Juez);

                    break;
                case 4:
                    System.out.println("Saliendo");
                    sc.close();
                    return;

                default:
                    throw new AssertionError();

            }

        }

    }

    private static void ConfigurarCoredores() {
        System.out.println("Cuantos corredores quiere en la Carrera?");
        numeroC = sc.nextInt();
        sc.nextLine();
        corredores = Executors.newFixedThreadPool(numeroC);
        System.out.println("La Carrera tendra " + numeroC + "Corredores");

    }

    private static void NombreCorredores(PuestoAvituallamiento puesto, JuezSalida Juez) {

        int distanciaTotal = Corredor.distanciaTotal;
        resultados = new ArrayList<>();
        listaCoredores = new ArrayList<>();

        for (int i = 0; i < numeroC; i++) {
            System.out.println("Introduzca el nombre del Corredor (Tiene" + numeroC + "Vacantes)");

            nombreC = sc.nextLine();

            Corredor Corredor = new Corredor(nombreC, distanciaTotal, puesto, Juez);
            listaCoredores.add(Corredor);

            resultados.add(corredores.submit(Corredor));
        }

    }

    private static void DistanciaCarrera() {
        System.out.println("Que distancia tendra la Carrera?");

        Corredor.distanciaTotal = sc.nextInt();
        sc.nextLine();

    }

    private static void Estadisticas() {
        System.out.println("Esptadisticas");
        System.out.println("Numero Corredores " + numeroC);
        System.out.println("Distancia Carrera " + metros);
        System.out.println("Capacidad des Puesto Avituallamiento " + numeroC);
        System.out.println("Nombre " + corredores.toString());
    }

    private static void Capacidad() {
        System.out.println("Que Capacidad quieres q tenga el Puesto de Avituallamiento");

        int CapacidadP = sc.nextInt();
        sc.nextLine();
        capacidad = CapacidadP;
        puesto = new PuestoAvituallamiento();
        puesto.setCapacidadP(capacidad);
    }

    private static void InicioCarrera(JuezSalida Juez) throws InterruptedException, ExecutionException {
        System.out.println("¡Suena el pistoletazo de salida!");

        Corredor ganador = null;
        Long tiempo = null;
        // TAREA 1.3: Inicia todos los hilos.
        // --- INICIA TU CÓDIGO AQUÍ ---

        System.out.println("¡PREPARADOS, LISTOS, YA!");

        Thread.sleep(2000);
        Juez.darSalida();

        // TAREA 2.1: Espera a que todos los hilos terminen usando join().
        // --- INICIA TU CÓDIGO AQUÍ ---

        try {
            corredores.shutdown();
        } catch (Exception e) {
            corredores.awaitTermination(500, TimeUnit.MILLISECONDS);

        }

        long mejorTiempo = Long.MAX_VALUE;

        for (int i = 0; i < resultados.size(); i++) {

            tiempo = resultados.get(i).get();
            Corredor corredorActual = listaCoredores.get(i);

            if (tiempo < mejorTiempo) {
                mejorTiempo = tiempo;
                ganador = corredorActual;
            }

        }

        System.out.println("\n¡¡¡LA CARRERA HA TERMINADO!!!");

        // TAREA 4.5: Obtén el ganador del podio e imprímelo por pantalla.
        // --- INICIA TU CÓDIGO AQUÍ ---

        System.out.println("=============================================");
        System.out.println("El ganador de la carrera es: " + ganador.getNombre() + " terminó en " + tiempo + " ms");
        System.out.println("=============================================");

        // --- TERMINA TU CÓDIGO AQUÍ ---
    }

}
