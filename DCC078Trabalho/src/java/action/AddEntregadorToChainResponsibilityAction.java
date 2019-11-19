package action;

import controller.Action;
import helper.Helper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import model.EntregadorChainResponsibility;
import model.extensores.Entregador;

public class AddEntregadorToChainResponsibilityAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String url = request.getHeader("referer");
            
            Entregador entregadorAdiciona = (Entregador) Helper.getInstance().getLoggedUser(request);
            
            EntregadorChainResponsibility.getInstance().addToChain(entregadorAdiciona);
            request.getRequestDispatcher(url).forward(request, response);
            
        } catch (ServletException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
