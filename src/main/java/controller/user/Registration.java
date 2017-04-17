package controller.user;

import entity.User;
import util.MailSender;
import validation.RegistrationValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by mihail on 16.04.17.
 */
@WebServlet(urlPatterns = "/registration")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        String message = "";
        User user = new User();

        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            user = new User(name, password, email);
            message = new RegistrationValidation().check(user);
        } catch (Exception e) {
            message += e.getMessage();
        }

        if (message == null) {
            MailSender sender = new MailSender();
            String code = UUID.randomUUID().toString();
            sender.send(user.getEmail(), "Активация на тирети", "<a href=\"http://tirety-svu.rhcloud.com/registration/" +
                    code + "\">Ссылка активации</a>");


            req.setAttribute("goodMessage", "На ваш email отправлена ссылка активации");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            //todo registration


        } else {
            req.setAttribute("message", message);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        }


    }
}