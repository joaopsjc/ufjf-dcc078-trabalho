/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jjsfa
 */
public abstract class DAO {
    
    public void closeResources(Connection conn, Statement st) {
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch(SQLException e) {

        }
    }
    
    public void executeUpdate(PreparedStatement st) throws SQLException{
        int affectedRows = st.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Update failed, no rows affected.");
        }
    }
    
    
    public void executeQueryDelete(String query) throws ClassNotFoundException, SQLException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            executeUpdate(st);
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
}
