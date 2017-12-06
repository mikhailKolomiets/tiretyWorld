package controller.cropland;

import entity.User;
import entity.locations.Cropland;
import service.locations.CroplandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihail on 15.10.17.
 */
@WebServlet(urlPatterns = "/add-cropland")
public class AddNewCropland extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            Cropland cropland = new Cropland();
            cropland.setPosition(user.getPosition());
            new CroplandService().addCropland(cropland);

        } catch (Exception e) {
            System.out.println(e.toString() + " AddNewCropland");
        }
        resp.sendRedirect("/world.html");
    }
}
