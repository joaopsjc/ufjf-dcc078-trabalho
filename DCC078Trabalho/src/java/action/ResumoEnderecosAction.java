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
import model.abstratos.Endereco;
import model.abstratos.Usuario;
import persistence.EnderecoDAO;

public class ResumoEnderecosAction implements Action{

    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            Usuario currentUser = Helper.getInstance().getLoggedUser(request);
            List<Endereco> listEnderecos = EnderecoDAO.getInstance().getEnderecosByUserId(currentUser.getId());
            request.setAttribute("listEnderecos", listEnderecos);
            request.getRequestDispatcher("Usuario/resumoEnderecos.jsp").forward(request, response);
        } catch(SQLException ex){
            response.sendRedirect("erro.jsp");
        } catch (ClassNotFoundException | ServletException ex) {
            Logger.getLogger(ResumoEmpresasAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
