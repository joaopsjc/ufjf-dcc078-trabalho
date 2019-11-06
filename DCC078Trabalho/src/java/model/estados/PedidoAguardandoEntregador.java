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
public class PedidoAguardandoEntregador implements PedidoEstado{
    
    @Override
    public String getEstado() {
        return "AguardandoEntregador";
    }
        
    @Override
    public String getNome() {
        return "Aguardando entregador";
    }
    
    @Override
    public boolean aCaminho(Pedido pedido) {
        if(pedido.getEntregador()!=null)
        {
            pedido.setEstado(new PedidoACaminho());
            return true;
        }
        else
        {
            return false;
        }
    }
    
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
        return false;
    }
}
