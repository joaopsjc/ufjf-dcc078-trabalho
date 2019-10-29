/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.implementadores;

import model.interfaces.Promocao;

/**
 *
 * @author ice
 */
public class PromocaoDiaMaes implements Promocao{
    private String nome = "Promoção Dia das Mães";
    private final String tipo = "DiaMaes";
    
    @Override
    public int obterDesconto() {
            return 20;
    }

    @Override
    public String obterPromocao() {
            return nome;
    }
    @Override
    public String getTipo() {
        return this.tipo;
    }
}
