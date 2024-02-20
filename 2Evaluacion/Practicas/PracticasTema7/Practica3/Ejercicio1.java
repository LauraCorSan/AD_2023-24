package Practica3;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {
            // Cargar el archivo XML
            File xmlFile = new File("Practica3/sucursales.xml");

            // Crear un DocumentBuilderFactory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Crear un DocumentBuilder
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parsear el archivo XML y obtener el documento
            Document doc = dBuilder.parse(xmlFile);

            // Normalizar el documento
            doc.getDocumentElement().normalize();

            // Obtener la lista de nodos de cuentas bancarias
            NodeList cuentaList = doc.getElementsByTagName("cuenta");

            // Iterar sobre la lista de cuentas bancarias
            for (int i = 0; i < cuentaList.getLength(); i++) {
                Element cuentaElement = (Element) cuentaList.item(i);

                // Obtener el tipo de cuenta
                String tipoCuenta = cuentaElement.getAttribute("tipo");

                // Verificar si el tipo de cuenta es "AHORRO"
                if (tipoCuenta.equals("AHORRO")) {
                    // Obtener los datos de la cuenta
                    String nombre = cuentaElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String numero = cuentaElement.getElementsByTagName("numero").item(0).getTextContent();
                    String saldoHaber = cuentaElement.getElementsByTagName("saldohaber").item(0).getTextContent();
                    String saldoDebe = cuentaElement.getElementsByTagName("saldodebe").item(0).getTextContent();

                    // Imprimir los datos de la cuenta
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Número: " + numero);
                    System.out.println("Saldo Haber: " + saldoHaber);
                    System.out.println("Saldo Debe: " + saldoDebe);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
