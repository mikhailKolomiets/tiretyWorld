package controller.map;

import entity.User;
import service.MapService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mihail on 13.10.17.
 */
@WebServlet(urlPatterns = "/add-map-location")
public class CreateLocation extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf8");

        HttpSession session = req.getSession();
        User user;

        try {
            user = (User) session.getAttribute("user");
            if (!user.getName().equals("test"))
                resp.sendRedirect("/world.html");
            else {
                String name = req.getParameter("name");
                int position = Integer.valueOf(req.getParameter("position"));
                System.out.println(name + "CL1"); // todo FT
                new MapService().addLocation(name, position);
                resp.sendRedirect("/world.html");
            }
        } catch (Exception e) {
            resp.sendRedirect("/world.html");
        }

    }
}
