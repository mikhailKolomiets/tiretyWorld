package controller;

import util.GameTimeUtil;
import util.Variable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihail on 28.10.17.
 */
@WebServlet(urlPatterns = "/getGameTime")
public class GetGameTimeAsGson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset = utf8");
        String gameTime = "{\"gameTime\":\"" + GameTimeUtil.createGameTimeFromLDT(Variable.STARTTIME) + "\"}";
        resp.getWriter().write(gameTime);
    }
}
