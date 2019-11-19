package action;

import controller.Action;
import helper.Helper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.abstratos.Usuario;
import persistence.EnderecoDAO;

public class TornarEnderecoPrincipalAction   implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id_endereco = request.getParameter("selectedId");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("");
            
        Usuario currentUser = Helper.getInstance().getLoggedUser(request);
        try {
            EnderecoDAO.getInstance().setEnderecoPrincipal(Long.parseLong(id_endereco),currentUser.getId());            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write(ex.getMessage());
        }
    }
}
