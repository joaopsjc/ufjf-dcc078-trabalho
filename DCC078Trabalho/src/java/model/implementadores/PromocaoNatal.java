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
public class PromocaoNatal implements Promocao{
    private Long id;
    private final String nome = "Promoção de Natal";
    private final String tipo = "Natal";
    
    @Override
    public int obterDesconto() {
            return 10;
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
