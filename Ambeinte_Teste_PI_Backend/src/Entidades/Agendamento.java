
package Entidades;

public class Agendamento {

    public int idCliente;
    public String nomeCliente;
    public int idServico;
    public String nomeServico;
    public String tipoAtendimento;
    public String dataAtendimento;
    public double precoAtendimento;
    public String Atualiza;
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }


    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getIdServico() {
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

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public String getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(String dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public double getPrecoAtendimento() {
        return precoAtendimento;
    }

    public void setPrecoAtendimento(double precoAtendimento) {
        this.precoAtendimento = precoAtendimento;
    }
    
    public String getAtualizacao() {
        return Atualiza;
    }

    public void SetAtualizacao(String Atualiza) {
        this.Atualiza = Atualiza;
    }

}
