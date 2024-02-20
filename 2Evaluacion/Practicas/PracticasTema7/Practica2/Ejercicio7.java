package Practica2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Ejercicio7 {
    public static void main(String[] args) {
        try {
            // Crear un constructor de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse("Practica2/productos.xml");

            // Obtener la lista de nodos <produc>
            NodeList productos = doc.getElementsByTagName("produc");

            // Iterar sobre los nodos de productos para encontrar los que cumplen las
            // condiciones
            for (int i = 0; i < productos.getLength(); i++) {
                Node productoNode = productos.item(i);
                NodeList hijosProducto = productoNode.getChildNodes();
                String denominacion = "";
                int stockActual = 0;
                int stockMinimo = 0;
                int codZona = 0;
                int precio = 0;

                // Buscar la denominación, el stock actual, el stock mínimo y el código de zona
                for (int j = 0; j < hijosProducto.getLength(); j++) {
                    Node hijo = hijosProducto.item(j);
                    if (hijo.getNodeName().equals("denominacion")) {
                        denominacion = hijo.getTextContent();
                    } else if (hijo.getNodeName().equals("stock_actual")) {
                        stockActual = Integer.parseInt(hijo.getTextContent());
                    } else if (hijo.getNodeName().equals("stock_minimo")) {
                        stockMinimo = Integer.parseInt(hijo.getTextContent());
                    } else if (hijo.getNodeName().equals("cod_zona")) {
                        codZona = Integer.parseInt(hijo.getTextContent());
                    } else if (hijo.getNodeName().equals("precio")) {
                        precio = Integer.parseInt(hijo.getTextContent());
                    }
                }

                // Verificar si el stock mínimo es mayor que el stock actual y si el código de
                // zona es 40
                if (stockMinimo > stockActual && codZona == 40) {
                    System.out.println("Nombre del Producto: " + denominacion);
                    System.out.println("Precio: " + precio);
                    System.out.println("---------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
