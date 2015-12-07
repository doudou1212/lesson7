package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by sunjing on 2015/12/2.
 */
public class ConnectDB {
    private Connection conn = null;
    public ConnectDB(String username,String password,String db){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/book" ;
            System.out.println("Connecting to database " + url);
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public  Connection getConn(){
        return this.conn;
    }

}
