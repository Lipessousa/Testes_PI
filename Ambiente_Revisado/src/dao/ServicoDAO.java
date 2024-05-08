package dao;

import conector.DbConector;
import entidades.Agendamento;
import entidades.Servico;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ServicoDAO {

    public static void Insercao(Servico Servico) throws SQLException {

        String Insert = "INSERT INTO Servico(NomeServico, PrecoLocal, PrecoVisita) VALUES(?, ?, ?)";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Insert);
        PS.setString(1, Servico.getNomeServico());
        PS.setDouble(2, Servico.getPrecoLocal());
        PS.setDouble(3, Servico.getPrecoVisita());
        PS.execute();
    }

    public static void AtualizaNomeServico(Servico Servico) throws SQLException {

        String Atualizacao = "update Servico set NomeServico = ? where lower(NomeServico) = Lower(?)";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
        PS.setString(1, Servico.getAlteracao());
        PS.setString(2, Servico.getNomeServico());
        PS.execute();
    }

    public static void AtualizaPrecoLocal(Servico Servico) throws SQLException {

        String Atualizacao = "update Servico set NomeServico = ? where lower(NomeServico) = Lower(?)";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
        PS.setDouble(1, Servico.getPrecoLocal());
        PS.setString(2, Servico.getNomeServico());
        PS.execute();
    }

    public static void AtualizaPrecoVisita(Servico Servico) throws SQLException {

        String Atualizacao = "update Servico set NomeServico = ? where lower(NomeServico) = Lower(?)";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
        PS.setDouble(1, Servico.getPrecoVisita());
        PS.setString(2, Servico.getNomeServico());
        PS.execute();
    }

    public static void Delecao(Servico Servico) throws SQLException {

        String Delecao = "delete from Servico where NomeServico = ?";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Delecao);
        PS.setString(1, Servico.getNomeServico());
        PS.execute();
    }

    public static synchronized void SelectServico(Agendamento Agendamento) throws SQLException {

        String consulta = "select * from Servico where lower(NomeServico) = lower(?)";
        ResultSet Resultado;
        PreparedStatement PS;
        Servico Servico = new Servico();

        PS = DbConector.CriaConexao().prepareStatement(consulta);
        PS.setString(1, Agendamento.getNomeServico());
        Resultado = PS.executeQuery();

        while (Resultado.next()) {
            Agendamento.setIdServico(Resultado.getInt("IdServico"));
            Servico.setPrecoVisita(Resultado.getDouble("PrecoVisita"));
            Servico.setPrecoLocal(Resultado.getDouble("PrecoLocal"));
        }

        if (Agendamento.tipoAtendimento.equals("Local")) {
            Agendamento.setPrecoAtendimento(Servico.precoLocal);
        } else {
            Agendamento.setPrecoAtendimento(Servico.precoVisita);
        }
        
    }

    public static synchronized void AtualizaServicoAgendamento(Agendamento Agendamento) throws SQLException {

        String Id = "Select * from Servico where NomeServico = ?";
        ResultSet Resultado;
        PreparedStatement PS;
        Servico Servico = new Servico();

        PS = DbConector.CriaConexao().prepareStatement(Id);
        PS.setString(1, Agendamento.getNomeServico());
        Resultado = PS.executeQuery();

        while (Resultado.next()) {
            Servico.setIdServico(Resultado.getInt("IdServico"));
            Servico.setPrecoVisita(Resultado.getDouble("PrecoVisita"));
            Servico.setPrecoLocal(Resultado.getDouble("PrecoLocal"));
        }

        Agendamento.setIdServico(Servico.getIdSercico());

        String UpdateId = "update Agendamento set IdServico = ? where lower(NomeCliente) = ?";

        PS = DbConector.CriaConexao().prepareStatement(UpdateId);
        PS.setInt(1, Agendamento.getIdServico());
        PS.setString(2, Agendamento.getNomeCliente());
        PS.execute();

        String LocalAtnedimento = "Select * from Cliente where lower(NomeCompleto) = lower(?)";

        PS = DbConector.CriaConexao().prepareStatement(LocalAtnedimento);
        PS.setString(1, Agendamento.getNomeCliente());
        Resultado = PS.executeQuery();

        while (Resultado.next()) {
            Agendamento.setTipoAtendimento(Resultado.getString("TipoAtendimento"));
        }

        if (Agendamento.tipoAtendimento.equals("Local")) {
            Agendamento.setPrecoAtendimento(Servico.precoLocal);

            String UpdatePreco = "update Agendamento set PrecoAtendimento = ? where lower(NomeCliente) = ?";

            PS = DbConector.CriaConexao().prepareStatement(UpdatePreco);
            PS.setDouble(1, Agendamento.getPrecoAtendimento());
            PS.setString(2, Agendamento.getNomeCliente());
            PS.execute();

        } else {

            Agendamento.setPrecoAtendimento(Servico.precoVisita);

            String UpdatePreco = "update Agendamento set PrecoAtendimento = ? where lower(NomeCliente) = ?";

            PS = DbConector.CriaConexao().prepareStatement(UpdatePreco);
            PS.setDouble(1, Agendamento.getPrecoAtendimento());
            PS.setString(2, Agendamento.getNomeCliente());
            PS.execute();
        }

    }

}
