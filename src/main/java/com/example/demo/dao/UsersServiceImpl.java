package com.example.demo.dao;

import com.example.demo.entities.Roles;
import com.example.demo.entities.Users;

public class UsersServiceImpl implements UsersService{
	private RolesRepository rolesRepository;
	private UsersRepository usersRepository;

	@Override
	public void addRoleToUser(String role, String username) {
		Roles roles = rolesRepository.findByName(role);
		Users user = usersRepository.findByName(username);
		user.getRoles().add(roles);
		// TODO Auto-generated method stub
		
	}

}
