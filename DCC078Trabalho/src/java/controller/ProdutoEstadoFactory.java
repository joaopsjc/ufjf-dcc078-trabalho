/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.interfaces.ProdutoEstado;

/**
 *
 * @author UC249567
 */
public class ProdutoEstadoFactory {
    public static ProdutoEstado create(String estado){
        ProdutoEstado actionObject;
        String nomeClasse = "model.estados.ProdutoEstado"+ estado;
        Class classe;
        Object objeto;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex){
            return null;
        }
        if (!(objeto instanceof ProdutoEstado)) return null;
        actionObject = (ProdutoEstado) objeto;
                
        return actionObject;        
    }
}
