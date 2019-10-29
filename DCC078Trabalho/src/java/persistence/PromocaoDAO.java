/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controller.PromocaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.interfaces.Promocao;

/**
 *
 * @author ice
 */
public class PromocaoDAO  extends DAO{
        private static PromocaoDAO instance = new PromocaoDAO();
    public static PromocaoDAO getInstance(){
        return instance;
    }
    
    public void insert(Promocao promocao) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into promocao(tipoPromocao) values (?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1,promocao.getTipo());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating promocao failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public List<Promocao> getAll() throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Promocao> promocaos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from promocao");

            while (rs.next())
            {
                Long id = rs.getLong("id");
                String tipoPromocao  = rs.getString("tipoPromocao");
                Promocao novaPromocao = PromocaoFactory.create(tipoPromocao);
                novaPromocao.setId(id);
                promocaos.add(novaPromocao);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return promocaos;
    }
    
    public void update(Promocao promocao) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update promocao set tipo=? where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setString(1,promocao.getTipo());
            st.setLong(2,promocao.getId());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update promocao failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public Promocao getById(Long id_promocao) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        Promocao promocao = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from promocao where id='"+id_promocao+"'");

            if (rs.next())
            {
                String tipoPomocao = rs.getString("tipoContato");
                Promocao novaPromocao = PromocaoFactory.create(tipoPomocao);
                novaPromocao.setId(id_promocao);
                promocao = novaPromocao;
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return promocao;
    }
    
    public void delete(Long id_promocao) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from promocao where id='"+id_promocao+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete promocao failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
}
