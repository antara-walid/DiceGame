package com.web.listeners;

import com.bo.User;
import com.web.helpers.DataManagementFactory;
import com.web.helpers.IGameDataManagement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@WebListener
public class ApplicationInitializer implements ServletContextListener {

    // this methode is called just after the initialization of the context
    public void contextInitialized(ServletContextEvent event)
    {
        // first we get the context
        ServletContext context = event.getServletContext();
        // second from web.xml we get the initial parameters responsible for
        // which storage type we will use
        String storageType = context.getInitParameter("type_stockage");

        // dependency injection
        IGameDataManagement gameData = DataManagementFactory.getFactory(storageType, context);

        context.setAttribute("gameData", gameData); // gameData is the storage

        // we can use Vector because it is synchronized but the prof methode is
        // simpler  plus Vector is considered legacy collection
        List<User> list = Collections.synchronizedList(new ArrayList<>());
        context.setAttribute("users", list);

        // testing
        System.out.println("listener is working");

    }

}
