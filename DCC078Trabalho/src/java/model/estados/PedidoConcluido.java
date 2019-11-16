package model.estados;

import model.Pedido;
import model.interfaces.PedidoEstado;

public class PedidoConcluido implements PedidoEstado {
    
    @Override
    public String getEstado() {
        return "Concluido";
    }
        
    @Override
    public String getNome() {
        return "Concluido";
    }
    
    @Override
    public boolean aCaminho(Pedido pedido) {
        return false;
    }
    
    @Override
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
