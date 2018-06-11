package cobaia.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;

import cobaia.persistencia.AbstractDAO;

public class UsuarioDAO extends AbstractDAO<Usuario> {
    public void create(Usuario o) {
       try(Connection con = super.openConnection()){
           String sql = "INSERT INTO usuarios (nome, email, senha, token) VALUES (?, ?, ?, ?)";

           PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setString(1,o.getNome());
           preparedStatement.setString(2, o.getEmail());
           preparedStatement.setString(3, DigestUtils.md5Hex(o.getSenha()));
           preparedStatement.setString(4, o.getToken());
           preparedStatement.executeUpdate();
           ResultSet rs = preparedStatement.getGeneratedKeys();
           while(rs.next()){
               System.out.println(rs.getInt("id"));
           }

       } catch (SQLException e) {
           e.printStackTrace();
       }


    }

    public void delete(int id) {
        String sql = "Delete from usuarios where id=?";
        try(Connection con = super.openConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Usuario o) {
        try(Connection con = super.openConnection()){
            String sql = "UPDATE usuarios set nome=?, email=?, senha=?, token=? where id=?";

            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,o.getNome());
            preparedStatement.setString(2, o.getEmail());
            preparedStatement.setString(3, DigestUtils.md5Hex(o.getSenha()));
            preparedStatement.setString(4, o.getToken());
            preparedStatement.setInt(5, o.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
