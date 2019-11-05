/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import helper.Helper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EntregadorChainResponsibility;
import model.abstratos.Usuario;
import model.extensores.Entregador;

/**
 *
 * @author jjsfa
 */
public class DoLogoutAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession sess = request.getSession(false);
        Usuario user = Helper.getInstance().getLoggedUser(request);
        if (user instanceof  Entregador)
            EntregadorChainResponsibility.getInstance().removeFromChain((Entregador) user);
        if (sess !=null)
            sess.invalidate();
        
        response.sendRedirect("login.jsp");
    }
}
