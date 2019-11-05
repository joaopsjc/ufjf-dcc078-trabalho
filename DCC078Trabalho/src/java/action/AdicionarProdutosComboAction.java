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
import model.abstratos.Produto;
import model.extensores.ProdutoCombo;
import persistence.ComboProdutoDAO;
import persistence.ProdutoDAO;

/**
 *
 * @author jjsfa
 */
public class AdicionarProdutosComboAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Long id_combo = Long.parseLong(request.getParameter("id_combo"));
            ProdutoCombo comboAtual = (ProdutoCombo) ProdutoDAO.getInstance().getById(id_combo);
            String selectedIds= request.getParameter("selectedIds");
            List<String> idsList = new ArrayList<>(Arrays.asList(selectedIds.split(",")));
            List<Produto> listProdutos = ProdutoDAO.getInstance().getByListId(idsList);
            comboAtual.setListaProdutos(listProdutos);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            int qtdItensCombo = comboAtual.getCountProdutos();
            String resultStr = "{\"message\":\"Sucesso\",\"qtdItensCombo\":"+qtdItensCombo+"}";
            
            ComboProdutoDAO.getInstance().insert(comboAtual);
            
            response.getWriter().write(resultStr);
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ExcluirProdutoAction.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write(ex.getMessage());
        }
    }
    
}
