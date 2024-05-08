
package entidades;

public class Usuario {
    public int idUsuario;
    public String login;
    public String senhaUsuario;
    public boolean administrador;
    
    public int getIdUsuario(){
        return idUsuario;
    }
    
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public String getLogin(){
        return login;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public String getSenhaUsuario(){
        return senhaUsuario;
    }
    
    public void setSenhaUsuario(String senhaUsuario){
        this.senhaUsuario = senhaUsuario;
    }
    
    public boolean getAdministrador(){
        return administrador;
    }
    
    public void setAdministrador(boolean administrador){
        this.administrador = administrador;
    }
    
    
}
