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
import model.Pedido;

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
            st.setLong(1, pedido.getCliente().getId());
            st.setLong(2, pedido.getEmpresa().getId());
            st.setLong(3, pedido.getEntregador().getId());
            st.setLong(4, pedido.getEndereco().getId());
            st.setDouble(5, pedido.getFrete());
            st.setString(6, pedido.getEstado().getEstado());
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
