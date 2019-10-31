/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.ProdutoDAO;

/**
 *
 * @author jjsfa
 */
public class ExcluirProdutoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException { 
        try {
            String selectedIds= request.getParameter("selectedIds");
            List<String> idsList = new ArrayList<>(Arrays.asList(selectedIds.split(",")));
            ProdutoDAO.getInstance().deleteByIds(idsList);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("");
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ExcluirProdutoAction.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write(ex.getMessage());
        }
    }
}
