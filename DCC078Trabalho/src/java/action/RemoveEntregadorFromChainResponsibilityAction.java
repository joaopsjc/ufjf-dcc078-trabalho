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

public class RemoveEntregadorFromChainResponsibilityAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String url = request.getHeader("referer");
            
            Entregador entregadorRemover = (Entregador) Helper.getInstance().getLoggedUser(request);
            try {
                EntregadorChainResponsibility.getInstance().removeFromChain(entregadorRemover);
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
