package Fichero3.src;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Eje2 {

    public static void main(String[] args) {

        try (InputStream si = new FileInputStream("src/imagen.jpg")) {

            int dato = 0;
            double contador = 0;
            int valornull = 0;

            byte[] bist = new byte[1024];

            int contadors = 0;

            StringBuffer ya = new StringBuffer();

            File ruta = new File("Fichero3/src/imagen.jpg");

            String nombre = ruta.getName();

            while ((dato = si.read(bist)) != -1) {
                contador += dato;

                for (int i = 0; i < bist.length; i++) {

                    if (bist[i] == 0) {
                        valornull++;

                    }
                    if (contadors < 10) {

                        ya.append(String.format("%02X ", bist[i]));
                        contadors++;
                    }

                }

            }

            String q;
            if (si.toString().isEmpty()) {
                q = "Si";
            } else {
                q = "No";
            }

            double kb = contador / 10000;
            double mb = kb / 100;

            System.out.println("\nArchivo: " + nombre);
            System.out.println("Tamaño: " + contador + " Bytes" + " (" + kb + " KB,  " + mb + " MB)");
            System.out.println("Bytes nulos: " + valornull);
            System.out.println("Cabecera (hex): " + ya);
            System.out.println("¿Esta vacio?: " + q);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
