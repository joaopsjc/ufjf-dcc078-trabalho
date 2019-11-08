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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.abstratos.Produto;
import persistence.ProdutoDAO;

/**
 *
 * @author jjsfa
 */
public class DisponibilizarIndisponibilizarProdutoAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException { 
        try {
            String selectedIds= request.getParameter("selectedIds");
            boolean isDisponibilizar= request.getParameter("isDisponibilizar").equals("true");
            List<Produto> listProdutos = Helper.getInstance().getListProdutosByIds(selectedIds,request);
            executarDisponibilizacaoIndisponibilizacaoProdutos(listProdutos,isDisponibilizar);
            ProdutoDAO.getInstance().updateEstadoByList(listProdutos);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("");
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ExcluirProdutoAction.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write(ex.getMessage());
        }
    }
    
    private void executarDisponibilizacaoIndisponibilizacaoProdutos(List<Produto> listProdutos,boolean isDisponibilizar){
        for(Iterator i = listProdutos.iterator();i.hasNext();){
            Produto p = (Produto)i.next();
            if (isDisponibilizar) 
                p.getEstado().disponivel(p);
            else
                p.getEstado().indisponivel(p);
        }
    }
}
