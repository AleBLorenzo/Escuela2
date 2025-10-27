import java.util.ArrayList;
import java.util.List;

public class Carrera {

    public static void main(String[] args) throws InterruptedException {
        // --- Preparación de objetos compartidos ---
        PuestoAvituallamiento puesto = new PuestoAvituallamiento();
        Podio podio = new Podio(); // TAREA 4.4: Crea la instancia del Podio.

        JuezSalida Juez = new JuezSalida();

        List<Thread> corredores = new ArrayList<>();

        // TAREA 1.2: Crea los corredores y los hilos. Asegúrate de pasar
        // la instancia del Podio a cada corredor.

        // --- INICIA TU CÓDIGO AQUÍ (Creación de corredores y hilos) ---

        Corredor Nelson = new Corredor("Nelson", puesto, podio, Juez);
        Corredor Ale = new Corredor("Alejandro", puesto, podio, Juez);
        Corredor Juan = new Corredor("Juan", puesto, podio, Juez);
        Corredor Nelson1 = new Corredor("pa", puesto, podio, Juez);
        Corredor Ale2 = new Corredor("Al", puesto, podio, Juez);
        Corredor Juan3 = new Corredor("Jn", puesto, podio, Juez);

        Thread NelsonT = new Thread(Nelson);
        Thread AleT = new Thread(Ale);
        Thread juanT = new Thread(Juan);
        Thread NelsonT1 = new Thread(Nelson1);
        Thread AleT2 = new Thread(Ale2);
        Thread juanT3 = new Thread(Juan3);

        corredores.add(NelsonT);
        corredores.add(AleT);
        corredores.add(juanT);
        corredores.add(NelsonT1);
        corredores.add(AleT2);
        corredores.add(juanT3);

        System.out.println("¡Suena el pistoletazo de salida!");

        // TAREA 1.3: Inicia todos los hilos.
        // --- INICIA TU CÓDIGO AQUÍ ---

        System.out.println("¡PREPARADOS, LISTOS, YA!");

        for (Thread corredor1 : corredores) {
            corredor1.start();

        }

        Thread.sleep(2000);
        Juez.darSalida();

        // TAREA 2.1: Espera a que todos los hilos terminen usando join().
        // --- INICIA TU CÓDIGO AQUÍ ---

        for (Thread corredor : corredores) {
            try {
                corredor.join();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n¡¡¡LA CARRERA HA TERMINADO!!!");

        // TAREA 4.5: Obtén el ganador del podio e imprímelo por pantalla.
        // --- INICIA TU CÓDIGO AQUÍ ---

        String ganador = podio.getGanador();
        System.out.println("=============================================");
        System.out.println("El ganador de la carrera es: " + ganador);
        System.out.println("=============================================");

        // --- TERMINA TU CÓDIGO AQUÍ ---
    }
}
