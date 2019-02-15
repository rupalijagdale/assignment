package com.uxpsystems.assignment.service;

import java.util.List;

import com.uxpsystems.assignment.model.User;

public interface UserService {
	public void addUser(User u);
	public void updateUser(User u);
	public List<User> listUsers();
	public User getUserById(long id);
	public void removeUser(long id);
}
