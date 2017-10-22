package controller.user;

import com.google.gson.Gson;
import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mihail on 18.10.17.
 */
@WebServlet(urlPatterns = "/get-userbeg")
public class GetUserbeg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json; charset=utf8");

        try {
            User user = (User) req.getSession().getAttribute("user");
            Map userBeg = new UserService().getUserbeg(user.getId());
            String toView = new Gson().toJson(userBeg);
            resp.getWriter().write(toView);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString() + " GetUserbeg 1");
        }


    }

}
