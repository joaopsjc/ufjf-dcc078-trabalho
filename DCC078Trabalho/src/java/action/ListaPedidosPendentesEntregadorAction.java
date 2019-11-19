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

public class ListaPedidosPendentesEntregadorAction  implements Action{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            Usuario usuarioEntregador = Helper.getInstance().getLoggedUser(request);
            List<Pedido> listaPedidos = usuarioEntregador.getPedidos();
            request.setAttribute("listPedidos", listaPedidos);
            request.getRequestDispatcher("Pedido/listaPedidosPendentesEntregador.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ListaPedidosPendentesEntregadorAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
