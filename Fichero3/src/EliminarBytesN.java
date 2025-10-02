package Fichero3.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class EliminarBytesN {


  public static void main(String[] args) {

    File nombre = new File("Fichero3/src/imagen_sin_nulos.jpg");

    try (
        InputStream si = new FileInputStream("Fichero3/src/imagen.jpg");
        OutputStream salida = new FileOutputStream(nombre)) {

      byte[] numeroByts = new byte[1024];

      int numeros;
      int contadorBN = 0;
      int contador = 0;
      int escritos = 0;

      while ((numeros = si.read(numeroByts)) != -1) {
contador+= numeros;
        for (int i = 0; i < numeros; i++) {
          
          if (numeroByts[i] == 0) {

            contadorBN++;
          } else {
            salida.write(numeroByts[i]);
            escritos++;
          }

        }

      }

      System.out.println("=== RESULTADO ===");
      System.out.println("Bytes leídos: " + contador);
      System.out.println("Bytes escritos: " + escritos);
      System.out.println("Bytes nulos eliminados: " + contadorBN);
      System.out.println("Archivo limpio guardado en: "+ nombre.getName());

    } catch (FileNotFoundException e) {
      System.out.println(e);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  
}
