package action;

import controller.Action;
import helper.Helper;
import helper.HelperPedido;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.abstratos.Usuario;
import model.extensores.Entregador;

public class AceitarEntregaPedidoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException { 
        String selectedIds= request.getParameter("selectedIds");
        List<String> idsList = new ArrayList<>(Arrays.asList(selectedIds.split(",")));
        Usuario entregador = Helper.getInstance().getLoggedUser(request);
        List<Pedido> pedidos = entregador.getPedidos(idsList);
        HelperPedido.getInstance().aceitarEntrega(pedidos, (Entregador) entregador);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("");
    }
}
