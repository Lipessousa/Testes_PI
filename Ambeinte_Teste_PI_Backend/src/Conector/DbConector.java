package Conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConector {

    public static Connection conexao;
    
    public static Connection getConexao() {
        
        String url = "jdbc:mysql://localhost:3306/dbsalao";
        String user = "root";
        String password = "1234";

        try {
            if (conexao == null) {
                conexao = DriverManager.getConnection(url, user, password);
                return conexao;
            } else {
                return null;
            }
        } catch (SQLException error) {
            System.out.println("Houve o seguinte erro de conexão: " + error);
            return null;
        }
    }

}
