/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import controller.UsuarioFactory;
import helper.Helper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import model.abstratos.Usuario;
import model.estados.ProdutoEstadoBloqueado;
import model.estados.ProdutoEstadoDisponivel;
import controller.ProdutoEstadoFactory;
import model.estados.ProdutoEstadoIndisponivel;
import persistence.ProdutoDAO;
import persistence.UsuarioDAO;

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
                produto= new Produto();
            produto.setCategoria(categoria);
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
        } catch (SQLException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
