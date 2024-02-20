package Practica3;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio2 {
    static final String RUTA = "Practica3/sucursales.xml";

    public static void main(String[] args) {
        try {
            File xmlFile = new File(RUTA);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // Obtener la lista de nodos de sucursales
            NodeList sucursalList = doc.getElementsByTagName("sucursal");

            // Iterar sobre la lista de sucursales
            for (int i = 0; i < sucursalList.getLength(); i++) {
                Element sucursalElement = (Element) sucursalList.item(i);

                // Obtener el código de la sucursal
                String codigoSucursal = sucursalElement.getAttribute("codigo");

                // Obtener la lista de cuentas bancarias de la sucursal
                NodeList cuentaList = sucursalElement.getElementsByTagName("cuenta");

                // Contador para el número de cuentas de tipo "AHORRO"
                int cuentasAhorro = 0;

                // Iterar sobre la lista de cuentas bancarias de la sucursal
                for (int j = 0; j < cuentaList.getLength(); j++) {
                    Element cuentaElement = (Element) cuentaList.item(j);

                    // Obtener el tipo de cuenta
                    String tipoCuenta = cuentaElement.getAttribute("tipo");

                    // Verificar si el tipo de cuenta es "AHORRO"
                    if (tipoCuenta.equals("AHORRO")) {
                        cuentasAhorro++;
                    }
                }

                System.out.println("Sucursal: " + codigoSucursal + ", Cuentas AHORRO: " + cuentasAhorro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
