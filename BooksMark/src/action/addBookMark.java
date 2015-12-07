package action;

import org.json.JSONException;
import org.json.JSONObject;
import page.CreateBookMark;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sunjing on 2015/12/5.
 */
public class addBookMark extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreateBookMark createBookMark = new CreateBookMark();
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        JSONObject jsonObject = null;
        if(name.length()<1 || url.length() < 1){
            try {
                jsonObject.put("isCreated","false");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            jsonObject = createBookMark.createMark(name,url);
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.flush();
        out.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
