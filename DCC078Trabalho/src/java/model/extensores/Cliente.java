/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extensores;

import java.sql.SQLException;
import model.Notificacao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.abstratos.Endereco;
import model.abstratos.Usuario;
import model.interfaces.Contato;
import java.util.List;
import model.DadosBancarios;
import model.Pedido;
import java.util.Observable;
import java.util.Observer;

import javax.mail.*;  
import javax.mail.internet.*;  
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.NotificacaoDAO;

/**
 *
 * @author andradeld
 */
public class Cliente extends Usuario implements Observer {
    
    private Observable pedido;
    
    public Cliente(){
        super();
    }
    
    public Cliente(String documento, Long id, String nome, String login, String senha) {
        super(id, documento, nome, login, senha);

    }

    public Cliente(String documento, Long id, String nome, String login, List<DadosBancarios> dadosBancarios, String senha) {
        super(id, documento, nome, login, dadosBancarios, senha);

    }

    public Cliente(String documento, Long id, String nome, String login, String senha, List<Endereco> enderecos) {
        super(id, documento, nome, login, senha, enderecos);

    }

    public Cliente(String documento, Long id, String nome, List<Contato> contatos, String login, String senha) {
        super(id, documento, nome, contatos, login, senha);

    }

    public Cliente(String documento, Long id, List<Pedido> pedidos, String nome, String login, String senha) {
        super(id, documento, pedidos, nome, login, senha);

    }

    public Cliente(String documento, Long id, String nome, String login, String senha, List<DadosBancarios> dadosBancarios, List<Endereco> enderecos, List<Contato> contatos, List<Pedido> pedidos) {
        super(id, documento, nome, login, senha, dadosBancarios, enderecos, contatos, pedidos);

    }

    @Override
    public String getTipo()
    {
        return "Cliente";
    }
    
    
    public Observable getPedido() {
        return pedido;
    }

    public void setPedido(Observable pedido) {
        this.pedido = pedido;
    }
    
    public Cliente(Observable pedido) {
        this.pedido = pedido;
        pedido.addObserver(this);
    }

    @Override
    public void update(Observable pedidoSubject, Object arg1) {
        if (!(pedidoSubject instanceof Pedido)) 
            return;
        
        try {
            Pedido pedidoAtualizado = (Pedido) pedidoSubject;
            String estado = pedidoAtualizado.getEstado().getNome();
            String message = "Olá,"+this.getNome()+". O pedido #"+pedidoAtualizado.getId()+" teve o estado alterado para " + estado;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            String horaNotificacao = dtf.format(now);
            Notificacao notificacao = new Notificacao(this.getId(),message,horaNotificacao);
            NotificacaoDAO.getInstance().insert(notificacao);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String getQtdCarrinho() {
        return getCarrinho().getCountProdutos()+"";
    }
}
