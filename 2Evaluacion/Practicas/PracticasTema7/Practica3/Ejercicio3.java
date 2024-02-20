package Practica3;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio3 {
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

                // Verificar si el código de la sucursal es "SUC3"
                if (codigoSucursal.equals("SUC3")) {
                    // Obtener la lista de cuentas bancarias de la sucursal
                    NodeList cuentaList = sucursalElement.getElementsByTagName("cuenta");

                    // Iterar sobre la lista de cuentas bancarias de la sucursal
                    for (int j = 0; j < cuentaList.getLength(); j++) {
                        Element cuentaElement = (Element) cuentaList.item(j);

                        // Obtener el tipo de cuenta
                        String tipoCuenta = cuentaElement.getAttribute("tipo");

                        // Verificar si el tipo de cuenta es "PENSIONES"
                        if (tipoCuenta.equals("PENSIONES")) {
                            // Obtener los datos
                            String nombre = cuentaElement.getElementsByTagName("nombre").item(0).getTextContent();
                            String numero = cuentaElement.getElementsByTagName("numero").item(0).getTextContent();
                            String aportacion = cuentaElement.getElementsByTagName("aportacion").item(0)
                                    .getTextContent();

                            System.out.println("Nombre: " + nombre);
                            System.out.println("Número: " + numero);
                            System.out.println("Aportación: " + aportacion);
                            System.out.println();
                        }
                    }
                    break; // Salir del bucle al encontrar sucursal "SUC3"
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
