package com.cognizant.springlearn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.model.User;

@RestController
public class UserControllerCollection {
	
	private Map<Long, User> userRepo = new HashMap<>();
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user){
		userRepo.put(user.getUserId(), user);
		return new ResponseEntity<Object>("User Added Successfully",HttpStatus.CREATED);
	}
	@GetMapping("/users")
	public ResponseEntity<Object> viewAllUsers(){
		return new ResponseEntity<Object>(userRepo.values(),HttpStatus.OK);
	}
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> viewUser(@PathVariable(value="id") Long id){
		if(!userRepo.containsKey(id)) {
			return new ResponseEntity<Object>("No Users found with id: "+id,HttpStatus.NOT_FOUND);
		}
		User body = userRepo.get(id);
		return new ResponseEntity<Object>(body,HttpStatus.OK);
	}
	@DeleteMapping("/users")
	public ResponseEntity<Object> deleteAllUser(){
		userRepo = new HashMap<>();
		return new ResponseEntity<Object>(userRepo.values(),HttpStatus.OK);
	}
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value="id") Long id){
		if(!userRepo.containsKey(id)) {
			return new ResponseEntity<Object>("No Users found with id: "+id,HttpStatus.NOT_FOUND);
		}
		userRepo.remove(id);
		return new ResponseEntity<Object>(userRepo.values(),HttpStatus.OK);
	}
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value="id") Long id,@RequestBody User user){
		if(!userRepo.containsKey(id)) {
			return new ResponseEntity<Object>("No Users found with id: "+id,HttpStatus.NOT_FOUND);
		}
		userRepo.remove(id);
		user.setUserId(id);
		userRepo.put(id, user);
		return new ResponseEntity<Object>(userRepo.values(),HttpStatus.OK);
	}
}
