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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import model.estados.ProdutoEstadoBloqueado;
import persistence.ProdutoDAO;

/**
 *
 * @author jjsfa
 */
public class BloquearDesbloquearProdutoAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException { 
        try {
            String selectedIds= request.getParameter("selectedIds");
            boolean isBloquear= request.getParameter("isBloquear").equals("true");
            List<Produto> listProdutos = Helper.getInstance().getListProdutos(selectedIds);
            executarBloqueioDesbloqueioProdutos(listProdutos,isBloquear);
            ProdutoDAO.getInstance().updateEstadoByList(listProdutos);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("");
        
        } catch (SQLException ex) {
            Logger.getLogger(ExcluirProdutoAction.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExcluirProdutoAction.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write(ex.getMessage());
        }
    }
    
    private void executarBloqueioDesbloqueioProdutos(List<Produto> listProdutos,boolean isBloquear){
        for(Iterator i = listProdutos.iterator();i.hasNext();){
            Produto p = (Produto)i.next();
            if (isBloquear) 
                p.getEstado().bloqueado(p);
            else
                p.getEstado().desbloqueado(p);
        }
    }
}
