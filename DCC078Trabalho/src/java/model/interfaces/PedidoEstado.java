/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

import model.Pedido;

/**
 *
 * @author Anderson
 */
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
