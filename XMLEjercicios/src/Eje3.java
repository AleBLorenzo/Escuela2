
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Eje3 {

    public static void main(String[] args) {

        File ruta = new File("src/estudiantes.xml");
        File rutaNueva = new File("src/estudiantes_modificado.xml");

        try {

            // Crear una instancia de DocumentBuilderFactory , un DocumentBuilder

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();

            // Cargamos el archivo XML existente

            Document documento = build.parse(ruta);
            // Normaliza el documento (combina nodos de texto contiguos y elimina espacios
            // innecesarios).
            documento.getDocumentElement().normalize();

            // Obtenemos todos los nodos <estudiante>
            NodeList lista = documento.getElementsByTagName("estudiante");

            // Recorremos los nodos para modificar datos
            for (int i = 0; i < lista.getLength(); i++) {

                // Obtiene cada nodo <estudiante>
                Node nodo = lista.item(i);
                // Lo convierte a Element para acceder
                Element e = (Element) nodo;

                // Extrae el nombre del estudiante
                String nombre = e.getElementsByTagName("nombre").item(0).getTextContent();

                // Si el nombre es Maria Rodriguez se actualiza su nota
                if (nombre.equals("María Rodríguez")) {
                    e.getElementsByTagName("nota").item(0).setTextContent("9");
                    System.out.println("Nota actualizada a 9");

                }

                // Si el nombre es Pedro Martin se modifica su atributo id
                if (nombre.equals("Pedro Martín")) {
                    e.setAttribute("id", "002-modificado");
                    System.out.println("id actualizada a 002-modificado");
                }

            }

            // Creamos un nuevo elemento <estudiante> con su atributo id="003"

            Element estudiante = documento.createElement("estudiante");
            estudiante.setAttribute("id", "003");

            // Añadimos las etiquetas hijas del nuevo estudiante

            Element nombre = documento.createElement("nombre");
            nombre.setTextContent("Laura González");
            estudiante.appendChild(nombre);

            Element edad = documento.createElement("edad");
            edad.setTextContent("21");
            estudiante.appendChild(edad);

            Element carrera = documento.createElement("carrera");
            carrera.setTextContent("Fisica");
            estudiante.appendChild(carrera);

            Element nota = documento.createElement("nota");
            nota.setTextContent("8.8");
            estudiante.appendChild(nota);

            // Agregamos el nuevo estudiante al elemento raíz del documento
            documento.getDocumentElement().appendChild(estudiante);

            System.out.println("Nuevo estudiante agregado");

            // Creamos el transformador que convierte el DOM en un archivo XML
            TransformerFactory transforF = TransformerFactory.newInstance();
            Transformer transf = transforF.newTransformer();
            DOMSource souse2 = new DOMSource(documento);

            StreamResult result = new StreamResult(rutaNueva);

            // Ejecuta la transformación (guardar en disco)
            transf.transform(souse2, result);

            // Volvemos a parsear el archivo ya modificado
            Document documento2 = build.parse(rutaNueva);

            NodeList hijo = documento2.getElementsByTagName("estudiante");

            // Recorremos todos los estudiantes para imprimir sus datos
            for (int j = 0; j < hijo.getLength(); j++) {

                Node hijos = hijo.item(j);

                if (hijos.getNodeType() == hijos.ELEMENT_NODE) {

                    Element eh = (Element) hijos;
                    // Muestra los datos del estudiante por consola
                    System.out.println("__________DATOS________");
                    System.out.println("id: " + eh.getAttribute("id"));
                    System.out.println("Nombre: " + eh.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Edad: " + eh.getElementsByTagName("edad").item(0).getTextContent());
                    System.out.println("Carrera: " + eh.getElementsByTagName("carrera").item(0).getTextContent());
                    System.out.println("Nota: " + eh.getElementsByTagName("nota").item(0).getTextContent());
                }

            }

        } catch (IOException | ParserConfigurationException | TransformerException | DOMException | SAXException ex) {
            System.out.println("Error al crear el archivo XML: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

}
