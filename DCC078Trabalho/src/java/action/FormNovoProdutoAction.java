package action;

import controller.Action;
import controller.ProdutoFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.abstratos.Produto;

public class FormNovoProdutoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String categoria = request.getParameter("categoria");
            Produto produto = ProdutoFactory.create(categoria);
            request.setAttribute("currentProduto", produto);
            request.getRequestDispatcher("Produto/detalheProduto.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}