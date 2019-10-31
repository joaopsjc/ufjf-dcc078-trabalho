/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author jjsfa
 */
public class ActionFactory {
    public static Action create(String action){
        Action actionObject;
        String nomeClasse = "action."+ action + "Action";
        Class classe;
        Object objeto;
        try{
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        }catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex){
            return null;
        }
        if (!(objeto instanceof Action)) return null;
        actionObject = (Action) objeto;
                
        return actionObject;        
    }
}
