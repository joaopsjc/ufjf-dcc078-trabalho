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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.abstratos.Usuario;
import persistence.UsuarioDAO;

/**
 *
 * @author jjsfa
 */
public class AlterarSenhaAction implements Action{
     @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String senha = request.getParameter("senha");
        String novaSenha = request.getParameter("novaSenha");
        String confirmSenha = request.getParameter("confirmSenha");
        String messageError = "";
        request.setAttribute("messageSuccess", "");
        try {
            HttpSession sess = request.getSession(true);
            
            Usuario currentUser = (Usuario)sess.getAttribute("loggedUser");
            Usuario usuario = UsuarioDAO.getInstance().authenticate(currentUser.getLogin(),senha);
            if (usuario == null)
                messageError = "Senha incorreta!";
            if (!confirmSenha.equals(novaSenha))
                messageError = "Senhas informadas não conferem!";
            
            if (messageError.length()>0){
                request.setAttribute("messageError", messageError);
                request.getRequestDispatcher("Usuario/alterarSenha.jsp").forward(request, response);
                return;
            }
            
            UsuarioDAO.getInstance().updateSenha(usuario,novaSenha);
            request.setAttribute("messageError", "");
            request.setAttribute("messageSuccess", "Senha atualizada com sucesso!");
            request.getRequestDispatcher("Usuario/alterarSenha.jsp").forward(request, response);
            
        } catch (ServletException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
