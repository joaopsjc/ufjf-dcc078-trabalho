/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controller.ProdutoEstadoFactory;
import controller.ProdutoFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.abstratos.Produto;

/**
 *
 * @author UC249567
 */
public abstract class ProdutoDAOAbstract extends DAO{
    
    public Produto populateProdutoObjectFromDataset(ResultSet rs) throws SQLException{
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");
        String categoria = rs.getString("categoria");
        String descricao = rs.getString("descricao");
        int quantidadeProduto = rs.getInt("quantidade");
        double preco = rs.getDouble("preco");
        String estado = rs.getString("estado");
        Long id_empresa = rs.getLong("id_empresa");

        Produto novoProduto =  ProdutoFactory.create(categoria);
        novoProduto.setId(id);
        novoProduto.setNome(nome);
        novoProduto.setId_empresa(id_empresa);
        novoProduto.setDescricao(descricao);
        novoProduto.setQuantidade(quantidadeProduto);
        novoProduto.setPreco(preco);
        novoProduto.setEstado(ProdutoEstadoFactory.create(estado));
        
        return novoProduto;
    }
    
    
    public List<Produto> getListProdutos(String query) throws ClassNotFoundException, SQLException{
        Connection conn = null;
        Statement st = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                Produto novoProduto = populateProdutoObjectFromDataset(rs);
                produtos.add(novoProduto);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return produtos;
    }
}
