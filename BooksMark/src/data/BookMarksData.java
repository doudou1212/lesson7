package data;

import dao.BookMarksDao;
import dao.BooksInfoDao;

import java.util.List;

/**
 * Created by sunjing on 2015/12/5.
 */
public class BookMarksData {
    private String username = null;
    private String password = null;
    public BookMarksData(String username, String password){
        this.username = username;
        this.password = password;
    }
    public boolean createMarks(String bookname,String url){
        BookMarksDao bookMarksDao = new BookMarksDao(this.username,this.password);
        boolean isCreated = bookMarksDao.createBookMark(bookname,url);
        return isCreated;
    }
    public List searchMarks(){
        BookMarksDao bookMarksDao = new BookMarksDao(this.username,this.password);
        List list = bookMarksDao.searchMarks();
        return list;
    }
}
