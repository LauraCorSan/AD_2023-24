package Practica2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Ejercicio5 {
    public static void main(String[] args) {
        try {
            // Crear un constructor de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = builder.parse("Practica2/productos.xml");

            // Obtener la lista de nodos <produc>
            NodeList productos = doc.getElementsByTagName("produc");

            // Inicializar variables para calcular la suma y el número de productos micros
            int sumaPrecios = 0;
            int numMicros = 0;

            // Iterar sobre los nodos de productos para encontrar los micros y calcular la
            // suma de precios
            for (int i = 0; i < productos.getLength(); i++) {
                Node productoNode = productos.item(i);
                NodeList hijosProducto = productoNode.getChildNodes();
                String denominacion = "";
                String precioStr = "";

                // Buscar la denominación y el precio del producto
                for (int j = 0; j < hijosProducto.getLength(); j++) {
                    Node hijo = hijosProducto.item(j);
                    if (hijo.getNodeName().equals("denominacion")) {
                        denominacion = hijo.getTextContent();
                    } else if (hijo.getNodeName().equals("precio")) {
                        precioStr = hijo.getTextContent();
                    }
                }

                // Verificar si el producto es un micro
                if (denominacion.contains("Micro")) {
                    // Incrementar el número de productos micros
                    numMicros++;

                    // Sumar el precio del producto
                    sumaPrecios += Integer.parseInt(precioStr);
                }
            }

            // Calcular la media de precios de los micros
            double mediaPreciosMicros = (double) sumaPrecios / numMicros;

            // Imprimir la media de precios de los micros
            System.out.println("La media de precio de los micros es: " + mediaPreciosMicros + "€");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
