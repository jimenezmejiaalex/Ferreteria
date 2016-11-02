package ferreteria.control;

import ferreteria.modelo.Empleado;
import ferreteria.modelo.Producto;
import ferreteria.modelo.conexion.Conexion;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class GestorPrincipal {

    // <editor-fold defaultstate="collapsed" desc="Constructors">   
    public GestorPrincipal() throws ClassNotFoundException {
        this(Conexion.obtenerInstancia());
    }

    public GestorPrincipal(Conexion cnx) {
        this.cnx = cnx;
        try {
            gestorDespa= new GestorDespachador(); 
            gestorAdmi = new GestorAdministracion();
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
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

    public boolean agregarEmpleado(Empleado emp) {
        boolean resultado = false;
        try {
            resultado = gestorAdmi.agregarDatosEmpleado(emp);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultado;
    }

    public void borrarEmpleado(String password, String nombre) {
        try {
            gestorAdmi.borrarEmpleado(password, nombre);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean actualizarEmpleado(Empleado emp, String id, String nom) {
        boolean result = false;
        try {
            result = gestorAdmi.actualizar(emp, id, nom);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public DefaultTableModel consultaProducto() {
        return (new DefaultTableModel(gestorAdmi.consultaDatosPro(), new String[]{"Codigo", "Descripcion", "Medida", "Precio unitario"}));
    }

    public DefaultTableModel consultaProducto(String cod) {
        return (new DefaultTableModel(gestorAdmi.consultaDatosPro(cod), new String[]{"Codigo", "Descripcion", "Medida", "Precio unitario"}));
    }

    public DefaultTableModel consultaProductoDes(String des) {
        return (new DefaultTableModel(gestorAdmi.consultaDatosproDes(des), new String[]{"Codigo", "Descripcion", "Medida", "Precio unitario"}));
    }

    public DefaultTableModel consultaProductoDesCod(String cod, String des) {
        return (new DefaultTableModel(gestorAdmi.consultaDatosCodDes(cod, des), new String[]{"Codigo", "Descripcion", "Medida", "Precio unitario"}));
    }

    public boolean agregaProductoCatalogo(Producto pro) {
        boolean exito = false;
        try {
            exito = gestorAdmi.agregarDatosProducto(pro);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exito;
    }

    public boolean actualizaProducto(Producto pro, String cod, String des) {
        boolean exito = false;
        try {
            exito = gestorAdmi.actualizarPro(pro, cod, des);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exito;
    }

    public boolean borrarProducto(String cod, String des) {
        boolean exito = false;
        try {
            exito = gestorAdmi.borrarProducto(cod, des);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exito;
    }

    // </editor-fold>
    private GestorDespachador gestorDespa;
    private GestorAdministracion gestorAdmi;
    private Conexion cnx;
}
