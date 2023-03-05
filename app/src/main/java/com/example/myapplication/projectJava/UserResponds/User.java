package com.example.myapplication.projectJava.UserResponds;

//一般用户信息
public class User {
    private String userId;
    private String userName;
    private String password;
    private Integer balance;



    public User(String userId, String userName, String password, Integer balance) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
    }

    public User(String userName, String password) {
        userId = "null";
        this.userName = userName;
        this.password = password;
        balance = 0;
    }


    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
