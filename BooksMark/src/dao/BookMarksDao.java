package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by sunjing on 2015/12/5.
 */
public class BookMarksDao {
    private Connection conn = null;
    public BookMarksDao(String username, String password){
        this.conn = (new ConnectDB(username,password,"book")).getConn();
    }
    public boolean createBookMark(String bookname,String url){
        if(null == this.conn){
            System.out.println("the connector of DB:Books is null");
            return false;
        }
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(null == stmt){
            System.out.println("the stmt is null");
            return false;
        }
        String sql = "INSERT INTO bookmarks (title,url,create_time,is_deleted) VALUES ('"+bookname+"' , '"+url+"', "+(new Date()).getTime()/1000+",0);";
        try {
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public List searchMarks(){
        if(null == this.conn){
            System.out.println("the connector of DB:Books is null");
            return  null;
        }
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(null == stmt){
            System.out.println("the stmt is null");
            return null;
        }
        String sql = "SELECT title,url from bookmarks ORDER BY create_time DESC ";

        ResultSet rs = null;
        List<Map> list = new ArrayList<Map>();
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                String title = rs.getString("title");
                String url = rs.getString("url");
                Map map = new HashMap();
                map.put("title",title);
                map.put("url", url);
                list.add(map);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
