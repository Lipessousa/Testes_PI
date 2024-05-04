package DAO;

import Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Conector.DbConector;

public class UsuarioDAO {

    public static boolean usuarioAcesso;

    public void RegistroUsuario(Usuario usuario) {
        String insert = "INSERT INTO Usuario(LoginUsuario, SenhaUsuario, Administrador) VALUES(?, ?, ?)";
        
        PreparedStatement ps;

        try {

            ps = DbConector.CriaConexao().prepareStatement(insert);

            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenhaUsuario());
            ps.setBoolean(3, usuario.getAdministrador());

            ps.execute();

        } catch (SQLException error) {
            System.out.println("Houve o seguinte erro na inserção de dados: " + error);
        }

    }

    public void Usuario(Usuario usuario) {
        String sql = "SELECT * FROM Usuario";

        PreparedStatement ps;

        try {
            ps = DbConector.CriaConexao().prepareStatement(sql);

            ResultSet resposta = ps.executeQuery();

            while (resposta.next()) {
                String usuarioNome = resposta.getString("LoginUsuario");

                System.out.println("Nome: " + usuarioNome);
            }

        } catch (SQLException error) {
            System.out.println("Houve o seguinte erro ma consulta: " + error);
        }

    }

    public void AlteraUsuario() {
        String sql = "UPDATE Usuario SET SenhaUsuario = ? WHERE LoginUsuario = ?";

        PreparedStatement ps;

        try {

            ps = DbConector.CriaConexao().prepareStatement(sql);

            ps.execute();

        } catch (SQLException error) {
            System.out.println("Houve o seguinte erro na alteração de dados: " + error);
        }
    }

    public void DeletaUsuario(Usuario usuario) {
        String sql = "DELETE FROM Usuario WHERE LoginUsuario = ?";

        PreparedStatement ps;

        try {

            ps = DbConector.CriaConexao().prepareStatement(sql);

            ps.execute();

        } catch (SQLException error) {
            System.out.println("Houve o seguinte erro na exclusão de dados: " + error);
        }

    }

    public void LoginUsuario(Usuario usuario) {
        String sql = "SELECT * FROM Usuario WHERE LoginUsuario = ? and SenhaUsuario = ? ";

        String usuarioLogin = null;
        String usuarioSenha = null;
        boolean usuarioAdm;

        try {

            PreparedStatement ps = DbConector.CriaConexao().prepareStatement(sql);

            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenhaUsuario());

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                usuarioLogin = res.getString("LoginUsuario");
                usuarioSenha = res.getString("SenhaUsuario");
                usuarioAdm = res.getBoolean("Administrador");
                usuarioAcesso = usuarioAdm;
            }

        } catch (SQLException error) {
            System.out.println("Houve o seguinte erro" + error);
        }
        

        if (usuarioLogin != null && usuarioSenha != null) {
            System.out.println("usuario: " + usuarioLogin + " | " + "senha: " + usuarioSenha);
        } else {
            System.out.println("Usuário ou senha incorretos");
        }
        
        
    }

}
