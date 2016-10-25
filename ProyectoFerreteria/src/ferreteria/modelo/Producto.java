/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria.modelo;

/**
 *
 * @author aleex
 */
public class Producto {

    public Producto(String codigo, String decripcion, String unidadMedida, double precioUnitario) {
        this.codigo = codigo;
        this.decripcion = decripcion;
        this.unidadMedida = unidadMedida;
        this.precioUnitario = precioUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", decripcion=" + decripcion + ", unidadMedida=" + unidadMedida + ", precioUnitario=" + precioUnitario + '}';
    }
    
    private String codigo;
    private String decripcion;
    private String unidadMedida;
    private double precioUnitario;
}
