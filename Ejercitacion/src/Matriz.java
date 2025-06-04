package Ejercitacion.src;

import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Comparable;
import java.util.Collections;

public class Matriz {
 

    

    public static void main(String[] args) {

        int n = 4;
        int[][] matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matriz[i][j] = 0;
                } else if (i < j) {
                    matriz[i][j] = 1;
                } else {
                    matriz[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();

        }
        System.out.println();

        int[][] matri = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j) {
                    matri[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (i == j) {
                    System.out.print(matri[i][j] + " ");
                } else if (i > j) {
                    System.out.print(matri[i][j] + " ");
                }

            }
            System.out.println();
        }

        System.out.println();

        int[][] matr = new int[5][5];

        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[i].length; j++) {
                if (i == 0) {
                    matr[i][j] = 1;
                } else if (j == 0) {
                    matr[i][j] = 1;
                } else if (j == 4) {
                    matr[i][j] = 1;
                } else if (i == 4) {
                    matr[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[i].length; j++) {
                System.out.print(matr[i][j] + " ");
            }
            System.out.println();

        }

        Random ran = new Random();
        int[][] num = new int[3][3];

        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                num[i][j] = ran.nextInt(100);
            }
        }

        int[][] num1 = new int[3][3];

        for (int i = 0; i < num1.length; i++) {
            for (int j = 0; j < num1[i].length; j++) {
                num1[i][j] = ran.nextInt(100);
            }
        }

        int[][] re = new int[3][3];

        for (int i = 0; i < re.length; i++) {
            for (int j = 0; j < re[i].length; j++) {
                re[i][j] = num[i][j] + num1[i][j];
            }
        }
         for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                System.out.print(num[i][j] + " ");
            }
            System.out.println();

        }

         for (int i = 0; i < re.length; i++) {
            for (int j = 0; j <re[i].length; j++) {
                System.out.print(re[i][j] + " ");
            }
            System.out.println();

        }

        int [][] v = new int [6][6];
        Random e = new Random();
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;

        for (int i = 0 ; i < v.length ; i++){
            
        for (int j = 0 ; j < v[i].length ; j++){
            v[i][j]= e.nextInt(30)+1;
            if (v[i][j]<max)
                max = v[i][j];
            
              if (v[i][j] > min)
                min = v[i][j];
            
            
        }
        System.out.println();
        }
         for (int i = 0; i < v.length; i++) {
            for (int j = 0; j <v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println( max + min);



        ArrayList <Integer> enteros = new ArrayList<>();
        enteros.add(5);
        enteros.add(2);
        enteros.add(9);
        enteros.add(1);
        enteros.add(7);
        enteros.add(2);
        enteros.add(8);
        enteros.add(4);
    
    for (int i =0 ; i< enteros.size(); i++){
        for(int j= 0 ; j < enteros.size()-1; j++){
            if (enteros.get(j) > enteros.get(j + 1) ){
             int temp = enteros.get(j);
             enteros.set(j, enteros.get(j + 1));
                    enteros.set(j + 1, temp);
            }

        }
    }
      
    for (Integer s : enteros){
        System.out.println(s);
    }

 ArrayList <String> nombres = new ArrayList<>();

 nombres.add("Juan");
 nombres.add("auan");
 nombres.add("Aleuan");
 nombres.add("Pedrouan");
 nombres.add("Jsfan");
 nombres.add("gfbnuan");
 nombres.add("dfsuan");
 nombres.add("zan");
 nombres.add("xuan");

Collections.sort(nombres);
for (String palabra : nombres) {
            System.out.println(palabra);
        }

}


}

