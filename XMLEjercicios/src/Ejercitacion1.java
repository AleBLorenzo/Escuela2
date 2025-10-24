
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercitacion1 {

    public static void main(String[] args)
            throws SAXException, IOException, TransformerException {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File("src/libro.xml"));

            AñadirDatos(documento);

            Modificar(documento);

            guardar(documento);

            MostrarInfo(documento);

        } catch (ParserConfigurationException ex) {

        }
    }

    private static void MostrarInfo(Document documento) throws DOMException {
        NodeList lista = documento.getElementsByTagName("libro");

        int contador = 0;

        for (int i = 0; i < lista.getLength(); i++) {

            Element s = (Element) lista.item(i);
            contador++;

            System.out.println("\nLibro " + contador);
            System.out.println("\nID: " + s.getAttribute("id"));
            System.out.println("Titulo: " + s.getElementsByTagName("titulo").item(0).getTextContent());
            System.out.println("Autor: " + s.getElementsByTagName("autor").item(0).getTextContent());
            System.out.println("Año: " + s.getElementsByTagName("anio").item(0).getTextContent());

        }
    }

    private static void AñadirDatos(Document documento) {

        NodeList lista = documento.getElementsByTagName("libro");

        Element e = null;

        for (int i = 0; i < lista.getLength(); i++) {

            e = (Element) lista.item(i);

        }

        if (!e.getAttribute("id").equals("4")) {

            Element libroNuevo = documento.createElement("libro");
            libroNuevo.setAttribute("id", "4");

            Element tituloNuevo = documento.createElement("titulo");
            tituloNuevo.setTextContent("Algo Nuevo");
            libroNuevo.appendChild(tituloNuevo);
            Element autorNuevo = documento.createElement("autor");
            autorNuevo.setTextContent("Franco");
            libroNuevo.appendChild(autorNuevo);
            Element añoNuevo = documento.createElement("anio");
            añoNuevo.setTextContent("2004");
            libroNuevo.appendChild(añoNuevo);

            Element biblioteca = (Element) documento.getElementsByTagName("biblioteca").item(0);
            biblioteca.appendChild(libroNuevo);

        }

    }

    private static void guardar(Document documento) throws TransformerException {
        TransformerFactory transf = TransformerFactory.newDefaultInstance();
        Transformer tranhs = transf.newTransformer();

        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult("src/libro.xml");

        tranhs.transform(source, result);
    }

    private static void Modificar(Document documento) throws DOMException {
        NodeList lista = documento.getElementsByTagName("libro");

        for (int i = 0; i < lista.getLength(); i++) {
            Element e = (Element) lista.item(i);

            if (e.getAttribute("id").equals("2")) {

                e.getElementsByTagName("anio").item(0).setTextContent("588");
                e.setAttribute("id", "5");
            }
        }
    }

}
