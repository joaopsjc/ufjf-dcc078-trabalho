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
public class Cliente extends Usuario {
    private final int CPF;

    public Cliente(int CPF, Long id, String nome, String login, String senha) {
        super(id, nome, login, senha);
        this.CPF = CPF;
    }

    public Cliente(int CPF, Long id, String nome, String login, List<DadoBancario> dadosBancarios, String senha) {
        super(id, nome, login, dadosBancarios, senha);
        this.CPF = CPF;
    }

    public Cliente(int CPF, Long id, String nome, String login, String senha, List<Endereco> enderecos) {
        super(id, nome, login, senha, enderecos);
        this.CPF = CPF;
    }

    public Cliente(int CPF, Long id, String nome, List<Contato> contatos, String login, String senha) {
        super(id, nome, contatos, login, senha);
        this.CPF = CPF;
    }

    public Cliente(int CPF, Long id, List<Pedido> pedidos, String nome, String login, String senha) {
        super(id, pedidos, nome, login, senha);
        this.CPF = CPF;
    }

    public Cliente(int CPF, Long id, String nome, String login, String senha, List<DadoBancario> dadosBancarios, List<Endereco> enderecos, List<Contato> contatos, List<Pedido> pedidos) {
        super(id, nome, login, senha, dadosBancarios, enderecos, contatos, pedidos);
        this.CPF = CPF;
    }

    
    public int getCPF() {
        return CPF;
    }
    
}
