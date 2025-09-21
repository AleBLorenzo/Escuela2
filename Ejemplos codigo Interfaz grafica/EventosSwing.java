import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventosSwing {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Ejemplo de Eventos con Layout");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 300);

        // Panel con disposicion vertical
        JPanel panel = new JPanel(new GridLayout(0, 1));

        //Area de texto donde escribir y la etiqueta de texto en la que se verá el resultado
        JTextArea areaTexto = new JTextArea();
        JLabel etiquetaResultado = new JLabel("Resultado: ");

        // Botones para escribir y limpiar el texto
        JButton botonEscribir = new JButton("Escribir");
        JButton botonReset = new JButton("Reset");

        // ComboBox y etiqueta asociada
        String[] opciones = {"Opción 1", "Opción 2", "Opción 3"};
        JComboBox<String> combo = new JComboBox<>(opciones);
        JLabel etiquetaCombo = new JLabel("Opción seleccionada: Opción 1");

        // Acción del botón Escribir. Así es la forma tradicional de escribirlo: clase anónima
        botonEscribir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etiquetaResultado.setText("Resultado: " + areaTexto.getText());
            }
        });

        // Acción del botón Reset. Forma moderna de escribirlo: función lambda, pero no permite modificar variables
        botonReset.addActionListener(e -> {
                areaTexto.setText("");
                etiquetaResultado.setText("Resultado: ");
        });

        // Acción de la comboBox
        combo.addActionListener(e -> {
            String seleccion = (String) combo.getSelectedItem();
            etiquetaCombo.setText("Opción seleccionada: " + seleccion);
        });

        // Añadir componentes al panel
        panel.add(new JLabel("Introduce un texto:"));
        panel.add(areaTexto);
        panel.add(botonEscribir);
        panel.add(botonReset);
        panel.add(etiquetaResultado);
        panel.add(combo);
        panel.add(etiquetaCombo);

        // Añadir panel a la ventana y ponerla visible
        ventana.add(panel);
        ventana.setVisible(true);
    }
}
