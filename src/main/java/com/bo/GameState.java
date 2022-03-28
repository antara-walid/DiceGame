package com.bo;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private User user;
    private boolean gameOver = false;
    private ArrayList<Integer> dices = new ArrayList<>(3);
    private List<Message> messages = new ArrayList<Message>();
    private  int total=0;

    public void reinit() {
        gameOver = false;
        messages = new ArrayList<Message>();
        dices.clear();
        user.setScore(0);
        user.setCompteurLancer(0);
        total =0;
    }

    public void addMessage(Message ms) {
        messages.add(ms);
    }

    // constructor
    public GameState(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Integer> getDices() {
        return dices;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
