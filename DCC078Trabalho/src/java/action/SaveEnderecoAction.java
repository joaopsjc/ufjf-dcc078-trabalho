package action;

import controller.Action;
import controller.EnderecoFactory;
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
import model.abstratos.Endereco;
import persistence.EnderecoDAO;
import persistence.ProdutoDAO;

public class SaveEnderecoAction  implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id_usuario = request.getParameter("id");
        String tipoEndereco = request.getParameter("tipoEndereco");
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String cep = request.getParameter("cep");
        
        Usuario currentUser = Helper.getInstance().getLoggedUser(request);
        try {
            Endereco endereco;
            endereco = EnderecoFactory.create(tipoEndereco);
            endereco.setLogradouro(logradouro);
            endereco.setNumero(numero);
            endereco.setComplemento(complemento);
            endereco.setBairro(bairro);
            endereco.setCidade(cidade);
            endereco.setEstado(estado);
            endereco.setCep(cep);
            endereco.setId_usuario(currentUser.getId());
                        
            if (id_usuario.length() == 0)
                EnderecoDAO.getInstance().insert(endereco);
            else{
                endereco.setId(Long.parseLong(id_usuario));
                EnderecoDAO.getInstance().update(endereco);
            }
            request.setAttribute("messageError", "");
            request.setAttribute("messageSuccess", "Endereço cadastrado com sucesso!");
            response.sendRedirect("FrontController?action=ResumoEnderecos");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("erro.jsp");
        }
    }
}
