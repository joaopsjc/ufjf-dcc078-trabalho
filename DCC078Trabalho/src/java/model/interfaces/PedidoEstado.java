package model.interfaces;

import model.Pedido;

public interface PedidoEstado {
    
    public String getEstado();
    
    public String getNome();
    
    public boolean aCaminho(Pedido pedido);
    
    public boolean cancelado(Pedido pedido);
    
    public boolean concluido(Pedido pedido);
    
    public boolean emPreparo(Pedido pedido);
    
    public boolean aguardandoRestaurante(Pedido pedido);
    public boolean aguardandoEntregador(Pedido pedido);
}
