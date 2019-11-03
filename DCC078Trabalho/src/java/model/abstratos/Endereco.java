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
            id,
            id_usuario;
    private String lagradouro,
            complemento,
            bairro,
            estado,
            cidade;

    public Endereco(int numero, long cep, long id, String lagradouro, String complemento, String bairro, String estado, String cidade) {
        this.numero = numero;
        this.cep = cep;
        this.id = id;
        this.lagradouro = lagradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
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

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public long getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }
    
    public String getTipo()
    {
        return "Endereco";
    }

    public long getId_usuario() {
        return id_usuario;
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

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(long cep) {
        this.cep = cep;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }
}
