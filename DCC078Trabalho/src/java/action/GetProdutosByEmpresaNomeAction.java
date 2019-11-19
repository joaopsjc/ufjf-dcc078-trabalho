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
import model.abstratos.Produto;
import model.abstratos.Usuario;
import persistence.ProdutoDAO;
import persistence.UsuarioDAO;

public class GetProdutosByEmpresaNomeAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String nomeEmpresa = request.getParameter("empresaProduto");
            String url = request.getHeader("referer");
            Usuario empresa = UsuarioDAO.getInstance().getByName(nomeEmpresa);
            List<Produto> listaProdutos = ProdutoDAO.getInstance().getProdutosDisponiveisByEmpresaId(empresa.getId());
            request.setAttribute("produtosByEmpresaNome", listaProdutos);
            request.getRequestDispatcher(url).forward(request, response);
            
        } catch (ServletException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProfileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
