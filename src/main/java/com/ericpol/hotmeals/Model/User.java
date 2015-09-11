package com.ericpol.hotmeals.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by vlad on 10.8.15.
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String login;

    private String password;
    
    protected User() { /* For JPA only */}
    
    public User(String login, String password) {
    	this.login = login;
    	this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
}
