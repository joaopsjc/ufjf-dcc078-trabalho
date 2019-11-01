/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import controller.PedidoEstadoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import model.abstratos.Endereco;
import model.abstratos.Usuario;
import model.interfaces.PedidoEstado;

/**
 *
 * @author ice
 */
public class PedidoDAO  extends DAO{
        private static PedidoDAO instance = new PedidoDAO();
    public static PedidoDAO getInstance(){
        return instance;
    }
    public void insert(Pedido pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into pedido(id_cliente,id_empresa,id_entregador,id_endereco,frete,estado) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            if (pedido.getCliente()!=null)
                st.setLong(1, pedido.getCliente().getId());
            else
                st.setNull(1, Types.INTEGER);
            if (pedido.getEmpresa()!=null)
                st.setLong(2, pedido.getEmpresa().getId());
            else
                st.setNull(2, Types.INTEGER);
            if (pedido.getEntregador()!=null)
                st.setLong(3, pedido.getEntregador().getId());
            else
                st.setNull(3, Types.INTEGER);
            if (pedido.getEndereco()!=null)
                st.setLong(4, pedido.getEndereco().getId());
            else
                st.setNull(4, Types.INTEGER);
            
            st.setDouble(5, pedido.getFrete());
            st.setString(6, pedido.getEstado().getEstado());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating pedido failed, no rows affected.");
            }
            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                pedido.setId(rs.getLong(1));
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    public Pedido getById(Long id_pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        Pedido pedido = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from pedido where id="+id_pedido);

            if (rs.next())
            {
                Long id_cliente = rs.getLong("id_cliente");
                Long id_empresa = rs.getLong("id_empresa");
                Long id_entregador = rs.getLong("id_entregador");
                Long id_endereco = rs.getLong("id_endereco");
                Double frete = rs.getDouble("frete");
                String estado = rs.getString("estado");
                Usuario cliente = UsuarioDAO.getInstance().getById(id_cliente);
                Usuario empresa = UsuarioDAO.getInstance().getById(id_empresa);
                Usuario entregador = UsuarioDAO.getInstance().getById(id_entregador);
                Endereco endereco = EnderecoDAO.getInstance().getById(id_endereco);
                PedidoEstado pedidoEstado = PedidoEstadoFactory.create(estado);
                
                pedido = new Pedido(id_pedido, endereco, frete,pedidoEstado);
                pedido.setCliente(cliente);
                pedido.setEmpresa(empresa);
                pedido.setEntregador(entregador);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return pedido;
    }
    public List<Pedido> getAll() throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from pedido");

            while (rs.next())
            {
                
                Long id = rs.getLong("id");
                Long id_cliente = rs.getLong("id_cliente");
                Long id_entregador = rs.getLong("id_entregador");
                Long id_empresa = rs.getLong("id_empresa");
                Long id_endereco = rs.getLong("id_endereco");
                Double frete = rs.getDouble("frete");
                String estado = rs.getString("estado");
                
                Usuario cliente = UsuarioDAO.getInstance().getById(id_cliente);
                Usuario empresa = UsuarioDAO.getInstance().getById(id_empresa);
                Usuario entregador = UsuarioDAO.getInstance().getById(id_entregador);
                Endereco endereco = EnderecoDAO.getInstance().getById(id_endereco);
                PedidoEstado pedidoEstado = PedidoEstadoFactory.create(estado);
                
                Pedido novoPedido = new Pedido(id, endereco, frete,pedidoEstado);
                novoPedido.setCliente(cliente);
                novoPedido.setEntregador(entregador);
                novoPedido.setEmpresa(empresa);
                pedidos.add(novoPedido);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return pedidos;
    }
    public List<Pedido> getPedidosByClienteId(Long id_cliente) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from pedido where id_cliente="+id_cliente);

            while (rs.next())
            {                
                Long id = rs.getLong("id");
                Long id_empresa = rs.getLong("id_empresa");
                Long id_entregador = rs.getLong("id_entregador");
                Long id_endereco = rs.getLong("id_endereco");
                Double frete = rs.getDouble("frete");
                String estado = rs.getString("estado");
                
                Usuario empresa = UsuarioDAO.getInstance().getById(id_empresa);
                Usuario entregador = UsuarioDAO.getInstance().getById(id_entregador);
                Endereco endereco = EnderecoDAO.getInstance().getById(id_endereco);
                Usuario cliente = UsuarioDAO.getInstance().getById(id_cliente);
                String[] status = estado.split(" ");
                estado = String.join("",estado.split(" "));
                PedidoEstado pedidoEstado = PedidoEstadoFactory.create(estado);
                
                Pedido novoPedido = new Pedido(id, endereco, frete,pedidoEstado);
                novoPedido.setEmpresa(empresa);
                novoPedido.setEntregador(entregador);
                novoPedido.setCliente(cliente);
                pedidos.add(novoPedido);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return pedidos;
    }
    public List<Pedido> getPedidosByEmpresaId(Long id_empresa) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from pedido where id_empresa="+id_empresa);

            while (rs.next())
            {
                
                Long id = rs.getLong("id");
                Long id_cliente = rs.getLong("id_cliente");
                Long id_entregador = rs.getLong("id_entregador");
                Long id_endereco = rs.getLong("id_endereco");
                Double frete = rs.getDouble("frete");
                String estado = rs.getString("estado");
                Usuario cliente = UsuarioDAO.getInstance().getById(id_cliente);
                Usuario entregador = UsuarioDAO.getInstance().getById(id_entregador);
                Endereco endereco = EnderecoDAO.getInstance().getById(id_endereco);
                PedidoEstado pedidoEstado = PedidoEstadoFactory.create(estado);
                
                Pedido novoPedido = new Pedido(id, endereco, frete,pedidoEstado);
                novoPedido.setCliente(cliente);
                novoPedido.setEntregador(entregador);
                pedidos.add(novoPedido);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return pedidos;
    }
    
    public List<Pedido> getPedidosByEntregadorId(Long id_entregador) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from pedido where id_entregador="+id_entregador);

            while (rs.next())
            {
                Long id = rs.getLong("id");
                Long id_empresa = rs.getLong("id_empresa");
                Long id_cliente = rs.getLong("id_cliente");
                Long id_endereco = rs.getLong("id_endereco");
                Double frete = rs.getDouble("frete");
                String estado = rs.getString("estado");
                Usuario empresa = UsuarioDAO.getInstance().getById(id_empresa);
                Usuario cliente = UsuarioDAO.getInstance().getById(id_cliente);
                Endereco endereco = EnderecoDAO.getInstance().getById(id_endereco);
                PedidoEstado pedidoEstado = PedidoEstadoFactory.create(estado);
                
                Pedido novoPedido = new Pedido(id, endereco, frete,pedidoEstado);
                novoPedido.setEmpresa(empresa);
                novoPedido.setCliente(cliente);
                pedidos.add(novoPedido);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return pedidos;
    }
    
    public List<Pedido> getPedidosConcluidosByEntregadorId(Long id_entregador) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from pedido where estado='Concluido' AND id_entregador="+id_entregador);

            while (rs.next())
            {
                Long id = rs.getLong("id");
                Long id_empresa = rs.getLong("id_empresa");
                Long id_cliente = rs.getLong("id_cliente");
                Long id_endereco = rs.getLong("id_endereco");
                Double frete = rs.getDouble("frete");
                String estado = rs.getString("estado");
                Usuario empresa = UsuarioDAO.getInstance().getById(id_empresa);
                Usuario cliente = UsuarioDAO.getInstance().getById(id_cliente);
                Endereco endereco = EnderecoDAO.getInstance().getById(id_endereco);
                PedidoEstado pedidoEstado = PedidoEstadoFactory.create(estado);
                
                Pedido novoPedido = new Pedido(id, endereco, frete,pedidoEstado);
                novoPedido.setEmpresa(empresa);
                novoPedido.setCliente(cliente);
                pedidos.add(novoPedido);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return pedidos;
    }
    
    public List<Pedido> getPedidosACaminhoByEntregadorId(Long id_entregador) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Pedido> pedidos = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from pedido where estado='ACaminho' AND id_entregador="+id_entregador);

            while (rs.next())
            {
                Long id = rs.getLong("id");
                Long id_empresa = rs.getLong("id_empresa");
                Long id_cliente = rs.getLong("id_cliente");
                Long id_endereco = rs.getLong("id_endereco");
                Double frete = rs.getDouble("frete");
                String estado = rs.getString("estado");
                Usuario empresa = UsuarioDAO.getInstance().getById(id_empresa);
                Usuario cliente = UsuarioDAO.getInstance().getById(id_cliente);
                Endereco endereco = EnderecoDAO.getInstance().getById(id_endereco);
                PedidoEstado pedidoEstado = PedidoEstadoFactory.create(estado);
                
                Pedido novoPedido = new Pedido(id, endereco, frete,pedidoEstado);
                novoPedido.setEmpresa(empresa);
                novoPedido.setCliente(cliente);
                pedidos.add(novoPedido);
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return pedidos;
    }
    
    public void updateEstado(Pedido pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update pedido set estado='?' where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setString(1,pedido.getEstado().getEstado());
            st.setLong(2,pedido.getId());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update estado failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void delete(Long id_pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("delete from pedido where id="+id_pedido,Statement.RETURN_GENERATED_KEYS);
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete pedido failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
}
