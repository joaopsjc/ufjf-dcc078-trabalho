/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.estados;

import model.Pedido;
import model.interfaces.PedidoEstado;

/**
 *
 * @author ice
 */
public class PedidoEmPreparo implements PedidoEstado {
    
    public String getEstado() {
        return "Em preparo";
    }
    
    public boolean aCaminho(Pedido pedido) {
        pedido.setEstado(new PedidoACaminho());
        return true;
    }
    
    public boolean cancelado(Pedido pedido) {
        pedido.setEstado(new PedidoCancelado());
        return true;
    }
    
    public boolean concluido(Pedido pedido) {
        return false;
    }
    
    public boolean emPreparo(Pedido pedido) {
        return false;
    }
}
