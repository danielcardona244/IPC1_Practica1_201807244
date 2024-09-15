package Controlador;

import Modelo.Producto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControladorArchivoProductos {
    
    
    private final String rutaArchivo = "src/Controlador/productos.csv";//ruta del csv

    // Verifica si el archivo existe, si no, lo crea con las cabeceras
    public ControladorArchivoProductos() {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                // Escribimos las cabeceras la primera vez
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                    writer.write("id,nombre,precio,cantidad\n");
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo CSV: " + e.getMessage());
            }
        }
    }

    /*    //guardar una lista completa de productos en el archivo CSV
    public void guardarProductosEnCSV(List<Producto> productos) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
    writer.write("id,nombre,precio,cantidad\n"); // Cabecera
    for (Producto producto : productos) {
    String linea = producto.getId() + "," + producto.getNombre() + "," + producto.getPrecio() + "," + producto.getCantidad();
    writer.write(linea);
    writer.newLine();
    }
    } catch (IOException e) {
    System.out.println("Error al guardar los productos en el archivo CSV: " + e.getMessage());
    }
    }*/

    // leer los productos del archivo CSV
    public List<Producto> leerProductosDesdeCSV() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            reader.readLine(); // Saltar la cabecera
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Producto producto = new Producto(
                    Integer.parseInt(datos[0]),  // id
                    datos[1],                    // nombre
                    Double.parseDouble(datos[2]), // precio
                    Integer.parseInt(datos[3])   // cantidad
                );
                productos.add(producto);
            }
        } catch (IOException e) {
            System.out.println("Error al leer los productos desde el archivo CSV: " + e.getMessage());
        }
        return productos;
    }

    // agregar un producto individual al archivo CSV
    public void agregarProductoAlCSV(Producto producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            String linea = producto.getId() + "," + producto.getNombre() + "," + producto.getPrecio() + "," + producto.getCantidad();
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al agregar el producto al archivo CSV: " + e.getMessage());
        }
    }

    // para eliminar un producto del archivo CSV por su ID
    public void eliminarProductoDeCSV(int idProducto) {
        List<Producto> productos = leerProductosDesdeCSV();
        productos.removeIf(producto -> producto.getId() == idProducto); // Eliminar por ID
        guardarProductosEnCSV(productos); // Guardar la lista actualizada
    }

    // para modificar un producto en el archivo CSV
    public void modificarProductoEnCSV(Producto productoModificado) {
        List<Producto> productos = leerProductosDesdeCSV();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == productoModificado.getId()) {
                productos.set(i, productoModificado); // Modificar el producto
                break;
            }
        }
        guardarProductosEnCSV(productos); // Guardar la lista actualizada
    }
}

   
    

