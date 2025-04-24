import java.io.FileReader;
import java.io.FileWriter;

public class App {
    public static void main(String[] args) throws Exception {

        FileWriter fileO = new FileWriter("ficheroOrigen.txt");
        fileO.write("Hola  soy un fichero de texto");
        fileO.close();

        FileWriter fileD = new FileWriter("ficheroDestino.txt");
        fileD.write("Hola soy un fichero de texto");
        fileD.close();
        

    }
}
