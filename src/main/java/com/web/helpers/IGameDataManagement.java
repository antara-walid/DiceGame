package com.web.helpers;

import com.bo.User;

import java.util.List;

public interface IGameDataManagement {
    public List<User> getAllUsers();

    public void updateBestScore(User user);

    public void insertUser(User user);

    public User getUserByLogin(String login);
}
