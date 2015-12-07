package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunjing on 2015/12/2.
 */
public class BooksInfoDao {
    private Connection conn = null;
    public BooksInfoDao(String username, String password){
        this.conn = (new ConnectDB(username,password,"book")).getConn();
    }
    public boolean createBook(String bookname,String createtime){
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
        String sql = "INSERT INTO booksinfo(title,create_time,is_deleted) VALUES ('"+bookname+"' ,"+new Integer(createtime)+",0);";
        try {
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public List selectBookByCount(String currentPage,String numPerPage,String keyword){
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
        String sql = null;
        System.out.println(keyword);
        if(null == keyword || keyword.length()<1){
            sql = "SELECT id,title,create_time from booksinfo where is_deleted=0 limit "+currentPage+"0"+", "+numPerPage;
        }
        else {
            sql = "SELECT id,title,create_time from booksinfo where title LIKE '%"+keyword+"%' and is_deleted=0 limit "+currentPage+"0"+", "+numPerPage;
        }
        ResultSet rs = null;
        List<Map> list = new ArrayList<Map>();
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                String id = Integer.toString(rs.getInt("id"));
                String title = rs.getString("title");
                String ctime = rs.getString("create_time");
                Map map = new HashMap();
                map.put("title",title);
                map.put("created", ctime);
                map.put("id",id);
                list.add(map);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String countBooks(String keyword){
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
        String sql = null;
        if(null == keyword || keyword.length()<1){
            sql = "SELECT count(id) as cn from booksinfo where is_deleted=0";
        }
        else{
            sql = "SELECT count(id) as cn from booksinfo where title LIKE '%"+keyword+"%' and is_deleted=0";
        }
        String bookNumbers = null;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                bookNumbers = Integer.toString(rs.getInt("cn"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookNumbers;
    }
    public boolean deleteBook(String id){
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
        String sql = "UPDATE booksinfo set is_deleted=1 where id="+id;
        try {
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
