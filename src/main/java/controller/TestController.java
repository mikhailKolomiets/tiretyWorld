package controller;

import service.TestService;

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
@WebServlet (urlPatterns = {"/test", "/test2"})
public class TestController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        try {
            TestService service = new TestService();
            service.setTestNumber("3");
        } catch (Exception e) {
            time += " - "+e.getMessage();
        }
        req.setAttribute("message", time);
        req.getRequestDispatcher("/test-page.jsp").forward(req, resp);
    }
}
