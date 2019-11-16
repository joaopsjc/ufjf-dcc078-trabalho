package model.extensores;

import model.abstratos.Endereco;
import model.abstratos.Usuario;
import java.util.List;
import model.Cardapio;
import model.Pedido;
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
}