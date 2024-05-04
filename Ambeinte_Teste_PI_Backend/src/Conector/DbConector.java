package Conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbConector {

// O bloco de constantes a seguir definem os dados que permitem o acesso ao banco de dados, e definem o tamanho máximo do pool de conexões
    public static final String url = "jdbc:mysql://localhost:3306/dbsalao?allowMultiQueries=true";
    public static final String usuario = "root";
    public static final String senha = "senhagenerica";
    private static final int TamanhoPool = 5;

// Essa lista serve para armazenar as conexões que serão criadas a partir dos próximos blocos de intrução, como a biblioteca de 
// Array, que permite a criação de arrays tipados de forma bem mais simples sem tem que passar por uma criação de classe.
    private List<Connection> conexao;

//Esse bloco cria uma nova conexão baseada no tamanho da pool pré-estabelecida pela constante "TamanhoPool", e para cada
//valor do tamanho da pool é criada uma nova conexão na lista de conexões criada em "conexao = new ArrayList<>();".
    public DbConector() throws SQLException {
        conexao = new ArrayList<>();
        for (int i = 0; i < TamanhoPool; i++) {
            conexao.add(DbConector.CriaConexao());
        }
    }

// a criação da conexão é retornada como um método, logo a variável conexao armazena n conexões criadas, esse é o método que será utilizado para 
// as conexões padroes, pois a partir dele todos os outros blocos serão executados
    public static Connection CriaConexao() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

// Esse bloco sincronizado verifica se a conexão está vazia, para cada conexão utilizada, a mesma é removida da pool, ou 
// reutilizada em outro momento através do método de devolver a conexão.
    public synchronized Connection GetConexao() {
        while (conexao.isEmpty()) {
            try {
                // o wait nesse momento serve para aguardar a próxima conexão que estiver disponível.
                wait();
            } catch (InterruptedException Erro) {
                Erro.printStackTrace();
            }
        }

        return conexao.remove(conexao.size() - 1);
    }

    public synchronized void DevolveConexao(Connection con) {
        // nesse momento a conexão é recriada e retornada ao pool como uma novo valor de conexão, mantendo o pool com seu máximo de conexões.
        conexao.add(con);

        // Esse comando notify avisa que o método está esperando por uma conexão válida ser liberada para prosseguir com o código.
        notify();

    }

}
