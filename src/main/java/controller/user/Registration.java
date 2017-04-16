package controller.user;

import entity.User;
import validation.RegistrationValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihail on 16.04.17.
 */
@WebServlet(urlPatterns = "/registration")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = "";

        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            User user = new User(name, password, email);
            message = new RegistrationValidation().check(user);
        }
        catch (Exception e) {
            message += e.getMessage();
        }

        if(message == null) {
            req.setAttribute("message", "все ок");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
            //todo registration
        } else {
            req.setAttribute("message", message);
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        }


    }
}
