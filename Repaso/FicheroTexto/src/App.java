
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class App {

    private final static String ruta = "FicheroTexto/src/datos_originales.txt";
    private final static String rutaG = "FicheroTexto/src/resumen_estadistico.txt";


    public static void main(String[] args) throws Exception {
     
        leerDocumento(ruta);

        resumen(rutaG);


    }

    public static void leerDocumento(String ruta) {

    
        int totallineas = 0;

        try (BufferedReader rd = new BufferedReader(new FileReader(ruta))){

            int numerodepalabras;
            String linea;
            while (( linea = rd.readLine())!= null) {
                
                       
                totallineas++;
             
                numerodepalabras = linea.split("\\s+").length -1;

                 
           System.out.println("La linea "+ totallineas);
             System.out.println("Numero de Palabras: "+numerodepalabras);

             if (linea.toLowerCase().contains("a")) {

                    System.out.println(" contiene la letra a\n");
                    
                }else{
                    System.out.println(" no contiene la letra a\n");
                }

            }
            
             
        } catch (Exception e) {
            System.out.println("Error : "+ e);
        }

    }

    public static void resumen(String rutaG ){
        try (BufferedReader br = new BufferedReader(new FileReader(ruta));
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaG,true))) {

            int numerodepalabras;
            String linea ;

            while ((linea = br.readLine())!= null) { 

             numerodepalabras = linea.split("\\s+").length -1;
                
                bw.write("Palabras "+numerodepalabras+" " + linea+"\n");
                bw.flush();

              
            }

        

            
        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }


    }


}
