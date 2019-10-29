/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controller.ContatoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
                throw new SQLException("Creating contato failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public Contato getById(Long id_contato) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        Contato contato = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from contato where id='"+id_contato+"'");

            if (rs.next())
            {
                
                Long id_usuario = rs.getLong("id_usuario");
                String valor  = rs.getString("valor");
                String tipoContato = rs.getString("tipoContato");
                Contato novoContato = ContatoFactory.create(tipoContato);  
                novoContato.setValor(valor);
                novoContato.setId(id_contato);
                novoContato.setIdUsuario(id_usuario);
                contato = novoContato;
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return contato;
    }

    public List<Contato> getContatosByUserId(Long id_usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Contato> contatos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from contato where id_usuario='"+id_usuario+"'");

            while (rs.next())
            {
                
                Long id = rs.getLong("id");
                String valor  = rs.getString("valor");
                String tipoContato = rs.getString("tipoContato");
                Contato novoContato = ContatoFactory.create(tipoContato);  
                novoContato.setValor(valor);
                novoContato.setId(id);
                novoContato.setIdUsuario(id_usuario);
                contatos.add(novoContato);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return contatos;
    }
    
    public List<Contato> getAll() throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Contato> contatos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from contato");

            while (rs.next())
            {
                
                Long id = rs.getLong("id");
                Long id_usuario = rs.getLong("id_usuario");
                String valor  = rs.getString("valor");
                String tipoContato = rs.getString("tipoContato");
                Contato novoContato = ContatoFactory.create(tipoContato);  
                novoContato.setValor(valor);
                novoContato.setId(id);
                novoContato.setIdUsuario(id_usuario);
                contatos.add(novoContato);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return contatos;
    }
    
    public void update(Contato contato) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update produto set id_usuario=?,valor=?,tipoContato=? where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,contato.getIdUsuario());
            st.setString(2,contato.getValor());
            st.setString(3,contato.getTipo());
            st.setLong(4,contato.getId());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update contato failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void delete(Long id_contato) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from contato where id='"+id_contato+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete endereco failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
}
