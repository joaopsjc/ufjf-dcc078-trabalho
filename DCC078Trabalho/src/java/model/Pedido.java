/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.abstratos.Endereco;
import model.interfaces.PedidoEstado;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import model.extensores.*;

/**
 *
 * @author andradeld
 */
public class Pedido {
    //campos só para o banco de dados?
    private Entregador entregador;
    private Cliente cliente;
    private Empresa empresa;
    
    private final Long id;
    private final List<Produto> produtos;
    private String observacao;
    private PedidoEstado estado;
    private Endereco endereco;
    private double frete,
            precoProdutos;

    public Pedido(Long id, List<Produto> produtos, String observacao,
            Endereco endereco, double frete) {
        this.id = id;
        this.produtos = produtos;
        this.observacao = observacao;
        this.endereco = endereco;
        this.frete = frete;
        precoProdutos = 0;
        Iterator<Produto> produtoIterator = produtos.iterator();
        while(produtoIterator.hasNext())
        {
            Produto produtoAtual = produtoIterator.next();
            precoProdutos+= produtoAtual.getPreco();
        }
    }
    public Pedido(Long id, String observacao,
            Endereco endereco, double frete) {
        this.id = id;
        this.produtos = new ArrayList<>();
        this.observacao = observacao;
        this.endereco = endereco;
        this.frete = frete;
        precoProdutos = 0;
    }

    public Long getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public String getObservacao() {
        return observacao;
    }

    public PedidoEstado getEstado() {
        return estado;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public double getFrete() {
        return frete;
    }

    public double getPrecoProdutos() {
        return precoProdutos;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public void addProduto(Produto novoProduto)
    {
        produtos.add(novoProduto);
        precoProdutos+= novoProduto.getPreco();
    }
    
}
