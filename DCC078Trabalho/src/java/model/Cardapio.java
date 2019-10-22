/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andradeld
 */
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
