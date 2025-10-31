package com.example;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class Main {

    private static final String RUTA = "src/main/resources/libros.dat";

    private static ArrayList<Libro> datos = new ArrayList<>();

    public static void main(String[] args) throws SAXException, IOException, DOMException, TransformerException {

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

        ArrayList<Libro> nuevo = new ArrayList<>();

        Gson json = new GsonBuilder().setPrettyPrinting().create();

        for (Libro libros : datos) {
            if (libros.isDisponible()) {

                nuevo.add(libros);
            }

        }

        try (BufferedWriter os = new BufferedWriter(new FileWriter("src/main/resources/libros_disponibles.json"))) {
            String serializado = json.toJson(nuevo);
            os.write(serializado);

        } catch (Exception e) {
            System.out.println("Error al escribir el archivo JSON: " + e);
        }
    }

    private static void xml() throws DOMException, TransformerException {

        try {
            // Calcular estadísticas
            int total = datos.size();
            int disponibles = 0;
            double sumaPrecios = 0.0;

            for (Libro libro : datos) {
                if (libro.isDisponible()) {
                    disponibles++;
                }
                sumaPrecios += libro.getPrecio();
            }

            double precioMedio = sumaPrecios / total;

            System.out.println("Estadísticas calculadas:");
            System.out.println("Total libros: " + total);
            System.out.println("libros disponibles: " + disponibles);
            System.out.println("Precio medio: " + String.format("%.2f", precioMedio));

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element raiz = document.createElement("estadisticas");
            document.appendChild(raiz);

            Element elemTotalLibros = document.createElement("totalLibros");
            elemTotalLibros.setTextContent(String.valueOf(total));
            raiz.appendChild(elemTotalLibros);

            Element elemdisponibles = document.createElement("disponibles");
            elemdisponibles.setTextContent(String.valueOf(disponibles));
            raiz.appendChild(elemdisponibles);

            Element elemPrecioMedio = document.createElement("precioMedio");
            elemPrecioMedio.setTextContent(String.format("%.2f", precioMedio));
            raiz.appendChild(elemPrecioMedio);

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transf = transFactory.newTransformer();

            transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("src/main/resources/libros_datos.xml"));

            transf.transform(source, result);

            System.out.println("Archivo XML generado correctamente");
        } catch (ParserConfigurationException ex) {
        }
    }
}
