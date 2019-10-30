/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
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
import persistence.ProdutoDAO;
import persistence.UsuarioDAO;

/**
 *
 * @author jjsfa
 */
public class GetProdutosByEmpresaNomeAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String nomeEmpresa = request.getParameter("empresaProduto");
            String url = request.getHeader("referer");
            Usuario empresa = UsuarioDAO.getInstance().getByName(nomeEmpresa);
            List<Produto> listaProdutos = ProdutoDAO.getInstance().getProdutosByEmpresaId(empresa.getId());
            request.setAttribute("produtosByCategoria", listaProdutos);
            request.getRequestDispatcher(url).forward(request, response);
            
        } catch (ServletException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
