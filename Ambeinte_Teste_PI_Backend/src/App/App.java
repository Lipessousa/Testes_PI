package App;

import Entidades.Usuario;
import DAO.UsuarioDAO;
import java.sql.SQLException;

public class App {

    public static void InsereUsuario() throws SQLException {
        Usuario u = new Usuario();

        UsuarioDAO dao = new UsuarioDAO();
        
        u.setLogin("felipe");
        u.setSenhaUsuario("1234");
        u.setAdministrador(false);

        dao.RegistroUsuario(u);
    }

    public static void AcessoUsuario() throws SQLException {
        Usuario u = new Usuario();
        
        UsuarioDAO dao = new UsuarioDAO();

        String usuario = "Mestre";
        String senha = "1234";
        
        u.setLogin(usuario);
        u.setSenhaUsuario(senha);
        

        dao.LoginUsuario(u);
        
        boolean adm = UsuarioDAO.usuarioAcesso;
        System.out.println(adm);
        
        
    }

    public static void main(String[] args) throws SQLException {
//        InsereUsuario();
        
        
        AcessoUsuario();
        
    }
}
