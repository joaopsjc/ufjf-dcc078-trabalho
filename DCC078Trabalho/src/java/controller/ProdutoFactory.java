/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.abstratos.Produto;

/**
 *
 * @author UC249567
 */
public class ProdutoFactory {
    public static Produto create(String categoria){
        Produto actionObject;
        String nomeClasse = "model.extensores.Produto"+ categoria;
        Class classe;
        Object objeto;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex){
            return null;
        }
        if (!(objeto instanceof Produto)) return null;
        actionObject = (Produto) objeto;
                
        return actionObject;        
    }
}
