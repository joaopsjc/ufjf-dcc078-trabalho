/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.abstratos.Usuario;


/**
 *
 * @author jjsfa
 */
public class UsuarioFactory {
    public static Usuario create(String tipoUsuario){
        Usuario usuarioObject;
        String nomeClasse = "model.extensores."+ tipoUsuario;
        Class classe;
        Object objeto;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex){
            return null;
        }
        if (!(objeto instanceof Usuario)) return null;
        usuarioObject = (Usuario) objeto;
                
        return usuarioObject;        
    }
}
