import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class ordenar {

    public static void main(String[] args) {

        FileReader s = null;
        BufferedReader d = null;

        FileWriter a = null;
        BufferedWriter w = null;

        List<String> datos = new ArrayList<>();

        try {

            s = new FileReader("src/desordenado.txt");
            d = new BufferedReader(s);

            String r ;
            while ((r = d.readLine()) != null) {

                datos.add(r);

            }

            Collections.sort(datos);

            a = new FileWriter("src/ordenados.txt");
            w = new BufferedWriter(a);

            for (String q : datos) {
                w.write(q);
                w.newLine();

            }
            
            w.flush();

        } catch (Exception e) {
            System.out.println(e);

        } finally {

            try {
                s.close();
                d.close();
                a.close();
                w.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
