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
public class Empleado extends Persona {

    public Empleado(String roll, String claveAcceso, String ID, String nombre) {
        super(ID, nombre);
        this.roll = roll;
        this.claveAcceso = claveAcceso;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    @Override
    public String toString() {
        return "Empleado " + super.toString() + "roll=" + roll + ", claveAcceso=" + claveAcceso + '}';
    }
    private String roll;
    private String claveAcceso;
}
