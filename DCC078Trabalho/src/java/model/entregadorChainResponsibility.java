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
