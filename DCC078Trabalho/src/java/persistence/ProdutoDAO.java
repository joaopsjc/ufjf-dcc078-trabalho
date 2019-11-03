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
            st = conn.prepareStatement("insert into produto(id_empresa,nome,categoria,descricao,quantidade,preco,estado) values (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,produto.getId_empresa());
            st.setString(2,produto.getNome());
            st.setString(3,produto.getCategoria());
            st.setString(4,produto.getDescricao());
            st.setInt(5,produto.getQuantidade());
            st.setDouble(6,produto.getPreco());
            st.setString(7,produto.getNomeEstado());
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
                    listaProdutos.add(novoProduto);
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return preencherNomeEmpresa(listaProdutos);
    }
    
    public List<Produto> getProdutosDisponiveisByEmpresaId(Long id_empresa) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Produto> listaProdutos = new ArrayList();
        try {
                conn = DatabaseLocator.getInstance().getConection();
                st = conn.createStatement();
                
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery("select * from produto where id_empresa ="+id_empresa
                +" and estado='Disponivel'");
                
                // iterate through the java resultset
                while (rs.next())
                {
                    Long id = rs.getLong("id");
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
                    listaProdutos.add(novoProduto);
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return preencherNomeEmpresa(listaProdutos);
    }
    
    public List<Produto> getProdutosDisponiveisByCategoria(String stringSearch) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Produto> listaProdutos = new ArrayList();
        try {
                conn = DatabaseLocator.getInstance().getConection();
                st = conn.createStatement();
                
                // execute the query, and get a java resultset
                stringSearch = stringSearch.toLowerCase();
                ResultSet rs = st.executeQuery("select * from produto where (lower(categoria) like '%"+stringSearch+"%'"
                +" or lower(nome) like '%"+stringSearch+"%')"
                +" and estado='Disponivel'");
                // iterate through the java resultset
                while (rs.next())
                {
                    Long id = rs.getLong("id");
                    Long id_empresa = rs.getLong("id_empresa");
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    int quantidade = rs.getInt("quantidade");
                    double preco = rs.getDouble("preco");
                    String estado = rs.getString("estado");
                    String categoria = rs.getString("categoria");
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
                    listaProdutos.add(novoProduto);
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return preencherNomeEmpresa(listaProdutos);
    }
    
    public List<Produto> getProdutosByCategoria(String stringSearch) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Produto> listaProdutos = new ArrayList();
        try {
                conn = DatabaseLocator.getInstance().getConection();
                st = conn.createStatement();
                
                // execute the query, and get a java resultset
                stringSearch = stringSearch.toLowerCase();
                ResultSet rs = st.executeQuery("select * from produto where (lower(categoria) like '%"+stringSearch+"%'"
                +" or lower(nome) like '%"+stringSearch+"%')");
                // iterate through the java resultset
                while (rs.next())
                {
                    Long id = rs.getLong("id");
                    Long id_empresa = rs.getLong("id_empresa");
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    int quantidade = rs.getInt("quantidade");
                    double preco = rs.getDouble("preco");
                    String estado = rs.getString("estado");
                    String categoria = rs.getString("categoria");
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
                    listaProdutos.add(novoProduto);
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return preencherNomeEmpresa(listaProdutos);
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
                
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery("select * from produto where id = "+ id +"");
                
                // iterate through the java resultset
                if (rs.next())
                {
                    Long id_empresa = rs.getLong("id_empresa");
                    String nome = rs.getString("nome");
                    String categoria = rs.getString("categoria");
                    String descricao = rs.getString("descricao");
                    int quantidade = rs.getInt("quantidade");
                    double preco = rs.getDouble("preco");
                    String estado = rs.getString("estado");
                    produto =  ProdutoFactory.create(categoria);
                    //categoria não é mais um elemento setável, 
                    // utilização do produtoFactory para criação de novo produtos
                    //new Produto(id,nome,descricao, categoria, quantidade, preco,id_empresa);
                    produto.setId(id);
                    produto.setNome(nome);
                    produto.setId_empresa(id_empresa);
                    produto.setDescricao(descricao);
                    produto.setQuantidade(quantidade);
                    produto.setPreco(preco);
                    produto.setEstado(ProdutoEstadoFactory.create(estado));
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return produto;
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
                    listaProdutos.add(novoProduto);
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
            st = conn.prepareStatement("update produto set id_empresa=?, nome=?, categoria=?, descricao=?, quantidade=?, preco=?, estado=? where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,produto.getId_empresa());
            st.setString(2,produto.getNome());
            st.setString(3,produto.getCategoria());
            st.setString(4,produto.getDescricao());
            st.setInt(5,produto.getQuantidade());
            st.setDouble(6,produto.getPreco());
            st.setString(7,produto.getNomeEstado());
            st.setLong(8,produto.getId());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update produto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void delete(Long id_produto) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from produto where id="+id_produto,Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete produto failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    private void delete(String id_produto) throws SQLException, ClassNotFoundException{
        this.delete(Long.parseLong(id_produto));
    }

    public void deleteByIds(List<String> ids) throws SQLException, ClassNotFoundException {
        for(Iterator i = ids.iterator();i.hasNext();)
            delete((String)i.next());
    }
    
    public void updateEstado(Produto produto) throws SQLException, ClassNotFoundException  {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update produto set estado='"+produto.getNomeEstado()+"' where id="+produto.getId(),Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Atualização do estado do produto falhou, 0 linhas afetadas.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
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
