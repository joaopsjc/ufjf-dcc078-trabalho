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
import model.Pedido;
import model.abstratos.Produto;
import controller.ProdutoEstadoFactory;
import controller.ProdutoFactory;
import controller.PromocaoFactory;
import java.sql.Types;
import java.util.Iterator;
import model.PedidoProduto;
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
    
    public void insert(Long idPedido, PedidoProduto pedido, String promocaoTipo) throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement("insert into pedidoProduto(id_pedido,id_produto,quantidade,tipoPromocao) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        st.setLong(1,idPedido);
        st.setLong(2,pedido.getProduto().getId());
        st.setInt(3,pedido.getQuantidade());
        
        if (promocaoTipo.length() >0)
            st.setString(4,promocaoTipo);
        else
            st.setNull(4, Types.VARCHAR);
        executeQuery(st);
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
    
    public void insert(Pedido pedido, String promocao) throws SQLException, ClassNotFoundException{
        for(Iterator i = pedido.getProdutos().iterator();i.hasNext();){
            PedidoProduto p = (PedidoProduto)i.next();
            insert(pedido.getId(), p,promocao);
        }
    }
}
