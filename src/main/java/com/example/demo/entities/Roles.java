package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
//@Data @AllArgsConstructor
public class Roles {
	@Id @GeneratedValue
	private Long id;
	private String role;
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Users> users=new ArrayList<>();
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Roles(String role) {
		super();
		this.role = role;
	}

	public Roles(String role, Collection<Users> users) {
		super();
		this.role = role;
		this.users = users;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Collection<Users> getUsers() {
		return users;
	}
	public void setUsers(Collection<Users> users) {
		this.users = users;
	}
	

}
