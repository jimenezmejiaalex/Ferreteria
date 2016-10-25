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
public class Cliente extends Persona {

    public Cliente(String telefono, String email, double descuento, String ID, String nombre) {
        super(ID, nombre);
        this.telefono = telefono;
        this.email = email;
        this.descuento = descuento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Cliente " + super.toString() + "telefono=" + telefono + ", email=" + email + ", descuento=" + descuento + '}';
    }

    private String telefono;
    private String email;
    private double descuento;
}
