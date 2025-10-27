
import java.io.BufferedReader;
import java.io.FileReader;

public class App3 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("FicheroTexto/src/entrada.txt"))) {

            String linea;
            int contador = 0;
            int numerropalabra = 0;

            while ((linea = br.readLine()) != null) {

                contador++;
                numerropalabra = linea.split("\\s+").length;

                for (char c : linea.toCharArray()) {
                    if (Character.isDigit(c)) {

                    }
                }

                System.out.println("Linea " + contador + " - " + numerropalabra);

            }

        } catch (Exception e) {
        }
    }
}
