/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controller.EnderecoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.abstratos.Endereco;

/**
 *
 * @author ice
 */
public class EnderecoDAO  extends DAO{
        private static EnderecoDAO instance = new EnderecoDAO();
    public static EnderecoDAO getInstance(){
        return instance;
    }
    
    public void insert(Endereco endereco, Long id_usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into endereco(id_usuario,numero,complemento,logradouro,bairro,cidade,estado,tipoEndereco,cep) values (?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id_usuario);
            st.setInt(2,endereco.getNumero());
            st.setString(3,endereco.getComplemento());
            st.setString(4,endereco.getLagradouro());
            st.setString(5,endereco.getBairro());
            st.setString(6,endereco.getCidade());
            st.setString(7,endereco.getEstado());
            st.setString(8,endereco.getTipo());
            st.setLong(9,endereco.getCep());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating endereco failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    public Endereco getById(Long id_endereco) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        Endereco endereco = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from endereco where id="+id_endereco);

            if (rs.next())
            {
                int número = rs.getInt("numero");
                String complemento = rs.getString("complemento");
                String logradouro = rs.getString("logradouro");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String tipoEndereco = rs.getString("tipoEndereco");
                Long cep = rs.getLong("cep");
                endereco = EnderecoFactory.create(tipoEndereco);
                endereco.setId(id_endereco);
                endereco.setNumero(número);
                endereco.setComplemento(complemento);
                endereco.setLagradouro(logradouro);
                endereco.setBairro(bairro);
                endereco.setCidade(cidade);
                endereco.setEstado(estado);
                endereco.setCep(cep);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return endereco;
    }

    public List<Endereco> getEnderecosByUserId(Long id_usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Endereco> enderecos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from endereco where id_usuario="+id_usuario);

            while (rs.next())
            {
                Long id = rs.getLong("id");
                int número = rs.getInt("numero");
                String complemento = rs.getString("complemento");
                String logradouro = rs.getString("logradouro");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String tipoEndereco = rs.getString("tipoEndereco");
                Long cep = rs.getLong("cep");
                Endereco novoEndereco = EnderecoFactory.create(tipoEndereco);
                novoEndereco.setId(id);
                novoEndereco.setNumero(número);
                novoEndereco.setComplemento(complemento);
                novoEndereco.setLagradouro(logradouro);
                novoEndereco.setBairro(bairro);
                novoEndereco.setCidade(cidade);
                novoEndereco.setEstado(estado);
                novoEndereco.setCep(cep);
                enderecos.add(novoEndereco);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return enderecos;
    }
    
    public void update(Endereco endereco) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update produto set id_usuario=?,numero=?,complemento='?',logradouro='?',bairro='?',cidade='?',estado='?',tipoEndereco='?',cep=? where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,endereco.getId_usuario());
            st.setInt(2,endereco.getNumero());
            st.setString(3,endereco.getComplemento());
            st.setString(4,endereco.getLagradouro());
            st.setString(5,endereco.getBairro());
            st.setString(6,endereco.getCidade());
            st.setString(7,endereco.getEstado());
            st.setString(8,endereco.getTipo());
            st.setLong(9,endereco.getCep());
            st.setLong(10,endereco.getId());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update endereco failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void delete(Long id_endereco) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from endereco where id="+id_endereco,Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete endereco failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
}
