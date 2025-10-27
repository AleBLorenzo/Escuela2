import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

// Falta lo de la inversa
public class creainversa {

    public static void main(String[] args) {

        FileReader lee = null;
        BufferedReader listo = null;
        FileWriter doc = null;
        BufferedWriter escrito = null;

        try {

            lee = new FileReader("src/entrada.txt");
            listo = new BufferedReader(lee);

            doc = new FileWriter("src/invertido.txt");
            escrito = new BufferedWriter(doc);

            String a;
            while ((a = listo.readLine()) != null) {

                System.out.println(a);

                StringBuilder lineainv = new StringBuilder(a).reverse();

                escrito.write(lineainv.toString());
                escrito.newLine();
                escrito.flush();
            }

        } catch (Exception e) {

        } finally {
            try {

                lee.close();
                listo.close();
                doc.close();
                escrito.close();

            } catch (Exception e) {

            }
        }
    }
}
