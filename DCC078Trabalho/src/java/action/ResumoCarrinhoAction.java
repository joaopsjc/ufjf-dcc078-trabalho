/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import helper.Helper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;
import model.implementadores.PromocaoDiaMaes;
import model.implementadores.PromocaoNatal;
import model.interfaces.Promocao;

/**
 *
 * @author jjsfa
 */
public class ResumoCarrinhoAction implements Action{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        try{
            Pedido carrinho = Helper.getInstance().getCarrinhoByClienteId(request);
            List<Promocao> promocoes = new ArrayList<>();
            promocoes.add(new PromocaoDiaMaes());
            promocoes.add(new PromocaoNatal());
            request.setAttribute("promocoes", promocoes);
            request.setAttribute("carrinho", carrinho);
            request.getRequestDispatcher("Cliente/resumoCarrinho.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ResumoEmpresasAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
