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
import model.Produto;
import persistence.ProdutoDAO;

/**
 *
 * @author jjsfa
 */
public class GetProdutosByGlobalSearchAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String stringSearch = request.getParameter("stringSearch");
            String url = request.getHeader("referer");
            List<Produto> listaProdutos = ProdutoDAO.getInstance().getProdutosByCategoria(stringSearch);
            request.setAttribute("listProdutos", listaProdutos);
            request.getRequestDispatcher("Produto/resumoProdutosCliente.jsp").forward(request, response);
            
        } catch (ServletException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
