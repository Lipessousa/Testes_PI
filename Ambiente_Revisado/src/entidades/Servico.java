
package entidades;

public class Servico {
    
    public int idServico;
    public String nomeServico;
    public double precoLocal;
    public double precoVisita;
    public String alteracao;

    public int getIdSercico() {
        return idServico;
    }
    
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public double getPrecoLocal() {
        return precoLocal;
    }

    public void setPrecoLocal(double precoLocal) {
        this.precoLocal = precoLocal;
    }

    public double getPrecoVisita() {
        return precoVisita;
    }

    public void setPrecoVisita(double precoVisita) {
        this.precoVisita = precoVisita;
    }
    
    public String getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(String alteracao) {
        this.alteracao = alteracao;
    }
}
