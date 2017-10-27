package controller;

import service.CounterServise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihail on 27.10.17.
 */
@WebServlet(urlPatterns = "/counter")
public class CounterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset = utf8");

        CounterServise counterServise = new CounterServise();
        boolean isTic = false;
        int countAll = 0, todayCount = 0;
        try {
            isTic = counterServise.setCountByIp(req.getRemoteAddr());
            countAll = counterServise.getAllCount();
            todayCount = counterServise.getTodayCount();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString() + " CheckLog");
        }
        System.out.println(" Tic: " + isTic + " url: " + req.getRemoteAddr() + " All count: " + countAll + " and today count: "
                + todayCount);

        String json ="{\"all\" : " + countAll + ", \"today\" : " + todayCount + "}";

        resp.getWriter().write(json);
    }
}
