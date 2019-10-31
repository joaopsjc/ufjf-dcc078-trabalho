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
public class ModificarPerfilAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String documento = request.getParameter("documento");
        
        
        try {
            HttpSession sess = request.getSession(true);
            
            Usuario usuario = (Usuario)sess.getAttribute("loggedUser");
            usuario.setLogin(login);
            usuario.setNome(nome);
            usuario.setDocumento(documento);
            UsuarioDAO.getInstance().update(usuario);
            request.setAttribute("messageError", "");
            request.setAttribute("messageSuccess", "Perfil atualizado com sucesso!");
            request.getRequestDispatcher("Usuario/profile.jsp").forward(request, response);
            
        } catch (ServletException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
