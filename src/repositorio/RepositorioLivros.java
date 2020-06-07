package repositorio;

import classes.DBConnection;
import classes.Livro;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author manoel.neto
 */
public class RepositorioLivros {
    
    private static Connection conexao;
    private static PreparedStatement sql;
    
    public static boolean adicoinar(Livro livro){
        boolean retorno = false;
        try{
            conexao = DBConnection.openConnection();
            
            sql = conexao.prepareStatement(
                    "INSERT INTO livros (titulo,ano,editora,autor,tem,edicao,serie,imagem) VALUES ("
                            + "'" + livro.getTitulo() + "', "
                            + "'" + livro.getAno()+ "', "
                            + "'" + livro.getEditora()+ "', "
                            + "'" + livro.getAutor()+ "', "
                            + "'" + livro.getTem()+ "', "
                            + "'" + livro.getEdicao()+ "', "
                            + "'" + livro.getSerie()+ "',"
                            + "'" + livro.getImagem() + "')"
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
    
    public static boolean atualizar(Livro livro){
        boolean retorno = false;
        try{
            conexao = DBConnection.openConnection();
            
            sql = conexao.prepareStatement(
                    "UPDATE livros SET "
                            + "titulo = '" + livro.getTitulo() + "', "
                            + "ano = '" + livro.getAno() + "', "
                            + "editora = '" + livro.getEditora() + "', "
                            + "autor = '" + livro.getAutor() + "', "
                            + "tem = '" + livro.getTem() + "', "
                            + "edicao = '" + livro.getEdicao() + "', "
                            + "serie = '" + livro.getSerie() + "', "
                            + "imagem = '" + livro.getImagem() + "' "
                     + "WHERE id = '" + livro.getId() + "'"
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
    
    public static ArrayList<Livro> listar(String pesquisa){
        ArrayList<Livro> livros = new ArrayList<Livro>();
        try{
            conexao = DBConnection.openConnection();
            
            if(pesquisa.isEmpty()){
                sql = conexao.prepareStatement("SELECT * FROM livros");
            }else{
                sql = conexao.prepareStatement("SELECT * FROM livros WHERE "
                        + "titulo LIKE '%"+pesquisa+"%' OR "
                        + "autor LIKE '%"+pesquisa+"%' OR "
                        + "editora LIKE '%"+pesquisa+"%' OR "
                        + "serie LIKE '%"+pesquisa+"%'"
                );
            }
            
            ResultSet rs = sql.executeQuery();
            
            while(rs.next()){
                livros.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("ano"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        rs.getString("serie"),
                        rs.getString("edicao"),
                        rs.getInt("tem"),
                        rs.getString("imagem")
                ));
            }
            
        }catch(Exception e){
            livros = null;
        } finally {
            DBConnection.closeConnection(conexao);
        }
        
        return livros;
    }
}
