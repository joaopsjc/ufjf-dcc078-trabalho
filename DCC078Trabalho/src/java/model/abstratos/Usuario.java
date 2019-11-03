/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.abstratos;

import model.interfaces.Contato;
import java.util.ArrayList;
import java.util.List;
import model.DadosBancarios;
import model.Pedido;

/**
 *
 * @author andradeld
 */
public abstract class Usuario {
    private Long id;
    private String nome,
            login,
            senha,
            documento;
    private final List<DadosBancarios> dadosBancarios;
    private final List<Endereco> enderecos;
    private final List<Contato> contatos;
    private final List<Pedido> pedidos;
    private List<Produto> produtos;
    private Pedido carrinho;

    public Usuario(){
        dadosBancarios = new ArrayList<>();
        enderecos = new ArrayList<>();
        contatos = new ArrayList<>();
        pedidos = new ArrayList<>();
        produtos = new ArrayList<>();
    }
    
    public Usuario(Long id, String documento, String nome, String login, String senha) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        dadosBancarios = new ArrayList<>();
        enderecos = new ArrayList<>();
        contatos = new ArrayList<>();
        pedidos = new ArrayList<>();
        produtos = new ArrayList<>();
    }
    public Usuario(Long id, String documento, String nome, String login, List<DadosBancarios> dadosBancarios, String senha) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.dadosBancarios = dadosBancarios;
        enderecos = new ArrayList<>();
        contatos = new ArrayList<>();
        pedidos = new ArrayList<>();
        produtos = new ArrayList<>();
    }
    public Usuario(Long id, String documento, String nome, String login, String senha, List<Endereco> enderecos) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        dadosBancarios = new ArrayList<>();
        this.enderecos = enderecos;
        contatos = new ArrayList<>();
        pedidos = new ArrayList<>();
        produtos = new ArrayList<>();
    }
    public Usuario(Long id, String documento, String nome,List<Contato> contatos, String login, String senha) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        dadosBancarios = new ArrayList<>();
        enderecos = new ArrayList<>();
        this.contatos = contatos;
        pedidos = new ArrayList<>();
        produtos = new ArrayList<>();
    }
    public Usuario(Long id, String documento,List<Pedido> pedidos, String nome, String login, String senha) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        dadosBancarios = new ArrayList<>();
        enderecos = new ArrayList<>();
        contatos = new ArrayList<>();
        this.pedidos = pedidos;
        produtos = new ArrayList<>();
    }
    public Usuario(Long id, String documento, String nome, String login, String senha,
             List<DadosBancarios> dadosBancarios, List<Endereco> enderecos,
             List<Contato> contatos,List<Pedido> pedidos) {
        this.id = id;
        this.documento = documento;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.dadosBancarios = dadosBancarios;
        this.enderecos = enderecos;
        this.contatos = contatos;
        this.pedidos = pedidos;
        produtos = new ArrayList<>();
    }

    public String getTipo()
    {
        return "Usuario";
    }
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public String getNickname(){
        return this.nome.split(" ")[0];
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getDocumento() {
        return documento;
    }
    
    public List<DadosBancarios> getDadosBancarios() {
        return dadosBancarios;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    
    public void addContato(Contato novoContato)
    {
        contatos.add(novoContato);
    }
    public void addDadoBancario(DadosBancarios novoDadoBancario)
    {
        dadosBancarios.add(novoDadoBancario);
    }
    public void addEndereco(Endereco novoEndereco)
    {
        enderecos.add(novoEndereco);
    }
    public void addPedido(Pedido novoPedido)
    {
        pedidos.add(novoPedido);
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setCarrinho(Pedido carrinho) {
        this.carrinho = carrinho;
    }

    public Pedido getCarrinho() {
        if (this.carrinho == null)
            this.carrinho = new Pedido();
        return this.carrinho;
    }

    public abstract String getQtdCarrinho();
    
}
