package Practica2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {
            // Crear un constructor de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document doc = builder.parse("Practica2/productos.xml");

            // Obtener la lista de nodos <denominacion> y <precio>
            NodeList denominaciones = doc.getElementsByTagName("denominacion");
            NodeList precios = doc.getElementsByTagName("precio");

            // Imprimir los nodos <denominacion> y <precio> de cada producto
            for (int i = 0; i < denominaciones.getLength(); i++) {
                Node denominacionNode = denominaciones.item(i);
                Node precioNode = precios.item(i);

                String denominacion = denominacionNode.getTextContent();
                String precio = precioNode.getTextContent();

                System.out.println("Producto " + (i + 1) + ":");
                System.out.println("Denominación: " + denominacion);
                System.out.println("Precio: " + precio);
                System.out.println("---------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
