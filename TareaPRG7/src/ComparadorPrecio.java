package TareaPRG7.src;

import java.util.Comparator;


public class ComparadorPrecio  implements Comparable<Producto>  {


    @Override
    public int compareTo(Producto otro) {
        return Double.compare(this.precio, otro.precio);
    }

}
