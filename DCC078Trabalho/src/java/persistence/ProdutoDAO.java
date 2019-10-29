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

/**
 *
 * @author ice
 */
public class ProdutoDAO  extends DAO{
        private static ProdutoDAO instance = new ProdutoDAO();
    public static ProdutoDAO getInstance(){
        return instance;
    }
    public void insert(Produto produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into produto(id_empresa,nome,categoria,descricao,quantidade,preco) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,produto.getId_empresa());
            st.setString(2,produto.getNome());
            st.setString(3,produto.getCategoria());
            st.setString(4,produto.getDescricao());
            st.setInt(5,produto.getQuantidade());
            st.setDouble(6,produto.getPreco());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating produto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;//  
        } finally {
            closeResources(conn, st);
        }
    }
    
    public List<Produto> getProdutosByEmpresaId(Long id_empresa) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Produto> listaProdutos = new ArrayList();
        try {
                conn = DatabaseLocator.getInstance().getConection();
                st = conn.createStatement();
                
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery("select * from produto where id_empresa ="+id_empresa);
                
                // iterate through the java resultset
                while (rs.next())
                {
                    Long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    String categoria = rs.getString("categoria");
                    String descricao = rs.getString("descricao");
                    int quantidade = rs.getInt("quantidade");
                    double preco = rs.getDouble("preco");
                    listaProdutos.add(new Produto(id,nome,descricao, categoria, quantidade, preco,id_empresa));
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return listaProdutos;
    }
    public List<Produto> getAll() throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Produto> listaProdutos = new ArrayList();
        try {
                conn = DatabaseLocator.getInstance().getConection();
                st = conn.createStatement();
                
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery("select * from produto");
                
                // iterate through the java resultset
                while (rs.next())
                {
                    Long id = rs.getLong("id");
                    Long id_empresa = rs.getLong("id_empresa");
                    String nome = rs.getString("nome");
                    String categoria = rs.getString("categoria");
                    String descricao = rs.getString("descricao");
                    int quantidade = rs.getInt("quantidade");
                    double preco = rs.getDouble("preco");
                    listaProdutos.add(new Produto(id,nome,descricao, categoria, quantidade, preco,id_empresa));
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return listaProdutos;
    }
    
    public void update(Produto produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update produto set id_empresa=?, nome=?, categoria=?, descricao=?, quantidade=?, preco=? where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,produto.getId_empresa());
            st.setString(2,produto.getNome());
            st.setString(3,produto.getCategoria());
            st.setString(4,produto.getDescricao());
            st.setInt(5,produto.getQuantidade());
            st.setDouble(6,produto.getPreco());
            st.setLong(7,produto.getId());
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
