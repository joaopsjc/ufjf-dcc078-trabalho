/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.abstratos.Produto;
import model.implementadores.PromocaoVazia;
import model.interfaces.Promocao;

/**
 *
 * @author jjsfa
 */
public class PedidoProduto {
    

    private int quantidade=0;
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
        //this.id_produto = produto.getId();
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
    
//NÃO SÃO NECESSÁRIAS, É POSSIVEL ACESSAR O PEDIDO E O PRODUTO ATRAVÉS DE
//OUTROS MEIOS
/*
    private Long id_pedido;
    private Long id_produto;
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
            
    */
}
