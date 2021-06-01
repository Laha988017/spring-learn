package com.cognizant.springlearn.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.dao.UserDao;
import com.cognizant.springlearn.model.User;

@RestController
public class UserControllerSql {
	
	@Autowired
	UserDao dao;
	
	@PostMapping("sql/users")
	public ResponseEntity<Object> addUser(@RequestBody User user){
		dao.addUser(user);
		return new ResponseEntity<Object>("User Added Successfully",HttpStatus.CREATED);
	}
	@GetMapping("sql/users")
	public ResponseEntity<Object> viewAllUsers(){
		return new ResponseEntity<Object>(dao.viewAllUsers(),HttpStatus.OK);
	}
	@GetMapping("sql/users/{id}")
	public ResponseEntity<Object> viewUser(@PathVariable(value="id") Long id){
		User user = dao.viewUser(id);
		if(user==null) {
			return new ResponseEntity<Object>("No user found with id "+id,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(user,HttpStatus.OK);
	}
	@DeleteMapping("sql/users")
	public ResponseEntity<Object> deleteAllUser(){
		dao.deleteAllUser();
		return new ResponseEntity<Object>("All Users Deleted",HttpStatus.OK);
	}
	@DeleteMapping("sql/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value="id") Long id){
		if(!dao.deleteUser(id))
			return new ResponseEntity<Object>("User with id="+id+" not present",HttpStatus.NOT_FOUND);
		return new ResponseEntity<Object>("User with id="+id+" deleted",HttpStatus.OK);
	}
	@PutMapping("sql/users/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value="id") Long id,@RequestBody User user){
		if(!dao.updateUser(id,user))
			return new ResponseEntity<Object>("User with id="+id+" not present",HttpStatus.NOT_FOUND);
		return new ResponseEntity<Object>("User with id="+id+" updated",HttpStatus.OK);
	}
}
