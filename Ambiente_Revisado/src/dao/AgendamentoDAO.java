package dao;

import conector.DbConector;
import entidades.Agendamento;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendamentoDAO {
    
    public static void Insercao(Agendamento Agendamento) throws SQLException{
        
        String Insert = "insert into Agendamento(IdCliente, NomeCliente, IdServico, NomeServico, TipoAtendimento, DataAtendimento, PrecoAtendimento) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement PS;
        
        ClienteDAO.SelectCliente(Agendamento);
        ServicoDAO.SelectServico(Agendamento);
        
            PS = DbConector.CriaConexao().prepareStatement(Insert);
            PS.setInt(1,Agendamento.getIdCliente());
            PS.setString(2, Agendamento.getNomeCliente());
            PS.setInt(3, Agendamento.getIdServico());
            PS.setString(4, Agendamento.getNomeServico());
            PS.setString(5, Agendamento.getTipoAtendimento());
            PS.setString(6, Agendamento.dataAtendimento);
            PS.setDouble(7, Agendamento.getPrecoAtendimento());
            PS.execute();
            
    }
    
    public static void AtualizaServicoAgendamento(Agendamento Agendamento) throws SQLException {

        String Atualizacao = "update Agendamento set NomeServico = ? where lower(NomeCliente) = Lower(?) and DataAtendimento = ?";
        PreparedStatement PS;

            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setString(1, Agendamento.getNomeServico());
            PS.setString(2, Agendamento.getNomeCliente());
            PS.setString(3, Agendamento.getDataAtendimento());
            PS.execute();
        
        ServicoDAO.AtualizaServicoAgendamento(Agendamento);
        
    }
    
    public static void AtualizaDataAgendamento(Agendamento Agendamento) throws SQLException{
        
        String Atualizacao = "update Agendamento set DataAtendimento = ? where lower(NomeCliente) = lower(?) and DataAtendimento = ?";
        PreparedStatement PS;
        

            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setString(1, Agendamento.getAtualizacao());
            PS.setString(2, Agendamento.getNomeCliente());
            PS.setString(3, Agendamento.getDataAtendimento());
            PS.execute();
        
    }
    
    public static void DelecaoAgendamento(Agendamento Agendamento)throws SQLException{

        String Delecao = "delete from Agendamento where lower(NomeCliente) = lower(?) and DataAtendimento = ?";
        PreparedStatement PS;


            PS = DbConector.CriaConexao().prepareStatement(Delecao);
            PS.setString(1, Agendamento.getNomeCliente());
            PS.setString(2, Agendamento.getDataAtendimento());
            PS.execute();
    }

}
