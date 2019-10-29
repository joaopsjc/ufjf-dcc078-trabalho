/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.estados.ProdutoEstadoBloqueado;
import model.estados.ProdutoEstadoDisponivel;
import model.estados.ProdutoEstadoIndisponivel;
import model.extensores.Empresa;
import model.interfaces.ProdutoEstado;

/**
 *
 * @author andradeld
 */
public class Produto {
    private Long id;
    private String nome,
            descricao,
            categoria;
    private int quantidade;
    private Double preco;
    private ProdutoEstado estado;
    private Long id_empresa;

    public Produto(Long id, String nome, String descricao, String categoria, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = 0;
        this.estado = new ProdutoEstadoDisponivel();
    }

    public Produto(Long id, String nome, String descricao, String categoria, int quantidade, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
        this.estado = new ProdutoEstadoDisponivel();
    }
    
    public Produto(Long id, String nome, String descricao, String categoria, int quantidade, Double preco, Long id_empresa) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
        this.id_empresa = id_empresa;
        this.estado = new ProdutoEstadoDisponivel();
    }

    public Produto() {
        this.estado = new ProdutoEstadoDisponivel();
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public ProdutoEstado getEstado() {
        return estado;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void produtoIndisponivel()
    {
        this.estado = new ProdutoEstadoIndisponivel();
    }
    public void produtoDisponivel()
    {
        this.estado = new ProdutoEstadoDisponivel();
    }
    public void produtoBloqueado()
    {
        this.estado = new ProdutoEstadoBloqueado();
    }
    
    public String getNomeEstado(){
        return estado.getEstado();
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstado(ProdutoEstado estado) {
        this.estado = estado;
    }
    
}
