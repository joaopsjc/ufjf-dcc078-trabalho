package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.abstratos.Usuario;
import controller.UsuarioFactory;
import persistence.UsuarioDAO;

public class DoRegisterAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String confirmSenha = request.getParameter("confirmSenha");
        String tipoUsuario = request.getParameter("tipoUsuario");
        String documento = request.getParameter("documento");       
        
        try {
            if (!senha.equals(confirmSenha)){
                request.setAttribute("messageError", "As senhas informadas não são idênticas");
                request.setAttribute("messageSuccess", "");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            
            Usuario usuario = UsuarioFactory.create(tipoUsuario);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setNome(nome);
            usuario.setDocumento(documento);
            UsuarioDAO.getInstance().insert(usuario);
            request.setAttribute("messageError", "");
            request.setAttribute("messageSuccess", "Registro realizado com sucesso!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            
        } catch (ServletException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
