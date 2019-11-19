package action;

import controller.Action;
import helper.Helper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.extensores.Entregador;

public class ListaPedidosEntregadorDisponiveisAction  implements Action{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            Entregador entregador = (Entregador) Helper.getInstance().getLoggedUser(request);
            List<Pedido> listaPedidos = new ArrayList<>();
            listaPedidos.add(entregador.getPedidoDisponivel());
            request.setAttribute("listPedidos", listaPedidos);
            request.getRequestDispatcher("Pedido/listaPedidos.jsp").forward(request, response);
            
        } catch(IOException | ServletException ex){
            Logger.getLogger(ListaPedidosEntregadorDisponiveisAction.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("erro.jsp");
        } 
    }
}
