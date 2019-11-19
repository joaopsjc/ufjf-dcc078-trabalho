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
    
    public void executeQuery(PreparedStatement st) throws SQLException, ClassNotFoundException{
        executeQuery(st,true);
    }
    
    public void executeQuery(PreparedStatement st, boolean closeConnection) throws SQLException, ClassNotFoundException{
        try {
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Query failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            if (closeConnection)
                closeResources(st.getConnection(), st);
        }
    }
    
    
    public void executeQueryDelete(String query) throws ClassNotFoundException, SQLException{
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        executeQuery(st);
    }
}
