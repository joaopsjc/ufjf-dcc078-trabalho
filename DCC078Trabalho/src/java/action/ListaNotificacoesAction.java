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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Notificacao;
import model.abstratos.Usuario;
import persistence.NotificacaoDAO;

/**
 *
 * @author UC249567
 */
public class ListaNotificacoesAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            Usuario currentUser = Helper.getInstance().getLoggedUser(request);
            List<Notificacao> listNotificacoes = NotificacaoDAO.getInstance().getNotificacoesNaoLidasByUserId(currentUser.getId());
            request.setAttribute("listNotificacoes", listNotificacoes);
            request.getRequestDispatcher("Cliente/notificacoes.jsp").forward(request, response);
        } catch(SQLException | ClassNotFoundException | ServletException ex){
            response.sendRedirect("erro.jsp");
            Logger.getLogger(ResumoEmpresasAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
