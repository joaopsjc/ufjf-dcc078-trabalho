/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TipoUsuario;
import persistence.EmpresaDAO;
import persistence.TipoUsuarioDAO;

/**
 *
 * @author jjsfa
 */
public class RegisterAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            List<TipoUsuario> listTipoUsuario = TipoUsuarioDAO.getInstance().getTiposUsuario();
            request.setAttribute("listTipoUsuario", listTipoUsuario);
            request.setAttribute("messageError", "");
            request.setAttribute("messageSuccess", "");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch(SQLException ex){
            response.sendRedirect("erro.jsp");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(RegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
