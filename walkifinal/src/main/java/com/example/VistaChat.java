package com.example;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VistaChat extends JFrame implements ObservadorMensajes {

    private JTextArea conversacion;
    private JScrollPane panel;
    private JTextField escritura;
    private JButton boton;

    private ClienteRed cliente;

    public VistaChat(ClienteRed cliente ) {
        this.cliente = cliente;
    
        setTitle("Chat Seguro - Cliente");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        conversacion = new JTextArea();
        conversacion.setEditable(false);
        panel = new JScrollPane(conversacion);

        //Panel inferior con campo y el boton
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BorderLayout());

        escritura = new JTextField();
        boton = new JButton("Enviar");

        panelInferior.add(escritura, BorderLayout.CENTER);
        panelInferior.add(boton, BorderLayout.EAST);

        //Anadir componentes al JFrame
        add(panel, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        //Accion para el boton
        boton.addActionListener(e -> enviarMensaje());

        //Acción al pulsar enter en el campo de texto
        escritura.addActionListener(e -> enviarMensaje());

        setVisible(true);
    }

    private void enviarMensaje() {
        String mensaje = escritura.getText().trim();
        if (mensaje.isEmpty()) return;

        cliente.Escribir(mensaje);  

        escritura.setText("");
    }

    //Meodo del observador llega desde ClienteRed en otro hilo
    @Override
    public void onMensajeRecibido(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            conversacion.append(mensaje + "\n");
        });
    }
}
