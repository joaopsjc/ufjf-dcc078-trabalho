package model;

import model.abstratos.Produto;
import model.implementadores.PromocaoVazia;
import model.interfaces.Promocao;

public class PedidoProduto {
    

    private int quantidade;
    private Produto produto;
    private Promocao promocao;

    public PedidoProduto() {
        this.quantidade = 0;
        this.produto = null;
        this.promocao = new PromocaoVazia();
    }

    public PedidoProduto(int quantidade,Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.promocao = new PromocaoVazia();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }
    
    public Promocao getPromocao() {
        return promocao;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void incrementaQuantidade() {
        this.quantidade++;
    }
}
