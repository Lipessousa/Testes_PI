package dao;

import conector.DbConector;
import entidades.Agendamento;
import entidades.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ClienteDAO {

    public static void Insercao(Cliente Cliente) throws SQLException {

        String Insert = "INSERT INTO Cliente(Nome, Sobrenome, Telefone, TipoAtendimento) VALUES(?, ?, ?, ?)";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Insert);
        PS.setString(1, Cliente.getNomeCliente());
        PS.setString(2, Cliente.getSobrenomeCliente());
        PS.setString(3, Cliente.getTelefone());
        PS.setString(4, Cliente.getTipoAtendimento());
        PS.execute();

    }

    //    Codigo para o insert via tela, cada VarCad � um dos text box a serem preenchidos
//    private void BtCadastroCliente(caralho) {
//        
//        String Nome = VarCadNome.GetText();
//        String Sobrenome = VarCadSobrenome.GetText();
//        String Telefone = VarCadTelefone.GetText();
//        String TipoAtendimento = VarCadTipoAtendimento.GetText();
//        
//        Cliente Cliente = new Cliente();
//        
//        Cliente.setNomeCliente(Nome);
//        Cliente.setSobrenomeCliente(Sobrenome);
//        Cliente.setTelefone(Telefone);
//        Cliente.setTipoAtendimento(TipoAtendimento);
//        
//        new ClienteDAO().Insercao(Cliente);
//        
//        
//    }
    public static void AtualizaTelefone(Cliente Cliente) throws SQLException {

        String Atualizacao = "update Cliente set Telefone = ? where lower(NC) = Lower(?)";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
        PS.setString(1, Cliente.getTelefone());
        PS.setString(2, Cliente.getNomeCompletoCliente());
        PS.execute();

    }

    public static void AtualizaNome(Cliente Cliente) throws SQLException {

        String Atualizacao = "update Cliente set Nome = ? where lower(NomeCompleto) = Lower(?)";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
        PS.setString(1, Cliente.getNomeCliente());
        PS.setString(2, Cliente.getNomeCompletoCliente());
        PS.execute();

    }

    public static void AtualizaSobrenome(Cliente Cliente) throws SQLException {

        String Atualizacao = "update Cliente set Sobrenome = ? where lower(NomeCompleto) = Lower(?)";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
        PS.setString(1, Cliente.getSobrenomeCliente());
        PS.setString(2, Cliente.getNomeCompletoCliente());
        PS.execute();
    }

    public static void Delecao(Cliente Cliente) throws SQLException {

        String Delecao = "delete from Cliente where NomeCompleto = ?";
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(Delecao);
        PS.setString(1, Cliente.getNomeCompletoCliente());
        PS.execute();
    }

    public static synchronized void SelectCliente(Agendamento Agendamento) throws SQLException {

        String consulta = "select IdCliente, TipoAtendimento from Cliente where lower(NomeCompleto) = lower(?)";
        ResultSet Resultado;
        PreparedStatement PS;

        PS = DbConector.CriaConexao().prepareStatement(consulta);
        PS.setString(1, Agendamento.getNomeCliente());
        Resultado = PS.executeQuery();

        while (Resultado.next()) {
            Agendamento.setIdCliente(Resultado.getInt("IdCliente"));
            Agendamento.setTipoAtendimento(Resultado.getString("TipoAtendimento"));
        }
        
    }

}
