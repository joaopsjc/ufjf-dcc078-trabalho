/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author andradeld
 */
public class Cliente extends Usuario {
    private MetodoPagamento metodoPagamento;

    public Cliente(Long id, String nome) {
        super(id, nome);
    }
}
