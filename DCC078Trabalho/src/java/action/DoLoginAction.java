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
import model.EntregadorChainResponsibility;
import model.abstratos.Usuario;
import model.extensores.Entregador;
import persistence.UsuarioDAO;

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
                HttpSession sessaoAtual = request.getSession(true);
                sessaoAtual.setAttribute("loggedUser", usuario);
                if (usuario instanceof  Entregador)
                    EntregadorChainResponsibility.getInstance().addToChain((Entregador) usuario);
                response.sendRedirect("FrontController?action=Home");
            }
        }catch (ServletException | SQLException | ClassNotFoundException ex) {
            response.sendRedirect("erro.jsp");
            Logger.getLogger(DoLoginAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
