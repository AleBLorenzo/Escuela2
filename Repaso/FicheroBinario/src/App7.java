
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class App7 {
    public static void main(String[] args) throws Exception {

        int datos = 0 ;
            long datostotales = 0;
            long datosnulos = 0 ;
            long datoscopiados= 0;
           

        
        try (FileInputStream fi = new FileInputStream(new File("FicheroBinario/src/imagen.png"));FileOutputStream fo = new FileOutputStream(new File("FicheroBinario/src/imagenNueva.png"))) {

            

            while ((datos = fi.read())!= -1) { 

                datostotales++;

                if (datos == 0) {
                    datosnulos++;
                }else{
                    datoscopiados++;
                     fo.write(datos);
                 fo.flush();

                }
          
               
                
            }

         System.out.println("Datos leidos "+ datostotales);
         System.out.println("Datos copiados "+ datoscopiados);
         System.out.println("Datos Nulos "+ datosnulos);


            
            
        } catch (Exception e) {

        }
    
     

            
    

    }
}
