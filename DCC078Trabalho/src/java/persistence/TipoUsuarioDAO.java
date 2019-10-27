/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.TipoUsuario;
import model.abstratos.Usuario;
import controller.UsuarioFactory;
import model.extensores.Empresa;

/**
 *
 * @author jjsfa
 */
public class TipoUsuarioDAO extends DAO{
    
    private static TipoUsuarioDAO instance = new TipoUsuarioDAO();
    public static TipoUsuarioDAO getInstance(){
        return instance;
    }
    
    public List<TipoUsuario> getTiposUsuario() throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<TipoUsuario> listaTiposUsuario = new ArrayList();
        try {
                conn = DatabaseLocator.getInstance().getConection();
                st = conn.createStatement();
                
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery("select id,nome from tipoUsuario");
                
                // iterate through the java resultset
                while (rs.next())
                {
                    Long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    listaTiposUsuario.add(new TipoUsuario(id,nome));
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return listaTiposUsuario;
    }

    public TipoUsuario getTipoUsuarioById(Long tipoUsuarioId)throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        TipoUsuario tipoUsuario = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select id,nome from tipoUsuario where id="+tipoUsuarioId);

            if (rs.next())
            {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                tipoUsuario = new TipoUsuario(id,nome);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return tipoUsuario;
    }
}
