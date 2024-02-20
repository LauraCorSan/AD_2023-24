package Practica2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Ejercicio2 {
    public static void main(String[] args) {
        try {
            // Crear un constructor de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = builder.parse("Practica2/productos.xml");

            // Obtener la lista de nodos <produc>
            NodeList productos = doc.getElementsByTagName("produc");

            // Iterar sobre los nodos de productos para encontrar las placas base
            for (int i = 0; i < productos.getLength(); i++) {
                Node productoNode = productos.item(i);
                NodeList hijosProducto = productoNode.getChildNodes();
                String denominacion = "";

                // Buscar la denominación del producto
                for (int j = 0; j < hijosProducto.getLength(); j++) {
                    Node hijo = hijosProducto.item(j);
                    if (hijo.getNodeName().equals("denominacion")) {
                        denominacion = hijo.getTextContent();
                        break;
                    }
                }

                // Verificar si la denominación contiene la palabra "Placa Base"
                if (denominacion.contains("Placa Base")) {
                    // Imprimir los detalles del producto
                    System.out.println("Producto " + (i + 1) + ":");
                    System.out.println("Denominación: " + denominacion);
                    // Puedes imprimir otros detalles del producto si lo deseas
                    System.out.println("---------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
