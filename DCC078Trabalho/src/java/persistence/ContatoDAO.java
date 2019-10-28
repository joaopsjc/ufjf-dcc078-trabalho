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
                contatos.add(novoContato);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return contatos;
    }
}
