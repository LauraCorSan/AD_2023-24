package Practica3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

public class Ejercicio5 {
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
                    // Imprimir todos los elementos de la sucursal
                    System.out.println("Sucursal con más de 3 cuentas:");
                    System.out.println("Código de Sucursal: " + sucursal.getAttribute("codigo"));
                    System.out
                            .println("Director: " + sucursal.getElementsByTagName("director").item(0).getTextContent());
                    System.out.println(
                            "Población: " + sucursal.getElementsByTagName("poblacion").item(0).getTextContent());
                    for (int j = 0; j < cuentasList.getLength(); j++) {
                        Element cuenta = (Element) cuentasList.item(j);
                        System.out.println("Cuenta " + (j + 1) + ":");
                        System.out.println(
                                "   Nombre: " + cuenta.getElementsByTagName("nombre").item(0).getTextContent());
                        System.out.println("   Tipo: " + cuenta.getAttribute("tipo"));
                        System.out.println(
                                "   Número: " + cuenta.getElementsByTagName("numero").item(0).getTextContent());
                        if (cuenta.getElementsByTagName("saldohaber").getLength() > 0) {
                            System.out.println("   Saldo Haber: "
                                    + cuenta.getElementsByTagName("saldohaber").item(0).getTextContent());
                        }
                        if (cuenta.getElementsByTagName("saldodebe").getLength() > 0) {
                            System.out.println("   Saldo Debe: "
                                    + cuenta.getElementsByTagName("saldodebe").item(0).getTextContent());
                        }
                        if (cuenta.getElementsByTagName("aportacion").getLength() > 0) {
                            System.out.println("   Aportación: "
                                    + cuenta.getElementsByTagName("aportacion").item(0).getTextContent());
                        }
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
