package DAO;

import Conector.DbConector;
import Entidades.Agendamento;
import Entidades.Servico;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ServicoDAO {

    public static void Insercao(Servico Servico) {

        String Insert = "INSERT INTO Servico(NomeServico, PrecoLocal, PrecoVisita) VALUES(?, ?, ?)";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Insert);
            PS.setString(1, Servico.getNomeServico());
            PS.setDouble(2, Servico.getPrecoLocal());
            PS.setDouble(3, Servico.getPrecoVisita());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public static void AtualizaNomeServico(Servico Servico) {

        String Atualizacao = "update Servico set NomeServico = ? where lower(NomeServico) = Lower(?)";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setString(1, Servico.getAlteracao());
            PS.setString(2, Servico.getNomeServico());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public static void AtualizaPrecoLocal(Servico Servico) {

        String Atualizacao = "update Servico set NomeServico = ? where lower(NomeServico) = Lower(?)";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setDouble(1, Servico.getPrecoLocal());
            PS.setString(2, Servico.getNomeServico());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public static void AtualizaPrecoVisita(Servico Servico) {

        String Atualizacao = "update Servico set NomeServico = ? where lower(NomeServico) = Lower(?)";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setDouble(1, Servico.getPrecoVisita());
            PS.setString(2, Servico.getNomeServico());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public static void Delecao(Servico Servico) {

        String Delecao = "delete from Servico where NomeServico = ?";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Delecao);
            PS.setString(1, Servico.getNomeServico());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public static synchronized void SelectServico(Agendamento Agendamento) throws SQLException {

        String consulta = "select IdServico, PrecoLocal, PrecoVisita from Servico where lower(NomeServico) = lower(?)";
        ResultSet Resultado = null;
        PreparedStatement PS;
        Servico Servico = new Servico();

        try {
            PS = DbConector.CriaConexao().prepareStatement(consulta);
            PS.setString(1, Agendamento.getNomeServico());
            Resultado = PS.executeQuery();

            while (Resultado.next()) {
                Servico.setIdServico(Resultado.getInt("IdServico"));
                Servico.setPrecoVisita(Resultado.getDouble("PrecoVisita"));
                Servico.setPrecoLocal(Resultado.getDouble("PrecoLocal"));
            }

            Resultado.close();
            PS.close();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();

        }
        Agendamento.setIdServico(Servico.idServico);

        if ("Local".equals(Agendamento.tipoAtendimento)) {
            Agendamento.setPrecoAtendimento(Servico.precoLocal);
        } else {
            Agendamento.setPrecoAtendimento(Servico.precoVisita);
        }
        System.out.println(Agendamento.idServico + " " + Agendamento.precoAtendimento);
        DbConector.CriaConexao().close();
    }
}
