package model.estados;

import model.Pedido;
import model.interfaces.PedidoEstado;

public class PedidoCancelado implements PedidoEstado{
    
    @Override
    public String getEstado() {
        return "Cancelado";
    }
        
    @Override
    public String getNome() {
        return "Cancelado";
    }
    
    @Override
    public boolean aCaminho(Pedido pedido) {
        return false;
    }
    
    public boolean cancelado(Pedido pedido) {
        return false;
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
        return false;
    }
}
