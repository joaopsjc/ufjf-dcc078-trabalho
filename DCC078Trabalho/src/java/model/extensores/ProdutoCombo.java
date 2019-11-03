/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extensores;

import java.util.ArrayList;
import java.util.List;
import model.abstratos.Produto;
import java.util.Iterator;

/**
 *
 * @author Andre William
 */
public class ProdutoCombo extends Produto{
    List<Produto> produtos;

    public ProdutoCombo(Long id, String nome, String descricao, Double preco) {
        super(id, nome, descricao, preco);
        produtos = new ArrayList<>();
    }

    public ProdutoCombo(Long id, String nome, String descricao, int quantidade, Double preco) {
        super(id, nome, descricao, quantidade, preco);
        produtos = new ArrayList<>();
    }

    public ProdutoCombo(Long id, String nome, String descricao, int quantidade, Double preco, Long id_empresa) {
        super(id, nome, descricao, quantidade, preco, id_empresa);
        produtos = new ArrayList<>();
    }
    @Override
    public String getCategoria()
    {
        return "Combo";
    }
    public ProdutoCombo() {
        produtos = new ArrayList<>();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    public void addProduto(Produto novoProduto)
    {
        produtos.add(novoProduto);
    }
    public void removeProduto(Produto novoProduto)
    {
        produtos.remove(novoProduto);
    }
    
    public void removeProdutoByName(String nomeProduto)
    {
        Iterator<Produto> produtosIterator = produtos.iterator();
        while(produtosIterator.hasNext())
        {
            Produto produtoAtual = produtosIterator.next();
            if(produtoAtual.getNome().equals(nomeProduto))
            {
                produtos.remove(produtoAtual);
            }
        }
    }
}
