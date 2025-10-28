
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class App2 {

    public static void main(String[] args) {
      

        try (BufferedReader br = new BufferedReader(new FileReader("FicheroTexto/src/texto_original.txt"))) {

            String linea ;

            while (( linea = br.readLine()) != null) {
                
               System.out.println("Longitud "+linea.length()+" - "+linea.toUpperCase().toString());

                if (linea.length() <= 40) {
                    
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("FicheroTexto/src/texto_menor.txt", true))){
                        
                        bw.write("Longitud "+linea.length()+" - "+linea.toUpperCase().toString()+"\n");
                        bw.flush();

                    } catch (Exception e) {
                    }
                } else if (linea.length() >= 40){
                    try (BufferedWriter bq = new BufferedWriter(new FileWriter("FicheroTexto/src/texto_mayor.txt",true))){
                        bq.write("Longitud "+linea.length()+" - "+linea.toUpperCase().toString()+"\n");
                        bq.flush();

                    } catch (Exception e) {
                    }

                     
                }
                
            }
            
        } catch (Exception e) {
        }
    }

}
