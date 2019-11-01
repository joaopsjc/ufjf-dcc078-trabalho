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
public class GetProdutosByEmpresaIdAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Long id_empresa = Long.parseLong(request.getParameter("empresaId"));
            List<Produto> listaProdutos = ProdutoDAO.getInstance().getProdutosDisponiveisByEmpresaId(id_empresa);
            request.setAttribute("listProdutos", listaProdutos);
            request.getRequestDispatcher("Produto/resumoProdutosCliente.jsp").forward(request, response);
            
        } catch (ServletException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
