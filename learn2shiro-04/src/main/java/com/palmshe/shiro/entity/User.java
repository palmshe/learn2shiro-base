package com.palmshe.shiro.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

    private String username;

    private String password;

    private String salt;

    private Boolean locked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", locked="
				+ locked + "]";
	}
	
	public User(){}
	
	public User(String username, String password, String salt, Boolean locked) {
		super();
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.locked = locked;
	}
}