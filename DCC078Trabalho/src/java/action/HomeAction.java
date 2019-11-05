/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EntregadorChainResponsibility;
import model.extensores.Entregador;

/**
 *
 * @author jjsfa
 */
public class HomeAction implements Action{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        List<Entregador> listEntregadores = EntregadorChainResponsibility.getInstance().getListEntregadores();
        request.setAttribute("listEntregadores", listEntregadores);
        try {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(HomeAction.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("erro.jsp");
        }
    }
}
