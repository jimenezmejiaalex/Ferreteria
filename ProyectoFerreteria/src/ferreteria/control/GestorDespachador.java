package ferreteria.control;

import ferreteria.modelo.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class GestorDespachador {

    public GestorDespachador() throws ClassNotFoundException{
        this(Conexion.obtenerInstancia());
    }
    public GestorDespachador(Conexion cnx) {
        this.cnx = cnx;
    }

    
    private PreparedStatement ps;
    private ResultSet rs;
    private ResultSetMetaData rsm;
    private DefaultTableModel tm;
    private final Conexion cnx;
}
