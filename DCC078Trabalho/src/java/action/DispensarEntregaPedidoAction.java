/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.abstratos.Usuario;
import model.extensores.Entregador;
import persistence.PedidoDAO;

/**
 *
 * @author jjsfa
 */
public class DispensarEntregaPedidoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException { 
        String selectedIds= request.getParameter("selectedIds");
        List<String> idsList = new ArrayList<>(Arrays.asList(selectedIds.split(",")));
        Usuario entregador = Helper.getInstance().getLoggedUser(request);
        List<Pedido> pedidos = entregador.getPedidos(idsList);
        HelperPedido.getInstance().dispensarEntrega(pedidos, (Entregador) entregador);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("");
    }
}
