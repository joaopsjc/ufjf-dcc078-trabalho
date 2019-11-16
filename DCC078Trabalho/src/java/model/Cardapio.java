package model;

import model.abstratos.Produto;
import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private final List<Produto> produtos;

    public Cardapio() {
        this.produtos = new ArrayList<>();
    }
    public Cardapio(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    public void addProduto(Produto novoProduto)
    {
        produtos.add(novoProduto);
    }
}
