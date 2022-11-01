package com.revature.daos;

import com.revature.pojos.User;
import com.revature.services.DatasourceService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements DatasourceCRUD<User> {

    Connection connection;

    public UserDAO() {
        this.connection = DatasourceService.ConnectionManager.getConnection();
    }


    @Override
    public void create(User user) {
        try {
            String sql = "INSERT INTO users (first_name, last_name, email, username, password, admin)" +
                    " VALUES (?, ?, ?, ?, ?, false)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());


            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                keys.getInt("user_id");
            }

            String defaultAdmin = "UPDATE users SET admin = true WHERE user_id = 1";
            PreparedStatement pstmtDefaultadmin = connection.prepareStatement(defaultAdmin);

            pstmtDefaultadmin.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();

            if (results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setEmail(results.getString("email"));
                user.setUsername(results.getString("username"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                User user = new User();
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setEmail(results.getString("email"));
                user.setUsername(results.getString("username"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;

    }

    @Override
    public void update(User user, Integer userId) {
        try {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, " + "username = ?, password = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());
            pstmt.setInt(6, userId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void adminUpdate(Integer userId, Boolean admin){
        try{
            String sql = "UPDATE users SET admin = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setBoolean(1, admin);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            if(id == 1){
                throw new Exception();
            }else{
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            if(id == 1){
                throw new Exception();
            }else{
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete a primary user");
        }
    }

    public User logInUser(String credentials, String password) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ? OR username = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, credentials);
            pstmt.setString(2, password);
            pstmt.setString(3, credentials);
            pstmt.setString(4, password);
            ResultSet results = pstmt.executeQuery();

            if (results.next()) {
                user.setUserId(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setEmail(results.getString("email"));
                user.setUsername(results.getString("username"));
                user.setPassword(results.getString("password"));
                user.setAdmin(results.getBoolean("admin"));

            } else {
                throw new Exception("Invalid credentials, please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials, please try again.");
        }
        return user;
    }
}