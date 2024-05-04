package DAO;

import Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Conector.DbConector;

public class UsuarioDAO {

    public static boolean usuarioAcesso;
    public static boolean verificacao;

    public static boolean Verificacao(Usuario usuario) throws SQLException {
        String read = "SELECT LoginUsuario from Usuario WHERE LoginUsuario = ?";

        PreparedStatement ps;

        String consulta = null;

        ps = DbConector.getConexao().prepareStatement(read);

        ps.setString(1, usuario.getLogin());

        ResultSet res = ps.executeQuery();

        while (res.next()) {
            consulta = res.getString("LoginUsuario");

            System.out.println("Nome: " + consulta);
        }

        if (consulta != null) {
            verificacao = true;
            return verificacao;
        } else {
            verificacao = false;
            return verificacao;
        }

    }

    public static void RegistroUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario(LoginUsuario, SenhaUsuario, Administrador) VALUES(?, ?, ?)";

        Verificacao(usuario);

        PreparedStatement ps;

        if (verificacao == false) {

            System.out.println("Este login ainda não existe");
            ps = DbConector.getConexao().prepareStatement(sql);

            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenhaUsuario());
            ps.setBoolean(3, usuario.getAdministrador());

        }

    }

    public void Usuario(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM Usuario";

        PreparedStatement ps;

        ps = DbConector.getConexao().prepareStatement(sql);

        ResultSet resposta = ps.executeQuery();

        while (resposta.next()) {
            String usuarioNome = resposta.getString("LoginUsuario");

            System.out.println("Nome: " + usuarioNome);
        }

    }

    public void AlteraUsuario() throws SQLException {
        String sql = "UPDATE Usuario SET SenhaUsuario = ? WHERE LoginUsuario = ?";

        PreparedStatement ps;

        ps = DbConector.getConexao().prepareStatement(sql);

        ps.execute();

    }

    public void DeletaUsuario(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE LoginUsuario = ?";

        PreparedStatement ps;

        ps = DbConector.getConexao().prepareStatement(sql);

        ps.execute();

    }

    public void LoginUsuario(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE LoginUsuario = ? and SenhaUsuario = ? ";

        String usuarioLogin = null;
        String usuarioSenha = null;
        boolean usuarioAdm;

        PreparedStatement ps = DbConector.getConexao().prepareStatement(sql);

        ps.setString(1, usuario.getLogin());
        ps.setString(2, usuario.getSenhaUsuario());

        ResultSet res = ps.executeQuery();

        while (res.next()) {
            usuarioLogin = res.getString("LoginUsuario");
            usuarioSenha = res.getString("SenhaUsuario");
            usuarioAdm = res.getBoolean("Administrador");
            usuarioAcesso = usuarioAdm;
        }

        if (usuarioLogin != null && usuarioSenha != null) {
            System.out.println("usuario: " + usuarioLogin + " | " + "senha: " + usuarioSenha);
        } else {
            System.out.println("Usuário ou senha incorretos");
        }

    }

}
