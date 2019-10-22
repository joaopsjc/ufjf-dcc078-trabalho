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
    private int CPF;

    public Cliente(int CPF, Long id, String nome, String login, String senha) {
        super(id, nome, login, senha);
        this.CPF = CPF;
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }
    
}
