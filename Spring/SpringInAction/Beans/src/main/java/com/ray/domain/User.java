package com.ray.domain;

/**
 * Created by Ray on 2017/5/30.
 */
public class User {

    private String userName;

    private String password;

    private static int count = 0;

    public User() {
        userName = "aaa";
        password = "bbbb";
        count ++;
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

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        User.count = count;
    }

    @Override
    public String toString() {
        return userName + " " + password + " " + count;
    }
}
