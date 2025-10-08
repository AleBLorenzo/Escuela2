package XMLEjercicios.src;

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

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Eje3 {

    public static void main(String[] args) throws SAXException, IOException, TransformerException {

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            DOMImplementation implement = build.getDOMImplementation();

            Document documento2 = build.parse(new File("XMLEjercicios/src/estudiantes.xml"));

            NodeList lista = documento2.getElementsByTagName("estudiante");

            for (int i = 0; i < lista.getLength(); i++) {

                Node nodo = lista.item(i);
                Element e = (Element) nodo;

                String nombre = e.getElementsByTagName("nombre").item(0).getTextContent();

                if (nombre.equals("María Rodríguez")) {

                    e.getElementsByTagName("nota").item(0).setTextContent("9");
                    System.out.println("Nota actualizada a 9");

                }
                if (nombre.equals("Pedro Martín")) {

                    e.setAttribute("id", "002-modificado");
                    System.out.println("id actualizada a 002-modificado");
                }

            }

            Element estudiante = documento2.createElement("estudiante");
            estudiante.setAttribute("id", "003");

            Element nombre = documento2.createElement("nombre");
            nombre.setTextContent("Laura González");
            estudiante.appendChild(nombre);

            Element edad = documento2.createElement("edad");
            edad.setTextContent("21");

            estudiante.appendChild(edad);

            Element carrera = documento2.createElement("carrera");
            carrera.setTextContent("Fisica");
            estudiante.appendChild(carrera);

            Element nota = documento2.createElement("nota");
            nota.setTextContent("8.8");
            estudiante.appendChild(nota);

            documento2.getDocumentElement().appendChild(estudiante);

            System.out.println("Nuevo estudiante agregado");

            TransformerFactory transforF = TransformerFactory.newInstance();
            Transformer transf = transforF.newTransformer();
            DOMSource souse2 = new DOMSource(documento2);

            StreamResult result = new StreamResult(new File("XMLEjercicios/src/estudiantes_modificado.xml"));

            transf.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transf.transform(souse2, result);

            Document documento = build.parse(new File("XMLEjercicios/src/estudiantes_modificado.xml"));

            NodeList hijo = documento.getElementsByTagName("estudiante");

          

                    for (int j = 0; j < hijo.getLength(); j++) {

                        Node hijos = hijo.item(j);

                        if (hijos.getNodeType() == hijos.ELEMENT_NODE) {

                            Element eh = (Element) hijos;
                            System.out.println("__________DATOS________");
                           System.out.println("id: "+eh.getAttribute("id"));
                           System.out.println("Nombre: "+eh.getElementsByTagName("nombre").item(0).getTextContent());
                           System.out.println("Edad: "+eh.getElementsByTagName("edad").item(0).getTextContent());
                           System.out.println("Carrera: "+eh.getElementsByTagName("carrera").item(0).getTextContent());
                           System.out.println("Nota: "+eh.getElementsByTagName("nota").item(0).getTextContent());
                           System.out.println("__________________");
                        }

                    }

                
            

        } catch (ParserConfigurationException ex) {
        }

    }

}
