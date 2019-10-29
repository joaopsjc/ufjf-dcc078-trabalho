/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controller.PedidoEstadoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import model.Produto;
import model.abstratos.Endereco;
import model.abstratos.Usuario;
import model.estados.ProdutoEstadoFactory;
import model.interfaces.PedidoEstado;
import model.interfaces.Promocao;

/**
 *
 * @author ice
 */
public class PedidoProdutoDAO  extends DAO{
        private static PedidoProdutoDAO instance = new PedidoProdutoDAO();
    public static PedidoProdutoDAO getInstance(){
        return instance;
    }
    
    
    public void insert(Long id_produto, Long id_pedido, Long id_promocao) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into pedidoProduto(id_pedido,id_produto,id_promocao) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_pedido);
            st.setLong(2,id_produto);
            st.setLong(3,id_promocao);
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating pedidoProduto failed, no rows affected.");
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
            st = conn.prepareStatement("delete from pedidoProduto where id_produto='"+id_produto+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete pedidoProduto failed, no rows affected.");
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
            st = conn.prepareStatement("delete from pedidoProduto where id_pedido='"+id_pedido+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete pedidoProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void delete(Long id_pedido, Long id_produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from pedidoProduto where id_pedido='"+id_pedido+"' AND" + "id_produto='"+id_produto+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete pedidoProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public List<Produto> getAllProdutosByPedidoId(Long id_pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT * from produto P "
                            + "INNER JOIN pedidoProduto PP "
                            + "ON P.id = PP.id_produto "
                            + "WHERE PP.id_pedido = '"+id_pedido+"'");
            while (rs.next())
            {
                Long id = rs.getLong("id");
                Long id_empresa = rs.getLong("id_empresa");
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                String descricao = rs.getString("descricao");
                int quantidade = rs.getInt("quantidade");
                double preco = rs.getDouble("preco");
                String estado = rs.getString("estado");
                Produto novoProduto =  new Produto(id,nome,descricao, categoria, quantidade, preco,id_empresa);
                novoProduto.setEstado(ProdutoEstadoFactory.create(estado));
                produtos.add(novoProduto);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return produtos;
    }
    //provavelmente nada boa
    public List<Pedido> getAllPedidosByProdutoId(Long id_produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT * from pedido P "
                            + "INNER JOIN pedidoProduto PP "
                            + "ON P.id = PP.id_pedido "
                            + "WHERE PP.id_produto = '"+id_produto+"'");
            while (rs.next())
            {
                Long id = rs.getLong("id");
                Long id_cliente = rs.getLong("id_cliente");
                Long id_entregador = rs.getLong("id_entregador");
                Long id_empresa = rs.getLong("id_empresa");
                Long id_endereco = rs.getLong("id_endereco");
                Double frete = rs.getDouble("frete");
                String estado = rs.getString("estado");
                
                Usuario cliente = UsuarioDAO.getInstance().getById(id_cliente);
                Usuario empresa = UsuarioDAO.getInstance().getById(id_empresa);
                Usuario entregador = UsuarioDAO.getInstance().getById(id_entregador);
                Endereco endereco = EnderecoDAO.getInstance().getById(id_endereco);
                PedidoEstado pedidoEstado = PedidoEstadoFactory.create(estado);
                
                Pedido novoPedido = new Pedido(id, endereco, frete,pedidoEstado);
                novoPedido.setCliente(cliente);
                novoPedido.setEntregador(entregador);
                novoPedido.setEmpresa(empresa);
                pedidos.add(novoPedido);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return pedidos;
    }
    public Promocao getPromocaoByPedidoProdutoId(Long id_produto, Long id_pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        Promocao promocao = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM pedidoProduto where id_pedido='"+id_pedido+"' AND" + "id_produto='"+id_produto+"'");
            if (rs.next())
            {
                Long id_promocao = rs.getLong("id_promocao");
                promocao = PromocaoDAO.getInstance().getById(id_promocao);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return promocao;
    }
    public void updatePromocao(Long id_produto, Long id_pedido, Long id_promocao) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update pedidoProduto set id_promocao=? where id_pedido=? AND id_produto=?",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_promocao);
            st.setLong(2,id_pedido);
            st.setLong(3,id_produto);
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update promocao em pedidoPromocao failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
}
