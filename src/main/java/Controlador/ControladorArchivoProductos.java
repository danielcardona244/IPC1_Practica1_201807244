package Controlador;

import Modelo.Producto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorArchivoProductos {
    private final String rutaArchivo = "C:\\Users\\cardo\\OneDrive\\Escritorio\\productos1.csv";

    public List<Producto> leerProductosDesdeCSV() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            lector.readLine(); // Saltar la cabecera
            while ((linea = lector.readLine()) != null) {
                String[] contenido = linea.split(",");
                if (contenido.length == 4) {
                    Producto producto = new Producto(
                        contenido[0], // Código
                        contenido[1], // Nombre
                        contenido[2], // Material
                        contenido[3]  // Color
                    );
                    productos.add(producto);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return productos;
    }
}