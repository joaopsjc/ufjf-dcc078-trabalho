/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.abstratos.Usuario;
import model.extensores.Entregador;

/**
 *
 * @author John
 */
public class EntregadorChainResponsibility {
    public Entregador primeiroEntregador=null;
    public Entregador ultimoEntregador=null;
    private static final EntregadorChainResponsibility instance = new EntregadorChainResponsibility();
    public static EntregadorChainResponsibility getInstance(){
        return instance;
    }
    public boolean addToChain(Entregador novoEntregador)
    {
        if(primeiroEntregador==null)
        {
            primeiroEntregador = novoEntregador;
            ultimoEntregador = novoEntregador;
            novoEntregador.setProxEntregador(novoEntregador);
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
            if(entregadorAtual.getProxEntregador().getId().equals(id_entregador))
            {
                return entregadorAtual;
            }
            entregadorAtual = entregadorAtual.getProxEntregador();
        }
        return null;
    }
    public Usuario getPrimeiroEntregador()
    {
        return primeiroEntregador;
    }
    public void removeFromChain(Entregador entregadorRemover)
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
                }
                else
                {
                    primeiroEntregador = primeiroEntregador.getProxEntregador();
                    ultimoEntregador.setProxEntregador(primeiroEntregador);
                }
            }
        }
    }            
}
