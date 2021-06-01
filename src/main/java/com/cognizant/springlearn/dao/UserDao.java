package com.cognizant.springlearn.dao;

import java.util.List;

import com.cognizant.springlearn.model.User;

public interface UserDao {
	boolean addUser(User user);
	List<User> viewAllUsers();
	User viewUser(long userId);
	void deleteAllUser();
	boolean deleteUser(long userId);
	boolean updateUser(long userId,User user);
}
