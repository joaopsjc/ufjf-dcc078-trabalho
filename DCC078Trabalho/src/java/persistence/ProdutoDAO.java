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
import java.util.Iterator;
import java.util.List;
import model.abstratos.Produto;
import model.estados.ProdutoEstadoDisponivel;
import controller.ProdutoEstadoFactory;
import controller.ProdutoFactory;

/**
 *
 * @author ice
 */
public class ProdutoDAO  extends ProdutoDAOAbstract{
    private static ProdutoDAO instance = new ProdutoDAO();
    public static ProdutoDAO getInstance(){
        return instance;
    }
    public void insert(Produto produto) throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement("insert into produto(id_empresa,nome,categoria,descricao,quantidade,preco,estado) values (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
        st.setLong(1,produto.getId_empresa());
        st.setString(2,produto.getNome());
        st.setString(3,produto.getCategoria());
        st.setString(4,produto.getDescricao());
        st.setInt(5,produto.getQuantidade());
        st.setDouble(6,produto.getPreco());
        st.setString(7,produto.getNomeEstado());
        executeQuery(st);
    }
    
    public List<Produto> getProdutosByEmpresaId(Long id_empresa) throws SQLException, ClassNotFoundException{
        String query = "select * from produto where id_empresa ="+id_empresa;        
        return preencherNomeEmpresa(getListProdutos(query));
    }
    
    public List<Produto> getProdutosDisponiveisByEmpresaId(Long id_empresa) throws SQLException, ClassNotFoundException{
        String query = "select * from produto where id_empresa ="+id_empresa
                +" and estado='Disponivel'";
        return preencherNomeEmpresa(getListProdutos(query));
    }
    
    public List<Produto> getProdutosDisponiveisByCategoria(String stringSearch) throws SQLException, ClassNotFoundException{
        stringSearch = stringSearch.toLowerCase();
        String query = "select * from produto where (lower(categoria) like '%"+stringSearch+"%'"
        +" or lower(nome) like '%"+stringSearch+"%')"
        +" and estado='Disponivel'";
        return preencherNomeEmpresa(getListProdutos(query));
    }
    
    private List<Produto> preencherNomeEmpresa(List<Produto> listaProdutos) throws SQLException, ClassNotFoundException{
        for(Iterator i = listaProdutos.iterator();i.hasNext();){
            Produto p = (Produto)i.next();
            p.setNomeEmpresa(UsuarioDAO.getInstance().getById(p.getId_empresa()).getNome());
        }        
        return listaProdutos;
    }
    
    public Produto getById(String id) throws SQLException, ClassNotFoundException{
        return getById(Long.parseLong(id));
    }
    
    public Produto getById(Long id) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        Produto produto = null;
        try {
                conn = DatabaseLocator.getInstance().getConection();
                st = conn.createStatement();
                
                ResultSet rs = st.executeQuery("select * from produto where id = "+ id);
                
                if (rs.next())
                {
                    produto = populateProdutoObjectFromDataset(rs);
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return produto;
    }
    
    public void update(Produto produto) throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement("update produto set id_empresa=?, nome=?, categoria=?, descricao=?, quantidade=?, preco=?, estado=? where id=?",Statement.RETURN_GENERATED_KEYS);
        st.setLong(1,produto.getId_empresa());
        st.setString(2,produto.getNome());
        st.setString(3,produto.getCategoria());
        st.setString(4,produto.getDescricao());
        st.setInt(5,produto.getQuantidade());
        st.setDouble(6,produto.getPreco());
        st.setString(7,produto.getNomeEstado());
        st.setLong(8,produto.getId());
        executeQuery(st);
    }
    
    public void delete(Long id_produto) throws SQLException, ClassNotFoundException{
        String query = "delete from produto where id="+id_produto;
        executeQueryDelete(query);
    }
    
    private void delete(String id_produto) throws SQLException, ClassNotFoundException{
        this.delete(Long.parseLong(id_produto));
    }

    public void deleteByIds(List<String> ids) throws SQLException, ClassNotFoundException {
        for(Iterator i = ids.iterator();i.hasNext();)
            delete((String)i.next());
    }
    
    public void updateEstado(Produto produto) throws SQLException, ClassNotFoundException  {        
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement("update produto set estado='"+produto.getNomeEstado()+"' where id="+produto.getId(),Statement.RETURN_GENERATED_KEYS);
        executeQuery(st);
    }
    
    public void updateEstadoByList(List<Produto> produtos) throws SQLException, ClassNotFoundException  {
        for(Iterator i = produtos.iterator();i.hasNext();)
            updateEstado((Produto)i.next());
    }

    public List<Produto> getByListId(List<String> ids) throws SQLException, ClassNotFoundException  {
        List<Produto> listProdutos = new ArrayList<>();
        for(Iterator i = ids.iterator();i.hasNext();)
            listProdutos.add(getById((String)i.next()));
        
        return preencherNomeEmpresa(listProdutos);
    }

}
