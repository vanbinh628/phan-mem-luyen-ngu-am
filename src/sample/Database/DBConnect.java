package sample.Database;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/englishmovie?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8", "root", "root");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
//    public static void main(String[] args) {
//        System.out.println(getConnection());
//    }

}
