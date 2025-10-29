package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, SAXException, IOException, TransformerException {
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder build = factory.newDocumentBuilder();
            
            Document documento = build.parse(new FileInputStream("xml/src/main/resources/inventario.xml"));

            NodeList lista = documento.getElementsByTagName("producto");

            System.out.println("total "+ lista.getLength());
            for (int i = 0; i < lista.getLength(); i++) {
              
              
                    Element e = (Element) lista.item(i) ;
                    
                    System.out.println(e.getParentNode().getNodeName());
                    System.out.println(e.getAttribute("id"));
                    System.out.println(e.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println(e.getElementsByTagName("categoria").item(0).getTextContent());
                    System.out.println(e.getElementsByTagName("precio").item(0).getTextContent());
                    System.out.println(e.getElementsByTagName("stock").item(0).getTextContent());

                
              
            }

            Element elemnto = documento.getDocumentElement();
            Element provedores = documento.createElement("proveedores");
            Element proveedor = documento.createElement("provedor");
            proveedor.setAttribute("id", "PR01");
            Element nombre = documento.createElement("nombre");
            nombre.setTextContent("Informatica globo");
            proveedor.appendChild(nombre);

            Element ciudad = documento.createElement("ciudad");
             ciudad.setTextContent("asdasd");
            proveedor.appendChild( ciudad);
            Element telefono = documento.createElement("telefono");
            telefono.setTextContent("fsdgfgdf");
            proveedor.appendChild(telefono);

            provedores.appendChild(proveedor);
            elemnto.appendChild(provedores);

               Source sou = new DOMSource(documento);
            Result resul = new StreamResult("xml/src/main/resources/inventario.xml");


            TransformerFactory trnsf = TransformerFactory.newInstance();
            Transformer former = trnsf.newTransformer();

         
            former.transform(sou, resul);



        } catch (ParserConfigurationException ex) {
        }
        

    }
}