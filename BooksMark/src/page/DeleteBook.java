package page;

import dao.DBinfo;
import data.BooksInfoData;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by sunjing on 2015/12/5.
 */
public class DeleteBook {
    public JSONObject deleteBook(String id){
        BooksInfoData booksInfoData = new BooksInfoData(DBinfo.USERNAME,DBinfo.PASSWORD);
        JSONObject jsonObject = new JSONObject();
        Boolean isDeleted = booksInfoData.deleteBook(id);
        try {
            jsonObject.put("isdeleted",isDeleted);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
