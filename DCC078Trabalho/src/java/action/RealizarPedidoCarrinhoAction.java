package action;

import controller.Action;
import helper.Helper;
import helper.HelperPedido;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.estados.PedidoAguardandoRestaurante;
import persistence.PedidoDAO;

public class RealizarPedidoCarrinhoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        try {
            Pedido pedido = Helper.getInstance().getCarrinhoByClienteId(request);
            String qtdItens= request.getParameter("qtdItens");
            String promocaoTipo = request.getParameter("promocao");
            
            List<String> qtdItensList = new ArrayList<>(Arrays.asList(qtdItens.split(",")));
            pedido.atualizaQuantidades(qtdItensList);
            pedido.setEstado(new PedidoAguardandoRestaurante());
            pedido.setCliente(Helper.getInstance().getLoggedUser(request));
            pedido.setEndereco();
            List<Pedido> pedidos = HelperPedido.getInstance().dividePedidoPorEmpresa(pedido);
            PedidoDAO.getInstance().insert(pedidos,promocaoTipo);
            
            Helper.getInstance().zeraCarrinhoByClienteId(request);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RealizarPedidoCarrinhoAction.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write(ex.getMessage());
        }
    }
    
    
}
