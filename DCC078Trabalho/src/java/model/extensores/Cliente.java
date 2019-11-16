package model.extensores;

import java.sql.SQLException;
import model.Notificacao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.abstratos.Endereco;
import model.abstratos.Usuario;
import model.interfaces.Contato;
import java.util.List;
import model.Pedido;
import java.util.Observable;
import java.util.Observer;

import javax.mail.*;  
import javax.mail.internet.*;  
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.NotificacaoDAO;

public class Cliente extends Usuario implements Observer {
    
    private Observable pedido;
    
    public Cliente(){
        super();
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
