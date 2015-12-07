package page;

import dao.DBinfo;
import data.BookMarksData;
import net.sf.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by sunjing on 2015/12/5.
 */
public class SearchMarks {
    public JSONObject search(){
        BookMarksData bookMarksData = new BookMarksData(DBinfo.USERNAME,DBinfo.PASSWORD);
        JSONArray jsonArray = null;
        JSONObject jsonObject = new JSONObject();
        List list = bookMarksData.searchMarks();
        if(null == list){
            jsonArray = JSONArray.fromObject("['data':'errno_1']");
            try {
                jsonObject.put("res",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
        jsonArray = JSONArray.fromObject(list);
        try {
            jsonObject.put("res",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
