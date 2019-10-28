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
public class DadosBancarios {
    private final Long id,
            codigoBanco,
            agencia,
            numero;
    private final String nomeBanco;

    public DadosBancarios(Long id, Long codigoBanco, Long agencia, Long numero, String nomeBanco) {
        this.id = id;
        this.codigoBanco = codigoBanco;
        this.agencia = agencia;
        this.numero = numero;
        this.nomeBanco = nomeBanco;
    }

    public Long getId() {
        return id;
    }

    public Long getCodigoBanco() {
        return codigoBanco;
    }

    public Long getAgencia() {
        return agencia;
    }

    public Long getNumero() {
        return numero;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }
    
}
