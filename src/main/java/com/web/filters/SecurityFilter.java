package com.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class SecurityFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest rq = (HttpServletRequest) request;

        // On récupère la session
        HttpSession session = rq.getSession();
        System.out.println("filter has being called");
        // On vérifie si l'authentification a déjà eu lieu
        if (session.getAttribute("user") == null) {

            // Si non il faut interdir l'accès
            rq.getRequestDispatcher("/WEB-INF/vues/pages/loginForm.jsp").forward(request, response);

            // Fin
            return;

        } else {

            // Si oui, alors continuer vers la resource suivante dans la chaine
            // (filtre suivant, servlet suivante ou jsp suivante..)
            chain.doFilter(request, response);

        }



    }
}
