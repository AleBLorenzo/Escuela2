
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Eje1 {

    private static final String RUTA = "XML/XmlEjercicios/lib/peliculas.xml";
     private static final String RUTAN = "XML/XmlEjercicios/lib/peliculas_modificado.xml";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        File archivo = new File(RUTA);
        
        if (!archivo.exists()) {
            crearxml0();
            leerXML();
           
        } else{
            leerXML();
        }

        modificarCrearNuevo();
        
        
        

        

    }

    private static void crearxml0()
            throws TransformerException, TransformerConfigurationException, ParserConfigurationException, DOMException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        DOMImplementation implement = builder.getDOMImplementation();

        Document documento = implement.createDocument(null, "cartelera", null);
        documento.setXmlVersion("1.0");

        Element lista = documento.createElement("cartelera");

        Element pelicula1 = documento.createElement("pelicula");
        pelicula1.setAttribute("id", "1");

        Element titulo1 = documento.createElement("titulo");
        titulo1.setTextContent("Inception");
        pelicula1.appendChild(titulo1);

        Element director1 = documento.createElement("director");
        director1.setTextContent("Christopher Nolan");
        pelicula1.appendChild(director1);

        Element anio1 = documento.createElement("anio");
        anio1.setTextContent("2010");
        pelicula1.appendChild(anio1);

        Element rating1 = documento.createElement("rating");
        rating1.setTextContent("8.8");
        pelicula1.appendChild(rating1);

        // Peli2

        Element pelicula2 = documento.createElement("pelicula");
        pelicula2.setAttribute("id", "2");

        Element titulo2 = documento.createElement("titulo");
        titulo2.setTextContent("Interstellar");
        pelicula2.appendChild(titulo2);

        Element director2 = documento.createElement("director");
        director2.setTextContent("Christopher Nolan");
        pelicula2.appendChild(director2);

        Element anio2 = documento.createElement("anio");
        anio2.setTextContent("2014");
        pelicula2.appendChild(anio2);

        Element rating2 = documento.createElement("rating");
        rating2.setTextContent("8.6");
        pelicula2.appendChild(rating2);

        lista.appendChild(pelicula1);
        lista.appendChild(pelicula2);

        DOMSource sou = new DOMSource(lista);
        StreamResult resol = new StreamResult(RUTA);

        TransformerFactory transfactory = TransformerFactory.newDefaultInstance();
        Transformer former = transfactory.newTransformer();

        former.transform(sou, resol);
    }

    private static void leerXML() throws IOException, ParserConfigurationException, SAXException, DOMException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document documento = builder.parse(RUTA);
        
        NodeList lista = documento.getElementsByTagName("pelicula");
        int total = lista.getLength();
        
        String nombreraiz = lista.item(0).getParentNode().getNodeName();
        int contador = 0;
        
        System.out.println("Elemento raíz: " + nombreraiz);
        System.out.println("Total de películas: " + total);
        
        for (int i = 0; i < lista.getLength(); i++) {
            
            Element e = (Element) lista.item(i);
            
            String id = e.getAttribute("id");
            String titulo = e.getElementsByTagName("titulo").item(0).getTextContent();
            String director = e.getElementsByTagName("director").item(0).getTextContent();
            String año = e.getElementsByTagName("anio").item(0).getTextContent();
            String rating = e.getElementsByTagName("rating").item(0).getTextContent();
            contador++;
            
            System.out.println("\n--- Pelicula " + contador + " ---");
            System.out.println("ID: " + id);
            System.out.println("Título: " + titulo);
            System.out.println("Director: " + director);
            System.out.println("Año: " + año);
            System.out.println("Rating: " + rating);
        }
    }

    private static void modificarCrearNuevo() throws ParserConfigurationException, TransformerException, DOMException, TransformerConfigurationException, SAXException, IOException {
        System.out.println("\nIntroduce el titulo de la pelicula");
        String titulo = sc.nextLine();
        System.out.println("Introduce el director de la pelicula");
        String director = sc.nextLine();
        System.out.println("Introduce el año de la pelicula");
        String anio = sc.nextLine();
        System.out.println("Introduce el rating de la pelicula");
        String rating = sc.nextLine();
        
        int contador = 0;
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document documento = builder.parse(RUTA);
        
        // Modificar algo q ya esta creado
        
        NodeList lista = documento.getElementsByTagName("pelicula");
        
        for (int i = 0; i < lista.getLength(); i++) {
            
            Element e = (Element) lista.item(i);
            contador++;
            
            if (e.getAttribute("id").equals("2")) {
                
                e.getElementsByTagName("anio").item(0).setTextContent("22");
            }
            
        }
        
        //añadir nuevo
        
        Element principal = documento.getDocumentElement();
        
        Element peliN = documento.createElement("pelicula");
        peliN.setAttribute("id", String.valueOf(contador));
        
        Element tituloN = documento.createElement("titulo");
        tituloN.setTextContent(titulo);
        peliN.appendChild(tituloN);
        
        Element directorN = documento.createElement("director");
        directorN.setTextContent(director);
        peliN.appendChild(directorN);
        
        Element anioN = documento.createElement("anio");
        anioN.setTextContent(anio);
        peliN.appendChild(anioN);
        
        Element ratingN = documento.createElement("rating");
        ratingN.setTextContent(rating);
        peliN.appendChild(ratingN);
        
        principal.appendChild(peliN);
        
        DOMSource sou = new DOMSource(documento);
        StreamResult result = new StreamResult(new File(RUTAN));
        
        TransformerFactory factori = TransformerFactory.newDefaultInstance();
        Transformer trans = factori.newTransformer();
        
        trans.transform(sou, result);
        
        System.out.println("Añadido");
    }

}
