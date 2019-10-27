/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import model.extensores.Entregador;

/**
 *
 * @author ice
 */
public class EntregadorDAO  extends DAO{
        private static EntregadorDAO instance = new EntregadorDAO();
    public static EntregadorDAO getInstance(){
        return instance;
    }

    public void insert(Entregador entregador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
