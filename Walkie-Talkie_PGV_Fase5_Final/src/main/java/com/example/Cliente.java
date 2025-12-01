package com.example;

public class Cliente {

    public static void main(String[] args) {
     
         ClienteRed clienteRed = new ClienteRed();
         VistaChat vista = new VistaChat(clienteRed);
         clienteRed.setObservador(vista);

         new Thread(() -> clienteRed.iniciarConexion("localhost", 1025)).start();

         vista.setVisible(true);
    }

}
