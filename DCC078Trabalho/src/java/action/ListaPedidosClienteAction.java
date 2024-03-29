package action;

import controller.Action;
import helper.Helper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.abstratos.Usuario;
import persistence.PedidoDAO;

public class ListaPedidosClienteAction  implements Action{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            Usuario usuario = Helper.getInstance().getLoggedUser(request);
            Long id_cliente = usuario.getId();
            List<Pedido> listaPedidos = PedidoDAO.getInstance().getPedidosWithProdutoPromocaoByClienteId(id_cliente);
            request.setAttribute("listPedidos", listaPedidos);
            request.getRequestDispatcher("Pedido/listaPedidos.jsp").forward(request, response);
        } catch(SQLException ex){
            Logger.getLogger(ListaPedidosClienteAction.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("erro.jsp");
        } catch (ClassNotFoundException | ServletException ex) {
            Logger.getLogger(ListaPedidosClienteAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
