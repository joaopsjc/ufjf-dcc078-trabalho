/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.abstratos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andradeld
 */
public abstract class Endereco {
    private Long 
            id,
            id_usuario;
    private String logradouro,
            complemento,
            bairro,
            estado,
            cidade,
            cep,
            numero;
    
    private boolean principal;

    public Endereco(String numero, String cep, Long id, String logradouro, String complemento, String bairro, String estado, String cidade) {
        this.numero = numero;
        this.cep = cep;
        this.id = id;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
    }   
    public Endereco(){
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
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

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }
    
    public abstract String getTipo();

    public Long getId_usuario() {
        return id_usuario;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
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

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
    
    public String getLogradouroExibicao(){
        return getTipo()+" "+this.getLogradouro();
    }
    public String getLogradouroCompleto(){
        List<String> result = new ArrayList<>();
        result.add(getTipo());
        result.add(" ");
        result.add(this.getLogradouro());
        result.add(", nº ");
        result.add(this.getNumero());
        if (this.getComplemento() !=null &&this.getComplemento().length()>0){
            result.add(", ");
            result.add(this.getComplemento());
        }
        
        result.add(", ");
        result.add(this.getBairro());
        return String.join("",result);
    }
    
    
}
