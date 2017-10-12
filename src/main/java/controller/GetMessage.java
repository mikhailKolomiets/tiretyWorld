package controller;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mihail on 12.10.17.
 */
@WebServlet(urlPatterns = "/get-message")
public class GetMessage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset = utf8");
        HttpSession session = req.getSession();

        String message = "";

        try{
            message = session.getAttribute("message").toString();
        } catch (Exception e) {

        }

        String outMessage = new Gson().toJson(message);
        resp.getWriter().write(outMessage);

    }
}
