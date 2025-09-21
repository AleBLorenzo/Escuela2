import javax.swing.*;

public class HolaMundoSwing {
    public static void main(String[] args) {
        // Ventana
        JFrame ventana = new JFrame("Hola mundo con Swing, soy una ventana JFrame");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //al pulsar botón de cerrar, cerramos el programa
        ventana.setSize(400, 200);
        ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Etiqueta de texto
        JLabel etiqueta = new JLabel("¡Hola Mundo! soy un texto JLabel");
        ventana.add(etiqueta);
        ventana.setVisible(true);
    }
}
