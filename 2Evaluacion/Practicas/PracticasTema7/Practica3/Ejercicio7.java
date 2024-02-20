package Practica3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

public class Ejercicio7 {
    static final String RUTA = "Practica3/sucursales.xml";

    public static void main(String[] args) {
        try {
            File xmlFile = new File(RUTA);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList sucursalesList = doc.getElementsByTagName("sucursal");

            // Recorrer cada sucursal
            for (int i = 0; i < sucursalesList.getLength(); i++) {
                Element sucursal = (Element) sucursalesList.item(i);
                NodeList cuentasList = sucursal.getElementsByTagName("cuenta");

                // Verificar si la sucursal tiene más de 3 cuentas
                if (cuentasList.getLength() > 3) {
                    // Obtener el nodo del director
                    String director = sucursal.getElementsByTagName("director").item(0).getTextContent();

                    // Obtener el nodo de la población
                    String poblacion = sucursal.getElementsByTagName("poblacion").item(0).getTextContent();

                    // Imprimir los nodos del director y la población
                    System.out.println("Nodo del Director: " + director);
                    System.out.println("Nodo de la Población: " + poblacion);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
