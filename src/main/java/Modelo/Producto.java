package Modelo;

public class Producto {
    private String codigo;
    private String nombre;
    private String color;
    private String material;
    private int tiempoEnsamblaje;
    private double costoEnsamblaje;
    private int tiempoPintura;
    private double costoPintura;
    private int tiempoEmpaquetado;
    private double costoEmpaquetado;

    public Producto(String codigo, String nombre, String color, String material, 
                    int tiempoEnsamblaje, double costoEnsamblaje,
                    int tiempoPintura, double costoPintura,
                    int tiempoEmpaquetado, double costoEmpaquetado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.color = color;
        this.material = material;
        this.tiempoEnsamblaje = tiempoEnsamblaje;
        this.costoEnsamblaje = costoEnsamblaje;
        this.tiempoPintura = tiempoPintura;
        this.costoPintura = costoPintura;
        this.tiempoEmpaquetado = tiempoEmpaquetado;
        this.costoEmpaquetado = costoEmpaquetado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    public int getTiempoEnsamblaje() {
        return tiempoEnsamblaje;
    }

    public double getCostoEnsamblaje() {
        return costoEnsamblaje;
    }

    public int getTiempoPintura() {
        return tiempoPintura;
    }

    public double getCostoPintura() {
        return costoPintura;
    }

    public int getTiempoEmpaquetado() {
        return tiempoEmpaquetado;
    }

    public double getCostoEmpaquetado() {
        return costoEmpaquetado;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setTiempoEnsamblaje(int tiempoEnsamblaje) {
        this.tiempoEnsamblaje = tiempoEnsamblaje;
    }

    public void setCostoEnsamblaje(double costoEnsamblaje) {
        this.costoEnsamblaje = costoEnsamblaje;
    }

    public void setTiempoPintura(int tiempoPintura) {
        this.tiempoPintura = tiempoPintura;
    }

    public void setCostoPintura(double costoPintura) {
        this.costoPintura = costoPintura;
    }

    public void setTiempoEmpaquetado(int tiempoEmpaquetado) {
        this.tiempoEmpaquetado = tiempoEmpaquetado;
    }

    public void setCostoEmpaquetado(double costoEmpaquetado) {
        this.costoEmpaquetado = costoEmpaquetado;
    }

   

    public double calcularCostoTotal() {
        return (tiempoEnsamblaje * costoEnsamblaje) + (tiempoPintura * costoPintura) + (tiempoEmpaquetado * costoEmpaquetado);
    }

    public int calcularTiempoTotal() {
        return tiempoEnsamblaje + tiempoPintura + tiempoEmpaquetado;
    }

    @Override
    public String toString() {
        return "Producto [Codigo=" + codigo + ", Nombre=" + nombre + ", Color=" + color + 
               ", Material=" + material + ", TiempoEnsamblaje=" + tiempoEnsamblaje + 
               ", CostoEnsamblaje=" + costoEnsamblaje + ", TiempoPintura=" + tiempoPintura + 
               ", CostoPintura=" + costoPintura + ", TiempoEmpaquetado=" + tiempoEmpaquetado + 
               ", CostoEmpaquetado=" + costoEmpaquetado + "]";
    }
}
