
package TareaPRG7.src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Collections;
import java.util.Date;

public class Producto implements Comparable<Producto> {

    public String nombre;
    public double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public int compareTo(Producto otro) {
        return this.nombre.compareTo(otro.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public static class ComparadorPrecio implements Comparator<Producto> {
        @Override
        public int compare(Producto p1, Producto p2) {
            return Double.compare(p1.getPrecio(), p2.getPrecio());
        }
    }

    public static void ordenar(ArrayList<Producto> productos1) {

        for (int i = 0; i < productos1.size() - 1; i++) {
            int indiceM = i;
            for (int j = i + 1; j < productos1.size(); j++) {

                String nuevo = productos1.get(j).getNombre();
                String mini = productos1.get(indiceM).getNombre();

                if (nuevo.compareTo(mini) < 0) {
                    indiceM = j;
                }
            }
            if (indiceM != i) {
                Producto temp = productos1.get(i);
                productos1.set(i, productos1.get(indiceM));
                productos1.set(indiceM, temp);
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Pan", 1.2));
        productos.add(new Producto("Leche", 0.9));
        productos.add(new Producto("Queso", 2.5));
        productos.add(new Producto("Carne", 5.0));
        productos.add(new Producto("Pescado", 4.0));
        productos.add(new Producto("Arroz", 1.5));
        productos.add(new Producto("Frutas", 3.0));
        productos.add(new Producto("Verduras", 2.0));
        productos.add(new Producto("Huevos", 1.8));
        productos.add(new Producto("Aceite", 3.5));
        productos.add(new Producto("Azúcar", 1.0));
        productos.add(new Producto("Sal", 0.5));
        productos.add(new Producto("Pasta", 1.3));
        productos.add(new Producto("Cereal", 2.2));
        productos.add(new Producto("Galletas", 1.7));
        productos.add(new Producto("Chocolate", 2.8));
        productos.add(new Producto("Bebida", 1.4));
        productos.add(new Producto("Sopa", 2.1));
        productos.add(new Producto("Salsa", 1.6));
        productos.add(new Producto("Cerveza", 3.0));
        productos.add(new Producto("Vino", 2.5));

        long inicio = new Date( System.nanoTime()).getTime();

        Collections.sort(productos);

        long fin = new Date( System.nanoTime()).getTime();
        long duracion = (fin - inicio) ;

        System.out.println("ordenado por sort:");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " - " + producto.getPrecio() + "€");
        }

        System.out.println("\nDuro " + duracion + " ns");

        ArrayList<Producto> productos2 = new ArrayList<>();
        productos2.add(new Producto("Pan", 1.2));
        productos2.add(new Producto("Leche", 0.9));
        productos2.add(new Producto("Queso", 2.5));
        productos2.add(new Producto("Carne", 5.0));
        productos2.add(new Producto("Pescado", 4.0));
        productos2.add(new Producto("Arroz", 1.5));
        productos2.add(new Producto("Frutas", 3.0));
        productos2.add(new Producto("Verduras", 2.0));
        productos2.add(new Producto("Huevos", 1.8));
        productos2.add(new Producto("Aceite", 3.5));
        productos2.add(new Producto("Azúcar", 1.0));
        productos2.add(new Producto("Sal", 0.5));
        productos2.add(new Producto("Pasta", 1.3));
        productos2.add(new Producto("Cereal", 2.2));
        productos2.add(new Producto("Galletas", 1.7));
        productos2.add(new Producto("Chocolate", 2.8));
        productos2.add(new Producto("Bebida", 1.4));
        productos2.add(new Producto("Sopa", 2.1));
        productos2.add(new Producto("Salsa", 1.6));
        productos2.add(new Producto("Cerveza", 3.0));
        productos2.add(new Producto("Vino", 2.5));

        long inicio1 = new Date( System.nanoTime()).getTime();

        Producto.ordenar(productos2);

        long fin1 = new Date( System.nanoTime()).getTime();
        long duracion1 = (fin1 - inicio1) ;

        System.out.println("\nordenados por ordenar");

        for (Producto producto : productos2) {
            System.out.println(producto.getNombre() + " - " + producto.getPrecio() + "€");
        }

        System.out.println("\nDuro " + duracion1  + " ns");

        ArrayList<Producto> productos21 = new ArrayList<>();
        productos21.add(new Producto("Pan", 1.2));
        productos21.add(new Producto("Leche", 0.9));
        productos21.add(new Producto("Queso", 2.5));
        productos21.add(new Producto("Carne", 5.0));
        productos21.add(new Producto("Pescado", 4.0));
        productos21.add(new Producto("Arroz", 1.5));
        productos21.add(new Producto("Frutas", 3.0));
        productos21.add(new Producto("Verduras", 2.0));
        productos21.add(new Producto("Huevos", 1.8));
        productos21.add(new Producto("Aceite", 3.5));
        productos21.add(new Producto("Azúcar", 1.0));
        productos21.add(new Producto("Sal", 0.5));
        productos21.add(new Producto("Pasta", 1.3));
        productos21.add(new Producto("Cereal", 2.2));
        productos21.add(new Producto("Galletas", 1.7));
        productos21.add(new Producto("Chocolate", 2.8));
        productos21.add(new Producto("Bebida", 1.4));
        productos21.add(new Producto("Sopa", 2.1));
        productos21.add(new Producto("Salsa", 1.6));

        ComparadorPrecio comparadorPrecio = new ComparadorPrecio();
        Collections.sort(productos21, comparadorPrecio);

        System.out.println("\nordenado por precio");
        for (Producto producto : productos21) {
            System.out.println(producto.getNombre() + " - " + producto.getPrecio() + "€");
        }
          
        long inicio2 = new Date( System.nanoTime()).getTime();
        String Frutas = "Frutas"; 
        for (Producto producto : productos) {
            if (producto.getNombre().equals(Frutas)) {
                System.out.println();
                System.out.println(producto.getNombre() + " - " + producto.getPrecio() + "€");
            }
        }
        long fin2 = new Date( System.nanoTime()).getTime();
        long duracion2 = (fin2 - inicio2) ;
        System.out.println("\nDuro " + duracion2  + " ns");


        int izquierda = 0;
        int derecha = productos.size() - 1;
        int medio = (izquierda + derecha) / 2;

        String Frutas2 = "Frutas";

        long inicio3 = new Date( System.nanoTime()).getTime();

        while (izquierda <= derecha) {
            medio = (izquierda + derecha) / 2;
            if (productos.get(medio).getNombre().equals(Frutas2)) {
                System.out.println();
                System.out.println(productos.get(medio).getNombre() + " - " + productos.get(medio).getPrecio() + "€");
                break;
            } else if (productos.get(medio).getNombre().compareTo(Frutas2) < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        long fin3 = new Date( System.nanoTime()).getTime();
        long duracion3 = (fin3 - inicio3) ;

        System.out.println("\nDuro " + duracion3  + " ns");

        if (izquierda > derecha) {
            System.out.println("El producto no se encuentra en la lista");
        }

    }
}
