

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

public class Eje2 {

    public static void main(String[] args) {

    
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            DOMImplementation implement = build.getDOMImplementation();

            Document documento = implement.createDocument(null,"empleados", null);
            documento.setXmlVersion("1.0");

            Element empleados = documento.createElement("empleados");

            Element empleado = documento.createElement("empleado");
            empleado.setAttribute("id", "001");
        

            Element nombre = documento.createElement("nombre");
            Text txNombre = documento.createTextNode("Ana");
            nombre.appendChild(txNombre);
            empleado.appendChild(nombre);

            Element  departamento = documento.createElement("departamento");
             Text txdep = documento.createTextNode("Recursos Humanos");
            departamento.appendChild(txdep);
            empleado.appendChild(departamento);

            Element salario = documento.createElement("salario");
             Text txsal = documento.createTextNode("45000");
           salario.appendChild(txsal);
            empleado.appendChild(salario);

            empleados.appendChild(empleado);

              Element empleado1 = documento.createElement("empleado");
            empleado1.setAttribute("id", "002");
        

            Element nombre1 = documento.createElement("nombre");
           Text txNombre1 = documento.createTextNode("Rosa");
           nombre1.appendChild(txNombre1);
            empleado1.appendChild(nombre1);

           Element  departamento1 = documento.createElement("departamento");
             Text txdep1 = documento.createTextNode("Obrero");
            departamento1.appendChild(txdep1);
            empleado1.appendChild(departamento1);
            
            Element salario1 = documento.createElement("salario");
             Text txsal1 = documento.createTextNode("35000");
           salario1.appendChild(txsal1);
            empleado1.appendChild(salario1);

            empleados.appendChild(empleado1);

            Element empleado2 = documento.createElement("empleado");
            empleado2.setAttribute("id", "003");
        

            Element nombre2 = documento.createElement("nombre");
           Text txNombre2 = documento.createTextNode("Juan");
           nombre2.appendChild(txNombre2);
            empleado2.appendChild(nombre2);

           Element  departamento2 = documento.createElement("departamento");
             Text txdep2 = documento.createTextNode("Administracion");
            departamento2.appendChild(txdep2);
            empleado2.appendChild(departamento2);
            
            Element salario2 = documento.createElement("salario");
             Text txsal2 = documento.createTextNode("55000");
           salario2.appendChild(txsal2);
            empleado2.appendChild(salario2);

            empleados.appendChild(empleado2);


            documento.getDocumentElement().appendChild(empleados);

            Source sou = new DOMSource(documento);
            Result resul = new StreamResult(new File ("empleados.xml"));

            Transformer transf = TransformerFactory.newInstance().newTransformer();
            transf.transform(sou, resul);
            
            
            
        } catch (Exception e) {
        }
    }

}
