package controller.user;

import entity.User;
import service.UserService;
import validation.LoginValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mihail on 11.10.17.
 */
@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf8");
        String validateResult = "exp";
        User user = new User();


        try {
            String name = req.getParameter("name");
            String pass = req.getParameter("pass");
            user = new User(name, pass, name);

            validateResult = new LoginValidation().check(user);

        } catch (Exception e) {
            System.out.println(e.toString() + " Login 1");//todo FT
        }

        HttpSession session = req.getSession();

        if (validateResult == null) {
            session.setAttribute("message", null);
            User sessionUser = new UserService().findUserByEmail(user.getEmail());
            if(sessionUser == null)
                sessionUser = new UserService().findUserByName(user.getName());

            session.setAttribute("user", sessionUser);

            resp.sendRedirect("/world.html");
        } else {
            session.setAttribute("message", validateResult);
            resp.sendRedirect("/");
        }

    }
}
