package Controlador;

import Modelo.Producto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorArchivoProductos {
    private final String rutaArchivo = "C:\\Users\\cardo\\OneDrive\\Escritorio\\Productos.csv";

    // Método que lee el CSV y devuelve una lista de productos
    public List<Producto> leerProductosDesdeCSV() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            lector.readLine(); // Saltar la cabecera
            while ((linea = lector.readLine()) != null) {
                String[] contenido = linea.split(",");
                Producto producto = new Producto(
                    contenido[0], // Código
                    contenido[1], // Nombre
                    contenido[2], // Material
                    contenido[3]  // Color
                );
                productos.add(producto);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return productos;
    }
}
