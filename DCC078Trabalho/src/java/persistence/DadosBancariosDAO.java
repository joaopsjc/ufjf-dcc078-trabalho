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
import model.DadosBancarios;

/**
 *
 * @author ice
 */
public class DadosBancariosDAO  extends DAO{
        private static DadosBancariosDAO instance = new DadosBancariosDAO();
    public static DadosBancariosDAO getInstance(){
        return instance;
    }
public void insert(DadosBancarios dadosBancarios, Long id_usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into usuario(id_usuario,nomeBanco,codigoBanco,agencia,numero) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_usuario);
            st.setString(2,dadosBancarios.getNomeBanco());
            st.setLong(3,dadosBancarios.getCodigoBanco());
            st.setLong(4,dadosBancarios.getAgencia());
            st.setLong(5,dadosBancarios.getNumero());
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
