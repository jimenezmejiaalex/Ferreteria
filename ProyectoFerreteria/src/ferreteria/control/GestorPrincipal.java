package ferreteria.control;

import ferreteria.modelo.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class GestorPrincipal {

    // <editor-fold defaultstate="collapsed" desc="Constructors">   
    public GestorPrincipal() throws ClassNotFoundException {
        this(Conexion.obtenerInstancia());
    }

    public GestorPrincipal(Conexion cnx) {
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
                data = new String[rows][3];
                rs = ps.executeQuery();
                while (rs.next()) {
                    data[i][0] = rs.getString(id);
                    data[i][1] = rs.getString(nombreE);
                    data[i][2] = rs.getString(rol);
                    System.out.println(rs.getString(id));
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
                data = new String[rows][3];
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString(nombreE).contains(nom)) {
                        data[i][0] = rs.getString(id);
                        data[i][1] = rs.getString(nombreE);
                        data[i][2] = rs.getString(rol);
                        i++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    // </editor-fold>
    private final String select = "SELECT `Empleado`.`idEmpleado`,\n"
            + "    `Empleado`.`nombreEmpleado`,\n"
            + "    `Empleado`.`clave`,\n"
            + "    `Empleado`.`rollEmpleado`\n"
            + "FROM `Ferreteria`.`Empleado`;";
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
