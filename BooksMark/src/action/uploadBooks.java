package action;

import page.ParseFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by sunjing on 2015/12/2.
 */
public class uploadBooks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(filePath+"resources//bookmarks.json");
        ParseFile parseFile = new ParseFile();
        parseFile.readData(file);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
