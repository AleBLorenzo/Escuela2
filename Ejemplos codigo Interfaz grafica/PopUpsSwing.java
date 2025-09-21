import javax.swing.*;

// Programa que lanza ventanas emergentes (pop-ups)
public class PopUpsSwing {
    public static void main(String[] args) {
        // Mostrar un mensaje
        JOptionPane.showMessageDialog(null, "¡Hola, mundo!");

        // Pedir al usuario que escriba su nombre
        String nombre = JOptionPane.showInputDialog("¿Cómo te llamas?");

        // Mostrar un cuadro de confirmación: Sí, No o Cancelar.
        int respuesta = JOptionPane.showConfirmDialog(null, nombre + ", ¿Te está gustando aprender Java?");

        if (respuesta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "¡Me alegra escuchar eso!");
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "¡Qué pena!");
        } else {
            JOptionPane.showMessageDialog(null, "No pasa nada, sigue practicando.");
        }
    }
}
