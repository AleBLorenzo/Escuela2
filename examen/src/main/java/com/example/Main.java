package com.example;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Main {

    private static final String RUTA = "src/main/resources/libros.dat";

    private static ArrayList<Libro> datos = new ArrayList<>();

    public static void main(String[] args) throws SAXException, IOException {

            leerLibrosBinario();
            
            escribirgson();
           
        xml(); 

    }

    private static void leerLibrosBinario() {
        try (DataInputStream ob = new DataInputStream(new FileInputStream(RUTA))) {

            try {
                while (true) {

                    String isbn = ob.readUTF();
                    String titulo = ob.readUTF();
                    String autor = ob.readUTF();
                    int anoPublicacion = ob.readInt();
                    int numeroPaginas = ob.readInt();
                    double precio = ob.readDouble();
                    boolean disponible = ob.readBoolean();

                    Libro libro = new Libro(isbn, titulo, autor, anoPublicacion, numeroPaginas, precio, disponible);

                    datos.add(libro);

                }

            } catch (Exception e) {
                System.out.println("Se Completo la lectura Corectamente");
            }

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    private static void escribirgson() throws JsonSyntaxException {

        Gson json = new Gson();

        try (BufferedWriter os = new BufferedWriter(new FileWriter("src/main/resources/libros_disponibles.json"))) {
            String serializado = json.toJson(datos);
            os.write(serializado);

        } catch (Exception e) {
            System.out.println("Error al escribir el archivo JSON: " + e);
        }
    }

    private static void xml() throws DOMException {
        try {
            
            String datosd = datos.toString();
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            DOMImplementation implementation = build.getDOMImplementation();
            
            Document docuemto = implementation.createDocument(null, "libros_disponibles", null);
            
            Element elemento = docuemto.createElement("datos");
            
        } catch (ParserConfigurationException ex) {
        }
    }
}
