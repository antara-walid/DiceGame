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
import java.util.Random;

@WebServlet(name = "BestScoreServlet", value = "/back/BestScoreServlet")
public class BestScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IGameDataManagement contextGame = (IGameDataManagement) getServletContext().getAttribute("gameData");

        // On récupére tous les utilisateurs
        List<User> users = contextGame.getAllUsers();

        // On stocke dans la requete (comme attribut) les utilisateurs. Cette
        // liste a une durée de vie = à la durée de vie de la requete. Donc elle
        // n'existera plus à la fin du cycle de vie de la requete
        request.setAttribute("users", users);

        // On redirige vers la vue (redirection coté serveur)
        getServletContext().getRequestDispatcher("/WEB-INF/vues/back/bestScore.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }
}
