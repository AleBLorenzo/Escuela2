import java.io.FileReader;
import java.io.FileWriter;


public class Copi {


    public static void main(String[] args) {
       
FileReader fr = null;
FileWriter fw = null;
        try {

         fr = new java.io.FileReader("Fichero2/src/frases.txt");
            fw = new java.io.FileWriter("Fichero2/src/destino.txt");

            int valor = fr.read();
            while (valor != -1) {
                System.out.print((char) valor);
                valor = fr.read();
                fw.write((char) valor);
            }

            
           



        }
        catch (Exception e) {
            System.out.println("Error al abrir el fichero");
        }
        finally {
            try {
                 fr.close();
                 fw.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }
}
