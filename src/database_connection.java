import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database_connection {

  public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/pos_db";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}