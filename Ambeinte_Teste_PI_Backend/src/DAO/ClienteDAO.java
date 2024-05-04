package DAO;

import Conector.DbConector;
import Entidades.Agendamento;
import Entidades.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ClienteDAO {

    public static void Insercao(Cliente Cliente) {

        String Insert = "INSERT INTO Cliente(Nome, Sobrenome, Telefone, TipoAtendimento) VALUES(?, ?, ?, ?)";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Insert);
            PS.setString(1, Cliente.getNomeCliente());
            PS.setString(2, Cliente.getSobrenomeCliente());
            PS.setString(3, Cliente.getTelefone());
            PS.setString(4, Cliente.getTipoAtendimento());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }

    }

    //    Codigo para o insert via tela, cada VarCad é um dos text box a serem preenchidos
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
    public static void AtualizaTelefone(Cliente Cliente) {

        String Atualizacao = "update Cliente set Telefone = ? where lower(NC) = Lower(?)";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setString(1, Cliente.getTelefone());
            PS.setString(2, Cliente.getNomeCompletoCliente());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public static void AtualizaNome(Cliente Cliente) {

        String Atualizacao = "update Cliente set Nome = ? where lower(NomeCompleto) = Lower(?)";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setString(1, Cliente.getNomeCliente());
            PS.setString(2, Cliente.getNomeCompletoCliente());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public static void AtualizaSobrenome(Cliente Cliente) {

        String Atualizacao = "update Cliente set Sobrenome = ? where lower(NomeCompleto) = Lower(?)";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Atualizacao);
            PS.setString(1, Cliente.getSobrenomeCliente());
            PS.setString(2, Cliente.getNomeCompletoCliente());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public static void Delecao(Cliente Cliente) {

        String Delecao = "delete from Cliente where NomeCompleto = ?";
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(Delecao);
            PS.setString(1, Cliente.getNomeCompletoCliente());
            PS.execute();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public static synchronized void SelectCliente(Agendamento Agendamento) throws SQLException {

        Cliente Cliente = new Cliente();
        String consulta = "select IdCliente, TipoAtendimento from Cliente where lower(NomeCompleto) = lower(?)";
        ResultSet Resultado = null;
        PreparedStatement PS;

        try {
            PS = DbConector.CriaConexao().prepareStatement(consulta);
            PS.setString(1, Agendamento.getNomeCliente());
            Resultado = PS.executeQuery();

            while (Resultado.next()) {
                Cliente.setIdCliente(Resultado.getInt("IdCliente"));
                Cliente.setTipoAtendimento(Resultado.getString("TipoAtendimento"));
            }

            Resultado.close();
            PS.close();
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();

        } finally {
            if (DbConector.CriaConexao() != null){
                try {
                    DbConector.CriaConexao().close();
                }catch (SQLException Excessao) {
            Excessao.printStackTrace();
                }
            }
        }

        Agendamento.setIdCliente(Cliente.IdCliente);
        Agendamento.setTipoAtendimento(Cliente.tipoAtendimento);
        System.out.println(Agendamento.idCliente + " " + Agendamento.tipoAtendimento);

    }

}
