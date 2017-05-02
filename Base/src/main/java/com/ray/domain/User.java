package com.ray.domain;

import java.io.Serializable;

/**
 * 2017/5/2 16:46

 CREATE TABLE mybatis.user
 (
 id VARCHAR(100) PRIMARY KEY,
 username VARCHAR(28),
 password VARCHAR(255)
 );

 */
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7565367228702877849L;

    private String userName;

    private String password;

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
}
