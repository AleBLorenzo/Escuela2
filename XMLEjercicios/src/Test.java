
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Test {
  public static void main(String[] args) throws Exception {

    try {

      // Lo clasico siempre hayq poner esto para escribir un XML.
      // Enm este caso esto seria al principio.

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder build = factory.newDocumentBuilder();
      DOMImplementation implement = build.getDOMImplementation();

      Document documento = implement.createDocument(null, "concepcionario", null);
      documento.setXmlVersion("1.0"); // opcional

      // aca hay q ir paso a paso añadiendo cada hijo a su correspodiente
      // padre para q al final te salga aqui hay q tener mucho ojo y cuidado con
      // el codigo q pones.

      Element Carros = documento.createElement("Carros");

      Element Carro = documento.createElement("Carro");

      Element Marca = documento.createElement("Marca");
      Text texto = documento.createTextNode("Carrazo");
      Marca.appendChild(texto);
      Carro.appendChild(Marca);

      Element Modelo = documento.createElement("Modelo");
      Text texto2 = documento.createTextNode("88");
      Modelo.appendChild(texto2);
      Carro.appendChild(Modelo);

      Carros.appendChild(Carro);
      documento.getDocumentElement().appendChild(Carros);

      // Lo clasico siempre hayq poner esto para escribir un XML.
      // Enm este caso esto seria al final.

      Source sou = new DOMSource(documento);

      Result result = new StreamResult(new File("conse.xml"));

      Transformer transfo = TransformerFactory.newInstance().newTransformer();
      transfo.transform(sou, result);

      try {

        // Lo clasico siempre hayq poner esto para Leer un XML.
        // Enm este caso esto seria al principio.

        DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder build1 = factory1.newDocumentBuilder();

        Document documento1 = build1.parse(new File("conse.xml"));

        NodeList lista = documento1.getElementsByTagName("Carro");

        // aca siempore hay q empezar a ller con un bucle for creando nodos q despues
        // los pasamos a elemetos
        // y en el caso q tengamos mas elementos dentro de otro creamos otro nodo los
        // como el hijo del elemto q ya vimos
        // y despies no volvemos a recorrer y en este caso mostrar los datos con un
        // print
        // dentro del if este proceso re repite hazta llegar al lugar de tus datos de
        // interes.

        for (int i = 0; i < lista.getLength(); i++) {

          Node nodo = lista.item(i);

          if (nodo.getNodeType() == nodo.ELEMENT_NODE) {
            Element e = (Element) nodo;
            NodeList hijo = e.getChildNodes();

            for (int j = 0; j < hijo.getLength(); j++) {

              Node hijos = hijo.item(j);

              if (hijos.getNodeType() == hijos.ELEMENT_NODE) {

                Element eh = (Element) hijos;

                System.out.println("Datos" + eh.getNodeName() + eh.getTextContent());

              }
            }
          }

        }

      } catch (Exception e) {
        e.printStackTrace();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
