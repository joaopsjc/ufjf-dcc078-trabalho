/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void insert(Produto produto, Long id_empresa) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into produto(id_empresa,nome,categoria,descricao,quantidade,preco) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_empresa);
            st.setString(2,produto.getNome());
            st.setString(3,produto.getCategoria());
            st.setString(4,produto.getDescricao());
            st.setInt(5,produto.getQuantidade());
            st.setDouble(6,produto.getPreco());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
}
