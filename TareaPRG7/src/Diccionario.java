package TareaPRG7.src;

import java.util.Scanner;
import java.util.TreeMap;

public class Diccionario {

    public static void main(String[] args) {

        TreeMap<String, String> diccionario = new TreeMap<>();

        diccionario.put("labrador", "Perro de raza grande, amigable y muy inteligente.");
        diccionario.put("siames", "Gato de raza oriental, elegante y muy vocal.");
        diccionario.put("persa", "Gato de raza con pelaje largo y cara achatada.");
        diccionario.put("pastor aleman", "Perro de raza grande, leal y protector.");
        diccionario.put("beagle", "Perro de raza mediana, muy curioso y enérgico.");
        diccionario.put("canario", "Ave pequeña, famosa por su canto melodioso.");
        diccionario.put("doberman", "Perro de raza fuerte, valiente y protector.");
        diccionario.put("bulldog", "Perro de raza robusta, de hocico corto y carácter tranquilo.");
        diccionario.put("golden retriever", "Perro de raza amigable, ideal para familias.");
        diccionario.put("maine coon", "Gato de raza grande, pelaje largo y carácter dócil.");
        diccionario.put("husky siberiano", "Perro de raza resistente, famoso por tirar trineos.");
        diccionario.put("chihuahua", "Perro de raza pequeña, muy valiente y alerta.");
        diccionario.put("dalmata", "Perro de raza conocida por sus manchas negras.");
        diccionario.put("san bernardo", "Perro de raza gigante, muy noble y buen rescatista.");
        diccionario.put("angora", "Gato de raza con pelaje largo y sedoso.");

        while (true) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Esto es un Diccionario de mascotas , Escriba el nombre de alguna de estas para conocer su descripción , Para SALIR escriba 'salir':");
            System.out.println();
            System.out.println("labrador, siames, persa, pastor aleman, beagle, \ncanario, doberman, bulldog, golden retriever, maine coon, \nhusky siberiano, chihuahua, dálmata, san bernardo, angora");
            System.out.println();
            System.out.println("Palabra: ");
            System.out.println();
            String palabra = scanner.nextLine();

            if (diccionario.containsKey(palabra)) {
                System.out.println("Definición: " + diccionario.get(palabra));
                System.out.println();
            } else if (palabra.equals("salir")) {
                break;
            } else {
                System.out.println("Palabra no encontrada en el diccionario.");
            }

        }
    }
}
