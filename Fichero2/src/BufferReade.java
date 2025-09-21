import java.io.BufferedReader;
import java.io.FileReader;

public class BufferReade {

    public static void main(String[] args) {

        BufferedReader br = null;
        FileReader fr = null;
        int contador = 0;

        try {

            fr = new java.io.FileReader("Fichero2/src/frases.txt");
            br = new java.io.BufferedReader(fr);

            String s;

            while ((s = br.readLine()) != null) {
                contador++;
                System.out.println(s);
            }

            System.out.println("El fichero tiene " + contador + " lineas");

        } catch (Exception e) {

            System.out.println("Error al abrir el fichero");
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar el fichero");
                }

        }

    }
}
