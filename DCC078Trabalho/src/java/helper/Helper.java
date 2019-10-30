/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Produto;
import model.abstratos.Usuario;

/**
 *
 * @author jjsfa
 */
public class Helper {
    
    private List<Produto> produtosResumo;
    
    private static Helper instance = new Helper();
    public static Helper getInstance(){
        return instance;
    }
    
    public Usuario getLoggedUser(HttpServletRequest httpRequest){
        HttpSession sess = httpRequest.getSession(true);

        Usuario user = (Usuario)sess.getAttribute("loggedUser");
        return user;
    }
    
    public List<Produto> getListProdutos(){
        return produtosResumo;
    }
    
    public List<Produto> getListProdutos(String selectedIds){
        List<String> listSelectedIds = stringTolist(selectedIds);
        List<Produto> result = new ArrayList<Produto>();
        for(Iterator i = produtosResumo.iterator();i.hasNext();){
            Produto p = (Produto)i.next();
            if (listSelectedIds.contains(p.getId().toString()))
                result.add(p);
        }
        return result;
    }
    
    public void setListProdutos(List<Produto> produtos){
        Produto p2;
        if (this.produtosResumo != null){
            for(Iterator i = produtos.iterator();i.hasNext();){
                Produto p = (Produto)i.next();
                for(Iterator j = produtosResumo.iterator();j.hasNext();){
                    p2 = (Produto)j.next();
                    if (p2.getId()==p.getId())
                        p.saveToMemento(p2.getEstadoSalvo());
                }                

            }
        }
        this.produtosResumo = produtos;
    }
    
    public List<String> stringTolist(String selectedIds){
        return new ArrayList<String>(Arrays.asList(selectedIds.split(",")));
    }
    
}
