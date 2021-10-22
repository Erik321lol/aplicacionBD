package xyz.mamposteria.aplicacionbd.models;

public class productos1 {
    private int  cod_producto ;
    private String nombre ;
    private String estado ;
    private String marca ;
    private double precio ;
    private String medida ;
    private int cantidad ;
    private int cod_proveedor ;
    private int cod_tipo_vehiculo ;
    private int cod_unidad_medida ;

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCod_proveedor() {
        return cod_proveedor;
    }

    public void setCod_proveedor(int cod_proveedor) {
        this.cod_proveedor = cod_proveedor;
    }

    public int getCod_tipo_vehiculo() {
        return cod_tipo_vehiculo;
    }

    public void setCod_tipo_vehiculo(int cod_tipo_vehiculo) {
        this.cod_tipo_vehiculo = cod_tipo_vehiculo;
    }

    public int getCod_unidad_medida() {
        return cod_unidad_medida;
    }

    public void setCod_unidad_medida(int cod_unidad_medida) {
        this.cod_unidad_medida = cod_unidad_medida;
    }
}
