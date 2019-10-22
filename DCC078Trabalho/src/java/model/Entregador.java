/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author andradeld
 */
public class Entregador extends Usuario {
    private Entregador proxEntregador;
    private int avaliacao;
    private final int CPF;

    public Entregador(Entregador proxEntregador, int avaliacao, int CPF, Long id, String nome, String login, String senha) {
        super(id, nome, login, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        this.CPF = CPF;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, int CPF, Long id, String nome, String login, List<DadoBancario> dadosBancarios, String senha) {
        super(id, nome, login, dadosBancarios, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        this.CPF = CPF;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, int CPF, Long id, String nome, String login, String senha, List<Endereco> enderecos) {
        super(id, nome, login, senha, enderecos);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        this.CPF = CPF;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, int CPF, Long id, String nome, List<Contato> contatos, String login, String senha) {
        super(id, nome, contatos, login, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        this.CPF = CPF;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, int CPF, Long id, List<Pedido> pedidos, String nome, String login, String senha) {
        super(id, pedidos, nome, login, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        this.CPF = CPF;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, int CPF, Long id, String nome, String login, String senha, List<DadoBancario> dadosBancarios, List<Endereco> enderecos, List<Contato> contatos, List<Pedido> pedidos) {
        super(id, nome, login, senha, dadosBancarios, enderecos, contatos, pedidos);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        this.CPF = CPF;
    }

    public void setProxEntregador(Entregador proxEntregador) {
        this.proxEntregador = proxEntregador;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Entregador getProxEntregador() {
        return proxEntregador;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public int getCPF() {
        return CPF;
    }
    
}
