
import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Representa la tarea que realizará cada corredor.
 */
public abstract class Corredor implements Callable<Long>, Serializable {

    private final String nombre;

    @Override
    public String toString() {
        return "Corredor [nombre=" + nombre + "]";
    }

    public static int distanciaTotal = Carrera.metros;
    private final PuestoAvituallamiento puesto;
    private final Podio podio;
    private final JuezSalida juez;

    public Corredor(String nombre, int distanciaTotal, PuestoAvituallamiento puesto, Podio podio, JuezSalida juez) {
        this.nombre = nombre;
        this.distanciaTotal = distanciaTotal;
        this.puesto = puesto;
        this.podio = podio;
        this.juez = juez;
    }

    @Override
    public Long call() {
        // TAREA 1.1: Completa el bucle de la carrera.
        long startTime = System.currentTimeMillis();

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
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
        // --- TERMINA TU CÓDIGO AQUÍ ---
    }
}
