package action;

import net.sf.json.JSONArray;
import org.json.JSONObject;
import page.SelectBooksInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by sunjing on 2015/11/17.
 */
public class getBooks extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SelectBooksInfo selectBooksInfo = new SelectBooksInfo();
        String currentPage = request.getParameter("pn");
        String numPerPage = request.getParameter("rn");
        String keyword = request.getParameter("keyword");
        JSONObject jsonObject = selectBooksInfo.selectBooksByCount(currentPage, numPerPage,keyword);
        /**
         * print result to HTML
         */
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
