package com.example.expensetracerapi.controller;

import javax.validation.Valid;

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

import com.example.expensetracerapi.entity.User;
import com.example.expensetracerapi.entity.userModel;
import com.example.expensetracerapi.exception.ResourceNotFoundException;
import com.example.expensetracerapi.service.UserService;

@RestController
public class userController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody userModel user){
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	
//	@GetMapping("/users/{id}")
//	public ResponseEntity<User> get(@PathVariable Long id){
//		return new ResponseEntity<User>(userService.read(id), HttpStatus.OK);
//	}
	
	@GetMapping("/users")
	public ResponseEntity<User> get(){
		return new ResponseEntity<User>(userService.read(), HttpStatus.OK);
	}
	
//	@PutMapping("/updateuser/{Id}")
//	public ResponseEntity<User> updateUser(@RequestBody userModel user, @PathVariable Long Id){
//		return new ResponseEntity<User> (userService.update(user, Id), HttpStatus.OK);
//	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<User> updateUser(@RequestBody userModel user){
		return new ResponseEntity<User> (userService.update(user), HttpStatus.OK);
	}
	
//	@DeleteMapping("/deleteuser/{Id}")
//	public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long Id) throws ResourceNotFoundException{
//		userService.deleteUser(Id);
//		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
//	}
	
	@DeleteMapping("/deleteuser")
	public ResponseEntity<HttpStatus> deleteUser(){
		userService.deleteUser();
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
}
