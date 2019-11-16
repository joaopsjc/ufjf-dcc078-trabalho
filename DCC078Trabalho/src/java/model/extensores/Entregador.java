package model.extensores;

import helper.HelperPedido;
import java.sql.SQLException;
import java.util.Iterator;
import model.abstratos.Endereco;
import model.abstratos.Usuario;
import model.interfaces.Contato;
import java.util.List;
import model.EntregadorChainResponsibility;
import model.Pedido;
import persistence.PedidoDAO;

public class Entregador extends Usuario {
    private Entregador proxEntregador;
    private int avaliacao;
    private Pedido pedidoDisponivel;

    public Entregador(){
        super();
    }
    
    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, String nome, String login, String senha) {
        super(id,documento, nome, login, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        pedidoDisponivel=null;

    }

    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, String nome, String login, String senha, List<Endereco> enderecos) {
        super(id,documento, nome, login, senha, enderecos);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        pedidoDisponivel=null;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, String nome, List<Contato> contatos, String login, String senha) {
        super(id,documento, nome, contatos, login, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        pedidoDisponivel=null;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, List<Pedido> pedidos, String nome, String login, String senha) {
        super(id,documento, pedidos, nome, login, senha);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        pedidoDisponivel=null;
    }

    public Entregador(Entregador proxEntregador, int avaliacao, String documento, Long id, String nome, String login, String senha, List<Endereco> enderecos, List<Contato> contatos, List<Pedido> pedidos) {
        super(id,documento, nome, login, senha, enderecos, contatos, pedidos);
        this.proxEntregador = proxEntregador;
        this.avaliacao = avaliacao;
        pedidoDisponivel=null;
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

    public void repassarPedidos(List<Pedido> pedidos) throws SQLException, ClassNotFoundException {
        getPedidos().removeAll(pedidos);
        Entregador e = getProxEntregador();
        if (e.getId().equals(getId()))
            EntregadorChainResponsibility.getInstance().populaListaPendente();
        else
            e.addPedido(pedidos);
    }
    
    public void aceitarPedidos(List<Pedido> pedidos) throws SQLException, ClassNotFoundException{
        Iterator<Pedido> iteratorPedidos = pedidos.iterator();
        while(iteratorPedidos.hasNext())
        {
            Pedido pedidoAtual = iteratorPedidos.next();
            pedidoAtual.setEntregador(this);
            pedidoAtual.getEstado().aCaminho(pedidoAtual);
            PedidoDAO.getInstance().setEntregadorEstadoPedido(pedidoAtual);
        }
    }
    
}
