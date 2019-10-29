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
import model.estados.ProdutoEstadoFactory;
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
        
        Usuario currentUser = Helper.getLoggedUser(request);
        try {            
            Produto produto = new Produto();
            produto.setCategoria(categoria);
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setPreco(preco);
            produto.setQuantidade(quantidade);
            produto.setId_empresa(currentUser.getId());
            if (estado.length()>0)
                produto.setEstado(ProdutoEstadoFactory.create(estado));
            
            if (!(produto.getEstado() instanceof ProdutoEstadoBloqueado)){
                if (quantidade==0)
                    produto.produtoIndisponivel();
                else
                    produto.produtoDisponivel();
            }            
            
            if (id.length() != 0)
                produto.setId(Long.parseLong(id));
            if (id.length() == 0)
                ProdutoDAO.getInstance().insert(produto);
            else
                ProdutoDAO.getInstance().update(produto);
            request.setAttribute("messageError", "");
            request.setAttribute("messageSuccess", "Produto cadastrado com sucesso!");
            List<Produto> listProdutos = ProdutoDAO.getInstance().getProdutosByEmpresaId(currentUser.getId());
            request.setAttribute("listProdutos", listProdutos);
            request.getRequestDispatcher("Produto/resumoProdutosEmpresa.jsp").forward(request, response);
            
        } catch (ServletException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
