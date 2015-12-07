package page;

import dao.DBinfo;
import data.BooksInfoData;
import net.sf.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunjing on 2015/12/3.
 */
public class SelectBooksInfo {
    public JSONObject selectBooksByCount(String currentPage,String numPage,String keyword){
        BooksInfoData booksInfoData = new BooksInfoData(DBinfo.USERNAME,DBinfo.PASSWORD);
        JSONArray jsonArray = null;
        JSONObject jsonObject = new JSONObject();
        List list = booksInfoData.selectBooksByCount(currentPage,numPage,keyword);
        String bookNumbers = booksInfoData.countBooks(keyword);
        String pages = Integer.toString(((new Integer(bookNumbers))-1)/10+1);
        if(null == list || null == pages){
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
            jsonObject.put("totalPages",pages);
            jsonObject.put("bookNumbers",bookNumbers);
            jsonObject.put("res",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
