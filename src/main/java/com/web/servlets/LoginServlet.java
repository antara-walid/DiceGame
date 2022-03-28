package com.web.servlets;

import com.bo.GameState;
import com.bo.Message;
import com.bo.User;
import com.web.helpers.IGameDataManagement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        IGameDataManagement gameData = (IGameDataManagement) getServletContext().getAttribute("gameData");
        User user = gameData.getUserByLogin(login);
        if( user != null)
        {
            if(user.getPassword() != null && user.getPassword().equals(password))
            {
                // game state is responsible for the game we added the user to game state
                GameState gameSate = new GameState(user);
                request.getSession().setAttribute("gameState", gameSate);

                // we add user to session
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("previousResult", -1);

                request.getSession().setAttribute("result",0);
                //  we redirect user to UserHome
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/back/userHome.jsp");
                rd.forward(request,response);
            }
            else
            {
                String  text = "login or password is invalid";
                Message message = new Message(text,1);
                request.setAttribute("message",message);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/pages/loginForm.jsp");
                rd.forward(request,response);
            }
        }else
        {
            String  text = "login or password is invalid";
            Message message = new Message(text,1);
            request.setAttribute("message",message);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/pages/loginForm.jsp");
            rd.forward(request,response);
        }
    }
}
