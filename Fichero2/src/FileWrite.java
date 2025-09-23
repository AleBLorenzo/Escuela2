import java.nio.file.*;
import java.io.FileWriter;
import java.io.Writer;

public class FileWrite {
  public static void main(String[] args) {

    try {

      int mumero1 = 101;
      FileWriter s = new FileWriter("Fichero2/src/numeros.txt");

      for (int i = 0; i < mumero1; i++) {

        String res = String.valueOf(i);
        s.write(res+"\n");
      }

      s.close();
    } catch (Exception e) {
      System.out.println(e);

    }

  }
}
