package ferreteria.control;

import ferreteria.modelo.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class GestorVentas {

    public GestorVentas() throws ClassNotFoundException {
        this(Conexion.obtenerInstancia());
    }

    public GestorVentas(Conexion cnx) {
        this.cnx = cnx;
    }
    public DefaultTableModel consultar(){
         Object[][] data = null;
        try {
            try (Connection c = cnx.obtenerConexion()) {
                ps = c.prepareStatement(selectCliente);
                rs = ps.executeQuery();
                rsm = rs.getMetaData();
                int i = 0;
                int rows = 0;
                while (rs.next()) {
                    rows++;
                }
                data = new String[rows][5];
                rs = ps.executeQuery();
                while (rs.next()) {
                    data[i][0] = rs.getString(id);
                    data[i][1] = rs.getString(nombre);
                    data[i][2] = rs.getString(telefono);
                    data[i][3] = rs.getString(email);
                    data[i][4] = String.valueOf(Float.valueOf(rs.getString(descuento))*100)+"%";
                    i++;
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(data);
        return new DefaultTableModel(data,new String[]{"ID","Nombre","Telefono","Email","Descuento"});
    }
    private final String nombre = "nombreCliente";
    private final String id="idCliente";
    private final String email="emailCliente";
    private final String telefono="telefonoCliente";
    private final String descuento="descuentoCliente";
    private final String selectCliente = "SELECT `Cliente`.`idCliente`,\n"
            + "    `Cliente`.`nombreCliente`,\n"
            + "    `Cliente`.`telefonoCliente`,\n"
            + "    `Cliente`.`emailCliente`,\n"
            + "    `Cliente`.`descuentoCliente`\n"
            + "FROM `Ferreteria`.`Cliente`;";
    private PreparedStatement ps;
    private ResultSet rs;
    private ResultSetMetaData rsm;
    private DefaultTableModel tm;
    private Conexion cnx;
}
