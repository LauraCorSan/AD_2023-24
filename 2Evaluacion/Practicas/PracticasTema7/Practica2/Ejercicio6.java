package Practica2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Ejercicio6 {
    public static void main(String[] args) {
        try {
            // Crear un constructor de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = builder.parse("Practica2/productos.xml");

            // Obtener la lista de nodos <produc>
            NodeList productos = doc.getElementsByTagName("produc");

            // Iterar sobre los nodos de productos para encontrar los que cumplen la
            // condición
            for (int i = 0; i < productos.getLength(); i++) {
                Node productoNode = productos.item(i);
                NodeList hijosProducto = productoNode.getChildNodes();
                String denominacion = "";
                int stockActual = 0;
                int stockMinimo = 0;

                // Buscar la denominación, el stock actual y el stock mínimo del producto
                for (int j = 0; j < hijosProducto.getLength(); j++) {
                    Node hijo = hijosProducto.item(j);
                    if (hijo.getNodeName().equals("denominacion")) {
                        denominacion = hijo.getTextContent();
                    } else if (hijo.getNodeName().equals("stock_actual")) {
                        stockActual = Integer.parseInt(hijo.getTextContent());
                    } else if (hijo.getNodeName().equals("stock_minimo")) {
                        stockMinimo = Integer.parseInt(hijo.getTextContent());
                    }
                }

                // Verificar si el stock mínimo es mayor que el stock actual
                if (stockMinimo > stockActual) {
                    // Imprimir los detalles del producto
                    System.out.println("Producto " + (i + 1) + ":");
                    System.out.println("Denominación: " + denominacion);
                    System.out.println("Stock Actual: " + stockActual);
                    System.out.println("Stock Mínimo: " + stockMinimo);
                    System.out.println("---------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
