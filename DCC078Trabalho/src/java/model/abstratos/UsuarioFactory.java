/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.abstratos;


/**
 *
 * @author jjsfa
 */
public class UsuarioFactory {
    public static Usuario create(String tipoUsuario){
        Usuario usuarioObject = null;
        String nomeClasse = "model.extensores."+ tipoUsuario;
        Class classe = null;
        Object objeto =null;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (Exception ex){
            return null;
        }
        if (!(objeto instanceof Usuario)) return null;
        usuarioObject = (Usuario) objeto;
                
        return usuarioObject;        
    }
}
