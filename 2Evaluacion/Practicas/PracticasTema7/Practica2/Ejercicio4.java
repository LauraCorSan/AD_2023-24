package Practica2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Ejercicio4 {
    public static void main(String[] args) {
        try {
            // Crear un constructor de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = builder.parse("Practica2/productos.xml");

            // Obtener la lista de nodos <produc>
            NodeList productos = doc.getElementsByTagName("produc");

            // Contador para almacenar el número de productos que cumplen las condiciones
            int contador = 0;

            // Iterar sobre los nodos de productos para contar los que cumplen las
            // condiciones
            for (int i = 0; i < productos.getLength(); i++) {
                Node productoNode = productos.item(i);
                NodeList hijosProducto = productoNode.getChildNodes();
                String denominacion = "";
                String codZonaStr = "";

                // Buscar la denominación y el código de zona del producto
                for (int j = 0; j < hijosProducto.getLength(); j++) {
                    Node hijo = hijosProducto.item(j);
                    if (hijo.getNodeName().equals("denominacion")) {
                        denominacion = hijo.getTextContent();
                    } else if (hijo.getNodeName().equals("cod_zona")) {
                        codZonaStr = hijo.getTextContent();
                    }
                }

                // Verificar si el producto es una memoria y si es de la zona 10
                if (denominacion.contains("Memoria") && codZonaStr.equals("10")) {
                    contador++;
                }
            }

            // Imprimir el número de productos que cumplen las condiciones
            System.out.println("Número de productos que son memorias y de la zona 10: " + contador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
