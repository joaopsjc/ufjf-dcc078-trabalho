/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.estados;

import model.interfaces.PedidoEstado;

/**
 *
 * @author UC249567
 */
public class PedidoEstadoFactory {
    public static PedidoEstado create(String estado){
        PedidoEstado actionObject = null;
        String nomeClasse = "model.estados.PedidoEstado"+ estado;
        Class classe = null;
        Object objeto =null;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (Exception ex){
            return null;
        }
        if (!(objeto instanceof PedidoEstado)) return null;
        actionObject = (PedidoEstado) objeto;
                
        return actionObject;        
    }
}