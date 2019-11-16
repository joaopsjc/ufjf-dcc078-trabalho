package model;

public class Notificacao {

    private Long id;
    private Long id_cliente;
    private String mensagem;
    private String dataHoraNotificacao;
    private boolean lida;
    
    public Notificacao(Long id_cliente, String mensagem, String dataHoraNotificacao) {
        this.id_cliente = id_cliente;
        this.mensagem = mensagem;
        this.dataHoraNotificacao = dataHoraNotificacao;
    }

    public Notificacao(Long id, Long id_cliente, String mensagem, String dataHoraNotificacao, boolean lida) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.mensagem = mensagem;
        this.dataHoraNotificacao = dataHoraNotificacao;
        this.lida = lida;
    }    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String message) {
        this.mensagem = message;
    }

    public String getDataHoraNotificacao() {
        return dataHoraNotificacao;
    }

    public void setDataHoraNotificacao(String dataHoraNotificacao) {
        this.dataHoraNotificacao = dataHoraNotificacao;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }  
    
    
}
