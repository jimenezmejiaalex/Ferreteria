package proyectoferreteria;

import ferreteria.control.GestorPrincipal;
import ferreteria.modelo.conexion.Conexion;
import ferreteria.vista.Admistracion;
import ferreteria.vista.Login;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ProyectoFerreteria {

    public static void main(String[] args) {
        new ProyectoFerreteria().init();
    }

    private void init() {
        try {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            JFrame.setDefaultLookAndFeelDecorated(true);
            GestorPrincipal gestor = new GestorPrincipal();
//        Login login = new Login(gestor);
//        login.init();
            Admistracion ad = new Admistracion(gestor);
            ad.init();
            try {
                Conexion cnx = Conexion.obtenerInstancia();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProyectoFerreteria.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProyectoFerreteria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
