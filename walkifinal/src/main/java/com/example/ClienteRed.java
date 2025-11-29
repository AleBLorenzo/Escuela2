package com.example;

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
    private Scanner sc = new Scanner(System.in);

    final String HOST = "localhost";
    final int PUERTO = 1025;

    public ClienteRed() {
            final String Contraseña = "1234567891234567";
        this.contraseñaCifrada = Cifrador.generarClave(Contraseña);
    }

    public  void iniciarConexion(String HOST ,int PUERTO) {

        try  {

            this.emisor = new Socket(HOST, PUERTO);

            this.salida = emisor.getOutputStream();
            this.escritor = new ObjectOutputStream(salida);

            this.entrada = emisor.getInputStream();
            this.buffer = new ObjectInputStream(entrada);

            // Con esto sacamos la info hacia el servidor
            // Creamos el PrintWriter para facilitar el envio
            // Con el autoflush en true apra q se haga de inmediato

            ReceptorMensajes receptorMensajes = new ReceptorMensajes(buffer);
            receptorMensajes.setObservador(observador);
            receptorMensajes.setContraseñaCifrada(this.contraseñaCifrada);
            Thread hiloreceptor = new Thread(receptorMensajes);

            try {
                hiloreceptor.start();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        } catch (IOException e) {

            System.out.println("Error" + e.getMessage());

        }

        sc.close();

    }

    public void Escribir(String mensaje) {

        try {
  

                if (mensaje.toLowerCase().equals("adios")) {

                    System.out.println("Connexion apagada");
                    byte[] cifrado = Cifrador.cifrar(mensaje, contraseñaCifrada, "AES");
                    escritor.writeObject(cifrado);
                    escritor.flush();

                } else {
                    byte[] cifrado = Cifrador.cifrar(mensaje, contraseñaCifrada, "AES");
                    escritor.writeObject(cifrado);
                    escritor.flush();

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


    private ObservadorMensajes observador;
    ObjectInputStream buffer;
   private SecretKey contraseñaCifrada;


  
    public ReceptorMensajes(ObjectInputStream buffer) {
        this.buffer = buffer;
    }

    public void setContraseñaCifrada(SecretKey contraseñaCifrada) {
        this.contraseñaCifrada = contraseñaCifrada;
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
