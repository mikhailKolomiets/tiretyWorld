package controller.user;

import service.RegistrationService;
import entity.User;
import service.UserService;
import util.GameTimeUtil;
import util.MD5;
import util.MailSender;
import util.Variable;
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
@WebServlet(urlPatterns = "/registration/*")
public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            String code = req.getPathInfo().substring(1);

            RegistrationService service = new RegistrationService();
            entity.Registration registrationData = service.findRDByCode(code);

            if (registrationData == null) {
                req.setAttribute("message", "Житель уже регистрировался по данной ссылке");
            } else {
                UserService userService = new UserService();
                User user = userService.createUser(registrationData);
                service.deleteRD(registrationData);

                user.setPositionToGo(1000100);
                user.setPosition(1000100);

                String timeAtNow = GameTimeUtil.createGameTimeFromLDT(Variable.STARTTIME).toString();
                user.setHealth(timeAtNow);
                user.setHunger(timeAtNow);
                user.setTire(timeAtNow);
                user.setRegistrationTime(timeAtNow);

                user = userService.updateUser(user);// TODO need test!!

                req.setAttribute("user", user);

                resp.sendRedirect("/world.html");

            }

        } catch (Exception r) {
            resp.sendRedirect("/");
        }

    }

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
            sender.send(user.getEmail(), "Активация на тирети", "http://" + Variable.LOCALHOST + "/registration/" +
                    code + " <- Ссылка активации\nИмя: " + user.getName() + "\nПароль: " + user.getPassword());

            message = sender.getMessageOb();
            if (message.length() == 0)
                message = "На ваш email отправлена ссылка активации";

            entity.Registration registrationData = new entity.Registration();
            registrationData.setName(user.getName());
            try {
                registrationData.setEmail(user.getEmail());
                registrationData.setPassword(MD5.getMD5(user.getPassword()));
                registrationData.setCode(code);
                new RegistrationService().addRegistrationData(registrationData);
            } catch (Exception e) {
                message = e.getMessage();
            }

            req.setAttribute("goodMessage", message);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);

        } else {
            UserMirror userMirror = new UserMirror();
            userMirror.setName(user.getName());
            req.setAttribute("message", message);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        }

    }
}
