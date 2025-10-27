import java.io.FileWriter;

public class FileWrite {
  public static void main(String[] args) {

    try {

      int mumeros = 100;
      FileWriter s = new FileWriter("src/numeros.txt");

      for (int i = 0; i <= mumeros; i++) {

        String res = String.valueOf(i);
        s.write(res+"\n");
      }

      s.close();
    } catch (Exception e) {
      System.out.println(e);

    }

  }
}
