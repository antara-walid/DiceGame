package com.web.servlets;

import com.bo.GameState;
import com.bo.Message;
import com.bo.User;
import com.web.helpers.IGameDataManagement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "GServlet", value = "/GServlet")
public class GServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // we get the user session
        HttpSession session = request.getSession();

        // we get the user from session
        User user = (User) session.getAttribute("user");

        // we get the storage
        IGameDataManagement gameContext = (IGameDataManagement) getServletContext().getAttribute("gameData");

        // gameState contains all the infos of the game
        GameState gameSate = (GameState) session.getAttribute("gameState");

        // we get the dice number

        int diceNbr  = Integer.parseInt(request.getParameter("dice") );
        if(diceNbr >3)
        {
            gameSate.setGameOver(true);
        }

        int result = 0;

        if(user.getCompteurLancer()<3 && !gameSate.isGameOver())
        {
            // rolling the dice
            result = new Random().nextInt(6)+1;
            if((user.getCompteurLancer() == 0 && (result == 6 || result == 5)) || (user.getCompteurLancer() == 1 && result==6))
            {
                gameSate.setGameOver(true);
            }
            else
            {
                System.out.println(result);
                System.out.println("conteur: "+ user.getCompteurLancer());
                // we use result to display the dice image
                session.setAttribute("result",result);
                if(session.getAttribute("previousResult") != null)
                {
                    int previousResult = (Integer) session.getAttribute("previousResult");
                    if(previousResult < result)
                    {
                        //total += result;
                    }
                    else{
                        user.setScore(0);
                        gameSate.setGameOver(true);
                    }

                }

                //total +=result;
                session.setAttribute("previousResult",result);

                user.incrementLance();
                getServletContext().getRequestDispatcher("/WEB-INF/vues/back/userHome.jsp").forward(request, response);
            }



        }else
        {
            gameSate.setGameOver(true);
            //user.setScore(total);

        }


        if (gameSate.isGameOver()) {
            // we add the message indicating that the game is over
            gameSate.addMessage(new Message("Game Over", Message.INFO));

            // updating the best score
            if (user.getScore() > user.getBestScore()) {

                // putting the new best score in bestScore attribute
                user.setBestScore(user.getScore());

                // Updating the data in our storage
                gameContext.updateBestScore(user);
            }

            gameSate.setGameOver(false);
            result = 0;
            //total= 0;
            session.setAttribute("result",result);
            session.setAttribute("gameState",gameSate);
            session.setAttribute("user",user);
            getServletContext().getRequestDispatcher("/ReinitGameServlet").forward(request, response);
            return;
        }




    }
}
