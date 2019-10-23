/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.interfaces.Promocao;

/**
 *
 * @author ice
 */
public class PromocaoGenerica implements Promocao{
    String nome = "Promoção Genérica";
    public int obterDesconto() {
            return 10;
        }

    public String obterPromocao() {
            return nome;
        }
    public void setNome(String novoNome)
    {
        nome = novoNome;
    }
}
