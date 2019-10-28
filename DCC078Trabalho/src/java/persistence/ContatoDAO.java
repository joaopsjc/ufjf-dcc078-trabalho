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
import model.interfaces.Contato;

/**
 *
 * @author ice
 */
public class ContatoDAO  extends DAO{
        private static ContatoDAO instance = new ContatoDAO();
    public static ContatoDAO getInstance(){
        return instance;
    }
    
    public void insert(Contato contato, Long id_usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into contato(id_usuario,valor,tipoContato) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_usuario);
            st.setString(2,contato.getValor());
            st.setString(3,contato.getTipo());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
}
