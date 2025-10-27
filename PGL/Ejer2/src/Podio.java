/**
 * TAREA 4.1: Completa esta clase para gestionar la línea de meta.
 * Representa el podio, un recurso compartido para determinar al ganador.
 */
public class Podio {

    private String ganador = null;


    /**
     * TAREA 4.2: Este método debe ser una sección crítica.
     * El primer corredor que lo llame debe ser registrado como el ganador.
     * Los corredores que lleguen después no deben poder cambiar el resultado.
     *
     * Añade 'synchronized' y la lógica necesaria para que solo el primer
     * hilo pueda establecer el valor de la variable 'ganador'.
     */
    public synchronized void registrarLlegada(String nombreCorredor) {
        // --- INICIA TU CÓDIGO AQUÍ ---
        if (ganador == null) {
            this.ganador = nombreCorredor;
            
        }

        // --- TERMINA TU CÓDIGO AQUÍ ---
    }

    public String getGanador() {
        return this.ganador;
    }
}
