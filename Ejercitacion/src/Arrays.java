package Ejercitacion.src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Arrays {

    public static void main(String[] args) {

        int suma = 0;

        int[] array = new int[10];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100 + 1);
            System.out.print(array[i]);
            if (array[i] % 2 == 0) {
                System.out.print("Son pares " + array[i]);
                suma += array[i];

            }
        }
        System.out.println(suma / 10);

        int[][] matriz = new int[5][5];

        int numero = 1;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                matriz[i][j] = numero++;

                System.out.print(matriz[i][j] + "");

            }
            System.out.println();
        }

        int[][] matri = new int[4][4];

        for (int i = 0; i < matri.length; i++) {
            for (int j = 0; j < matri[i].length; j++) {
                matri[i][j] = (i == j) ? 1 : 0;
                System.out.print(matri[i][j] + "");
            }
            System.out.println();
        }

        int[][][] mat = new int[3][3][3];

        int nume = 1;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                for (int k = 0; k < mat[i][j].length; k++) {

                    mat[i][j][k] = nume;

                    System.out.print(mat[i][j][k]);

                }
                System.out.println();
            }
            System.out.println();
        }

        mat[1][1][1] = 0;

        for (int z = 0; z < 3; z++) {
            System.out.println("Capa " + z + ":");
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    System.out.print(mat[x][y][z] + " ");
                }
                System.out.println();
            }
        }

        int[][][] mat1 = new int[3][3][3];

        int nume1 = 0;

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[i].length; j++) {
                for (int k = 0; k < mat1[i][j].length; k++) {

                    mat1[i][j][k] = nume++;

                    System.out.print(mat1[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

    }

    public static class Caja<T> {

        private T contenido;

        public void setContenido(T valor) {
            this.contenido = valor;
        }

        public T getContenido() {
            return this.contenido;
        }

        public static <T> void ImprimirArray(T[] array) {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
        }

        public static void main(String[] args) {

            Caja<Integer> ale = new Caja<>();
            ale.setContenido(1233);
            System.out.println(ale.getContenido());

            String[] frutas = { "Manzana", "Pera", "Uva" };
            ImprimirArray(frutas);

            ArrayList<String> nombres = new ArrayList<>();
            nombres.add("Alejandro");
            nombres.add("yasma");
            nombres.add("ale");
            nombres.add("Cristian");
            nombres.add("ss");
            nombres.add("yasa");

            nombres.remove(1);

            for (String nombre : nombres) {
                if (nombres.contains("ale")) {
                    System.out.println(nombre);
                }

            }

            ArrayList<Integer> numeros = new ArrayList<>();
            numeros.add(2);
            numeros.add(9);
            numeros.add(7);
            numeros.add(5);
            numeros.add(6);

            int suma = 0;
            Collections.sort(numeros);

            for (int i = 0; i < numeros.size(); i++) {
                suma += numeros.get(i);
                System.out.println(numeros);
            }

            int primero = numeros.getFirst();
            int ultimo = numeros.getLast();

            int media = suma / numeros.size();

            System.out.println(primero);
            System.out.println(ultimo);

            System.out.println(suma);
            System.out.println(media);

            ArrayList<String> users = new ArrayList<>();

            Scanner sc = new Scanner(System.in);

            for (int i = 0; i < 11; i++) {
                users.add(sc.nextLine());
            }

            for (String nombre : users) {
                if (nombre.length() > 5) {
                    System.out.println(nombre);
                }
            }

            Collections.reverse(users);

            HashSet<Integer> nome = new HashSet<>();

            nome.add(5);
            nome.add(4);
            nome.add(3);
            nome.add(5);
            nome.add(8);
            nome.add(55);
            nome.add(6);
          
           int  palabra= 9;

           for(Integer nu : nome){
            System.out.println(nu);
           }
            
                if (nome.contains(palabra)) {
                    System.out.println(palabra);
                } else{
                    System.out.println("no contiene la palabra");
                }
               
                System.out.println("introduce una frase");

                 String frace =sc.nextLine();
            

                
                String [] palabre = frace.split(" ");
HashSet <String> pala = new HashSet<>(palabre);
                for(String pa : palabre){
                    System.out.println(pa);
                }
            
        }

    }

}
