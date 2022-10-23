package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Users {
	@Id @GeneratedValue
	private Long id;
	private String login;
	private String pass;
	@ManyToMany(mappedBy="users",fetch=FetchType.EAGER)
	private Collection<Roles> roles=new ArrayList<>();
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String login, String pass) {
		super();
		this.login = login;
		this.pass = pass;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Collection<Roles> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Roles> roles) {
		this.roles = roles;
	}
	

}
