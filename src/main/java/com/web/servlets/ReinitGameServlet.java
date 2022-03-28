package com.web.servlets;

import com.bo.GameState;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ReinitGameServlet", value = "/back/ReinitGameServlet")
public class ReinitGameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        GameState gs = (GameState) session.getAttribute("gameState");
        session.setAttribute("result",0);
        if (gs != null) {

            gs.reinit();
        }

        // reinit old result avec une valeur impossible pour le dé
        session.setAttribute("previousResult", -1);
        session.setAttribute("result",0);

        getServletContext().getRequestDispatcher("/WEB-INF/vues/back/userHome.jsp").forward(request, response);

        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
