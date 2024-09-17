package Modelo;

public class Producto {
    private String codigo;
    private String nombre;
    private String material;
    private String color;

    // Constructor solo con los atributos que estás usando
    public Producto(String codigo, String nombre, String color, String material) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.color = color;
        this.material = material;
    }

    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Producto [Codigo=" + codigo + ", Nombre=" + nombre + ", Color=" + color + ", Material=" + material + "]";
    }
}
