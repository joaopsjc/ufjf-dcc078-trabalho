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
        Endereco enderecoObject = null;
        String nomeClasse = "model.extensores."+ tipoContato;
        Class classe = null;
        Object objeto = null;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (Exception ex){
            return null;
        }
        if (!(objeto instanceof Endereco)) return null;
        enderecoObject = (Endereco) objeto;
                
        return enderecoObject;        
    }
}
