package model.extensores;

import helper.HelperPedido;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.abstratos.Usuario;
import model.Cardapio;
import model.abstratos.Produto;

public class Empresa extends Usuario{
    private Cardapio cardapio;
    private int avaliacao;

    public Empresa(){
        super();
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    public void addProdutoCardapio(Produto novoProduto)
    {
        cardapio.addProduto(novoProduto);
    }
    @Override
    public String getTipo()
    {
        return "Empresa";
    }

    @Override
    public String getQtdCarrinho() {
        return "";
    }

    @Override
    public void setDynamicInfo(HttpSession sessaoAtual) {
        try {
            sessaoAtual.setAttribute("countPedidosPendentesEmpresa", HelperPedido.getInstance().getCountPedidosPendentesEmpresa(this.getId()));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}