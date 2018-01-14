package controller.map;

import entity.User;
import entity.locations.IronMind;
import service.MapService;
import service.locations.IronMindService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mihail on 16.12.17.
 */
@WebServlet(urlPatterns = "/update-iron-mind")
public class UpdateIronMind extends HttpServlet {

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
                int iron = Integer.valueOf(req.getParameter("iron"));
                int silver = Integer.valueOf(req.getParameter("silver"));
                int gold = Integer.valueOf(req.getParameter("gold"));

                IronMind ironMind = new IronMind();
                ironMind.setIronOre(iron);
                ironMind.setSilverOre(silver);
                ironMind.setGoldOre(gold);
                ironMind.setPosition(1040102);

                new IronMindService().updateIronMindByCoordinate(ironMind);
                resp.sendRedirect("/world.html");
            }
        } catch (Exception e) {
            resp.sendRedirect("/world.html");
        }
    }
}
