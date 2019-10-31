/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.interfaces.PedidoEstado;


/**
 *
 * @author jjsfa
 */
public class PedidoEstadoFactory {
    public static PedidoEstado create(String tipoEstadoPedido){
        PedidoEstado pedidoEstadoObject;
        String nomeClasse = "model.estados.Pedido"+ tipoEstadoPedido;
        Class classe;
        Object objeto;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex){
            return null;
        }
        if (!(objeto instanceof PedidoEstado)) return null;
        pedidoEstadoObject = (PedidoEstado) objeto;
                
        return pedidoEstadoObject;        
    }
}
