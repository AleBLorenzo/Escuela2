import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.StringBuffer;
import java.lang.Comparable;

// Falta lo de la inversa
public class creainversa {

    public static void main(String[] args) {

        FileReader lee = null;
        BufferedReader listo = null;
        FileWriter doc = null;
        BufferedWriter escrito = null;

        try {

            lee = new FileReader("Fichero2/src/entrada.txt");
            listo = new BufferedReader(lee);

            doc = new FileWriter("Fichero2/src/invertido.txt");
            escrito = new BufferedWriter(doc);

            String a;
            while ((a = listo.readLine()) != null) {

                System.out.println(a);

            }

            for (int i = 0; i < listo.read(); i++) {

                String res = String.valueOf(i);
                
                escrito.write(res);
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
