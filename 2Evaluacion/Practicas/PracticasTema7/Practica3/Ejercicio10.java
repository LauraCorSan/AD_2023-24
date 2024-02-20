package Practica3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

public class Ejercicio10 {
    static final String RUTA = "Practica3/sucursales.xml";

    public static void main(String[] args) {
        try {
            File xmlFile = new File(RUTA);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Obtener la lista de todas las cuentas
            NodeList cuentasList = doc.getElementsByTagName("cuenta");

            // Recorrer cada cuenta
            for (int i = 0; i < cuentasList.getLength(); i++) {
                Element cuenta = (Element) cuentasList.item(i);
                // Obtener el saldo haber de la cuenta si existe
                NodeList saldosHaberList = cuenta.getElementsByTagName("saldohaber");
                if (saldosHaberList.getLength() > 0) {
                    int saldoHaber = Integer.parseInt(saldosHaberList.item(0).getTextContent());
                    // Verificar si el saldo haber es mayor de 10000
                    if (saldoHaber > 10000) {
                        String numeroCuenta = cuenta.getElementsByTagName("numero").item(0).getTextContent();
                        String nombreCuenta = cuenta.getElementsByTagName("nombre").item(0).getTextContent();

                        System.out.println("Número de Cuenta: " + numeroCuenta);
                        System.out.println("Nombre de Cuenta: " + nombreCuenta);
                        System.out.println("Saldo Haber: " + saldoHaber);
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
