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
import model.abstratos.Usuario;
import model.estados.PedidoEmPreparo;
import model.extensores.*;

/**
 *
 * @author andradeld
 */
public class Pedido {
    //campos só para o banco de dados?
    private Usuario entregador,
            cliente,
            empresa;
    
    private final Long id;
    private final List<Produto> produtos;
    private PedidoEstado estado;
    private Endereco endereco;
    private double frete,
            precoProdutos;

    public Pedido(Long id, List<Produto> produtos,Endereco endereco,
            double frete) {
        this.id = id;
        estado = new PedidoEmPreparo();
        this.produtos = produtos;
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
    public Pedido(Long id, Endereco endereco, double frete) {
        this.id = id;
        estado = new PedidoEmPreparo();
        this.produtos = new ArrayList<>();
        this.endereco = endereco;
        this.frete = frete;
        precoProdutos = 0;
    }
    public Pedido(Long id, List<Produto> produtos,Endereco endereco,
            double frete, PedidoEstado pedidoEstado) {
        this.id = id;
        estado = pedidoEstado;
        this.produtos = produtos;
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
    public Pedido(Long id, Endereco endereco, double frete,
            PedidoEstado pedidoEstado) {
        this.id = id;
        estado = pedidoEstado;
        this.produtos = new ArrayList<>();
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

    public Usuario getEntregador() {
        return entregador;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Usuario getEmpresa() {
        return empresa;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public void setEntregador(Usuario entregador) {
        this.entregador = entregador;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public void setEmpresa(Usuario empresa) {
        this.empresa = empresa;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
    }
    
    public void addProduto(Produto novoProduto)
    {
        produtos.add(novoProduto);
        precoProdutos+= novoProduto.getPreco();
    }   
}
