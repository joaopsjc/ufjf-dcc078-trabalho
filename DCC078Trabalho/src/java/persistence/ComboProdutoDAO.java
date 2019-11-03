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
import model.abstratos.Produto;
import model.abstratos.Endereco;
import model.abstratos.Usuario;
import controller.ProdutoEstadoFactory;
import controller.ProdutoFactory;
import controller.PromocaoFactory;
import java.util.Iterator;
import model.PedidoProduto;
import model.extensores.ProdutoCombo;
import model.interfaces.PedidoEstado;
import model.interfaces.Promocao;

/**
 *
 * @author ice
 */
public class ComboProdutoDAO  extends DAO{
        private static ComboProdutoDAO instance = new ComboProdutoDAO();
    public static ComboProdutoDAO getInstance(){
        return instance;
    }
    
    
    public void insert(Long id_produto, Long id_combo) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into comboProduto(id_combo,id_produto) values (?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_combo);
            st.setLong(2,id_produto);
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating ComboProduto failed, no rows affected.");
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
            st = conn.prepareStatement("delete from comboProduto where id_produto="+id_produto,Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete comboProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void deleteByComboId(Long id_combo) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from comboProduto where id_combo="+id_combo,Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete comboProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void delete(Long id_combo, Long id_produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from comboProduto where id_combo='"+id_combo+"' AND" + "id_produto='"+id_produto+"'",Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete comboProduto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public List<PedidoProduto> getAllComboProdutoByComboId(Long id_combo) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<PedidoProduto> produtos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT * from produto P "
                            + "INNER JOIN comboProduto CP "
                            + "ON P.id = CP.id_produto "
                            + "WHERE CP.id_combo = "+id_combo);
            while (rs.next())
            {
                Long id = rs.getLong("id");
                Long id_empresa = rs.getLong("id_empresa");
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                String descricao = rs.getString("descricao");
                int quantidadeProduto = rs.getInt("P.quantidade");
                int quantidadePedido = rs.getInt("PP.quantidade");
                double preco = rs.getDouble("preco");
                String estado = rs.getString("estado");
                String tipoPromocao = rs.getString("tipoPromocao");
                
                Promocao novaPromocao = PromocaoFactory.create(tipoPromocao);
                
                Produto novoProduto =  ProdutoFactory.create(categoria);
                //categoria não é mais um elemento setável, 
                // utilização do produtoFactory para criação de novo produtos
                //new Produto(id,nome,descricao, categoria, quantidade, preco,id_empresa);
                novoProduto.setId(id);
                novoProduto.setNome(nome);
                novoProduto.setId_empresa(id_empresa);
                novoProduto.setDescricao(descricao);
                novoProduto.setQuantidade(quantidadeProduto);
                novoProduto.setPreco(preco);
                novoProduto.setEstado(ProdutoEstadoFactory.create(estado));
                
                PedidoProduto pedidoProduto = new PedidoProduto(quantidadePedido, novoProduto);
                pedidoProduto.setPromocao(novaPromocao);
                
                produtos.add(pedidoProduto);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return produtos;
    }
    
    public List<Produto> getAllProdutosByComboId(Long id_combo) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT * from produto P "
                            + "INNER JOIN comboProduto CP "
                            + "ON P.id = CP.id_produto "
                            + "WHERE CP.id_combo = "+id_combo);
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
                
                Produto novoProduto =  ProdutoFactory.create(categoria);
                //categoria não é mais um elemento setável, 
                // utilização do produtoFactory para criação de novo produtos
                //new Produto(id,nome,descricao, categoria, quantidade, preco,id_empresa);
                novoProduto.setId(id);
                novoProduto.setNome(nome);
                novoProduto.setId_empresa(id_empresa);
                novoProduto.setDescricao(descricao);
                novoProduto.setQuantidade(quantidade);
                novoProduto.setPreco(preco);
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
    public List<Pedido> getAllComboByProdutoId(Long id_produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT * from produto P "
                            + "INNER JOIN comboProduto CP "
                            + "ON P.id = CP.id_combo "
                            + "WHERE CP.id_produto = "+id_produto);
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
    
    public void insert(ProdutoCombo combo) throws SQLException, ClassNotFoundException{
        for(Iterator<Produto> i = combo.getProdutos().iterator();i.hasNext();){
            Produto novoProduto = i.next();
            insert(novoProduto.getId(),combo.getId());
        }
    }
}
