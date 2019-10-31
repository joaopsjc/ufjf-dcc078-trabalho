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
public class PedidoConcluido implements PedidoEstado {
    
    @Override
    public String getEstado() {
        return "Concluido";
    }
    
    public boolean aCaminho(Pedido pedido) {
        return false;
    }
    
    public boolean cancelado(Pedido pedido) {
        return false;
    }
    
    public boolean concluido(Pedido pedido) {
        return false;
    }
    
    public boolean emPreparo(Pedido pedido) {
        return false;
    }
}
