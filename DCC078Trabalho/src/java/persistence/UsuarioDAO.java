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
import model.abstratos.Usuario;
import controller.UsuarioFactory;

/**
 *
 * @author jjsfa
 */
public class UsuarioDAO extends DAO{
    
    private static final UsuarioDAO instance = new UsuarioDAO();
    public static UsuarioDAO getInstance(){
        return instance;
    }
    
    public void insert(Usuario usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into usuario(nome,login,senha,documento,tipoUsuario) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1,usuario.getNome());
            st.setString(2,usuario.getLogin());
            st.setString(3,usuario.getSenha());
            st.setString(4,usuario.getDocumento());
            st.setString(5,usuario.getTipo());
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

    
    public Usuario authenticate(String login, String senha) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        Usuario usuario = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select id,nome,documento,tipoUsuario from usuario where login='"+login+"'"
            + " and senha='"+senha+"'");

            if (rs.next())
            {
                Long id = rs.getLong("id");
                String documento = rs.getString("documento");
                String nome = rs.getString("nome");
                String tipoUsuario = rs.getString("tipoUsuario");
                        
                usuario = UsuarioFactory.create(tipoUsuario);
                usuario.setId(id);
                usuario.setNome(nome);
                usuario.setLogin(login);
                usuario.setSenha(senha);
                usuario.setDocumento(documento);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return usuario;
    }

    public void update(Usuario usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update usuario set nome=?, login=?,documento=? where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setString(1,usuario.getNome());
            st.setString(2,usuario.getLogin());
            st.setString(3,usuario.getDocumento());
            st.setLong(4,usuario.getId());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update user failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public void updateSenha(Usuario usuario, String senha) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update usuario set senha=? where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setString(1,senha);
            st.setLong(2,usuario.getId());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update user failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
}
