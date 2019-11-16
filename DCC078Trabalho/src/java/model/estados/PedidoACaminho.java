package model.estados;

import model.Pedido;
import model.interfaces.PedidoEstado;

public class PedidoACaminho implements PedidoEstado {
    
    @Override
    public String getEstado() {
        return "ACaminho";
    }
    
    @Override   
    public String getNome() {
        return "A caminho";
    }
    
    @Override
    public boolean aCaminho(Pedido pedido) {
        return false;
    }
    
    @Override
    public boolean cancelado(Pedido pedido) {
        pedido.setEstado(new PedidoCancelado());
        return true;
    }
    
    @Override
    public boolean concluido(Pedido pedido) {
        pedido.setEstado(new PedidoConcluido());
        return true;
    }
    
    @Override
    public boolean emPreparo(Pedido pedido) {
        return false;
    }

    @Override
    public boolean aguardandoRestaurante(Pedido pedido) {
        return false;
    }

    @Override
    public boolean aguardandoEntregador(Pedido pedido) {
        return false;
    }
}
