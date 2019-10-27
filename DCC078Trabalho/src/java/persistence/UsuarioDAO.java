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
import model.abstratos.Usuario;

/**
 *
 * @author jjsfa
 */
public class UsuarioDAO extends DAO{
    
    private static UsuarioDAO instance = new UsuarioDAO();
    public static UsuarioDAO getInstance(){
        return instance;
    }
    
    public void insert(Usuario usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into usuario(nome,login,senha,tipoUsuarioId) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1,usuario.getNome());
            st.setString(2,usuario.getLogin());
            st.setString(3,usuario.getSenha());
            st.setLong(4,usuario.getTipoUsuarioId());
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
