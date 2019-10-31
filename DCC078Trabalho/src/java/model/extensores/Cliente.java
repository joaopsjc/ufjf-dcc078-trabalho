/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extensores;

import model.abstratos.Endereco;
import model.abstratos.Usuario;
import model.interfaces.Contato;
import java.util.List;
import model.DadosBancarios;
import model.Pedido;
import java.util.Observable;
import java.util.Observer;
import model.interfaces.PedidoEstado;

import javax.mail.*;  
import javax.mail.internet.*;  
import java.util.Properties;

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
        if (pedidoSubject instanceof Pedido) {
            Pedido pedidoAtualizado = (Pedido) pedidoSubject;
            PedidoEstado estado = pedidoAtualizado.getEstado();
            
            System.out.println("Estado pedido alterado para "+ estado+ "!");
            /*Enviar email, faltar inserir email e senha e testar, line 93 e 94*/
 /*               String host="mail.javatpoint.com";
                final String user="sonoojaiswal@javatpoint.com";
                final String password="xxxxx";

                String to="anderson.andrade@gmail.com";

                 //Get the session object
                 Properties props = new Properties();
                 props.put("mail.smtp.host",host);
                 props.put("mail.smtp.auth", "true");

                 Session session = Session.getDefaultInstance(props,
                  new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(user,password);
                    }
                  });

                 //Compose the message
                  try {
                   MimeMessage message = new MimeMessage(session);
                   message.setFrom(new InternetAddress(user));
                   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
                   message.setSubject("javatpoint");
                   message.setText("O seu pedido teve o estado alterado para " + estado);

                  //send the message
                   Transport.send(message);

                   System.out.println("message sent successfully...");

                   } catch (MessagingException e) {e.printStackTrace();}  
*/
        }
    }

    @Override
    public String getQtdCarrinho() {
        return getCarrinho().getCountProdutos()+"";
    }
}
