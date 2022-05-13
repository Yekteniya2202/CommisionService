package ru.babaev.Urls;


import ru.babaev.Controllers.ComissionController;
import ru.babaev.Models.Response.ComissionResponse;
import ru.babaev.Models.Response.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getComission")
public class ComissionEndPoint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        try {
            ComissionController comissionController = new ComissionController();
            var comission = comissionController.read(req.getParameter("card"));
            Response response = new ComissionResponse(comission, 200, "OK");
            resp.getWriter().write(response.serialize());

        }
        catch (Exception e){
            Response response = new ComissionResponse(null, 403, e.getMessage());
            resp.getWriter().write(response.serialize());
        }
    }

}
