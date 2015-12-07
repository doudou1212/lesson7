package page;

import dao.DBinfo;
import data.BookMarksData;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sunjing on 2015/12/5.
 */
public class CreateBookMark {
    public JSONObject createMark(String bookname ,String bookurl){
        BookMarksData bookMarksData = new BookMarksData(DBinfo.USERNAME,DBinfo.PASSWORD);
        boolean isCreated = bookMarksData.createMarks(bookname,bookurl);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("isCreated",isCreated);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
}
