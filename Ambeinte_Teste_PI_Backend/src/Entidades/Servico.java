
package Entidades;

public class Servico {
    
    public int idServico;
    public String nomeServico;
    public float precoLocal;
    public float precoVisita;

    public int getIdSercico() {
        return idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public float getPrecoLocal() {
        return precoLocal;
    }

    public void setPrecoLocal(float precoLocal) {
        this.precoLocal = precoLocal;
    }

    public float getPrecoVisita() {
        return precoVisita;
    }

    public void setPrecoVisita(float precoVisita) {
        this.precoVisita = precoVisita;
    }
}
