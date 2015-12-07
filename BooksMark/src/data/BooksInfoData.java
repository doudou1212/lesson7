package data;

import dao.BooksInfoDao;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by sunjing on 2015/12/2.
 */
public class BooksInfoData {
    private String username = null;
    private String password = null;
    public BooksInfoData(String username, String password){
        this.username = username;
        this.password = password;
    }
    public boolean createBooks(String bookname,String createtime){
        BooksInfoDao booksInfoDao = new BooksInfoDao(this.username,this.password);
        boolean isCreated = booksInfoDao.createBook(bookname,createtime);
        return isCreated;
    }
    public List selectBooksByCount(String currentPage,String numPerPage,String keyword){
        BooksInfoDao booksInfoDao = new BooksInfoDao(this.username,this.password);
        List list = booksInfoDao.selectBookByCount(currentPage, numPerPage,keyword);
        return list;
    }
    public String countBooks(String keyword){
        BooksInfoDao booksInfoDao = new BooksInfoDao(this.username,this.password);
        String bookNumbers = booksInfoDao.countBooks(keyword);
        return bookNumbers;
    }
    public boolean deleteBook(String id){
        BooksInfoDao booksInfoDao = new BooksInfoDao(this.username,this.password);
        boolean isDeleted = booksInfoDao.deleteBook(id);
        return isDeleted;
    }
}
