package Practica3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

public class Ejercicio4 {
    static final String RUTA = "Practica3/sucursales.xml";

    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
            File xmlFile = new File(RUTA);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Obtener la lista de todas las sucursales
            NodeList sucursalesList = doc.getElementsByTagName("sucursal");

            // Recorrer cada sucursal
            for (int i = 0; i < sucursalesList.getLength(); i++) {
                Element sucursal = (Element) sucursalesList.item(i);
                String codigo = sucursal.getAttribute("codigo");
                String director = sucursal.getElementsByTagName("director").item(0).getTextContent();

                // Calcular el saldo total haber
                NodeList cuentasList = sucursal.getElementsByTagName("cuenta");
                int saldoTotalHaber = 0;
                for (int j = 0; j < cuentasList.getLength(); j++) {
                    Element cuenta = (Element) cuentasList.item(j);
                    NodeList saldosHaberList = cuenta.getElementsByTagName("saldohaber");
                    if (saldosHaberList.getLength() > 0) {
                        saldoTotalHaber += Integer.parseInt(saldosHaberList.item(0).getTextContent());
                    }
                }

                System.out.println("Código de Sucursal: " + codigo + ", Director: " + director + ", Saldo Total Haber: "
                        + saldoTotalHaber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
