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
import model.abstratos.Produto;
import controller.ProdutoEstadoFactory;
import controller.ProdutoFactory;
import java.util.Iterator;
import model.extensores.ProdutoCombo;

/**
 *
 * @author ice
 */
public class ComboProdutoDAO  extends DAO{
    private static final ComboProdutoDAO instance = new ComboProdutoDAO();
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
            executeUpdate(st);
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void deleteByProdutoId(Long id_produto) throws SQLException, ClassNotFoundException{
        String query = "delete from comboProduto where id_produto="+id_produto;
        executeQueryDelete(query);
    }
    
    public void deleteByComboId(Long id_combo) throws SQLException, ClassNotFoundException{
        String query = "delete from comboProduto where id_combo="+id_combo;
        executeQueryDelete(query);
    }
    
    public void delete(Long id_produto, Long id_combo) throws SQLException, ClassNotFoundException{
        String query = "delete from comboProduto where id_combo= "+id_combo+" AND " + " id_produto= "+id_produto;
        executeQueryDelete(query);
    }
    
    public List<Produto> getNotAllProdutosByComboId(Long id_combo, long id_empresa) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(
                    "SELECT * from produto P where p.id not in (" +
                    "select id_produto from comboproduto where id_combo = " + id_combo +
                    ") and P.categoria!='Combo' and P.id_empresa="+id_empresa);
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
    
    public void insert(ProdutoCombo combo) throws SQLException, ClassNotFoundException{
        for(Iterator<Produto> i = combo.getProdutos().iterator();i.hasNext();){
            Produto novoProduto = i.next();
            insert(novoProduto.getId(),combo.getId());
        }
    }
    public void delete(ProdutoCombo combo) throws SQLException, ClassNotFoundException{
        for(Iterator<Produto> i = combo.getProdutos().iterator();i.hasNext();){
            Produto novoProduto = i.next();
            delete(novoProduto.getId(),combo.getId());
        }
    }
    
    private Produto populateProdutoObjectFromDataset(ResultSet rs) throws SQLException{
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
}
