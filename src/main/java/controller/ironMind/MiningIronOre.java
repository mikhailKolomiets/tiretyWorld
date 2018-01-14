package controller.ironMind;

import com.google.gson.Gson;
import entity.User;
import entity.items.Userbeg;
import entity.locations.Cropland;
import entity.locations.IronMind;
import service.UserService;
import service.locations.CroplandService;
import service.locations.IronMindService;
import util.GameTimeUtil;
import util.Variable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 * Created by mihail on 17.12.17.
 */
@WebServlet(urlPatterns = "/mineIronOre")
public class MiningIronOre extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset = utf8");
        String responseMassage = "Еще собирать 1 час";
        String json;
        try {

            User user = (User) req.getSession().getAttribute("user");

            Cropland cropland = new Cropland();
            cropland.setPosition(user.getPosition());
            cropland.setUserId(user.getId());

            CroplandService service = new CroplandService();

            cropland = service.getCroplandByIdAndCoordinate(cropland);

            LocalDateTime nowTime = GameTimeUtil.createGameTimeFromLDT(Variable.STARTTIME);

            LocalDateTime foulTime = nowTime.plusHours(5);
            LocalDateTime timeFromBase;

            LocalDateTime miningTime = nowTime.plusHours(1);
            if (cropland.getId() == 0) {
                cropland.setTimeDown(miningTime.toString());
                service.refrashCropland(cropland);
                timeFromBase = miningTime;
            } else {
                timeFromBase = LocalDateTime.parse(cropland.getTimeDown());
            }

            if (timeFromBase.isBefore(nowTime) && timeFromBase.isBefore(foulTime)) {

                int percent = (int) (Math.random() * 100);
                boolean successMind = (int) (Math.random() * 100) > 50;

                if (successMind) {

                    IronMindService ironMindService = new IronMindService();
                    UserService userService = new UserService();

                    IronMind ironMind = ironMindService.findIronMindByCoordinate(user.getPosition());

                    Userbeg userbeg = userService.getUserbegByUserId(user.getId());
                    TreeMap items = userbeg.getItems();

                    if (percent < 10 && ironMind.getGoldOre() > 0) {
                        int qt = items.containsKey("Золотая руда") ? (int) items.get("Золотая руда") : 0;
                        items.put("Золотая руда", qt + 1);
                        userbeg.setItems(items);
                        ironMind.setGoldOre(ironMind.getGoldOre() - 1);
                        responseMassage = "Добылась золотая руда, добываем дальше";
                    } else if (percent < 30 && ironMind.getSilverOre() > 0) {
                        responseMassage = "Добылась серебряная руда, добываем дальше";
                        int qt = items.containsKey("Серебряная руда") ? (int) items.get("Серебряная руда") : 0;
                        items.put("Серебряная руда", qt + 1);
                        userbeg.setItems(items);
                        ironMind.setSilverOre(ironMind.getSilverOre() - 1);
                    } else if (ironMind.getIronOre() > 0) {
                        responseMassage = "Добылась железная руда, добываем дальше";
                        int qt = items.containsKey("Железная руда") ? (int) items.get("Железная руда") : 0;
                        items.put("Железная руда", qt + 1);
                        userbeg.setItems(items);
                        ironMind.setIronOre(ironMind.getIronOre() - 1);
                    }
                    ironMindService.updateIronMindByCoordinate(ironMind);
                    userService.updateUserbeg(userbeg);

                } else {
                    responseMassage = "Ничего не добылось, добываем дальше";
                }

                cropland.setTimeDown(miningTime.toString());
                service.refrashCropland(cropland);
                // mining


            } else if (timeFromBase.isAfter(foulTime) && cropland.getId() != 0) {
                responseMassage = "Началась добыча руды";
                cropland.setTimeDown(miningTime.toString());
                service.refrashCropland(cropland);
            } else if (timeFromBase.isAfter(nowTime)) {
                responseMassage = "Окончание добычи в " + timeFromBase.getHour() + " : " + timeFromBase.getMinute();
            }

            json = "{\"harvMessage\":\"" + responseMassage + "\"}";
        } catch (Exception e) {
            System.out.println(e.toString() + " MiningIronOre 1");
            json = "{\"harvMessage\":\"" + e + "\"}";
        }
        resp.getWriter().write(json);
    }

}

