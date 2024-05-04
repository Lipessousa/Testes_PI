package DAO;

import Conector.DbConector;
import Entidades.Agendamento;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendamentoDAO {
    
    public static void Insert(Agendamento Agendamento) throws SQLException{
        
        String Insert = "INSERT INTO Cliente(IdCliente, NomeCliente, IdServico, NomeServico, TipoAtendimento, DataAtendimento, PrecoAtendimento) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement PS;
        
        ClienteDAO.SelectCliente(Agendamento);
        ServicoDAO.SelectServico(Agendamento);

        try {
            PS = DbConector.CriaConexao().prepareStatement(Insert);
            PS.setInt(1, Agendamento.getIdCliente());
            PS.setString(2, Agendamento.getNomeCliente());
            PS.setInt(3, Agendamento.getIdServico());
            PS.setString(4, Agendamento.getNomeServico());
            PS.setString(5, Agendamento.getTipoAtendimento());
            PS.setString(6, Agendamento.getDataAtendimento());
            PS.setDouble(7, Agendamento.getPrecoAtendimento());
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }

    }
    
    public static void AtualizaServicoAgendamento(Agendamento Agendamento) {

        String Atualizacao = "update Agendamento set NomeServico = ? where lower(NomeCliente) = Lower(?)";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setString(1, Agendamento.getNomeServico());
            PS.setString(2, Agendamento.getNomeCliente());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
        
        ServicoDAO.AtualizaServicoAgendamento(Agendamento);
        
    }
    
    public static void AtualizaDataAgendamento(Agendamento Agendamento){
        
        String Atualizacao = "update Agendamento set DataAtendimento = ? where lower(NomeCliente) = ?";
        PreparedStatement PS;
        
        try {
            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setString(1, Agendamento.getDataAtendimento());
            PS.setString(2, Agendamento.getNomeCliente());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
        
    }
    
    public static void DelecaoAgendamento(Agendamento Agendamento){

        String Delecao = "delete from Agendamento where lower(NomeCliente) = lower(? and DataAtendimento = ?";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Delecao);
            PS.setString(1, Agendamento.getNomeCliente());
            PS.setString(2, Agendamento.getDataAtendimento());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

}
