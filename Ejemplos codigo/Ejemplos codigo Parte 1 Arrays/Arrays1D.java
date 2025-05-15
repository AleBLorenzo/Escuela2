import java.util.ArrayList;

public class Arrays1D {
    public static void main(String[] args) {

        // Ejemplo 1: Array estatico
        //Inicializacion
        int[] array = {1, 2, 3, 4, 5};

        // Mostrar array
        System.out.println("Array estático: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();


        // Ejemplo 2: Array dinamico con ArrayList
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.add(40);
        lista.add(50);

        // Mostrar array
        System.out.println("\nArrayList: ");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i) + " ");
        }
        System.out.println();
    }
}
