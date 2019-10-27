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
import model.Cardapio;
import model.DadoBancario;
import model.Pedido;
import model.Produto;

/**
 *
 * @author jjsfa
 */
public class Empresa extends Usuario{
    private Cardapio cardapio;
    private long CNPJ;
    private int avaliacao;

    public Empresa(){
        super();
    }
    
    public Empresa(Cardapio cardapio, long CNPJ, int avaliacao, Long id, String nome, String login, String senha) {
        super(id, nome, login, senha);
        this.cardapio = cardapio;
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(Cardapio cardapio, long CNPJ, int avaliacao, Long id, String nome, String login, List<DadoBancario> dadosBancarios, String senha) {
        super(id, nome, login, dadosBancarios, senha);
        this.cardapio = cardapio;
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(Cardapio cardapio, long CNPJ, int avaliacao, Long id, String nome, String login, String senha, List<Endereco> enderecos) {
        super(id, nome, login, senha, enderecos);
        this.cardapio = cardapio;
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(Cardapio cardapio, long CNPJ, int avaliacao, Long id, String nome, List<Contato> contatos, String login, String senha) {
        super(id, nome, contatos, login, senha);
        this.cardapio = cardapio;
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(Cardapio cardapio, long CNPJ, int avaliacao, Long id, List<Pedido> pedidos, String nome, String login, String senha) {
        super(id, pedidos, nome, login, senha);
        this.cardapio = cardapio;
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(Cardapio cardapio, long CNPJ, int avaliacao, Long id, String nome, String login, String senha, List<DadoBancario> dadosBancarios, List<Endereco> enderecos, List<Contato> contatos, List<Pedido> pedidos) {
        super(id, nome, login, senha, dadosBancarios, enderecos, contatos, pedidos);
        this.cardapio = cardapio;
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }
    public Empresa(long CNPJ, int avaliacao, Long id, String nome, String login, String senha) {
        super(id, nome, login, senha);
        this.cardapio = new Cardapio();
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(long CNPJ, int avaliacao, Long id, String nome, String login, List<DadoBancario> dadosBancarios, String senha) {
        super(id, nome, login, dadosBancarios, senha);
        this.cardapio = new Cardapio();
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(long CNPJ, int avaliacao, Long id, String nome, String login, String senha, List<Endereco> enderecos) {
        super(id, nome, login, senha, enderecos);
        this.cardapio = new Cardapio();
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(long CNPJ, int avaliacao, Long id, String nome, List<Contato> contatos, String login, String senha) {
        super(id, nome, contatos, login, senha);
        this.cardapio = new Cardapio();
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(long CNPJ, int avaliacao, Long id, List<Pedido> pedidos, String nome, String login, String senha) {
        super(id, pedidos, nome, login, senha);
        this.cardapio = new Cardapio();
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }

    public Empresa(long CNPJ, int avaliacao, Long id, String nome, String login, String senha, List<DadoBancario> dadosBancarios, List<Endereco> enderecos, List<Contato> contatos, List<Pedido> pedidos) {
        super(id, nome, login, senha, dadosBancarios, enderecos, contatos, pedidos);
        this.cardapio = new Cardapio();
        this.CNPJ = CNPJ;
        this.avaliacao = avaliacao;
    }
    
    public long getCNPJ() {
        return CNPJ;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    public void addProdutoCardapio(Produto novoProduto)
    {
        cardapio.addProduto(novoProduto);
    }
}