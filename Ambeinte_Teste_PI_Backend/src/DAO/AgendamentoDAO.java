package DAO;

import Conector.DbConector;
import Entidades.Agendamento;
import Entidades.Cliente;
import Entidades.Servico;
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
        
        
//    int idCliente; -- busca (java?)
//    String nomeCliente; -- puxado pelo user
//    int idServico; -- busca (java??)
//    String nomeServico; -- puxado pelo user
//    String tipoAtendimento; -- busca (java?)
//    String dataAtendimento; -- convert formato original (2024-05-31 14:43:02)
//    float precoAtendimento; -- busca (java?)
        
    }
    
    
}
