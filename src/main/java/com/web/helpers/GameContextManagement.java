package com.web.helpers;

import com.bo.User;
import jakarta.servlet.ServletContext;

import java.io.Serializable;
import java.util.List;

// this class is responsible for managing the application context which means managing the list of
// users in the context

public class GameContextManagement implements IGameDataManagement{
    private ServletContext context;
    public static GameContextManagement instance;

    // constructor
    private GameContextManagement(ServletContext context) {
        this.context = context;
    }

    // we use the methode getInstance to make sure we have one instance of GameContextManagement because the application
    // have one context

    // getInstance is synchronized because it can be used by multiple users at the same time
    synchronized public static final GameContextManagement getInstance(ServletContext context)
    {
        if(instance == null)
        {
            return new GameContextManagement(context);
        }
        return instance;
    }



    @Override
    public List<User> getAllUsers() {
        // with the help of listeners we added a list to the context just after
        // its creation
        List<User> list = (List<User>) context.getAttribute("users");
        return list;
    }

    @Override
    public void updateBestScore(User user) {
        List<User> list = (List<User>) context.getAttribute("users");
        for(User user2 :list)
        {
            if(user.getLogin().equals(user2.getLogin()))
                user2.setBestScore(user.getBestScore());
        }
    }

    @Override
    public void insertUser(User user) {
        List<User> list = (List<User>) context.getAttribute("users");
        list.add(user);
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> list = (List<User>) context.getAttribute("users");
        for(User user :list)
        {
            if(user.getLogin().equals(login))
                return user;
        }
        return null;
    }
}
