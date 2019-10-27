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
public class DoLoginAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
               
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        
        try{
            Usuario usuario = UsuarioDAO.getInstance().authenticate(login,senha);
            if (usuario == null){
                request.setAttribute("messageError", "Login e/ou senha estão incorretos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                
            }else{
                HttpSession sess = request.getSession(true);
                sess.setAttribute("loggedUser", usuario.getId());
                response.sendRedirect("FrontController?action=Home");
            }
        }catch (ServletException ex) {
            response.sendRedirect("erro.jsp");
            Logger.getLogger(DoLoginAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            response.sendRedirect("erro.jsp");
            Logger.getLogger(DoLoginAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            response.sendRedirect("erro.jsp");
            Logger.getLogger(DoLoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
