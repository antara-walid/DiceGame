package com.web.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeconnectServlet", value = "/DeconnectServlet")
public class DeconnectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeconnectServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalider la session
        request.getSession().invalidate();

        // On redirige vers login
        getServletContext().getRequestDispatcher("/WEB-INF/vues/pages/loginForm.jsp").forward(request, response);

        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
