package controller.map;

import com.google.gson.Gson;
import entity.Map;
import entity.User;
import service.MapService;
import util.UserRunUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by mihail on 14.10.17.
 */
@WebServlet(urlPatterns = "/get-locations")
public class GetLocations extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset= utf8");
        try {
            User user = (User) req.getSession().getAttribute("user");
            List<Map> locations = new MapService().getLocations(user.getPosition(), 6);

            for (Map location : locations) {
                int distance = UserRunUtil.countDistance(user.getPosition(), location.getPosition());
                location.setSecondGo(distance * 5);
            }

            String findLocation = new Gson().toJson(locations); // todo user speed (6)
            resp.getWriter().write(findLocation);
        } catch (Exception e) {
            System.out.println(e.toString() + " GetLocation 1");
        }
    }
}
