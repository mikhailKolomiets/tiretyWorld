package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mihail on 16.04.17.
 */
@WebServlet (urlPatterns = "/test")
public class TestController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        req.setAttribute("message", time);
        req.getRequestDispatcher("/test-page.jsp").forward(req, resp);
    }
}
