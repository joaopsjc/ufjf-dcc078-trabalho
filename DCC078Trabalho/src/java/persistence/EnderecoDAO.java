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
import java.util.Iterator;
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
    
    public void insert(Endereco endereco) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into endereco(id_usuario,numero,complemento,logradouro,bairro,cidade,estado,tipoEndereco,cep) values (?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,endereco.getId_usuario());
            st.setString(2,endereco.getNumero());
            st.setString(3,endereco.getComplemento());
            st.setString(4,endereco.getLogradouro());
            st.setString(5,endereco.getBairro());
            st.setString(6,endereco.getCidade());
            st.setString(7,endereco.getEstado());
            st.setString(8,endereco.getTipo());
            st.setString(9,endereco.getCep());
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
    public Endereco getById(String id) throws SQLException, ClassNotFoundException{
        return getById(Long.parseLong(id));
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
                String numero = rs.getString("numero");
                String complemento = rs.getString("complemento");
                String logradouro = rs.getString("logradouro");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String tipoEndereco = rs.getString("tipoEndereco");
                String cep = rs.getString("cep");
                boolean isPrincipal = rs.getInt("isPrincipal")==1;
                Long id_usuario = rs.getLong("id_usuario");
                endereco = EnderecoFactory.create(tipoEndereco);
                endereco.setId(id_endereco);
                endereco.setNumero(numero);
                endereco.setComplemento(complemento);
                endereco.setLogradouro(logradouro);
                endereco.setBairro(bairro);
                endereco.setCidade(cidade);
                endereco.setEstado(estado);
                endereco.setCep(cep);
                endereco.setPrincipal(isPrincipal);
                endereco.setId_usuario(id_usuario);
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
                String numero = rs.getString("numero");
                String complemento = rs.getString("complemento");
                String logradouro = rs.getString("logradouro");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String tipoEndereco = rs.getString("tipoEndereco");
                String cep = rs.getString("cep");
                boolean isPrincipal = rs.getInt("isPrincipal")==1;
                Endereco novoEndereco = EnderecoFactory.create(tipoEndereco);
                novoEndereco.setId(id);
                novoEndereco.setNumero(numero);
                novoEndereco.setComplemento(complemento);
                novoEndereco.setLogradouro(logradouro);
                novoEndereco.setBairro(bairro);
                novoEndereco.setCidade(cidade);
                novoEndereco.setEstado(estado);
                novoEndereco.setCep(cep);
                novoEndereco.setPrincipal(isPrincipal);
                novoEndereco.setId_usuario(id_usuario);
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
            st = conn.prepareStatement("update endereco set id_usuario=?,numero=?,complemento=?,logradouro=?,bairro=?,cidade=?,estado=?,tipoEndereco=?,cep=? where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,endereco.getId_usuario());
            st.setString(2,endereco.getNumero());
            st.setString(3,endereco.getComplemento());
            st.setString(4,endereco.getLogradouro());
            st.setString(5,endereco.getBairro());
            st.setString(6,endereco.getCidade());
            st.setString(7,endereco.getEstado());
            st.setString(8,endereco.getTipo());
            st.setString(9,endereco.getCep());
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
    
    private void delete(String id) throws SQLException, ClassNotFoundException{
        delete((Long.parseLong(id)));
    }

    public void deleteByIds(List<String> idsList) throws SQLException,  ClassNotFoundException {
        for(Iterator i = idsList.iterator();i.hasNext();)
            delete((String)i.next());
    }

    public void setEnderecoPrincipal(Long id, Long idUsuario) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update endereco set isPrincipal=0 where id_usuario=?",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,idUsuario);
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update endereco failed, no rows affected.");
            }
            st = conn.prepareStatement("update endereco set isPrincipal=1 where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id);
            
            affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update endereco failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public Endereco getPrincipalByUserId(Long id_usuario) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        Endereco endereco = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from endereco where isPrincipal=1 and id_usuario="+id_usuario);

            if (rs.next())
            {
                String numero = rs.getString("numero");
                String complemento = rs.getString("complemento");
                String logradouro = rs.getString("logradouro");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String tipoEndereco = rs.getString("tipoEndereco");
                String cep = rs.getString("cep");
                boolean isPrincipal = rs.getInt("isPrincipal")==1;
                Long id_endereco = rs.getLong("id");
                endereco = EnderecoFactory.create(tipoEndereco);
                endereco.setId(id_endereco);
                endereco.setNumero(numero);
                endereco.setComplemento(complemento);
                endereco.setLogradouro(logradouro);
                endereco.setBairro(bairro);
                endereco.setCidade(cidade);
                endereco.setEstado(estado);
                endereco.setCep(cep);
                endereco.setPrincipal(isPrincipal);
                endereco.setId_usuario(id_usuario);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return endereco;
    }

}
