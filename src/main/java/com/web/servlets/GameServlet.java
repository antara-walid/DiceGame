package com.web.servlets;

import com.bo.GameState;
import com.bo.Message;
import com.bo.User;
import com.web.helpers.IGameDataManagement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(name = "GameServlet", value = "/back/GameServlet")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
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
        int result;


        if(gameSate.isGameOver() == true)
        {
            // updating the best score
            if (user.getScore() > user.getBestScore()) {

                // putting the new best score in bestScore attribute
                user.setBestScore(user.getScore());

                // Updating the data in our storage
                gameContext.updateBestScore(user);
            }

            gameSate.setGameOver(false);


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/back/gameOver.jsp");
            rd.forward(request,response);
            return;

        }
        else {

            if (diceNbr > 3) {
                gameSate.setGameOver(true);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/back/gameOver.jsp");
                rd.forward(request,response);
                return;
            }

            // checking if the dice was chosen before
            ArrayList<Integer> dices = (ArrayList<Integer>) gameSate.getDices();

                for (int i : dices) {
                    if (i == diceNbr) {
                        System.out.println("dice :"+ i);
                        gameSate.setGameOver(true);
                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/back/gameOver.jsp");
                        rd.forward(request,response);
                        break;
                    }
                }
            dices.add(diceNbr);
            System.out.println("dicenbr" + diceNbr);



            result = 0;


                // rolling the dice
                result = new Random().nextInt(6) + 1;

                if ( (user.getCompteurLancer() == 0 && (result == 6 || result == 5) ) || ( (user.getCompteurLancer() == 1 && result == 6))) {
                    gameSate.setGameOver(true);
                    session.setAttribute("result", result);
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/back/gameOver.jsp");
                    rd.forward(request,response);
                }
                else {
                    System.out.println("conteur: " + user.getCompteurLancer());
                    System.out.println(result);

                    // we use result to display the dice image
                    session.setAttribute("result", result);
                        int previousResult = (Integer) session.getAttribute("previousResult");
                        if (previousResult < result) {
                            gameSate.setTotal(gameSate.getTotal() + result);
                        }else
                        {
                            user.setScore(0);
                            gameSate.setGameOver(true);
                            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/back/gameOver.jsp");
                            rd.forward(request,response);
                            return;
                        }
                        session.setAttribute("previousResult", result);

                        user.incrementLance();

                    if(user.getCompteurLancer()> 2)
                    {
                        gameSate.setGameOver(true);
                        try{
                            user.setScore(gameSate.getTotal());
                        }catch (Exception e)
                        {
                            System.out.println("this one causes an error");
                        }
                        getServletContext().getRequestDispatcher("/back/GameServlet").forward(request, response);
                        System.out.println("total is "+ gameSate.getTotal());
                    }
                    getServletContext().getRequestDispatcher("/WEB-INF/vues/back/userHome.jsp").forward(request, response);


                }


            }

        }


}
