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
public abstract class Usuario {
    private Long id;
    private String nome;
    private Endereco endereco;
    private Contato contato;
    
    public Usuario(Long id, String nome){
        this.id=id;
        this.nome=nome;
    }
}
