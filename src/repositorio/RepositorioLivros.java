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
    
    public static boolean adicoinar(Livro livro){
        boolean retorno = false;
        try{
            sql = conexao.prepareStatement(
                    "INSERT INTO livros (titulo,ano,editora,autor,tem,edicao,serie) VALUES ("
                            + "'" + livro.getTitulo() + "', "
                            + "'" + livro.getAno()+ "', "
                            + "'" + livro.getEditora()+ "', "
                            + "'" + livro.getAutor()+ "', "
                            + "'" + livro.getTem()+ "', "
                            + "'" + livro.getEdicao()+ "', "
                            + "'" + livro.getSerie()+ "')"
            );
            
            sql.executeUpdate();
            
            retorno = true;
            
        }catch(Exception e){
            retorno = false;
        } finally {
            DBConnection.closeConnection(conexao);
        }
        
        return retorno;
    }
    
    public static ArrayList<Livro> listar(){
        return livros;
    }
}
