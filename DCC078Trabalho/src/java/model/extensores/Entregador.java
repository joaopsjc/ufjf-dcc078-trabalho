package model.extensores;

import java.sql.SQLException;
import java.util.Iterator;
import model.abstratos.Usuario;
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
