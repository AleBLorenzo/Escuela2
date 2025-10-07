

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
import org.w3c.dom.Text;

public class Test {
    public static void main(String[] args) throws Exception {
        
      try {
          
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            DOMImplementation implement = build.getDOMImplementation();
    
            Document documento = implement.createDocument(null, "concepcionario", null);
            documento.setXmlVersion("1.0");

            Element Carros = documento.createElement("Carros");

            Element Carro = documento.createElement("Carro");

            Element Marca = documento.createElement("Marca");
            Text texto = documento.createTextNode("Carrazo");
            Marca.appendChild(texto);
            Carro.appendChild(Marca);
           
            documento.getDocumentElement().appendChild(Carros);


            Source sou = new DOMSource(documento);

            Result result = new StreamResult(new File("conse.xml"));


            Transformer transfo = TransformerFactory.newInstance().newTransformer();
            transfo.transform(sou, result);



      } catch (Exception e) {
      }

    }
}
