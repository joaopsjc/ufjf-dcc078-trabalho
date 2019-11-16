package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.abstratos.Usuario;
import model.extensores.Entregador;
import persistence.PedidoDAO;

public class EntregadorChainResponsibility {
    private List<Pedido> listaPedidosPendentes;
    public Entregador primeiroEntregador=null;
    public Entregador ultimoEntregador=null;
    private static final EntregadorChainResponsibility instance = new EntregadorChainResponsibility();
    public static EntregadorChainResponsibility getInstance(){
        return instance;
    }
    
    private EntregadorChainResponsibility(){
        try {
            populaListaPendente();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EntregadorChainResponsibility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean addToChain(Entregador novoEntregador)
    {
        if(primeiroEntregador==null)
        {
            primeiroEntregador = novoEntregador;
            ultimoEntregador = novoEntregador;
            novoEntregador.setProxEntregador(novoEntregador);
            esvaziaListaPendente(novoEntregador);
            return true;
        }
        else
        {
            Entregador entregadorAtual = primeiroEntregador;
            while(!entregadorAtual.equals(ultimoEntregador))
            {
                if(entregadorAtual.getProxEntregador().getId().equals(novoEntregador.getId()))
                {
                    return false;
                }
                entregadorAtual = entregadorAtual.getProxEntregador();
            }
            novoEntregador.setProxEntregador(primeiroEntregador);
            ultimoEntregador.setProxEntregador(novoEntregador);
            ultimoEntregador = novoEntregador;
            esvaziaListaPendente(novoEntregador);
            return true;
        }
    }
    //procura se o entregador está na cadeia de responsabilidade, se estiver retorna ele, se não estiver retorna nulo
    //para não precisar procurá-lo no banco de dados.
    public Usuario isInChain(Long id_entregador)
    {
        Entregador entregadorAtual = primeiroEntregador;
        while(!entregadorAtual.equals(ultimoEntregador))
        {
            if(entregadorAtual.getId().equals(id_entregador))
            {
                return entregadorAtual;
            }
            entregadorAtual = entregadorAtual.getProxEntregador();
        }
        if(entregadorAtual.getId().equals(id_entregador))
            return entregadorAtual;
        return null;
    }
    public Usuario getPrimeiroEntregador()
    {
        return primeiroEntregador;
    }
    public void removeFromChain(Entregador entregadorRemover) throws SQLException, ClassNotFoundException
    {
        Entregador entregadorAtual = primeiroEntregador;
        if(entregadorAtual!=null)
        {
            if(!entregadorRemover.getId().equals(primeiroEntregador.getId()))
            {
                while(!entregadorAtual.getProxEntregador().getId().equals(entregadorRemover.getId()) && !entregadorAtual.equals(ultimoEntregador))
                {
                    entregadorAtual = entregadorAtual.getProxEntregador();
                }
                if(!entregadorAtual.equals(ultimoEntregador))
                {
                    entregadorAtual.setProxEntregador(entregadorAtual.getProxEntregador().getProxEntregador());
                }
            }
            else
            {
                if(primeiroEntregador==ultimoEntregador)
                {
                    primeiroEntregador=null;
                    ultimoEntregador=null;
                    populaListaPendente();
                }
                else
                {
                    primeiroEntregador = primeiroEntregador.getProxEntregador();
                    ultimoEntregador.setProxEntregador(primeiroEntregador);
                }
            }
        }
    }
    
    public List<Entregador> getListEntregadores(){
        List<Entregador> result = new ArrayList<>();
        if (primeiroEntregador ==null)
            return result;
        Entregador entregadorAtual = primeiroEntregador;
        while(!entregadorAtual.equals(ultimoEntregador))
        {
            result.add(entregadorAtual);
            entregadorAtual = entregadorAtual.getProxEntregador();
        }
        result.add(entregadorAtual);
        return result;
    }

    public void addPedidoListaPendente(Pedido pedido) {
        listaPedidosPendentes.add(pedido);
    }
    public void removePedidoListaPendente(Pedido pedido) {
        listaPedidosPendentes.remove(pedido);
    }
    
    public void esvaziaListaPendente(Entregador entregador){
        if (listaPedidosPendentes.size()>0){
            entregador.addPedido(listaPedidosPendentes);
            listaPedidosPendentes.clear();
        }
    }
    
    public void populaListaPendente() throws SQLException, ClassNotFoundException{
        listaPedidosPendentes = PedidoDAO.getInstance().getPedidosPendentesEntregador();
    }
}
