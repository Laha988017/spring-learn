package com.cognizant.springlearn.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.cognizant.springlearn.mapper.UserMapper;
import com.cognizant.springlearn.model.User;

@Component
public class UserDaoImpl implements UserDao {

	
	DriverManagerDataSource datasource;
	
	@Autowired
	JdbcTemplate jdbcTemp;
	
	private final String SQL_ADD_USER = "insert into users values(?,?,?)";
	private final String SQL_FIND_ALL_USER = "select * from users";
	private final String SQL_A_USER = "select * from users where userId=?";
	private final String SQL_DELETE_ALL_USERS = "DELETE from users";
	private final String SQL_DELETE_A_USER = "DELETE from users where userId=?";
	private final String SQL_UPDATE_A_USER = "UPDATE users SET userName=?,password=? where userId=?";
	
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return jdbcTemp.update(SQL_ADD_USER,user.getUserId(),user.getUserName(),user.getPassword())>0;
	}

	@Override
	public List<User> viewAllUsers() {
		// TODO Auto-generated method stub
		return jdbcTemp.query(SQL_FIND_ALL_USER, new UserMapper());
	}

	@SuppressWarnings("deprecation")
	@Override
	public User viewUser(long userId) {
		User user;
		try {
		user = jdbcTemp.queryForObject(SQL_A_USER,new Object[] {userId}, new UserMapper());
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
					
		return user;
	}

	@Override
	public void deleteAllUser() {
		// TODO Auto-generated method stub
		jdbcTemp.update(SQL_DELETE_ALL_USERS);
	}

	@Override
	public boolean deleteUser(long userId) {
		// TODO Auto-generated method stub
		return jdbcTemp.update(SQL_DELETE_A_USER, userId)>0;
	}

	@Override
	public boolean updateUser(long userId,User user) {
		// TODO Auto-generated method stub
		return jdbcTemp.update(SQL_UPDATE_A_USER,user.getUserName(),user.getPassword(), userId)>0;
	}

}
