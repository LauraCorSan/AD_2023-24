package Practica3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

public class Ejercicio9 {
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
                String codigo = sucursal.getAttribute("codigo");
                NodeList cuentasList = sucursal.getElementsByTagName("cuenta");

                // Inicializar la suma de las aportaciones de las cuentas del tipo "PENSIONES"
                int sumaAportaciones = 0;

                // Recorrer todas las cuentas de la sucursal
                for (int j = 0; j < cuentasList.getLength(); j++) {
                    Element cuenta = (Element) cuentasList.item(j);
                    // Verificar si la cuenta es del tipo "PENSIONES"
                    if (cuenta.getAttribute("tipo").equals("PENSIONES")) {
                        // Obtener la aportación y sumarla
                        int aportacion = Integer
                                .parseInt(cuenta.getElementsByTagName("aportacion").item(0).getTextContent());
                        sumaAportaciones += aportacion;
                    }
                }
                System.out.println(
                        "Código de Sucursal: " + codigo + ", Suma de Aportaciones de Pensiones: " + sumaAportaciones);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
