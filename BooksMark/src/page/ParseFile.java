package page;

import dao.BooksInfoDao;
import dao.DBinfo;
import data.BooksInfoData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by sunjing on 2015/12/2.
 */
public class ParseFile {
    public void readData(File file){
        /**
         * read all content form source file
         */
        FileInputStream in = null;
        byte[] content = new byte[0];
        try {
            in = new FileInputStream(file);
            Long filelength = file.length();
            content = new byte[filelength.intValue()];
            in.read(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * parse json
         */
        JSONArray jsonArray = null;
        try {
            String books = new String(content,"utf-8");
            jsonArray = new JSONArray(books);
            BooksInfoData booksInfo = new BooksInfoData(DBinfo.USERNAME,DBinfo.PASSWORD);
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject result = jsonArray.getJSONObject(i);
                String title = result.get("title").toString();
                String ctime = result.get("created").toString();
                booksInfo.createBooks(title,ctime);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
