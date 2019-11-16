package model.estados;

import model.Pedido;
import model.interfaces.PedidoEstado;

public class PedidoEmPreparo implements PedidoEstado {
    
    @Override
    public String getEstado() {
        return "EmPreparo";
    } 
    
    @Override
    public String getNome() {
        return "Em preparo";
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
        return false;
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
        pedido.setEstado(new PedidoAguardandoEntregador());
        return true;
    }
}
