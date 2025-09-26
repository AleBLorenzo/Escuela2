package Fichero3.src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Propiedades {

    public static void main(String[] args) {

        try (InputStream si = new FileInputStream("Fichero3/src/imagen.jpg")) {

            int dato = 0;
            double contador = 0;
            int nulo = 0;
            int valornull = 0;

            while ((dato = si.read()) != -1) {
                contador++;

            }

            for (int i = 0; i < si.read(); i++) {
         
               if (si.toString().contains(null)){
                valornull++;

               }
          }
            nulo++;

            double kb = contador / 1000;
            double mb = kb / 1000;

            System.out.println("\nArchivo: " + si.toString());
            System.out.println("Tamaño: " + contador + " Bytes" + " (" + kb + " KB,  " + mb + " MB)");
            System.out.println("Bytes nulos: " + valornull);
            System.out.println("Cabecera (hex): ");
            System.out.println("¿Esta vacio?: " + si.toString().isEmpty());

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
