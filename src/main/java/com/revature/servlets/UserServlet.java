package com.revature.servlets;

import com.revature.pojos.User;
import com.revature.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("User servlet initializing...");
        this.service = new UserService();
        this.mapper = new ObjectMapper();
        System.out.println("User servlet initialization successful");
    }
    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");

        if(param == null){
            List<User> userList = service.getListUsers();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        }else{
            Integer userID = Integer.parseInt(req.getParameter("user-id"));

            User user = service.getUser(userID);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }

        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }

        String json = builder.toString();
        User newUser = mapper.readValue(json, User.class);
        service.saveUser(newUser);

        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");
        Integer userId = Integer.parseInt(param);
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while (buffer.ready()){
            builder.append(buffer.readLine());
        }

        String json = builder.toString();
        User updateUser = mapper.readValue(json, User.class);

        service.updateUser(updateUser, userId);

        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");
        Integer userId = Integer.parseInt(param);
        service.deleteUser(userId);

        resp.setStatus(200);
        resp.setContentType("Application/Json, Charset=UTF-8");
    }
}