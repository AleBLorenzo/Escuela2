public class JuezSalida {

    boolean listosParaEmpezar = false;
 
    
    public synchronized void esperarSalida(){

        while (!listosParaEmpezar) {
            try {
               wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void darSalida(){

        listosParaEmpezar = true;
           notifyAll();

      
    }
}
