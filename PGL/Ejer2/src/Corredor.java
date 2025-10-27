
/**
 * Representa la tarea que realizará cada corredor.
 */
public class Corredor implements Runnable {

    private final String nombre;
    private final int distanciaTotal = 10;
    private final PuestoAvituallamiento puesto;
    private final Podio podio;
    private final JuezSalida juez;

    public Corredor(String nombre, PuestoAvituallamiento puesto, Podio podio, JuezSalida juez) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.podio = podio;
        this.juez = juez;
    }

    @Override
    public void run() {
        // TAREA 1.1: Completa el bucle de la carrera.

        juez.esperarSalida();

        System.out.println(nombre + " ha comenzado a correr.");

        for (int i = 1; i <= distanciaTotal; i++) {
            System.out.println("   " + nombre + " va por el metro " + i);

            if (i == 5) {
                puesto.usarPuesto(this.nombre);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
            podio.registrarLlegada(nombre);

        }

        System.out.println("¡¡¡ " + nombre + " ha llegado a la meta !!!");

        // TAREA 4.3: Justo después de terminar, el corredor debe registrar su
        // llegada en el podio. Llama al método correspondiente de la clase Podio.
        // --- INICIA TU CÓDIGO AQUÍ ---

        podio.registrarLlegada(this.nombre);

        // --- TERMINA TU CÓDIGO AQUÍ ---
    }
}
