/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controller.ContatoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
            st = conn.prepareStatement("insert into dadosBancarios(id_usuario,nomeBanco,codigoBanco,agencia,numero) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_usuario);
            st.setString(2,dadosBancarios.getNomeBanco());
            st.setLong(3,dadosBancarios.getCodigoBanco());
            st.setLong(4,dadosBancarios.getAgencia());
            st.setLong(5,dadosBancarios.getNumero());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating dadosBancarios failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public List<DadosBancarios> getDadosBancariosByUserId(Long id_usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<DadosBancarios> dadosBancarios = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from dadosBancarios where id_usuario='"+id_usuario+"'");

            while (rs.next())
            {
                Long id = rs.getLong("id");
                String nomeBanco = rs.getString("nomeBanco");
                Long codigoBanco = rs.getLong("codigoBanco");
                Long agencia = rs.getLong("agencia");
                Long numero = rs.getLong("numero");
                DadosBancarios novoDadosBancarios = new DadosBancarios(id, codigoBanco, agencia, numero, nomeBanco);
                dadosBancarios.add(novoDadosBancarios);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return dadosBancarios;
    }
}
