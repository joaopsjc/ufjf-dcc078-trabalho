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
public abstract class Usuario {
    private final Long id;
    private String nome,
            login,
            senha;
    private final List<DadoBancario> dadosBancarios;
    private final List<Endereco> enderecos;
    private final List<Contato> contatos;
    private final List<Pedido> pedidos;

    public Usuario(Long id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        dadosBancarios = new ArrayList<>();
        enderecos = new ArrayList<>();
        contatos = new ArrayList<>();
        pedidos = new ArrayList<>();
    }
    public Usuario(Long id, String nome, String login, List<DadoBancario> dadosBancarios, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.dadosBancarios = dadosBancarios;
        enderecos = new ArrayList<>();
        contatos = new ArrayList<>();
        pedidos = new ArrayList<>();
    }
    public Usuario(Long id, String nome, String login, String senha, List<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        dadosBancarios = new ArrayList<>();
        this.enderecos = enderecos;
        contatos = new ArrayList<>();
        pedidos = new ArrayList<>();
    }
    public Usuario(Long id, String nome,List<Contato> contatos, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        dadosBancarios = new ArrayList<>();
        enderecos = new ArrayList<>();
        this.contatos = contatos;
        pedidos = new ArrayList<>();
    }
    public Usuario(Long id,List<Pedido> pedidos, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        dadosBancarios = new ArrayList<>();
        enderecos = new ArrayList<>();
        contatos = new ArrayList<>();
        this.pedidos = pedidos;
    }
    public Usuario(Long id, String nome, String login, String senha,
             List<DadoBancario> dadosBancarios, List<Endereco> enderecos,
             List<Contato> contatos,List<Pedido> pedidos) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.dadosBancarios = dadosBancarios;
        this.enderecos = enderecos;
        this.contatos = contatos;
        this.pedidos = pedidos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public List<DadoBancario> getDadosBancarios() {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void addContato(Contato novoContato)
    {
        contatos.add(novoContato);
    }
    public void addDadoBancario(DadoBancario novoDadoBancario)
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
}
