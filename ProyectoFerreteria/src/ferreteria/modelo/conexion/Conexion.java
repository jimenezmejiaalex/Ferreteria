package ferreteria.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Conexion() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public static Conexion obtenerInstancia() throws ClassNotFoundException {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection obtenerConexion() throws SQLException {
        Connection cnx = DriverManager.getConnection(protocolo + "://" + direccionIP + ":" + puerto + "/"
                + nombre, nombreUsuario, claveAcceso);
        return cnx;
    }
    
    private static Connection Conexion;
    private String nombreUsuario = "root";
    private String nombre = "Ferreteria";
    private String claveAcceso = "8739087l";
    private String protocolo = "jdbc:mysql";
    private String direccionIP = "localhost";
    private String puerto = "3306";
    private static Conexion instancia = null;
}
