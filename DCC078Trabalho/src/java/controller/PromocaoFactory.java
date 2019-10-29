/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.interfaces.Promocao;


/**
 *
 * @author jjsfa
 */
public class PromocaoFactory {
    public static Promocao create(String tipoContato){
        Promocao usuarioObject = null;
        String nomeClasse = "model.implementadores.Promocao"+ tipoContato;
        Class classe = null;
        Object objeto = null;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (Exception ex){
            return null;
        }
        if (!(objeto instanceof Promocao)) return null;
        usuarioObject = (Promocao) objeto;
                
        return usuarioObject;        
    }
}
