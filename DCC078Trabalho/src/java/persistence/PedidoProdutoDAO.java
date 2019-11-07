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
    
    
    public void insert(Long id_produto, Long id_pedido, String tipoPromocao) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into pedidoProduto(id_pedido,id_produto,tipoPromocao) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_pedido);
            st.setLong(2,id_produto);
            st.setString(3,tipoPromocao);
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
    
    public void insert(Long idPedido, PedidoProduto pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into pedidoProduto(id_pedido,id_produto,quantidade) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,idPedido);
            st.setLong(2,pedido.getProduto().getId());
            st.setInt(3,pedido.getQuantidade());
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
    
    public void insert(Long idPedido, PedidoProduto pedido, String promocaoTipo) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into pedidoProduto(id_pedido,id_produto,quantidade,tipoPromocao) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,idPedido);
            st.setLong(2,pedido.getProduto().getId());
            st.setInt(3,pedido.getQuantidade());
            st.setString(4,promocaoTipo);
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
    
    public List<PedidoProduto> getAllProdutoPedidoByPedidoId(Long id_pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<PedidoProduto> produtos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT *,P.quantidade as produtoQuantidade from produto P "
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
                int quantidadeProduto = rs.getInt("produtoQuantidade");
                int quantidadePedido = rs.getInt("quantidade");
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
    
    public List<Produto> getAllProdutosByPedidoId(Long id_pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT P.* from produto P "
                            + "INNER JOIN pedidoProduto PP "
                            + "ON P.id = PP.id_produto "
                            + "WHERE PP.id_pedido = "+id_pedido);
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
    
    public List<PedidoProduto> getAllPedidoProdutosByPedidoId(Long id_pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<PedidoProduto> pedidoProdutos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT P.*, PP.quantidade as pedidoQuantidade,PP.tipoPromocao as pedidoPromocao from produto P "
                            + "INNER JOIN pedidoProduto PP "
                            + "ON P.id = PP.id_produto "
                            + "WHERE PP.id_pedido = "+id_pedido);
            while (rs.next())
            {
                Long id = rs.getLong("id");
                Long id_empresa = rs.getLong("id_empresa");
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                String tipoPromocao = rs.getString("pedidoPromocao");
                String descricao = rs.getString("descricao");
                int quantidade = rs.getInt("quantidade");
                int pedidoQuantidade = rs.getInt("pedidoQuantidade");
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
                
                Promocao promocao = PromocaoFactory.create(tipoPromocao);
                
                PedidoProduto novoPedidoProduto = new PedidoProduto(pedidoQuantidade, novoProduto);
                novoPedidoProduto.setPromocao(promocao);

                pedidoProdutos.add(novoPedidoProduto);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return pedidoProdutos;
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
                            + "WHERE PP.id_produto = "+id_produto);
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

            ResultSet rs = st.executeQuery("SELECT * FROM pedidoProduto where id_pedido="+id_pedido+" AND" + "id_produto="+id_produto);
            if (rs.next())
            {
                String tipoPromocao = rs.getString("tipoPromocao");
                promocao = PromocaoFactory.create(tipoPromocao);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return promocao;
    }
    public void updatePromocao(Long id_produto, Long id_pedido, String tipoPromocao) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update pedidoProduto set tipoPromocao='?' where id_pedido=? AND id_produto=?",Statement.RETURN_GENERATED_KEYS);
            st.setString(1,tipoPromocao);
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

    public void insert(Pedido pedido) throws SQLException, ClassNotFoundException{
        for(Iterator i = pedido.getProdutos().iterator();i.hasNext();){
            PedidoProduto p = (PedidoProduto)i.next();
            insert(pedido.getId(),p);
        }
    }

    public void insert(Pedido pedido, String promocao) throws SQLException, ClassNotFoundException{
        for(Iterator i = pedido.getProdutos().iterator();i.hasNext();){
            PedidoProduto p = (PedidoProduto)i.next();
            insert(pedido.getId(), p,promocao);
        }
    }
}
