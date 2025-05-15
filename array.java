public class array {

    public static void main(String[] args) {

     int  [][][] matriz ={{{1,2,3},{4,5,6},{7,8,9}},{ {1,2,3},{4,5,6},{10,11,12}}};
    
     for (int i=0 ;i<matriz.length;i++){
         for (int j=0;j<matriz[i].length;j++){
             for (int k=0;k<matriz[i][j].length;k++){
                 System.out.print(matriz[i][j][k]+" ");
             }
             System.out.println();
         }
         System.out.println();
     }
     System.out.println("El tamaño de la matriz es: "+matriz.length);
    
    }
}
