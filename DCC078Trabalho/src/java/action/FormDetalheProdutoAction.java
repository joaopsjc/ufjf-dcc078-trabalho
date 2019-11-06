/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.abstratos.Produto;
import persistence.ComboProdutoDAO;
import persistence.ProdutoDAO;

/**
 *
 * @author jjsfa
 */
public class FormDetalheProdutoAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            Long id = Long.parseLong(request.getParameter("id"));
            Produto currentProduto = ProdutoDAO.getInstance().getById(id);
            if (currentProduto.getCategoria().equals("Combo")){
                Long id_empresa = Helper.getInstance().getLoggedUser(request).getId();

                List<Produto> listProdutosNotCombo = ComboProdutoDAO.getInstance().getNotAllProdutosByComboId(id,id_empresa);
                List<Produto> listProdutosCombo = ComboProdutoDAO.getInstance().getAllProdutosByComboId(id);

                request.setAttribute("currentProduto",currentProduto);
                request.setAttribute("listProdutosNotCombo",listProdutosNotCombo);
                request.setAttribute("listProdutosCombo",listProdutosCombo);
                request.getRequestDispatcher("Produto/detalheCombo.jsp").forward(request, response);
                return;
            }
            request.setAttribute("currentProduto",currentProduto);
            request.getRequestDispatcher("Produto/detalheProduto.jsp").forward(request, response);
        } catch(SQLException ex){
            response.sendRedirect("erro.jsp");
        } catch (ClassNotFoundException | ServletException ex) {
            Logger.getLogger(ResumoEmpresasAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
