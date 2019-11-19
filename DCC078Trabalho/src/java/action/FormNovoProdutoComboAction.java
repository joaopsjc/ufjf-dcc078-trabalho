package action;

import controller.Action;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.extensores.ProdutoCombo;

public class FormNovoProdutoComboAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("currentProduto", new ProdutoCombo());
            request.getRequestDispatcher("Produto/detalheProduto.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
