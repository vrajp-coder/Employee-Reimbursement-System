package com.revature.pojos;

import java.util.Objects;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private boolean admin;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String email, String username, String password, boolean admin) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public User(String firstName, String lastName, String email, String username, String password, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public User(int userId, String firstName, String lastName, String username, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String credentials, String password) {
        this.email = credentials;
        this.username = credentials;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(email, user.email)
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}