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
public class entregadorChainResponsibility {
    public Entregador primeiroEntregador=null;
    public Entregador ultimoEntregador=null;
    private static entregadorChainResponsibility instance = new entregadorChainResponsibility();
    public static entregadorChainResponsibility getInstance(){
        return instance;
    }
    public void addToChain(Entregador novoEntregador)
    {
        if(primeiroEntregador==null)
        {
            primeiroEntregador = novoEntregador;
            ultimoEntregador = novoEntregador;
            novoEntregador.setProxEntregador(novoEntregador);
        }
        else
        {
            novoEntregador.setProxEntregador(primeiroEntregador);
            ultimoEntregador.setProxEntregador(novoEntregador);
            ultimoEntregador = novoEntregador;
        }
    }
    public Usuario getPrimeiroEntregador()
    {
        return primeiroEntregador;
    }
    public void removeFromChain(Entregador entregadorRemover)
    {
        Entregador entregadorAtual = primeiroEntregador;

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
