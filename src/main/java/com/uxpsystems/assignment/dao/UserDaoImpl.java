package com.uxpsystems.assignment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.model.User;

@Repository
public class UserDaoImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		logger.debug("User Saved successfully... User is:{}",user);
	}

	@Override
	public void updateUser(User user) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.merge(user);
		} catch(Exception e) {
			System.out.print("Exception:"+e);
		}

		logger.debug("User updated successfully... User is :{}", user);
	}

	
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery(" from User").list();
		
		for(User u : userList) {
			System.out.print("user is :"+ u.getUsername() +"\n");
			
		}

		return userList;
	}

	@Override
	public User getUserById(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, id);
		logger.info("User loaded successfully :{}", u);
		return u;
	}

	@Override
	public void deleteUser(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, id);
		if(u != null) {
			session.delete(u);
		}	
	}

}
