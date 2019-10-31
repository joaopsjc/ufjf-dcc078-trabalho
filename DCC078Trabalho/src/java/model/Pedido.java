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
import java.util.Objects;
import java.util.Observable;
import model.abstratos.Usuario;
import model.estados.PedidoEmPreparo;

/**
 *
 * @author andradeld
 */
public class Pedido extends Observable {
    //campos só para o banco de dados?
    private Usuario entregador,
            cliente,
            empresa;
    
    private Long id;
    private List<PedidoProduto> produtos =new ArrayList<>();
    private PedidoEstado estado;
    private Endereco endereco;
    private double frete,
            precoProdutos;

    public Pedido(Long id, List<PedidoProduto> produtos,Endereco endereco,
            double frete) {
        this.id = id;
        estado = new PedidoEmPreparo();
        this.produtos = produtos;
        this.endereco = endereco;
        this.frete = frete;
        precoProdutos = 0;
        Iterator<PedidoProduto> produtoIterator = produtos.iterator();
        while(produtoIterator.hasNext())
        {
            PedidoProduto produtoAtual = produtoIterator.next();
            precoProdutos+= produtoAtual.getProduto().getPreco();
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
    public Pedido(Long id, List<PedidoProduto> produtos,Endereco endereco,
            double frete, PedidoEstado pedidoEstado) {
        this.id = id;
        estado = pedidoEstado;
        this.produtos = produtos;
        this.endereco = endereco;
        this.frete = frete;
        precoProdutos = 0;
        Iterator<PedidoProduto> produtoIterator = produtos.iterator();
        while(produtoIterator.hasNext())
        {
            PedidoProduto produtoAtual = produtoIterator.next();
            precoProdutos+= produtoAtual.getProduto().getPreco();
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

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public List<PedidoProduto> getProdutos() {
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
        setChanged(); //observer
        notifyObservers(); //observer
    }
    
    public void addProduto(PedidoProduto novoProduto)
    {
        produtos.add(novoProduto);
        precoProdutos+= novoProduto.getProduto().getPreco();
    }
    public void removeProduto(PedidoProduto produto)
    {
        precoProdutos-= produto.getProduto().getPreco();
        produtos.remove(produto);
    }

    public void addListaProdutos(List<Produto> listProdutos) {
        for(Iterator i = listProdutos.iterator();i.hasNext();){
            Produto p = (Produto)i.next();
            PedidoProduto pedidoProduto = getPedidoProdutoByProdutoId(p.getId());
            if (pedidoProduto != null)
                pedidoProduto.incrementaQuantidade();
            else{
                pedidoProduto = new PedidoProduto();
                pedidoProduto.setQuantidade(1);
                pedidoProduto.setProduto(p);
                addProduto(pedidoProduto);
            }
        }
            
    }
    
    private PedidoProduto getPedidoProdutoByProdutoId(String id){
        return getPedidoProdutoByProdutoId(Long.parseLong(id));
    }
    
    private PedidoProduto getPedidoProdutoByProdutoId(Long id){
        for(Iterator i = produtos.iterator();i.hasNext();){
            PedidoProduto pedidoproduto = (PedidoProduto)i.next();
            if (Objects.equals(pedidoproduto.getProduto().getId(), id))
                return pedidoproduto;
        }
        return null;
    }

    public int getCountProdutos() {
        return produtos.size();
    }

    public void removeListaProdutos(List<String> idsList) {
        for(Iterator i = idsList.iterator();i.hasNext();){
            String idProduto = (String)i.next();
            PedidoProduto pedidoProduto = getPedidoProdutoByProdutoId(idProduto);
            if (pedidoProduto != null)
                removeProduto(pedidoProduto);
        }
    }
}
