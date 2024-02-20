package Practica2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Ejercicio8 {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse("Practica2/productos.xml");

            NodeList productos = doc.getElementsByTagName("produc");

            // Inicializar el precio máximo y el nombre del producto más caro
            int precioMaximo = Integer.MIN_VALUE;
            String productoMasCaro = "";

            // Iterar sobre los nodos de productos para encontrar el más caro
            for (int i = 0; i < productos.getLength(); i++) {
                Node productoNode = productos.item(i);
                NodeList hijosProducto = productoNode.getChildNodes();
                String denominacion = "";
                int precio = 0;

                // Buscar la denominación y el precio del producto
                for (int j = 0; j < hijosProducto.getLength(); j++) {
                    Node hijo = hijosProducto.item(j);
                    if (hijo.getNodeName().equals("denominacion")) {
                        denominacion = hijo.getTextContent();
                    } else if (hijo.getNodeName().equals("precio")) {
                        precio = Integer.parseInt(hijo.getTextContent());
                    }
                }

                // Verificar si el precio es mayor que el máximo actual
                if (precio > precioMaximo) {
                    precioMaximo = precio;
                    productoMasCaro = denominacion;
                }
            }

            System.out.println("El producto más caro es: " + productoMasCaro);
            System.out.println("Precio: " + precioMaximo + "€");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
