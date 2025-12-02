package com.example.walkie;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Cifrador {

    private static final String ALGORITMO = "AES";

    public static SecretKey generarClave(String contraseña, String ALGORITMO) {

        byte[] contraseñabyte = contraseña.getBytes();

        SecretKey clave = new SecretKeySpec(contraseñabyte, ALGORITMO);

        return clave;

    }

    public static byte[] cifrar(String mensaje, SecretKey clave, String ALGORITMO) {

        byte[] mensajecifrado = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);

            cipher.init(Cipher.ENCRYPT_MODE, clave);

            byte[] mensajebytes = mensaje.getBytes();

            mensajecifrado = cipher.doFinal(mensajebytes);

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mensajecifrado;
    }

    public static String descifrar(byte[] mensajeCifrado, SecretKey clave) {

        String mensajeoriginal = null;

        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);

            cipher.init(Cipher.DECRYPT_MODE, clave);

            byte[] mensaje = cipher.doFinal(mensajeCifrado);

            mensajeoriginal = new String(mensaje);

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mensajeoriginal;
    }
}
