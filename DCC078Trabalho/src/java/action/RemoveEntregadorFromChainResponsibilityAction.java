/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
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
            Long id_entregador = Long.parseLong(request.getParameter("entregadorId"));
            String url = request.getHeader("referer");
            
            Entregador entregadorAdiciona = (Entregador) UsuarioDAO.getInstance().getById(id_entregador);
            EntregadorChainResponsibility.getInstance().removeFromChain(entregadorAdiciona);
            request.getRequestDispatcher(url).forward(request, response);
            
        } catch (ServletException | ClassNotFoundException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RemoveEntregadorFromChainResponsibilityAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
