import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class BufferWhrit {

    public static void main(String[] args) {

        FileWriter fw = null;
        BufferedWriter bw = null;
        Scanner sc = new Scanner(System.in);

        try {

            fw = new FileWriter("Fichero2/src/frases_usuario.txt", true);
            bw = new BufferedWriter(fw);
            

            System.out.println("Escribe una frase: ");

            String user = null;
            while (user != "exit") {

                user = sc.nextLine();

                if (user.equals("exit")) {
                    break;
                }
                bw.write(user);
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
