package ferreteria.control;

import ferreteria.modelo.Empleado;
import ferreteria.modelo.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class GestorAdministracion {

    // <editor-fold defaultstate="collapsed" desc="Constructors">   
    public GestorAdministracion() throws ClassNotFoundException {
        this(Conexion.obtenerInstancia());
    }

    public GestorAdministracion(Conexion cnx) {
        this.cnx = cnx;
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
    public Object[][] consultaDatos() {
        Object[][] data = null;
        try {
            try (Connection c = cnx.obtenerConexion()) {
                ps = c.prepareStatement(select);
                rs = ps.executeQuery();
                rsm = rs.getMetaData();
                int i = 0;
                int rows = 0;
                while (rs.next()) {
                    rows++;
                }
                data = new String[rows][4];
                rs = ps.executeQuery();
                while (rs.next()) {
                    data[i][0] = rs.getString(id);
                    data[i][1] = rs.getString(nombreE);
                    data[i][2] = rs.getString(rol);
                    data[i][3] = rs.getString(clave);
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public Object[][] consultaDatos(String nom) {
        Object[][] data = null;
        try {
            try (Connection c = cnx.obtenerConexion()) {
                ps = c.prepareStatement(select);
                rs = ps.executeQuery();
                rsm = rs.getMetaData();
                int i = 0;
                int rows = 0;
                while (rs.next()) {
                    if (rs.getString(nombreE).contains(nom)) {
                        rows++;
                    }
                }
                data = new String[rows][4];
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString(nombreE).contains(nom)) {
                        data[i][0] = rs.getString(id);
                        data[i][1] = rs.getString(nombreE);
                        data[i][2] = rs.getString(rol);
                        data[i][3] = rs.getString(clave);
                        i++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public Object[][] consultaDatosID(String ID) {
        Object[][] data = null;
        try {
            try (Connection c = cnx.obtenerConexion()) {
                ps = c.prepareStatement(select);
                rs = ps.executeQuery();
                rsm = rs.getMetaData();
                int i = 0;
                int rows = 0;
                while (rs.next()) {
                    if (rs.getString(id).contains(ID)) {
                        rows++;
                    }
                }
                data = new String[rows][4];
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString(id).contains(ID)) {
                        data[i][0] = rs.getString(id);
                        data[i][1] = rs.getString(nombreE);
                        data[i][2] = rs.getString(rol);
                        data[i][3] = rs.getString(clave);
                        i++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(data);
        return data;
    }

    public Object[][] consultaDatosIDNom(String ID, String nom) {
        Object[][] data = null;
        try {
            try (Connection c = cnx.obtenerConexion()) {
                ps = c.prepareStatement(select);
                rs = ps.executeQuery();
                rsm = rs.getMetaData();
                int i = 0;
                int rows = 0;
                while (rs.next()) {
                    if (rs.getString(id).contains(ID) && rs.getString(nombreE).contains(nom)) {
                        rows++;
                    }
                }
                data = new String[rows][4];
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString(id).contains(ID) && rs.getString(nombreE).contains(nom)) {
                        data[i][0] = rs.getString(id);
                        data[i][1] = rs.getString(nombreE);
                        data[i][2] = rs.getString(rol);
                        data[i][3] = rs.getString(clave);
                        i++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(data);
        return data;
    }

    public boolean agregarDatosEmpleado(Empleado emp) throws SQLException {
        boolean exito = false;
        try (Connection c = cnx.obtenerConexion()) {
            ps = c.prepareStatement(agregar);
            ps.clearParameters();
            ps.setString(1, emp.getID());
            ps.setString(2, emp.getNombre());
            ps.setString(3, emp.getClaveAcceso());
            ps.setString(4, emp.getRoll());
            int r = ps.executeUpdate();
            exito = (r == 1);
        }
        return exito;
    }

    public boolean borrarEmpleado(String password, String nombre) throws SQLException {
        boolean exito;
        try (Connection c = cnx.obtenerConexion();
                PreparedStatement stm = c.prepareStatement(borrar)) {

            stm.clearParameters();
            stm.setString(1, password + "," + nombre);

            exito = (stm.executeUpdate() == 1);
        }
        return exito;
    }
    public boolean actualizar(Empleado emp){
        
        return true;
    }
    // </editor-fold>

    private static final String borrar
            = "DELETE FROM `Empleado` WHERE idEmpleado=?,nombreEmpleado=?; ";

    private static final String update = "UPDATE `Ferreteria`.`Empleado`\n"
            + "SET\n"
            + "`idEmpleado` = <{idEmpleado: }>,\n"
            + "`nombreEmpleado` = <{nombreEmpleado: }>,\n"
            + "`clave` = <{clave: }>,\n"
            + "`rollEmpleado` = <{rollEmpleado: }>\n"
            + "WHERE `idEmpleado` = ? AND `nombreEmpleado` = ?;";

    private final String select = "SELECT `Empleado`.`idEmpleado`,\n"
            + "    `Empleado`.`nombreEmpleado`,\n"
            + "    `Empleado`.`clave`,\n"
            + "    `Empleado`.`rollEmpleado`\n"
            + "FROM `Ferreteria`.`Empleado`;";
    private final String agregar
            = "INSERT INTO `Empleado` "
            + "(`idEmpleado`, `nombreEmpleado`, `clave`, `rollEmpleado`) "
            + "VALUES (?, ?, ?, ?); ";
    private final String id = "idEmpleado";
    private final String nombreE = "nombreEmpleado";
    private final String clave = "clave";
    private final String rol = "rollEmpleado";
    private PreparedStatement ps;
    private ResultSet rs;
    private ResultSetMetaData rsm;
    private DefaultTableModel tm;
    private Conexion cnx;
}
