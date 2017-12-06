package controller;

import com.google.gson.Gson;
import entity.Bank;
import service.BankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihail on 22.10.17.
 */
@WebServlet(urlPatterns = "/get-banking")
public class BankController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset = utf8");

        Bank bank = new BankService().findBankingByUserId(-7);

        String json = new Gson().toJson(bank);

        resp.getWriter().write(json);
    }
}
