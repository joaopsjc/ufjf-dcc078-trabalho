package action;

import controller.Action;
import controller.EnderecoFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.abstratos.Endereco;

public class FormNovoEnderecoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Endereco endereco = EnderecoFactory.create("Rua");
            request.setAttribute("currentEndereco", endereco);
            request.getRequestDispatcher("Usuario/detalheEndereco.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
