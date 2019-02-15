package com.uxpsystems.assignment.dao;

import java.util.List;

import com.uxpsystems.assignment.model.User;

public interface UserDAO {

	public void addUser(User user);
	public void updateUser(User user);
	public List<User> listUsers();
	public User getUserById(long id);
	public void deleteUser(long id);
}
