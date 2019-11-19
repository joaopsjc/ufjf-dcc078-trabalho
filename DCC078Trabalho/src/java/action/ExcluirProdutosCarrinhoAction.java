package action;

import controller.Action;
import helper.Helper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;

public class ExcluirProdutosCarrinhoAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Pedido pedido = Helper.getInstance().getCarrinhoByClienteId(request);
        String selectedIds= request.getParameter("selectedIds");
        List<String> idsList = new ArrayList<>(Arrays.asList(selectedIds.split(",")));
        pedido.removeListaProdutos(idsList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int qtdItensCarrinho = pedido.getCountProdutos();
        String resultStr = "{\"message\":\"Sucesso\",\"qtdItensCarrinho\":"+qtdItensCarrinho+"}";
        response.getWriter().write(resultStr);
    }
    
}
