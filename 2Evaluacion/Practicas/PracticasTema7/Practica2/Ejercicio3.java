package Practica2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Ejercicio3 {
    public static void main(String[] args) {
        try {
            // Crear un constructor de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = builder.parse("Practica2/productos.xml");

            // Obtener la lista de nodos <produc>
            NodeList productos = doc.getElementsByTagName("produc");

            // Iterar sobre los nodos de productos para encontrar los que cumplen las
            // condiciones
            for (int i = 0; i < productos.getLength(); i++) {
                Node productoNode = productos.item(i);
                NodeList hijosProducto = productoNode.getChildNodes();
                String precioStr = "";
                String codZonaStr = "";

                // Buscar el precio y el código de zona del producto
                for (int j = 0; j < hijosProducto.getLength(); j++) {
                    Node hijo = hijosProducto.item(j);
                    if (hijo.getNodeName().equals("precio")) {
                        precioStr = hijo.getTextContent();
                    } else if (hijo.getNodeName().equals("cod_zona")) {
                        codZonaStr = hijo.getTextContent();
                    }
                }

                // Convertir el precio y el código de zona a números enteros
                int precio = Integer.parseInt(precioStr);
                int codZona = Integer.parseInt(codZonaStr);

                // Verificar las condiciones
                if (precio > 60 && codZona == 20) {
                    // Imprimir los detalles del producto
                    System.out.println("Producto " + (i + 1) + ":");
                    // Puedes imprimir otros detalles del producto si lo deseas
                    System.out.println("Precio: " + precio + "€");
                    System.out.println("Código de Zona: " + codZona);
                    System.out.println("---------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
