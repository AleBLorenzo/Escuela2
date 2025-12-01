package com.example.walkie

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VistaChat extends JFrame implements ObservadorMensajes {

    private JTextArea conversacion;
    private JScrollPane panel;
    private JTextField escritura;
    private JButton boton;

    @Override
    public void onMensajeRecibido(String mensaje) {
        // TODO: Implement message reception logic
    }
}
