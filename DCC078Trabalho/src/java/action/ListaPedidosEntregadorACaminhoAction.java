/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import helper.Helper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.extensores.Entregador;
import persistence.PedidoDAO;

/**
 *
 * @author jjsfa
 */
public class ListaPedidosEntregadorACaminhoAction  implements Action{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            
            Entregador entregador = (Entregador) Helper.getInstance().getLoggedUser(request);
            Long id_entregador = entregador.getId();
            List<Pedido> listaPedidos = PedidoDAO.getInstance().getPedidosACaminhoByEntregadorId(id_entregador);
            request.setAttribute("listPedidos", listaPedidos);
            request.getRequestDispatcher("Pedido/listaPedidos.jsp").forward(request, response);
            
        } catch(IOException | ServletException ex){
            Logger.getLogger(ListaPedidosEntregadorACaminhoAction.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("erro.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(ListaPedidosEntregadorACaminhoAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaPedidosEntregadorACaminhoAction.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
