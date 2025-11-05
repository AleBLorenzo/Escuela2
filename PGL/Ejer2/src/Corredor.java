
import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Representa la tarea que realizará cada corredor.
 */
public class Corredor implements Callable<Long>, Serializable {

    private final String nombre;

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Corredor [nombre=" + nombre + "]";
    }

    public static int distanciaTotal;
    public final PuestoAvituallamiento puesto;
    private final JuezSalida juez;

    public Corredor(String nombre, int distanciaTotal, PuestoAvituallamiento puesto, JuezSalida juez) {
        this.nombre = nombre;
        this.distanciaTotal = distanciaTotal;
        this.puesto = puesto;

        this.juez = juez;
    }

    @Override
    public Long call() throws InterruptedException {
        // TAREA 1.1: Completa el bucle de la carrera.
        long startTime = System.currentTimeMillis();

        juez.esperarSalida();

        System.out.println(nombre + " ha comenzado a correr.");

        for (int i = 1; i <= distanciaTotal; i++) {
            System.out.println("   " + nombre + " va por el metro " + i);

            if (i == (distanciaTotal / 2)) {
                puesto.usarPuesto(this.nombre);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }

        }

        System.out.println("¡¡¡ " + nombre + " ha llegado a la meta !!!");

        // TAREA 4.3: Justo después de terminar, el corredor debe registrar su
        // llegada en el podio. Llama al método correspondiente de la clase Podio.
        // --- INICIA TU CÓDIGO AQUÍ ---

        long endTime = System.currentTimeMillis();

        long finals = endTime - startTime;
        return finals;
        // --- TERMINA TU CÓDIGO AQUÍ ---
    }
}
