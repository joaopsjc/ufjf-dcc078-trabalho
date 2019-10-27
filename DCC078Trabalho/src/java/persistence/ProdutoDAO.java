/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ice
 */
public class ProdutoDAO  extends DAO{
        private static ProdutoDAO instance = new ProdutoDAO();
    public static ProdutoDAO getInstance(){
        return instance;
    }
    
}
