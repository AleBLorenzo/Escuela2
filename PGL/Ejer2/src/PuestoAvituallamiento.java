
import java.util.concurrent.Semaphore;

/**
 * Representa el puesto de agua. Es un recurso compartido.
 */
public class PuestoAvituallamiento {

    static int CapacidadP;

   private static Semaphore semafaro = new Semaphore(CapacidadP);

    public static int getCapacidadP() {
        return CapacidadP;
    }

    public static void setCapacidadP(int CapacidadP) {
        PuestoAvituallamiento.CapacidadP = CapacidadP;
    }
    
    /**
     * TAREA 3.1: Identifica este método como la sección crítica.
     * TAREA 3.2: Añade 'synchronized' para evitar que los hilos se pisen.
     */


     public void  usarPuesto(String nombreCorredor){  
            
            
            try {

                System.out.println("Ha llegado al puesto");
             
                semafaro.acquire();
                Thread.sleep(1000);
               
                   System.out.println("<< " + nombreCorredor + " ha salido del puesto.");


            } catch (InterruptedException  e) {
                 Thread.currentThread().interrupt();
            } 
                 semafaro.release();
            
            
         
       
    }
}
