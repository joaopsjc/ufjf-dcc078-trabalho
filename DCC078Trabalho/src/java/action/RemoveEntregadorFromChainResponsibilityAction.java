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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import model.EntregadorChainResponsibility;
import model.extensores.Entregador;
import persistence.UsuarioDAO;

/**
 *
 * @author jjsfa
 */
public class RemoveEntregadorFromChainResponsibilityAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String url = request.getHeader("referer");
            
            Entregador entregadorAdiciona = (Entregador) Helper.getInstance().getLoggedUser(request);
            try {
                EntregadorChainResponsibility.getInstance().removeFromChain(entregadorAdiciona);
            } catch (SQLException ex) {
                Logger.getLogger(RemoveEntregadorFromChainResponsibilityAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RemoveEntregadorFromChainResponsibilityAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher(url).forward(request, response);
            
        } catch (ServletException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
