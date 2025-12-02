package com.example.walkie;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.crypto.SecretKey;

public class ClienteRed {

    private Socket emisor;
    private OutputStream salida;
    private ObjectOutputStream escritor;
    private ObjectInputStream buffer;
    private InputStream entrada;
    private SecretKey contraseñaCifrada;
    private ObservadorMensajes observador;

    public ClienteRed(ObjectInputStream buffer, SecretKey contraseñaCifrada, Socket emisor, InputStream entrada,
            ObjectOutputStream escritor, OutputStream salida) {
        this.buffer = buffer;
        this.contraseñaCifrada = contraseñaCifrada;
        this.emisor = emisor;
        this.entrada = entrada;
        this.escritor = escritor;
        this.salida = salida;
    }

    private static void iniciarConexion() {

        // Declaramos el puerto del cliente y la dirrecion y el Scanner.
        final String HOST = "localhost";
        final int PUERTO = 1025;

        Scanner sc = new Scanner(System.in);
        final String Contraseña = "1234567891234567";
        SecretKey contraseñaCifrada = Cifrador.generarClave(Contraseña, "AES");

        // Creamos un socket con los datos del HOST y el PUERTO.
        // try-catch para si el servidor esta en escucha si no lanza exepcion

        try (Socket emisor = new Socket(HOST, PUERTO);
                OutputStream salida = emisor.getOutputStream();
                ObjectOutputStream escritor = new ObjectOutputStream(salida);

                InputStream entrada = emisor.getInputStream();
                ObjectInputStream buffer = new ObjectInputStream(entrada);) {

            // Con esto sacamos la info hacia el servidor
            // Creamos el PrintWriter para facilitar el envio
            // Con el autoflush en true apra q se haga de inmediato

            ReceptorMensajes receptorMensajes = new ReceptorMensajes(buffer);
            Thread hiloreceptor = new Thread(receptorMensajes);

            try {
                hiloreceptor.start();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Escribir(escritor, sc, contraseñaCifrada);

        } catch (IOException e) {

            System.out.println("Error" + e.getMessage());

        }

        sc.close();

    }

    private static void Escribir(ObjectOutputStream escritor, Scanner sc, SecretKey contraseñaCifrada) {

        try {
            while (true) {

                System.out.println("Escribe el mensaje a enviar: ");
                String mensaje = sc.nextLine();

                if (mensaje.toLowerCase().equals("adios")) {

                    System.out.println("Connexion apagada");
                    byte[] cifrado = Cifrador.cifrar(mensaje, contraseñaCifrada, "AES");
                    escritor.writeObject(cifrado);
                    escritor.flush();
                    break;

                } else {
                    byte[] cifrado = Cifrador.cifrar(mensaje, contraseñaCifrada, "AES");
                    escritor.writeObject(cifrado);
                    escritor.flush();

                }

            }
        } catch (Exception e) {
        }

    }

    public void setEmisor(ObservadorMensajes observador) {

    }

    public Socket getEmisor() {
        return emisor;
    }

    public void setEmisor(Socket emisor) {
        this.emisor = emisor;
    }

    public OutputStream getSalida() {
        return salida;
    }

    public void setSalida(OutputStream salida) {
        this.salida = salida;
    }

    public ObjectOutputStream getEscritor() {
        return escritor;
    }

    public void setEscritor(ObjectOutputStream escritor) {
        this.escritor = escritor;
    }

    public ObjectInputStream getBuffer() {
        return buffer;
    }

    public void setBuffer(ObjectInputStream buffer) {
        this.buffer = buffer;
    }

    public InputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(InputStream entrada) {
        this.entrada = entrada;
    }

    public SecretKey getContraseñaCifrada() {
        return contraseñaCifrada;
    }

    public void setContraseñaCifrada(SecretKey contraseñaCifrada) {
        this.contraseñaCifrada = contraseñaCifrada;
    }

    public ObservadorMensajes getObservador() {
        return observador;
    }

    public void setObservador(ObservadorMensajes observador) {
        this.observador = observador;
    }

}

class ReceptorMensajes implements Runnable {

    public static final String Contraseña = "1234567891234567";
    private ObservadorMensajes observador;
    ObjectInputStream buffer;
    SecretKey contraseñaCifrada = Cifrador.generarClave(Contraseña, "AES");

    public ReceptorMensajes(ObjectInputStream buffer) {
        this.buffer = buffer;
    }

    @Override

    public void run() {

        Object datos;
        try {
            while ((datos = buffer.readObject()) != null) {

                byte[] mensaje = (byte[]) datos;

                String mensajedescifrado = Cifrador.descifrar(mensaje, contraseñaCifrada);

                if (observador != null) {
                    observador.onMensajeRecibido(mensajedescifrado);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
        }

    }

    public ObjectInputStream getBuffer() {
        return buffer;
    }

    public void setBuffer(ObjectInputStream buffer) {
        this.buffer = buffer;
    }

    public ObservadorMensajes getObservador() {
        return observador;
    }

    public void setObservador(ObservadorMensajes observador) {
        this.observador = observador;
    }

}
