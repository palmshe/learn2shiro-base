package com.palmshe.shiro.entity;

import java.io.Serializable;

public class Permission implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

    private String permission;

    private String description;

    private Boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

	@Override
	public String toString() {
		return "Permission [id=" + id + ", permission=" + permission + ", description=" + description + ", available="
				+ available + "]";
	}
	
	public Permission(){}

	public Permission(String permission, String description, Boolean available) {
		super();
		this.permission = permission;
		this.description = description;
		this.available = available;
	}
}