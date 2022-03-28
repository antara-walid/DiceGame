package com.web.servlets;

import com.bo.Message;
import com.bo.User;
import com.web.helpers.IGameDataManagement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/pages/registrationForm.jsp");
        rd.forward(request,response);
        System.out.println("registrationServlet have being called");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // getting infos from registration page
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(firstName,lastName,login,password,0,0);


        // in this case context
        IGameDataManagement gameStorage = (IGameDataManagement)getServletContext().getAttribute("gameData");
        for (User user1 : gameStorage.getAllUsers())
        {
            if(user1.getLogin().equals(user.getLogin()))
            {
                String text = "login taken try another login";
                Message message = new Message(text,1);
                request.setAttribute("message",message);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/pages/registrationForm.jsp");
                rd.forward(request,response);
            }
        }

        try {
            gameStorage.insertUser(user);
            String text = "User have being added successfully ,you can Login now";
            Message message = new Message(text,0);
            request.setAttribute("message",message);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/pages/loginForm.jsp");
            rd.forward(request,response);
        }catch (Exception e)
        {
            System.out.println(e.getMessage() + "test");
        }






    }
}
