package com.web.dao;

import com.bo.User;
import com.web.helpers.IGameDataManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDatabaseManagement implements IGameDataManagement {
    private static GameDatabaseManagement instance;
    private static String connexionString = "jdbc:mysql://localhost:3306/gamedb?user=root&password=";

    // constructor
    private GameDatabaseManagement() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
    }

    synchronized public static final GameDatabaseManagement getInstance()  {
        if (instance == null) {
            instance = new GameDatabaseManagement();
        }
        return instance;
    }

    @Override
    public  List<User> getAllUsers() {
        Connection con = null;
        List<User> list = new ArrayList<>();

        try {
            con = DriverManager.getConnection(connexionString);

            try {
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("select * from Utilisateur");

                while (rs.next()) {
                    User u = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
                            rs.getString("password"), rs.getDouble("score"), rs.getDouble("bestScore"), 0);
                    list.add(u);

                }
            } finally {
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException e) {

        }
        return list;
    }

    @Override
    public  void updateBestScore(User user) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connexionString);

            try {

                PreparedStatement pstmt = con.prepareStatement("UPDATE Utilisateur set bestScore = ? where login=?");
                pstmt.setDouble(1, user.getBestScore());
                pstmt.setString(2, user.getLogin());
                pstmt.executeUpdate();

            } finally {
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException e) {

        }
    }

    @Override
    public  void insertUser(User user) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(connexionString);

            try {

                PreparedStatement pstmt = con.prepareStatement(
                        "insert into Utilisateur ( nom,  prenom,  login,  password,  score,  bestScore) values ( ?,  ?,  ?,  ?,  ?,  ?)");
                pstmt.setString(1, user.getFirstName());
                pstmt.setString(2, user.getLastName());
                pstmt.setString(3, user.getLogin());
                pstmt.setString(4, user.getPassword());
                pstmt.setDouble(5, user.getScore());
                pstmt.setDouble(6, user.getBestScore());

                pstmt.executeUpdate();

            } finally {
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException e) {

        }
    }

    @Override
    public  User getUserByLogin(String login) {
        Connection con = null;
        List<User> list = new ArrayList<>();

        try {
            con = DriverManager.getConnection(connexionString);

            try {


                PreparedStatement pstmt = con.prepareStatement("select * from Utilisateur where login =?");
                pstmt.setString(1, login);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    User u = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
                            rs.getString("password"), rs.getDouble("score"), rs.getDouble("bestScore"), 0);
                    list.add(u);

                }

            } finally {
                if (con != null) {
                    con.close();
                }
            }
        } catch (SQLException e) {

        }
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
