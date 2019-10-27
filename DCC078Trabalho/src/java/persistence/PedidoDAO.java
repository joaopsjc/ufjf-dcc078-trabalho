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
public class PedidoDAO  extends DAO{
        private static PedidoDAO instance = new PedidoDAO();
    public static PedidoDAO getInstance(){
        return instance;
    }
}
