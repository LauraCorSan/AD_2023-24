package Practica2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Ejercicio10 {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse("Practica2/productos.xml");

            NodeList productos = doc.getElementsByTagName("produc");

            int precioMaximoZona10 = Integer.MIN_VALUE;
            String productoMasCaroZona10 = "";

            // Iterar sobre los nodos de productos para encontrar el más caro de la zona 10
            for (int i = 0; i < productos.getLength(); i++) {
                Node productoNode = productos.item(i);
                NodeList hijosProducto = productoNode.getChildNodes();
                String denominacion = "";
                int precio = 0;
                int codZona = 0;

                for (int j = 0; j < hijosProducto.getLength(); j++) {
                    Node hijo = hijosProducto.item(j);
                    if (hijo.getNodeName().equals("denominacion")) {
                        denominacion = hijo.getTextContent();
                    } else if (hijo.getNodeName().equals("precio")) {
                        precio = Integer.parseInt(hijo.getTextContent());
                    } else if (hijo.getNodeName().equals("cod_zona")) {
                        codZona = Integer.parseInt(hijo.getTextContent());
                    }
                }

                // Verificar si el producto pertenece a la zona 10 y si su precio es mayor que
                // el máximo actual
                if (codZona == 10 && precio > precioMaximoZona10) {
                    precioMaximoZona10 = precio;
                    productoMasCaroZona10 = denominacion;
                }
            }

            System.out.println("El producto más caro de la zona 10 es: " + productoMasCaroZona10);
            System.out.println("Precio: " + precioMaximoZona10 + " euros");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
