package Modelo;

public class Producto {
    private String codigo;
    private String nombre;
    private String material;
    private String color;

    public Producto(String codigo, String nombre, String material, String color) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.material = material;
        this.color = color;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

   public int calcularTiempoEnsamblaje() {
        switch (material.toLowerCase()) {
            case "metal": return 15;
            case "madera": return 25;
            case "vidrio": return 10;
            case "nylon": return 20;
            case "hule": return 10;
            case "poliester": return 5;
            default: return 0;
        }
    }

    public int calcularTiempoPintura() {
        switch (color.toLowerCase()) {
            case "verde": return 15;
            case "negro": return 25;
            case "na": return 0;
            case "azul": return 20;
            case "rojo": return 10;
            case "amarillo": return 5;
            default: return 0;
        }
    }

    public double calcularCostoProduccion() {
        double costoEnsamblaje = obtenerCostoMaterial() * calcularTiempoEnsamblaje();
        double costoPintura = obtenerCostoPintura() * calcularTiempoPintura();
        return costoEnsamblaje + costoPintura;
    }

    private double obtenerCostoMaterial() {
        switch (material.toLowerCase()) {
            case "metal": return 3.0;
            case "madera": return 1.0;
            case "vidrio": return 6.0;
            case "nylon": return 2.0;
            case "hule": return 5.0;
            case "poliester": return 4.0;
            default: return 0.0;
        }
    }

    private double obtenerCostoPintura() {
        switch (color.toLowerCase()) {
            case "verde": return 3.0;
            case "negro": return 1.0;
            case "na": return 0.0;
            case "azul": return 2.0;
            case "rojo": return 5.0;
            case "amarillo": return 4.0;
            default: return 0.0;
        }
    }
}