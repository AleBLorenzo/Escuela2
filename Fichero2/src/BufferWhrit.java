import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class BufferWhrit {

    public static void main(String[] args) {

        FileWriter fw = null;
        BufferedWriter bw = null;
        Scanner sc = new Scanner(System.in);

        try {

            fw = new FileWriter("src/frases_usuario.txt", true);
            bw = new BufferedWriter(fw);
            

            System.out.println("Escribe una frase: ");

           for (int i = 1; i <= 5; i++) {

             System.out.print("Frase " + i + ": ");
             String frase = sc.nextLine();
             bw.write(frase);
             bw.newLine();
                bw.flush();

            }

            sc.close();

        } catch (Exception e) {

            System.out.println(e);

        } finally {

            try {
                fw.close();
                bw.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
