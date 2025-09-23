import java.io.FileReader;

public class FileReade {

    /**
     * @param args
     */
    public static void main(String[] args) {

        FileReader fr = null;
        try {

            fr = new FileReader("Fichero2/src/frases.txt");

            int s;

            while ( (s = fr.read()) != -1 ) {
                System.out.print((char) s);
            }

        } catch (Exception e) {

            System.out.println("Error al abrir el fichero");
        } finally {
                try {
                    fr.close();
                    
                } catch (Exception e) {
                    System.out.println("Error al cerrar el fichero");
                }

        }

    }

}
