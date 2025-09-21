import java.util.ArrayList;

public class Matriz2D {
    public static void main(String[] args) {

        // Ejemplo 1: matriz usando arrays estaticos
        //inicializacion
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6}
        };

        //mostrar matriz
        System.out.println("Matriz 2D estática: ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }


        // Ejemplo 2: Matriz usando la clase ArrayList
        int filas = 2;
        int columnas = 3;

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        // inicializar con ceros
        for (int i = 0; i < filas; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < columnas; j++) {
                fila.add(0);
            }
            matrix.add(fila);
        }

        //mostrar matriz
        System.out.println("\nMatriz 2D con ArrayList: ");
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
