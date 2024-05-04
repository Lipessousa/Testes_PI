package DAO;

import Conector.DbConector;
import Entidades.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ClienteDAO {

    public void Insercao(Cliente Cliente) {

        String Insert = "INSERT INTO Cliente(Nome, Sobrenome, Telefone, TipoAtendimento) VALUES(?, ?, ?, ?)";
        PreparedStatement PS;

        try {
            PS = DbConector.getConexao().prepareStatement(Insert);
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }

    }

    public void Atualizacao(Cliente Cliente) {

        String Atualizacao = "update Cliente set ? = ? where ? = ?";
        PreparedStatement PS;

        try {
            PS = DbConector.getConexao().prepareStatement(Atualizacao);
        } catch (SQLException Excessao) {
            Excessao.printStackTrace();
        }
    }

    public void Delecao(Cliente Cliente) {

        String Delecao = "delete from Cliente where IdCliente = ?";
        PreparedStatement PS;

        try {
            PS = DbConector.getConexao().prepareStatement(Delecao);
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
    }
