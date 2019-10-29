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
public class PedidoACaminho implements PedidoEstado {
    
    public String getEstado() {
        return "A caminho";
    }
    
    public boolean aCaminho(Pedido pedido) {
        return false;
    }
    
    public boolean cancelado(Pedido pedido) {
        pedido.setEstado(new PedidoCancelado());
        return true;
    }
    
    public boolean concluido(Pedido pedido) {
        pedido.setEstado(new PedidoConcluido());
        return true;
    }
    
    public boolean emPreparo(Pedido pedido) {
        return false;
    }
}
