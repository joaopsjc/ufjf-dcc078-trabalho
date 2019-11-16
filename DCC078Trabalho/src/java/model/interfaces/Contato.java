package model.interfaces;

public interface Contato {
    public String getValor();
    public long getId();
    public long getIdUsuario();
    public String getTipo();
    public void setValor(String novoValor);
    public void setId(Long novoId);
    public void setIdUsuario(Long novoIdUsuario);
}
