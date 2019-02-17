package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uxpsystems.assignment.dao.UserDAO;
import com.uxpsystems.assignment.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void addUser(User u) {
		this.userDAO.addUser(u);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED)
	public void updateUser(User u) {
		this.userDAO.updateUser(u);
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		List<User> userList = this.userDAO.listUsers();
		return userList;
	}

	@Override
	@Transactional
	public User getUserById(long id) {
		User user = this.userDAO.getUserById(id);
		return user;
	}

	@Override
	@Transactional
	public void removeUser(long id) {
		this.userDAO.deleteUser(id);
	}

}
