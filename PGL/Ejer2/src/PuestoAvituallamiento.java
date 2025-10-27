/**
 * Representa el puesto de agua. Es un recurso compartido.
 */
public class PuestoAvituallamiento {

    
    /**
     * TAREA 3.1: Identifica este método como la sección crítica.
     * TAREA 3.2: Añade 'synchronized' para evitar que los hilos se pisen.
     */


     public synchronized void  usarPuesto(String nombreCorredor){

        System.out.println("Ha llegado al puesto");
        try {
           Thread.currentThread().sleep(1000);
        } catch (Exception e) {
        }

        System.out.println("<< " + nombreCorredor + " ha salido del puesto.");
    }
}
