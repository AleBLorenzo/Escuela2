import javax.swing.*;
import java.awt.*;

public class ComponentesSwing {
    public static void main(String[] args) {
        // Ventana
        JFrame ventana = new JFrame("Componentes Swing");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 400);

        //Disposiciçon vertical. Por defecto es FlowLayout (de izquierda a derecha)
        ventana.setLayout(new GridLayout(0, 1)); //0 filas (automático) y 1 columna

        // Componentes
        JLabel etiqueta = new JLabel("JLabel");
        JButton boton = new JButton("JButton");
        JTextField campoTexto = new JTextField("JTextField");
        JCheckBox casilla = new JCheckBox("JCheckBox");
        JRadioButton radio = new JRadioButton("JRadioButton");
        JComboBox<String> combo = new JComboBox<>(new String[] {"JComboBox: opción 1", "ComboBox: opción 2", "ComboBox: opción 3"});
        JSlider deslizador = new JSlider();
        JPasswordField campoPassword = new JPasswordField("JPasswordField");

        // Añadir componentes a la ventana
        ventana.add(etiqueta);
        ventana.add(boton);
        ventana.add(campoTexto);
        ventana.add(casilla);
        ventana.add(radio);
        ventana.add(combo);
        ventana.add(deslizador);
        ventana.add(campoPassword);

        // Mostrar la ventana
        ventana.setVisible(true);
    }
}
