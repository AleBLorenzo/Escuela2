package TareaPRG7.src;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Pila<T> {

    private ArrayList<T> elementos = new ArrayList<>();

    public void push(T valor) {
        elementos.add(valor);
    }

    public T pop() {
        if (!elementos.isEmpty()) {
            return elementos.remove(elementos.size() - 1);
        }
        return null;
    }

    public static void main(String[] args) {

        Pila<String> pila = new Pila<>();

        while (true) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Selecciona una opción:");
            System.out.println("1. Añadir texto");
            System.out.println("2. Deshacer");
            System.out.println("3. Mostrar todo");
            System.out.println("4. Salir");

            int sc = scanner.nextInt();
            String texto = scanner.nextLine();

            switch (sc) {

                case 1:
                    System.out.println("Escribe el texto q desees");
                    texto = scanner.nextLine();
                    pila.push(texto);

                    break;
                case 2:
                    System.out.println("Deshaciendo el ultimo texto");
                    pila.pop();
                    break;
                case 3:

                    if (!pila.elementos.isEmpty()) {
                        System.out.println("Contenido");
                        for (String elemento : pila.elementos) {
                            System.out.println(elemento);
                        }
                    } else {
                        System.out.println("Sin texto");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcion no valida");
            }
        }

    }
}
