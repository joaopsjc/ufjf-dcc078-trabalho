/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.abstratos;

/**
 *
 * @author andradeld
 */
public abstract class Endereco {
    private int numero;
    private long cep,
                id;
    private String lagradouro,
            complemento,
            referencia,
            estado,
            cidade;

    public Endereco(Long id, String lagradouro, int numero, String complemento, String referencia, String cidade, String estado, long cep) {
        this.id = id;
        this.lagradouro = lagradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.referencia = referencia;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getLagradouro() {
        return lagradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public long getCep() {
        return cep;
    }
    
    public long getId() {
        return id;
    }
    
    public void setLagradouro(String lagradouro) {
        this.lagradouro = lagradouro;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(long cep) {
        this.cep = cep;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
