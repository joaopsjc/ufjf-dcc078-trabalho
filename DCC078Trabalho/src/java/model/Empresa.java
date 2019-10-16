/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jjsfa
 */
public class Empresa extends Usuario{
    private Cardapio cardapio;

    public Empresa(Long id, String nome) {
        super(id, nome);
    }

}