package controller.map;

import entity.Map;
import entity.User;
import service.MapService;
import service.UserService;
import util.UserRunUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by mihail on 15.10.17.
 */
@WebServlet(urlPatterns = "/go-to/*")
public class GoToPosition extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf8");
        HttpSession session = req.getSession();

        try {
            User user = (User) session.getAttribute("user");
            if (user == null)
                resp.sendRedirect("/");

            else {
                UserService userService = new UserService();
                if (user.getPositionToGo() != user.getPosition()) {
                    if (LocalDateTime.now().isAfter(LocalDateTime.parse(user.getWentTime()))) {
                        user.setPosition(user.getPositionToGo());
                        userService.updateUser(user);
                    }
                } else {
                    int goTo = Integer.valueOf(req.getPathInfo().substring(1));

                    user.setPositionToGo(goTo);
                    long distance = UserRunUtil.countDistance(user.getPosition(), goTo);
                    LocalDateTime time = LocalDateTime.now();

                    user.setWentTime(time.plusSeconds(distance * 5).toString());
                    new UserService().updateUser(user);
                }

                session.setAttribute("user", user);
                resp.sendRedirect("/world.html");
            }

        } catch (Exception e) {
            System.out.println(e.toString() + " GoToPosition 1");
        }

    }
}
