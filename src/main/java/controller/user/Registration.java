package controller.user;

import service.RegitrationService;
import entity.User;
import util.MD5;
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
        } catch (Throwable e) {
            message += e.getMessage();
        }

        if (message == null) {
            MailSender sender = new MailSender();
            String code = UUID.randomUUID().toString();
            sender.send(user.getEmail(), "Активация на тирети", "http://tirety-svu.rhcloud.com/registration/" +
                    code + " <- Ссылка активации");

            message = sender.getMessageOb();
            if(message.length() == 0)
                message = "На ваш email отправлена ссылка активации";

            entity.Registration registrationData = new entity.Registration();
            registrationData.setName(user.getName());
            try {
                registrationData.setPassword(MD5.getMD5(user.getPassword()));
                new RegitrationService().addRegistrationData(registrationData);
            } catch (Exception e) {
                message = e.getMessage();
            }

            req.setAttribute("goodMessage", message);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            //todo registration
            //new RegitrationService().addRegistrationData();


        } else {
            UserMirror userMirror = new UserMirror();
            userMirror.setName(user.getName());
            req.setAttribute("message", message);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        }




    }
}
