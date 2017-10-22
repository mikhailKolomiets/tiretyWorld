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
import java.util.TreeMap;

/**
 * Created by mihail on 19.10.17.
 * landing/xxyyy xx - cultureId y - croplandId
 */

@WebServlet(urlPatterns = "/landing/*")
public class Landing extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CroplandService croplandService = new CroplandService();
        CultureService cultureService = new CultureService();
        UserService userService = new UserService();


        try {//todo check present culture! >0 qt

            User user = (User) req.getSession().getAttribute("user");

            if (user == null) {
                resp.sendRedirect("/");
            } else {

                String request = req.getPathInfo().substring(1);

                long croplandId = Long.valueOf(request.substring(2));
                long cultureId = Long.valueOf(request.substring(0, 2));

                Culture culture = cultureService.getCultureById(cultureId);
                Userbeg userbeg = userService.getUserbegByUserId(user.getId());

                int qtPresent = userbeg.getItems().get(culture.getName());

                if (qtPresent > 0) {
                    TreeMap<String, Integer> usersCulture = userbeg.getItems();
                    qtPresent--;
                    usersCulture.put(culture.getName(), qtPresent);
                    userbeg.setItems(usersCulture);

                    LocalDateTime agingTime = LocalDateTime.now().plusSeconds(culture.getTimeRisePerSecond());

                    Cropland cropland = croplandService.getCroplandById(croplandId);

                    cropland.setItemName(culture.getName());
                    cropland.setUserId(((User) req.getSession().getAttribute("user")).getId());//todo redirect before
                    cropland.setCultureId(cultureId);
                    cropland.setTimeDown(agingTime.toString());
                    userService.updateUserbeg(userbeg);
                    croplandService.refrashCropland(cropland);
                }
                resp.sendRedirect("/world.html");
            }
        } catch (Exception e) {
            System.out.println(e.toString() + " Landing 1");
            e.printStackTrace();
            resp.sendRedirect("/world.html");
        }

    }
}