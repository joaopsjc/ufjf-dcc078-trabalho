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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.abstratos.Produto;
import model.abstratos.Usuario;
import controller.ProdutoEstadoFactory;
import controller.ProdutoFactory;
import persistence.ProdutoDAO;

/**
 *
 * @author jjsfa
 */
public class SaveProdutoAction  implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String categoria = request.getParameter("categoria");
        String descricao = request.getParameter("descricao");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        Double preco = Double.parseDouble(request.getParameter("preco"));
        String estado = request.getParameter("estado");
        
        Usuario currentUser = Helper.getInstance().getLoggedUser(request);
        try {
            Produto produto;
            if (id.length() != 0)
                produto= ProdutoDAO.getInstance().getById(id);
            else
                produto= ProdutoFactory.create(categoria);
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setPreco(preco);
            produto.setQuantidade(quantidade);
            produto.setId_empresa(currentUser.getId());
            if (estado.length()>0)
                produto.setEstado(ProdutoEstadoFactory.create(estado));
            
            if (quantidade==0)
                produto.getEstado().indisponivel(produto);
            else
                produto.getEstado().disponivel(produto);
            
            if (id.length() != 0)
                produto.setId(Long.parseLong(id));
            if (id.length() == 0)
                ProdutoDAO.getInstance().insert(produto);
            else
                ProdutoDAO.getInstance().update(produto);
            request.setAttribute("messageError", "");
            request.setAttribute("messageSuccess", "Produto cadastrado com sucesso!");
            response.sendRedirect("FrontController?action=ResumoProdutos");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
