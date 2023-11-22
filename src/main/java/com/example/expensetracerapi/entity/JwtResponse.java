package com.example.expensetracerapi.entity;


public class JwtResponse {

	private final String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}
}
