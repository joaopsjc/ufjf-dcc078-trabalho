/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.interfaces.Contato;


/**
 *
 * @author jjsfa
 */
public class ContatoFactory {
    public static Contato create(String tipoContato){
        Contato usuarioObject = null;
        String nomeClasse = "model.implementadores.Contato"+ tipoContato;
        Class classe = null;
        Object objeto = null;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (Exception ex){
            return null;
        }
        if (!(objeto instanceof Contato)) return null;
        usuarioObject = (Contato) objeto;
                
        return usuarioObject;        
    }
}
