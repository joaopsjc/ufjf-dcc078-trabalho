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
import model.abstratos.Endereco;
import persistence.EnderecoDAO;

/**
 *
 * @author jjsfa
 */
public class FormDetalheEnderecoAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            Long id = Long.parseLong(request.getParameter("id"));
            Endereco endereco = EnderecoDAO.getInstance().getById(id);
            request.setAttribute("currentEndereco",endereco);
            request.getRequestDispatcher("Usuario/detalheEndereco.jsp").forward(request, response);
        } catch(SQLException ex){
            response.sendRedirect("erro.jsp");
        } catch (ClassNotFoundException | ServletException ex) {
            Logger.getLogger(FormDetalheEnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
