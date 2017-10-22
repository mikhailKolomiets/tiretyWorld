package controller.cropland;


import entity.User;
import entity.items.Culture;
import entity.items.Userbeg;
import entity.locations.Cropland;
import service.UserService;
import service.items.CultureService;
import service.locations.CroplandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mihail on 22.10.17.
 */
@WebServlet(urlPatterns = "/harvest/*")
public class Harvest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long croplandId = Long.valueOf(req.getPathInfo().substring(1));

        User user = new User();

        try {
            user = (User) req.getSession().getAttribute("user");
        } catch (Exception e) {
            resp.sendRedirect("/");
        }

        CroplandService croplandService = new CroplandService();
        CultureService cultureService = new CultureService();
        UserService userService = new UserService();

        try {
            Cropland cropland = croplandService.getCroplandById(croplandId);

            if (cropland.getUserId() != user.getId())
                resp.sendRedirect("/");
            else if (LocalDateTime.parse(cropland.getTimeDown()).isAfter(LocalDateTime.now())) {
                resp.sendRedirect("/world.html");
            } else {
                Culture culture = cultureService.getCultureById(cropland.getCultureId());
                Userbeg userbeg = userService.getUserbegByUserId(user.getId());

                TreeMap mappedBeg = new TreeMap<String, Integer>();

                if (userbeg.getItems() != null) {
                    mappedBeg = userService.getUserbegByUserId(user.getId()).getItems();
                }


                int qt = mappedBeg.containsKey(culture.getName()) ? (int) mappedBeg.get(culture.getName()) : 0;
                qt += culture.getBaseRise();
                mappedBeg.put(culture.getName(), qt);

                userbeg.setItems(mappedBeg);
                userService.updateUserbeg(userbeg);

                cropland.setItemName("");
                cropland.setTimeDown("");
                cropland.setUserId(0);
                cropland.setCultureId(0);

                croplandService.refrashCropland(cropland);

                //todo timing rise

                resp.sendRedirect("/world.html");
            }
        } catch (Exception e) {
            System.out.println(e.toString() + " Harvest 1");
            e.printStackTrace();
            resp.sendRedirect("/");
        }

    }
}
