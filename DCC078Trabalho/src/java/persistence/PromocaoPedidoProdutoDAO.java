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
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import model.estados.ProdutoEstadoFactory;

/**
 *
 * @author ice
 */
public class PromocaoPedidoProdutoDAO  extends DAO{
        private static PromocaoPedidoProdutoDAO instance = new PromocaoPedidoProdutoDAO();
    public static PromocaoPedidoProdutoDAO getInstance(){
        return instance;
    }
    
    public void insert(Long id_promocao, Long id_produto, Long id_pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into promocaoPedidoProduto(id_pedido,id_produto,id_promocao) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_pedido);
            st.setLong(2,id_produto);
            st.setLong(3,id_promocao);
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating promocaoPedidoProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;//  
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void deleteByProdutoId(Long id_produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from promocaoPedidoProduto where id_produto='"+id_produto+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete promocaoPedidoProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void deleteByPedidoId(Long id_pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from promocaoPedidoProduto where id_pedido='"+id_pedido+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete promocaoPedidoProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void deleteByPedidoProdutoId(Long id_pedido, Long id_produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from promocaoPedidoProduto where id_pedido='"+id_pedido+"' AND" + "id_produto='"+id_produto+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete promocaoPedidoProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void delete(Long id_promocao, Long id_pedido, Long id_produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from promocaoPedidoProduto where id_pedido='"+id_pedido+"' AND" + "id_produto='"+id_produto+"' AND" + "id_promocao='"+id_promocao+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete promocaoPedidoProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
}
