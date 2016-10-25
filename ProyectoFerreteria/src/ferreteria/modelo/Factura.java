/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferreteria.modelo;

import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author aleex
 */
public class Factura {

    public Factura(Calendar fecha, String numeroFactura, LocalTime hora, Cliente cliente, Persona vendedor, String detalle) {
        this.fecha = fecha;
        this.numeroFactura = numeroFactura;
        this.hora = hora;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.detalle = detalle;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Persona getVendedor() {
        return vendedor;
    }

    public void setVendedor(Persona vendedor) {
        this.vendedor = vendedor;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Factura{" + "fecha=" + fecha + ", numeroFactura=" + numeroFactura + ", hora=" + hora + ", cliente=" + cliente + ", vendedor=" + vendedor + ", detalle=" + detalle + '}';
    }
    
    private Calendar fecha;
    private String numeroFactura;
    private LocalTime hora;
    private Cliente cliente;
    private Persona vendedor;
    private String detalle;
}
