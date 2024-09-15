package Modelo;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;


    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método toString para facilitar la impresión de un producto
    @Override
    public String toString() {
        return "Producto [ID=" + id + ", Nombre=" + nombre + ", Precio=" + precio + ", Cantidad=" + cantidad + "]";
    }
}
