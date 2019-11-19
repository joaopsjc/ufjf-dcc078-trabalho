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
import java.util.ArrayList;
import java.util.List;

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
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement("insert into usuario(nome,login,senha,documento,tipoUsuario) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        st.setString(1,usuario.getNome());
        st.setString(2,usuario.getLogin());
        st.setString(3,usuario.getSenha());
        st.setString(4,usuario.getDocumento());
        st.setString(5,usuario.getTipo());
        executeQuery(st);
    }

    public Usuario authenticate(String login, String senha) throws SQLException, ClassNotFoundException{          
        String query = "select * from usuario where login='"+login+"'"
            + " and senha='"+senha+"'";
        return getUsuarioSingle(query);
    }

    public Usuario getById(Long id_usuario) throws SQLException, ClassNotFoundException{    
        String query = "select * from usuario where id="+id_usuario;
        return getUsuarioSingle(query);
    }

    public List<Usuario> getAllEmpresas() throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from usuario where tipoUsuario = 'Empresa'");

            while(rs.next())
            {
                Usuario novoUsuario = populateProdutoObjectFromDataset(rs);
                usuarios.add(novoUsuario);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return usuarios;
    }
    
    public void update(Usuario usuario) throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement("update usuario set nome=?, login=?,documento=? where id=?",Statement.RETURN_GENERATED_KEYS);
        st.setString(1,usuario.getNome());
        st.setString(2,usuario.getLogin());
        st.setString(3,usuario.getDocumento());
        st.setLong(4,usuario.getId());
        executeQuery(st);
    }

    public void updateSenha(Usuario usuario, String senha) throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement("update usuario set senha=? where id=?",Statement.RETURN_GENERATED_KEYS);
        st.setString(1,senha);
        st.setLong(2,usuario.getId());
        executeQuery(st);
    }
    
    private Usuario getUsuarioSingle(String query) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        Usuario usuario = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            if (rs.next())
            {
                usuario = populateProdutoObjectFromDataset(rs);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return usuario;
    }
    
    private Usuario populateProdutoObjectFromDataset(ResultSet rs) throws SQLException{
        Long id_usuario = rs.getLong("id");
        String login = rs.getString("login");
        String senha = rs.getString("senha");
        String documento = rs.getString("documento");
        String tipoUsuario = rs.getString("tipoUsuario");
        String nome = rs.getString("nome");

        Usuario usuario = UsuarioFactory.create(tipoUsuario);
        usuario.setId(id_usuario);
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setDocumento(documento);
        return usuario;
    }
}
