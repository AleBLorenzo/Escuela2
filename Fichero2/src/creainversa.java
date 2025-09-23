import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.StringBuffer;
import java.util.ArrayList;
import java.lang.Comparable;

// Falta lo de la inversa
public class creainversa {

    public static void main(String[] args) {

        FileReader lee = null;
        BufferedReader listo = null;
        FileWriter doc = null;
        BufferedWriter escrito = null;

        ArrayList<String> lineas = new ArrayList<>();

        try {

            lee = new FileReader("Fichero2/src/entrada.txt");
            listo = new BufferedReader(lee);

            doc = new FileWriter("Fichero2/src/invertido.txt");
            escrito = new BufferedWriter(doc);

            String a;
            while ((a = listo.readLine()) != null) {

                System.out.println(a);
                lineas.add(a);

            }

            for (int i = lineas.size() - 1; i >= 0; i--) {

                escrito.write(lineas.get(i));
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
