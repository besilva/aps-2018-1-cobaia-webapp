package cobaia.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cobaia.modelo.AbstractModel;


public abstract class AbstractDAO<T extends AbstractModel> {
    private final  static String URL ="jdbc:hsqldb:database/files/mochinho", USER = "SA", PASSWORD ="";

    public abstract void create(T o);
    public abstract void delete(int id);
    public abstract void update(T o);
    public Connection openConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        return  con;
    }



}
