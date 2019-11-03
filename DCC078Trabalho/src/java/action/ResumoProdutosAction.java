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
import model.abstratos.Usuario;
import persistence.ProdutoDAO;

/**
 *
 * @author jjsfa
 */
public class ResumoProdutosAction implements Action{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            Usuario usuario = Helper.getInstance().getLoggedUser(request);
            List<Produto> listProdutos = ProdutoDAO.getInstance().getProdutosDisponiveisByEmpresaId(usuario.getId());
            Helper.getInstance().setListProdutos(listProdutos,request);
            request.setAttribute("listProdutos", listProdutos);
            request.getRequestDispatcher("Produto/resumoProdutosEmpresa.jsp").forward(request, response);
        } catch(SQLException ex){
            response.sendRedirect("erro.jsp");
        } catch (ClassNotFoundException | ServletException ex) {
            Logger.getLogger(ResumoEmpresasAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
