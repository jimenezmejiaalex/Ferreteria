package ferreteria.control;

import ferreteria.modelo.Empleado;
import ferreteria.modelo.Producto;
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
    
        // <editor-fold defaultstate="collapsed" desc="Empleado">
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
            stm.setString(1, password);
            stm.setString(2, nombre);
            exito = (stm.executeUpdate() == 1);
        }
        return exito;
    }

    public boolean actualizar(Empleado emp, String id, String nom) throws SQLException {
        boolean exito;

        try (Connection c = cnx.obtenerConexion()) {
            System.out.println(emp + "\n" + id + "\n" + nom);
            ps = c.prepareStatement(update);
            ps.clearParameters();
            ps.setString(1, emp.getID());
            ps.setString(2, emp.getNombre());
            ps.setString(3, emp.getClaveAcceso());
            ps.setString(4, emp.getRoll());
            ps.setString(5, id);
            ps.setString(6, nom);
            exito = (ps.executeUpdate() == 1);
        }

        return exito;
    }
    // </editor-fold>
    
        // <editor-fold defaultstate="collapsed" desc="Producto">
    public boolean agregarDatosProducto(Producto pro) throws SQLException {
        boolean exito = false;
        try (Connection c = cnx.obtenerConexion()) {
            ps = c.prepareStatement(agregarPro);
            ps.clearParameters();
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getDecripcion());
            ps.setString(3, pro.getUnidadMedida());
            ps.setString(4, String.valueOf(pro.getPrecioUnitario()));
            int r = ps.executeUpdate();
            exito = (r == 1);
        }
        return exito;
    }
    public Object[][] consultaDatosPro() {
        Object[][] data = null;
        try {
            try (Connection c = cnx.obtenerConexion()) {
                ps = c.prepareStatement(selectPro);
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
                    data[i][0] = rs.getString(codigo);
                    data[i][1] = rs.getString(descripcion);
                    data[i][2] = rs.getString(unidadMedida);
                    data[i][3] = rs.getString(precioUnitario);
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public Object[][] consultaDatosPro(String cod) {
        Object[][] data = null;
        try {
            try (Connection c = cnx.obtenerConexion()) {
                ps = c.prepareStatement(selectPro);
                rs = ps.executeQuery();
                rsm = rs.getMetaData();
                int i = 0;
                int rows = 0;
                while (rs.next()) {
                    if (rs.getString(codigo).contains(cod)) {
                        rows++;
                    }
                }
                data = new String[rows][4];
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString(codigo).contains(cod)) {
                        data[i][0] = rs.getString(codigo);
                        data[i][1] = rs.getString(descripcion);
                        data[i][2] = rs.getString(unidadMedida);
                        data[i][3] = rs.getString(precioUnitario);
                        i++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public Object[][] consultaDatosproDes(String des) {
        Object[][] data = null;
        try {
            try (Connection c = cnx.obtenerConexion()) {
                ps = c.prepareStatement(selectPro);
                rs = ps.executeQuery();
                rsm = rs.getMetaData();
                int i = 0;
                int rows = 0;
                while (rs.next()) {
                    if (rs.getString(descripcion).contains(des)) {
                        rows++;
                    }
                }
                data = new String[rows][4];
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString(descripcion).contains(des)) {
                        data[i][0] = rs.getString(codigo);
                        data[i][1] = rs.getString(descripcion);
                        data[i][2] = rs.getString(unidadMedida);
                        data[i][3] = rs.getString(precioUnitario);
                        i++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public Object[][] consultaDatosCodDes(String cod, String des) {
        Object[][] data = null;
        try {
            try (Connection c = cnx.obtenerConexion()) {
                ps = c.prepareStatement(selectPro);
                rs = ps.executeQuery();
                rsm = rs.getMetaData();
                int i = 0;
                int rows = 0;
                while (rs.next()) {
                    if (rs.getString(codigo).contains(cod) && rs.getString(descripcion).contains(des)) {
                        rows++;
                    }
                }
                data = new String[rows][4];
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString(codigo).contains(cod) && rs.getString(descripcion).contains(des)) {
                        data[i][0] = rs.getString(codigo);
                        data[i][1] = rs.getString(descripcion);
                        data[i][2] = rs.getString(unidadMedida);
                        data[i][3] = rs.getString(precioUnitario);
                        i++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public boolean actualizarPro(Producto producto, String cod, String des) throws SQLException {
        boolean exito;

        try (Connection c = cnx.obtenerConexion()) {
            ps = c.prepareStatement(updatePro);
            ps.clearParameters();
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getDecripcion());
            ps.setString(3, producto.getUnidadMedida());
            ps.setString(4, String.valueOf(producto.getPrecioUnitario()));
            ps.setString(5, cod);
            ps.setString(6, des);
            exito = (ps.executeUpdate() == 1);
        }

        return exito;
    }
    public boolean borrarProducto(String cod, String des) throws SQLException {
        boolean exito;
        try (Connection c = cnx.obtenerConexion();
                PreparedStatement stm = c.prepareStatement(deletePro)) {
            stm.clearParameters();
            stm.setString(1, cod);
            stm.setString(2, des);
            exito = (stm.executeUpdate() == 1);
        }
        return exito;
    }
    
    // </editor-fold>    
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Atributos">   
    private final String borrar
            = "DELETE FROM `Ferreteria`.`Empleado`\n"
            + "WHERE `idEmpleado` = ? AND `nombreEmpleado` = ?;";

    private final String update = "UPDATE `Ferreteria`.`Empleado`\n"
            + "SET\n"
            + "`idEmpleado` = ?,\n"
            + "`nombreEmpleado` = ?,\n"
            + "`clave` = ?,\n"
            + "`rollEmpleado` = ?\n"
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

    private final String agregarPro = "INSERT INTO `Ferreteria`.`ProductoCatalogo`\n"
            + "(`idProducto`,`descripProducto`,`unidadMedida`,`precioUnitario`)"
            + "VALUES (?,?,?,?);";
    private final String selectPro = "SELECT `ProductoCatalogo`.`idProducto`,\n"
            + "    `ProductoCatalogo`.`descripProducto`,\n"
            + "    `ProductoCatalogo`.`unidadMedida`,\n"
            + "    `ProductoCatalogo`.`precioUnitario`\n"
            + "FROM `Ferreteria`.`ProductoCatalogo`;";
    private final String updatePro = "UPDATE `Ferreteria`.`ProductoCatalogo`\n"
            + "SET\n"
            + "`idProducto` = ?,\n"
            + "`descripProducto` = ?,\n"
            + "`unidadMedida` = ?,\n"
            + "`precioUnitario` = ?\n"
            + "WHERE `idProducto` = ? AND `descripProducto` = ?;";
    private final String deletePro = "DELETE FROM `Ferreteria`.`ProductoCatalogo`\n"
            + "WHERE `idProducto` = ? AND `descripProducto` = ?;";
    
    private final String codigo = "idProducto";
    private final String descripcion = "descripProducto";
    private final String unidadMedida = "unidadMedida";
    private final String precioUnitario = "precioUnitario";
    private final String id = "idEmpleado";
    private final String nombreE = "nombreEmpleado";
    private final String clave = "clave";
    private final String rol = "rollEmpleado";
    private PreparedStatement ps;
    private ResultSet rs;
    private ResultSetMetaData rsm;
    private DefaultTableModel tm;
    private Conexion cnx;
      //</editor-fold>
}
