package university.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import university.core.dto.CrossTable;
import university.service.CrossTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "GroupServlet", urlPatterns = "/groups")
public class CrossServlet extends HttpServlet {

    private final CrossTableService crossTableService;
    private final ObjectMapper mapper;

    public CrossServlet() {
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .registerModule(new JavaTimeModule());
        this.crossTableService = CrossTableService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");


        PrintWriter writer = resp.getWriter();
        writer.write(mapper.writeValueAsString(crossTableService.readAll()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        CrossTable crossTable = mapper.readValue(req.getInputStream(), CrossTable.class);
        crossTableService.create(crossTable);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        CrossTable crossTable = mapper.readValue(req.getInputStream(), CrossTable.class);
        crossTableService.delete(crossTable);
    }
}
