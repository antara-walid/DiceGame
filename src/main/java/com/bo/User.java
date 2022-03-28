package com.bo;

public class User {
    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private double score;

    private double bestScore;

    private int compteurLancer; // 3 max

    // constructors
    public User() {
    }

    public User(String firstName, String lastName, String login, String password, double score, double bestScore,
                int compteurLancer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.score = score;
        this.bestScore = bestScore;
        this.compteurLancer = compteurLancer;
    }
    public User(String firstName, String lastName, String login, String password, double score, double bestScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.score = score;
        this.bestScore = bestScore;
    }

    // display

    @Override
    public String toString() {
        return "user{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", bestScore=" + bestScore +
                ", compteurLancer=" + compteurLancer +
                '}';
    }

    // getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getBestScore() {
        return bestScore;
    }

    public void setBestScore(double bestScore) {
        this.bestScore = bestScore;
    }

    public int getCompteurLancer() {
        return compteurLancer;
    }

    public void setCompteurLancer(int compteurLancer) {
        this.compteurLancer = compteurLancer;
    }
    public void incrementLance() {
        this.compteurLancer++;
    }

}
