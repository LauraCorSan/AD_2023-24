package Practica3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

public class Ejercicio11 {
    static final String RUTA = "Practica3/sucursales.xml";

    public static void main(String[] args) {
        try {
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
                NodeList cuentasList = sucursal.getElementsByTagName("cuenta");

                // Contador de cuentas del tipo AHORRO
                int cuentasAhorro = 0;
                // Suma del saldo debe de las cuentas del tipo AHORRO
                int sumaSaldoDebe = 0;

                // Recorrer todas las cuentas de la sucursal
                for (int j = 0; j < cuentasList.getLength(); j++) {
                    Element cuenta = (Element) cuentasList.item(j);
                    // Verificar si la cuenta es del tipo AHORRO
                    if (cuenta.getAttribute("tipo").equals("AHORRO")) {
                        cuentasAhorro++;
                        // Obtener el saldo debe y sumarlo
                        int saldoDebe = Integer
                                .parseInt(cuenta.getElementsByTagName("saldodebe").item(0).getTextContent());
                        sumaSaldoDebe += saldoDebe;
                    }
                }
                // Verificar si la sucursal tiene más de 3 cuentas del tipo AHORRO
                if (cuentasAhorro > 3) {
                    System.out.println("Código de Sucursal: " + codigo
                            + ", Suma del Saldo Debe de las Cuentas del Tipo AHORRO: " + sumaSaldoDebe);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
