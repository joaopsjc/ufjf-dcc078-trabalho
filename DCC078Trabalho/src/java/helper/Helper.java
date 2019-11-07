/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Pedido;
import model.PedidoProduto;
import model.abstratos.Endereco;
import model.abstratos.Produto;
import model.abstratos.Usuario;
import model.extensores.Empresa;
import persistence.EnderecoDAO;
import persistence.NotificacaoDAO;
import persistence.UsuarioDAO;

/**
 *
 * @author jjsfa
 */
public class Helper {
    
    
    private static final Helper instance = new Helper();
    public static Helper getInstance(){
        return instance;
    }
    
    public Usuario getLoggedUser(HttpServletRequest httpRequest){
        HttpSession sess = httpRequest.getSession(true);

        Usuario user = (Usuario)sess.getAttribute("loggedUser");
        return user;
    }
    
    public List<Produto> getListProdutos(HttpServletRequest httpRequest){
        return getLoggedUser(httpRequest).getProdutos();
    }
    
    public List<Produto> getListProdutosByIds(String selectedIds,HttpServletRequest request){
        List<String> listSelectedIds = stringTolist(selectedIds);
        List<Produto> result = new ArrayList<>();
        for(Iterator i = getListProdutos(request).iterator();i.hasNext();){
            Produto p = (Produto)i.next();
            if (listSelectedIds.contains(p.getId().toString()))
                result.add(p);
        }
        return result;
    }
    
    public void setListProdutos(List<Produto> produtos,HttpServletRequest request){
        Produto p2;
        List<Produto> listProdutos = getListProdutos(request);
        if (listProdutos != null){
            for(Iterator i = produtos.iterator();i.hasNext();){
                Produto p = (Produto)i.next();
                for(Iterator j = listProdutos.iterator();j.hasNext();){
                    p2 = (Produto)j.next();
                    if (Objects.equals(p2.getId(), p.getId()))
                        p.saveToMemento(p2.getEstadoSalvo());
                }   

            }
        }
        getLoggedUser(request).setProdutos(produtos);
    }
    
    public List<String> stringTolist(String selectedIds){
        return new ArrayList<>(Arrays.asList(selectedIds.split(",")));
    }

    public Pedido getCarrinhoByClienteId(HttpServletRequest httpRequest) {
        Pedido pedido = getLoggedUser(httpRequest).getCarrinho();
        return pedido;
    }

    public void addCarrinho(Pedido carrinho,HttpServletRequest httpRequest) {
        getLoggedUser(httpRequest).setCarrinho(carrinho);
    }

    public void zeraCarrinhoByClienteId(HttpServletRequest request) {
        getLoggedUser(request).setCarrinho(new Pedido());
    }

    
    
    public void setDynamicInfoLoggedUser(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession sess = httpRequest.getSession(false);

        if (sess == null)
            return;
        
        Usuario user = (Usuario)sess.getAttribute("loggedUser");
        if (user == null)
            return;
        sess.setAttribute("menuPageName", "menu"+user.getTipo()+".jsp");       
        
        switch(user.getTipo()){
            case "Entregador":
                setDynamicInfoEntregador(sess,user);
                break;
            case "Empresa":
                setDynamicInfoEmpresa(sess,user);
                break;
            case "Cliente":
                setDynamicInfoCliente(sess,user);
                break;
        }
    }
    
    private void setDynamicInfoEntregador(HttpSession sess, Usuario user) throws SQLException, ClassNotFoundException{
        sess.setAttribute("countPedidosPendentesEntregador", HelperPedido.getInstance().getCountPedidosPendentesEntregador(user.getId()));
    }
    private void setDynamicInfoEmpresa(HttpSession sess, Usuario user) throws SQLException, ClassNotFoundException{
        sess.setAttribute("countPedidosPendentesEmpresa", HelperPedido.getInstance().getCountPedidosPendentesEmpresa(user.getId()));
    }
    private void setDynamicInfoCliente(HttpSession sess, Usuario user) throws SQLException, ClassNotFoundException{
        Endereco endereco = EnderecoDAO.getInstance().getPrincipalByUserId(user.getId());
        user.setEnderecoPrincipal(endereco);
        sess.setAttribute("countNotificacoesCliente", NotificacaoDAO.getInstance().getCountNotificacoesCliente(user.getId()));
    }

    
}