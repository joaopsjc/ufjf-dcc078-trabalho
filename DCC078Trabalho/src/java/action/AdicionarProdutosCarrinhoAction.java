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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.abstratos.Produto;
import persistence.ProdutoDAO;

/**
 *
 * @author jjsfa
 */
public class AdicionarProdutosCarrinhoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Pedido pedido = Helper.getInstance().getCarrinhoByClienteId(request);
            String selectedIds= request.getParameter("selectedIds");
            List<String> idsList = new ArrayList<>(Arrays.asList(selectedIds.split(",")));
            List<Produto> listProdutos = ProdutoDAO.getInstance().getByListId(idsList);
            pedido.addListaProdutos(listProdutos);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            int qtdItensCarrinho = pedido.getCountProdutos();
            String resultStr = "{\"message\":\"Sucesso\",\"qtdItensCarrinho\":"+qtdItensCarrinho+"}";
            response.getWriter().write(resultStr);
        
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ExcluirProdutoAction.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().write(ex.getMessage());
        }
    }
    
}
