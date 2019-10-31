/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jjsfa
 */
public class PedidoProduto {
    
    private Long id_pedido;
    private Long id_produto;
    private int quantidade=0;
    Produto produto;

    public PedidoProduto() {
    }

    public PedidoProduto(Long id_pedido, Long id_produto, int quantidade,Produto produto) {
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.id_produto = produto.getId();
    }

    public void incrementaQuantidade() {
        this.quantidade++;
    }
    
    
            
    
}
