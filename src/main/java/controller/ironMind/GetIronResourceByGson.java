package controller.ironMind;

import com.google.gson.Gson;
import entity.User;
import entity.locations.IronMind;
import service.locations.IronMindService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihail on 16.12.17.
 */
@WebServlet(urlPatterns = "/getOre")
public class GetIronResourceByGson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json; charset= utf8");
        try {
            User user = (User) req.getSession().getAttribute("user");

            IronMind ironMind = new IronMindService().findIronMindByCoordinate(user.getPosition());

            String findLocation = new Gson().toJson(ironMind);
            resp.getWriter().write(findLocation);
        } catch (Exception e) {
            System.out.println(e.toString() + " GetIronResourceByGson 1");
        }
    }
}
