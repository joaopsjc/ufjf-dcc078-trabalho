/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import model.Pedido;
import model.entregadorChainResponsibility;
import model.extensores.Entregador;
import persistence.PedidoDAO;

/**
 *
 * @author jjsfa
 */
public class ativaChainResponsibilityAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Long id_pedido = Long.parseLong(request.getParameter("pedidoId"));
            Pedido novoPedido = PedidoDAO.getInstance().getById(id_pedido);
            String url = request.getHeader("referer");
            Entregador primeiroEntregador = (Entregador) entregadorChainResponsibility.getInstance().getPrimeiroEntregador();
            primeiroEntregador.novaReponsabilidade(novoPedido);
            request.getRequestDispatcher(url).forward(request, response);
            
        } catch (ServletException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ativaChainResponsibilityAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ativaChainResponsibilityAction.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
}
