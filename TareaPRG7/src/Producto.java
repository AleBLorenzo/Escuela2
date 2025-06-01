package TareaPRG7.src;

import java.util.ArrayList;
import java.util.Comparator;

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

    public static void main(String[] args) {

        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Pan", 1.2));
        productos.add(new Producto("Leche", 0.9));
        productos.add(new Producto("Queso", 2.5));

    }
}
