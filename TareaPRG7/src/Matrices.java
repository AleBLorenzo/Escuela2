package TareaPRG7.src;

import java.util.Random;
import java.util.Scanner;

public class Matrices {

    public int[][] matriz;
    public int filas;
    public int columnas;

    public Matrices(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new int[filas][columnas];

    }

    public Matrices() {
        this.filas = 3;
        this.columnas = 3;
        this.matriz = new int[filas][columnas];

    }

    public Matrices suma(Matrices otra) {
        Matrices resultado = new Matrices(filas, columnas);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado.matriz[i][j] = this.matriz[i][j] + otra.matriz[i][j];
            }
        }
        return resultado;
    }

     public static Matrices identidad(int n) {
        Matrices identidad = new Matrices(n, n);
        for (int i = 0; i < n; i++) {
            identidad.matriz[i][i] = 1;
        }
        return identidad;
    }


    public static void main(String[] args) {

        Matrices matriz = new Matrices(3, 3);
        Matrices matriz1 = new Matrices( 3, 3);

        Random random = new Random();

        System.out.println("Matriz Ramdom");

        for (int i = 0; i < matriz.filas; i++) {
            for (int j = 0; j < matriz.columnas; j++) {
                System.out.print(matriz.matriz[i][j] = random.nextInt(100));
                System.out.print(" ");

            }
            System.out.println();
        }

        System.out.println("Matriz por defecto");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce los valores");

        for (int i = 0; i < matriz1.filas; i++) {
            for (int j = 0; j < matriz1.columnas; j++) {
                System.out.print("Introduce el valor para la posición [" + i + "][" + j + "]: ");
                matriz1.matriz[i][j] = scanner.nextInt();
                System.out.print(" ");
            }
            System.out.println();

        }

        System.out.println("Matriz introducida por el usuario");

        for (int i = 0; i < matriz1.filas; i++) {
            for (int j = 0; j < matriz1.columnas; j++) {
                System.out.print(matriz1.matriz[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        Matrices suma = matriz.suma(matriz1);

        System.out.println("Matriz suma");

        for (int i = 0; i < suma.filas; i++) {
            for (int j = 0; j < suma.columnas; j++) {
                System.out.print(suma.matriz[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        Matrices identidad = Matrices.identidad(3);

        System.out.println("Matriz identidad");
        
        for (int i = 0; i < identidad.filas; i++) {
            for (int j = 0; j < identidad.columnas; j++) {
                System.out.print(identidad.matriz[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        
    }
}
