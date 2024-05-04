package DAO;

import Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Conector.DbConector;

public class UsuarioDAO {

    public static boolean usuarioAcesso;

    public static void RegistroUsuario(Usuario usuario) throws SQLException {
        String read = "SELECT LoginUsuario FROM Usuario WHERE LoginUsuario = ?";
        String insert = "INSERT INTO Usuario(LoginUsuario, SenhaUsuario, Administrador) VALUES(?, ?, ?)";

        PreparedStatement ps;

        String usuarioNome = null;

        ps = DbConector.CriaConexao().prepareStatement(read);

        ps.setString(1, usuario.getLogin());
        
        ResultSet res = ps.executeQuery();

        while (res.next()) {
            usuarioNome = res.getString("LoginUsuario");

            System.out.println("Login: " + usuarioNome);
        }

        if (usuarioNome != null) {
            System.out.println("Usuário já existe");
        } else {
            ps = DbConector.CriaConexao().prepareStatement(insert);

            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenhaUsuario());
            ps.setBoolean(3, usuario.getAdministrador());

            ps.execute();
        }
        

    }

    public void Usuario(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM Usuario";

        PreparedStatement ps;

        ps = DbConector.CriaConexao().prepareStatement(sql);

        ResultSet resposta = ps.executeQuery();

        while (resposta.next()) {
            String usuarioNome = resposta.getString("LoginUsuario");

            System.out.println("Nome: " + usuarioNome);
        }

    }

    public void AlteraSenhaUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET SenhaUsuario = ? WHERE LoginUsuario = ?";

        PreparedStatement ps;

        ps = DbConector.CriaConexao().prepareStatement(sql);
        
        ps.setString(1, usuario.getSenhaUsuario());
        ps.setString(2, usuario.getLogin());

        ps.execute();
    }
    
    
    public void AlteraLoginUsuario(Usuario usuario) throws SQLException{
        String sql = "UPDATE Usuario SET LoginUsuario = ? WHERE LoginUsuario = ?";

        PreparedStatement ps;

        ps = DbConector.CriaConexao().prepareStatement(sql);
        
        ps.setString(1, usuario.getLogin());
        ps.setString(2, usuario.getLogin());

        ps.execute();
    }

    public void DeletaUsuario(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE LoginUsuario = ?";

        PreparedStatement ps;
        
        ps = DbConector.CriaConexao().prepareStatement(sql);

        ps.setString(1, usuario.getLogin());

        ps.execute();

    }

    public void LoginUsuario(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE LoginUsuario = ? and SenhaUsuario = ? ";

        String usuarioLogin = null;
        String usuarioSenha = null;
        boolean usuarioAdm;

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

        if (usuarioLogin != null && usuarioSenha != null) {
            System.out.println("usuario: " + usuarioLogin + " | " + "senha: " + usuarioSenha);
        } else {
            System.out.println("Usuário ou senha incorretos");
        }

    }

}
