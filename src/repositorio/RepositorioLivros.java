package repositorio;

import classes.DBConnection;
import classes.Livro;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Connection;

/**
 * @author manoel.neto
 */
public class RepositorioLivros {
    private static ArrayList<Livro> livros = new ArrayList<Livro>();
    
    private static Connection conexao = DBConnection.openConnection();
    private static PreparedStatement sql;
    
    public static void teste(){
        try{
            sql = conexao.prepareStatement("Insert into livros(titulo,ano,editora,autor,tem,edicao,serie) VALUES ('teste', '1', 'teste', 'teste','1', 'teste', 'teste')");
            sql.executeUpdate();
            DBConnection.closeConnection(conexao);
        }catch(Exception e){
            //nada
        } 
    }
    
    public static void adicoinar(Livro livro){
        livros.add(livro);
    }
    
    public static ArrayList<Livro> listar(){
        return livros;
    }
}
