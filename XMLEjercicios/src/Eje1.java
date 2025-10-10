
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Eje1 {

    public static void main(String[] args) {

        int contador = 1;

        try {

            // Crear una instancia de DocumentBuilderFactory , un DocumentBuilder

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document documento = build.parse(new File("libros.xml"));

            // Mostrar el elemento raíz
            System.out.println("Elemento Raiz: " + documento.getDocumentElement().getNodeName());

            // Obtener todos los elementos <libro>
            NodeList lista = documento.getElementsByTagName("libro");
            System.out.println("Numero total de libros: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++) {
                Node nodo = lista.item(i);
                // Recorrer la lista de libros
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                    Element e = (Element) nodo;
                    // Extraer los valores de cada elemento
                    String id = e.getAttribute("id");
                    String titulo = e.getElementsByTagName("titulo").item(0).getTextContent();
                    String autor = e.getElementsByTagName("autor").item(0).getTextContent();
                    String precio = e.getElementsByTagName("precio").item(0).getTextContent();

                    // Mostrar información organizada
                    System.out.println("--- Libro " + contador++ + " ---");
                    System.out.println("ID: " + id);
                    System.out.println("Titulo: " + titulo);
                    System.out.println("Autor: " + autor);
                    System.out.println("Precio: " + precio);

                }
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println("Error al procesar el archivo XML: " + e.getMessage());
            e.printStackTrace(); // Muestra detalles técnicos si hay fallo
        }

    }

}
