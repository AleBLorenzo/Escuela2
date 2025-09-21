import java.util.ArrayList;
import java.util.Iterator;

public class IteradorPares {
    public static void main(String[] args) {

        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numeros.add(i);
        }

        //recorre la lista con un iterador
        Iterator<Integer> it = numeros.iterator();
        while (it.hasNext()) {
            int num = it.next();
            if (num % 2 == 0) { //si el numero es par lo elimina
                it.remove();
            }
        }

        System.out.println("Números impares:" + numeros); //toString()
    }
}
