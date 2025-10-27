import java.io.FileReader;

public class FileReade {

    public static void main(String[] args) {

        FileReader fr = null;
        try {

            fr = new FileReader("src/frases.txt");

            int s;

            while ((s = fr.read()) != -1) {
                System.out.print((char) s);
            }

        } catch (Exception e) {

            System.out.println("Error al abrir el fichero");
        } finally {
            try {
                if ((fr != null)) {
                    fr.close();

                }

            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
            }

        }

    }

}
