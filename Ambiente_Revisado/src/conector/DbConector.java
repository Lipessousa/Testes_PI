package conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbConector {

// O bloco de constantes a seguir definem os dados que permitem o acesso ao banco de dados, e definem o tamanho m�ximo do pool de conex�es
    public static final String url = "jdbc:mysql://localhost:3306/dbsalao?allowMultiQueries=true";
    public static final String usuario = "root";
    public static final String senha = "senhagenerica";
    private static final int TamanhoPool = 5;

// Essa lista serve para armazenar as conex�es que ser�o criadas a partir dos pr�ximos blocos de intru��o, como a biblioteca de 
// Array, que permite a cria��o de arrays tipados de forma bem mais simples sem tem que passar por uma cria��o de classe.
    private List<Connection> conexao;

//Esse bloco cria uma nova conex�o baseada no tamanho da pool pr�-estabelecida pela constante "TamanhoPool", e para cada
//valor do tamanho da pool � criada uma nova conex�o na lista de conex�es criada em "conexao = new ArrayList<>();".
    public DbConector() throws SQLException {
        conexao = new ArrayList<>();
        for (int i = 0; i < TamanhoPool; i++) {
            conexao.add(DbConector.CriaConexao());
        }
    }

// a cria��o da conex�o � retornada como um m�todo, logo a vari�vel conexao armazena n conex�es criadas, esse � o m�todo que ser� utilizado para 
// as conex�es padroes, pois a partir dele todos os outros blocos ser�o executados
    public static Connection CriaConexao() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

// Esse bloco sincronizado verifica se a conex�o est� vazia, para cada conex�o utilizada, a mesma � removida da pool, ou 
// reutilizada em outro momento atrav�s do m�todo de devolver a conex�o.
    public synchronized Connection GetConexao() {
        while (conexao.isEmpty()) {
            try {
                // o wait nesse momento serve para aguardar a pr�xima conex�o que estiver dispon�vel.
                wait();
            } catch (InterruptedException Erro) {
                Erro.printStackTrace();
            }
        }

        return conexao.remove(conexao.size() - 1);
    }

    public synchronized void DevolveConexao(Connection con) {
        // nesse momento a conex�o � recriada e retornada ao pool como uma novo valor de conex�o, mantendo o pool com seu m�ximo de conex�es.
        conexao.add(con);

        // Esse comando notify avisa que o m�todo est� esperando por uma conex�o v�lida ser liberada para prosseguir com o c�digo.
        notify();

    }

}
