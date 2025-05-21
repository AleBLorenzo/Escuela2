import java.util.TreeSet;
import java.lang.Comparable;
import java.time.chrono.ChronoLocalDate;

public class Producto implements Comparable<Producto> {

    public String nombre;
    public int precio;

    public Producto(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public int compareTo(Producto otro) {
        if (this.precio == otro.precio) {
            return this.nombre.compareTo(otro.nombre);
        } else {
            return Integer.compare(this.precio, otro.precio);
        }
        
    }

    @Override
    public String toString() {
        return "Producto " + "nombre=" + nombre + ", precio=" + precio;
    }

    public static void main(String[] args) {

        Producto p1 = new Producto("caldero", 100);
        Producto p2 = new Producto("hoya", 105);
        Producto p3 = new Producto("sarten", 18);
        Producto p4 = new Producto("cuchara", 45);
        Producto p5 = new Producto("plato", 20);
        Producto p6 = new Producto("fogon", 200);
        Producto p7 = new Producto("alla", 100);

        TreeSet<Producto> almacen = new TreeSet<>();
        almacen.add(p1);
        almacen.add(p2);
        almacen.add(p3);
        almacen.add(p4);
        almacen.add(p5);
        almacen.add(p6);
        almacen.add(p7);

        for (Producto producto : almacen) {
            System.out.println(producto);
        }
    }
}
