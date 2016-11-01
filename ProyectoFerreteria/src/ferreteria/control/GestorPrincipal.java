package ferreteria.control;

import ferreteria.modelo.Empleado;
import ferreteria.modelo.conexion.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorPrincipal {

    // <editor-fold defaultstate="collapsed" desc="Constructors">   
    public GestorPrincipal() throws ClassNotFoundException {
        this(Conexion.obtenerInstancia());
    }

    public GestorPrincipal(Conexion cnx) {
        this.cnx = cnx;
        try {
            gestorAdmi= new GestorAdministracion();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Setters and getters"> 

    public Conexion getCnx() {
        return cnx;
    }

    public void setCnx(Conexion cnx) {
        this.cnx = cnx;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Methods">
    public Object[][] consultaDatosEmpleado() {
        return gestorAdmi.consultaDatos();
    }
    public Object[][] consultaDatosEmpleado(String nom) {
        return gestorAdmi.consultaDatos(nom);
    }
    public Object[][] consultaDatosIDEmpleado(String ID) {
        return gestorAdmi.consultaDatosID(ID);
    }
    public Object[][] consultaDatosIDNomEmpleado(String ID, String nom) {
        return gestorAdmi.consultaDatosIDNom(ID, nom);
    }
    public boolean agregarEmpleado(Empleado emp){
        boolean resultado = false;
        try {
            resultado = gestorAdmi.agregarDatosEmpleado(emp);
        } catch (SQLException e) {
           System.err.println(e.getMessage());
        }
        return resultado;
    }
    public void borrarEmpleado(String password ,String nombre){
        try {
            gestorAdmi.borrarEmpleado(password, nombre);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public boolean actualizarEmpleado(Empleado emp, String id, String nom){
        boolean result= false;
        try {
             result = gestorAdmi.actualizar(emp, id, nom);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
    // </editor-fold>
    private GestorAdministracion gestorAdmi;
    private Conexion cnx;
}
