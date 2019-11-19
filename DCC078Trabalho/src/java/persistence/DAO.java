/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            System.out.println(e.getMessage());
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Long executeQuery(PreparedStatement st) throws SQLException, ClassNotFoundException{
        return executeQuery(st,true);
    }
    
    public Long executeQuery(PreparedStatement st, boolean closeConnection) throws SQLException, ClassNotFoundException{
        Long generatedKey = null;
        try {
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Query failed, no rows affected.");
            }
            ResultSet rs = st.getGeneratedKeys();

            if (rs!=null && rs.next()) {
                generatedKey = rs.getLong(1);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            if (closeConnection)
                closeResources(st.getConnection(), st);
        }
        return generatedKey;
    }
    
    
    public void executeQueryDelete(String query) throws ClassNotFoundException, SQLException{
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        executeQuery(st);
    }
}
