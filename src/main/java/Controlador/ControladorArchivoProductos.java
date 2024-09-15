package Controlador;

import Modelo.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorArchivoProductos {
    private final String rutaArchivo = "src/Controlador/productos.csv";

    public ControladorArchivoProductos() {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                    writer.write("codigo,nombre,color,material,tiempoEnsamblaje,costoEnsamblaje,tiempoPintura,costoPintura,tiempoEmpaquetado,costoEmpaquetado\n");
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo CSV: " + e.getMessage());
            }
        }
    }

    public void guardarProductosEnCSV(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("codigo,nombre,color,material,tiempoEnsamblaje,costoEnsamblaje,tiempoPintura,costoPintura,tiempoEmpaquetado,costoEmpaquetado\n");
            for (Producto producto : productos) {
                String linea = producto.getCodigo() + "," + producto.getNombre() + "," + producto.getColor() + "," +
                               producto.getMaterial() + "," + producto.getTiempoEnsamblaje() + "," + producto.getCostoEnsamblaje() + "," +
                               producto.getTiempoPintura() + "," + producto.getCostoPintura() + "," + producto.getTiempoEmpaquetado() + "," +
                               producto.getCostoEmpaquetado();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los productos en el archivo CSV: " + e.getMessage());
        }
    }

    public List<Producto> leerProductosDesdeCSV() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            reader.readLine(); // Saltar la cabecera
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                Producto producto = new Producto(
                    datos[0],    // codigo
                    datos[1],    // nombre
                    datos[2],    // color
                    datos[3],    // material
                    Integer.parseInt(datos[4]), // tiempoEnsamblaje
                    Double.parseDouble(datos[5]), // costoEnsamblaje
                    Integer.parseInt(datos[6]), // tiempoPintura
                    Double.parseDouble(datos[7]), // costoPintura
                    Integer.parseInt(datos[8]), // tiempoEmpaquetado
                    Double.parseDouble(datos[9])  // costoEmpaquetado
                );
                productos.add(producto);
            }
        } catch (IOException e) {
            System.out.println("Error al leer los productos desde el archivo CSV: " + e.getMessage());
        }
        return productos;
    }
}
