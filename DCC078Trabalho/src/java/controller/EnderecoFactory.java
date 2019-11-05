/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.abstratos.Endereco;


/**
 *
 * @author jjsfa
 */
public class EnderecoFactory {
    public static Endereco create(String tipoContato){
        Endereco enderecoObject;
        String nomeClasse = "model.extensores.Endereco"+ tipoContato;
        Class classe;
        Object objeto;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex){
            return null;
        }
        if (!(objeto instanceof Endereco)) return null;
        enderecoObject = (Endereco) objeto;
                
        return enderecoObject;        
    }
}
