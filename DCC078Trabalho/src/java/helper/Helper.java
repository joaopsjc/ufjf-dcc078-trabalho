/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.abstratos.Usuario;

/**
 *
 * @author jjsfa
 */
public class Helper {
    
    public static Usuario getLoggedUser(HttpServletRequest httpRequest){
        HttpSession sess = httpRequest.getSession(true);

        Usuario user = (Usuario)sess.getAttribute("loggedUser");
        return user;
    }
}
