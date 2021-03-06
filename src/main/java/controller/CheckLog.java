package controller;

import com.google.gson.Gson;
import entity.User;
import service.CounterServise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mihail on 10.10.17.
 */
@WebServlet(urlPatterns = "/checklog")
public class CheckLog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset = utf8");
        HttpSession session = req.getSession();
        User user = new User();

        try {
            user = (User) session.getAttribute("user");
        } catch (Exception e) {}

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

            String userOut = new Gson().toJson(user);
            resp.getWriter().write(userOut);

    }
}
