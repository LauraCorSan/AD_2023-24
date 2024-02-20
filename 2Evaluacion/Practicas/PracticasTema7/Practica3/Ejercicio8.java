package Practica3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

public class Ejercicio8 {
    static final String RUTA = "Practica3/sucursales.xml";

    public static void main(String[] args) {
        try {
            File xmlFile = new File(RUTA);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList sucursalesList = doc.getElementsByTagName("sucursal");

            // Contador de sucursales con población Madrid
            int sucursalesMadrid = 0;

            // Recorrer cada sucursal
            for (int i = 0; i < sucursalesList.getLength(); i++) {
                Element sucursal = (Element) sucursalesList.item(i);
                String poblacion = sucursal.getElementsByTagName("poblacion").item(0).getTextContent();

                // Verificar si la población es Madrid
                if (poblacion.equals("Madrid")) {
                    sucursalesMadrid++;
                }
            }

            System.out.println("Número de sucursales con población Madrid: " + sucursalesMadrid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
