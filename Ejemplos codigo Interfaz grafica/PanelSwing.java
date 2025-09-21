import javax.swing.*;
import java.awt.*;

public class PanelSwing {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Ejemplo con JPanel");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(300, 200);

        // Crear panel con disposicion de (3 filas, 1 columna) + 20 pixeles de separacion entre paneles
        // Si fuera new JPanel(), sería por defecto FlowLayout (de izquierda a derecha)
        JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); //bordes del panel
        panel.setBackground(Color.BLUE); //cambia color de fondo del panel

        // Botones
        JButton boton1 = new JButton("Botón 1");
        JButton boton2 = new JButton("Botón 2");
        JButton boton3 = new JButton("Botón 3");

        // Añadir botones al panel
        panel.add(boton1);
        panel.add(boton2);
        panel.add(boton3);

        // Añadir panel a la ventana
        ventana.add(panel);

        ventana.setVisible(true);
    }
}
