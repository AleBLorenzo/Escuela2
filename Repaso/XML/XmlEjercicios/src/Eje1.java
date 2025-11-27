
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Eje1 {

    public static void main(String[] args) throws Exception {

        final String Ruta = "XML/XmlEjercicios/lib/proyectos_inicial.xml";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder build = factory.newDocumentBuilder();
        Document documento = build.parse(new File(Ruta));
        int contador = 1;

        NodeList lista = documento.getElementsByTagName("proyecto");

        for (int i = 0; i < lista.getLength(); i++) {

            Element e = (Element) lista.item(i);
            contador++;

            System.out.println("--- Proyecto " + contador + " ---");
            System.out.println("Nombre: " + e.getElementsByTagName("nombre").item(0).getTextContent());
            System.out.println("Estado:" + e.getElementsByTagName("estado").item(0).getTextContent());
            System.out.println("Fecha de Inicio:" + e.getElementsByTagName("fecha_inicio").item(0).getTextContent());
            System.out.println("Presupuesto: " + e.getElementsByTagName("presupuesto").item(0).getTextContent());

        }

    }

}
