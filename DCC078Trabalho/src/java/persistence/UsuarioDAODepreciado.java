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
import model.TipoUsuario;
import model.abstratos.Usuario;
import controller.UsuarioFactory;

/**
 *
 * @author jjsfa
 */
public class UsuarioDAODepreciado extends DAO{
    
    private static final UsuarioDAODepreciado instance = new UsuarioDAODepreciado();
    public static UsuarioDAODepreciado getInstance(){
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
            //substituido por getTipo()
            //st.setLong(4,usuario.getTipoUsuario().getId());
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

            ResultSet rs = st.executeQuery("select id,tipoUsuarioId from usuario where login='"+login+"'"
            + " and senha='"+senha+"'");

            if (rs.next())
            {
                Long id = rs.getLong("id");
                Long tipoUsuarioId = rs.getLong("tipoUsuarioId");
                TipoUsuario tipo = TipoUsuarioDAO.getInstance().getTipoUsuarioById(tipoUsuarioId);
                usuario = UsuarioFactory.create(tipo.getNome());
                usuario.setId(id);
                //substituido por getTipo()
                //usuario.setTipoUsuario(tipo);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return usuario;
    }
}