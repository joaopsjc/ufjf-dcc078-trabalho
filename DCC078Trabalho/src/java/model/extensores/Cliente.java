/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extensores;

import model.abstratos.Endereco;
import model.abstratos.Usuario;
import model.interfaces.Contato;
import java.util.List;
import model.DadosBancarios;
import model.Pedido;

/**
 *
 * @author andradeld
 */
public class Cliente extends Usuario {
    
    public Cliente(){
        super();
    }
    
    public Cliente(String documento, Long id, String nome, String login, String senha) {
        super(id, documento, nome, login, senha);

    }

    public Cliente(String documento, Long id, String nome, String login, List<DadosBancarios> dadosBancarios, String senha) {
        super(id, documento, nome, login, dadosBancarios, senha);

    }

    public Cliente(String documento, Long id, String nome, String login, String senha, List<Endereco> enderecos) {
        super(id, documento, nome, login, senha, enderecos);

    }

    public Cliente(String documento, Long id, String nome, List<Contato> contatos, String login, String senha) {
        super(id, documento, nome, contatos, login, senha);

    }

    public Cliente(String documento, Long id, List<Pedido> pedidos, String nome, String login, String senha) {
        super(id, documento, pedidos, nome, login, senha);

    }

    public Cliente(String documento, Long id, String nome, String login, String senha, List<DadosBancarios> dadosBancarios, List<Endereco> enderecos, List<Contato> contatos, List<Pedido> pedidos) {
        super(id, documento, nome, login, senha, dadosBancarios, enderecos, contatos, pedidos);

    }

    @Override
    public String getTipo()
    {
        return "Cliente";
    }
}
