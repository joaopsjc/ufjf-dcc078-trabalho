/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Pedido;
import model.PedidoProduto;
import model.abstratos.Produto;
import model.abstratos.Usuario;
import model.extensores.Empresa;
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

    public List<Pedido> dividePedidoPorEmpresa(Pedido pedido) throws SQLException, ClassNotFoundException {
        
        HashMap<Long, Pedido> pedidosPorEmpresa = new HashMap<>();
        for(Iterator i = pedido.getProdutos().iterator();i.hasNext();){
            //empresas.containsKey 
            PedidoProduto pedidoProduto = (PedidoProduto)i.next();
            Pedido p;
            if (pedidosPorEmpresa.containsKey(pedidoProduto.getProduto().getId_empresa())){
                p = pedidosPorEmpresa.get(pedidoProduto.getProduto().getId_empresa());
            }else{
                p = new Pedido();
                Empresa empresa = (Empresa)UsuarioDAO.getInstance().getById(pedidoProduto.getProduto().getId_empresa());
                p.setEmpresa(empresa);
                pedidosPorEmpresa.put(empresa.getId(),p);
                p.setCliente(pedido.getCliente());
                p.setEstado(pedido.getEstado());
            }
            p.addProduto(pedidoProduto);
        }       
        List<Pedido> result = new ArrayList<>(pedidosPorEmpresa.values());
        
        return result;
    }
    
}