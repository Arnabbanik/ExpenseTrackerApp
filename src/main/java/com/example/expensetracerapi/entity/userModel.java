package com.example.expensetracerapi.entity;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class userModel {

	@NotBlank(message = "name field cannot be null")
	private String name;
	
	@NotBlank(message = "name field cannot be null")
	@Email(message = "Give a valid Email")
	private String email;
	
	@NotBlank(message = "name field cannot be null")
	@Size(min=5 , message="password should have 5 char")
	private String password;
	
	private long age=0L;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

}
