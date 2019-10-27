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
public class AvenidaDAO extends DAO{
        private static AvenidaDAO instance = new AvenidaDAO();
    public static AvenidaDAO getInstance(){
        return instance;
    }
    
}
