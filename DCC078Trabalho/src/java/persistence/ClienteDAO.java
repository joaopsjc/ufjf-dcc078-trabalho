/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import model.extensores.Cliente;

/**
 *
 * @author ice
 */
public class ClienteDAO  extends DAO{
        private static ClienteDAO instance = new ClienteDAO();
    public static ClienteDAO getInstance(){
        return instance;
    }
    

    public void insert(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
