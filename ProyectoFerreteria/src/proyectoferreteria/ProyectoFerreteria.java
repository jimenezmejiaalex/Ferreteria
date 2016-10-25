/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoferreteria;

import ferreteria.control.GestorPrincipal;
import ferreteria.vista.Login;

/**
 *
 * @author aleex
 */
public class ProyectoFerreteria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ProyectoFerreteria().init();
    }
    private void init(){
        GestorPrincipal gestor = new GestorPrincipal();
        Login login = new Login(gestor);
        login.init();
    }
    
}
