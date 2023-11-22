package com.example.expensetracerapi.service;

import com.example.expensetracerapi.entity.User;
import com.example.expensetracerapi.entity.userModel;

public interface UserService {

	User createUser(userModel user);
	
	//User read(Long Id);
	User read();
	
	//User update(userModel user, Long Id);
	User update(userModel user);
	
	//void deleteUser(Long Id);
	void deleteUser();
	
	User getLoggedInUser();
}
