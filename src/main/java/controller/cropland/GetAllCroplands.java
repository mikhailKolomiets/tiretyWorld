package controller.cropland;

import com.google.gson.Gson;
import service.locations.CroplandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mihail on 19.10.17.
 */
@WebServlet(urlPatterns = "/get-all-croplands")
public class GetAllCroplands extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json ; charset = utf8");

        ArrayList croplands = (ArrayList) new CroplandService().getAllCroplands();
        String json = new Gson().toJson(croplands);

        resp.getWriter().write(json);

    }
}
