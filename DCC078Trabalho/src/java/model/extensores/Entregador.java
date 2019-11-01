/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extensores;

import java.sql.SQLException;
import model.abstratos.Endereco;
import model.abstratos.Usuario;
import model.interfaces.Contato;
import java.util.List;
import model.DadosBancarios;
import model.Pedido;
import persistence.PedidoDAO;

/**
 *
 * @author andradeld
 */
public class Entregador extends Usuario {
    private Entregador proxEntregador;
    private int avaliacao;
    private boolean disponivel;
    private Pedido pedidoDisponivel;

    public Entregador(){
        super();
    }
    
    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, String nome, String login, String senha) {
        super(id,documento, nome, login, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        disponivel = false;
        pedidoDisponivel=null;

    }

    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, String nome, String login, List<DadosBancarios> dadosBancarios, String senha) {
        super(id,documento, nome, login, dadosBancarios, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        disponivel = false;
        pedidoDisponivel=null;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, String nome, String login, String senha, List<Endereco> enderecos) {
        super(id,documento, nome, login, senha, enderecos);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        disponivel = false;
        pedidoDisponivel=null;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, String nome, List<Contato> contatos, String login, String senha) {
        super(id,documento, nome, contatos, login, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        disponivel = false;
        pedidoDisponivel=null;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, List<Pedido> pedidos, String nome, String login, String senha) {
        super(id,documento, pedidos, nome, login, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        disponivel = false;
        pedidoDisponivel=null;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, String nome, String login, String senha, List<DadosBancarios> dadosBancarios, List<Endereco> enderecos, List<Contato> contatos, List<Pedido> pedidos) {
        super(id,documento, nome, login, senha, dadosBancarios, enderecos, contatos, pedidos);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        disponivel = false;
        pedidoDisponivel=null;
    }

    public void setProxEntregador(Entregador proxEntregador) {
        this.proxEntregador = proxEntregador;
    }
    
    public void novaReponsabilidade(Pedido novoPedido) {
        if(disponivel)
        {
            disponivel=false;
            pedidoDisponivel = novoPedido;
        }
        else
        {
            getProxEntregador().novaReponsabilidade(novoPedido);
        }
    }
    public void aceitarPedido() throws SQLException, ClassNotFoundException
    {
        if(pedidoDisponivel!=null)
        {
            super.getPedidos().add(pedidoDisponivel);
            pedidoDisponivel.getEstado().aCaminho(pedidoDisponivel);
            PedidoDAO.getInstance().updateEstado(pedidoDisponivel);
            pedidoDisponivel=null;
            disponivel=true;
        }
    }
    public void rejeitarPedido() {
        if(pedidoDisponivel!=null)
        {
            getProxEntregador().novaReponsabilidade(pedidoDisponivel);
            pedidoDisponivel=null;
            disponivel=true;
        }
    }

    public void setDisponivel() {
        this.disponivel = true;
    }
    
    public void setNotDisponivel() {
        this.disponivel = false;
    }
    
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Entregador getProxEntregador() {
        return proxEntregador;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public Pedido getPedidoDisponivel() {
        return pedidoDisponivel;
    }

    @Override
    public String getTipo()
    {
        return "Entregador";
    }

    @Override
    public String getQtdCarrinho() {
        return "";
    }
    
}
