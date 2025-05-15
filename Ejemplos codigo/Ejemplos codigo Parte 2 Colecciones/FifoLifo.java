import java.util.ArrayDeque;
import java.util.Deque;

public class FifoLifo {
    public static void main(String[] args) {
        // FIFO (cola): First in, first out
        Deque<String> cola = new ArrayDeque<>();
        cola.add("A");
        cola.add("B");
        cola.add("C");

        System.out.println("FIFO (cola):");
        while (!cola.isEmpty()) {
            System.out.println("Sale: " + cola.poll()); // quita del principio
        }

        // LIFO (pila): last in, first out
        Deque<String> pila = new ArrayDeque<>();
        pila.push("A");
        pila.push("B");
        pila.push("C");

        System.out.println("\nLIFO (pila):");
        while (!pila.isEmpty()) {
            System.out.println("Sale: " + pila.pop()); // quita último añadido
        }
    }
}
