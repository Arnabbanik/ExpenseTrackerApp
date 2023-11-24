package com.example.expensetracerapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracerapi.controller.util.JwtTokenUtil;
import com.example.expensetracerapi.entity.AuthModel;
import com.example.expensetracerapi.entity.JwtResponse;
import com.example.expensetracerapi.entity.User;
import com.example.expensetracerapi.entity.userModel;
import com.example.expensetracerapi.security.CustomUserDetailsService;
import com.example.expensetracerapi.service.UserService;

@RestController
@CrossOrigin(origins="*")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
//	@PostMapping("/register")
//	public ResponseEntity<User> createUser(@Valid @RequestBody userModel user){
//		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
//	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authmodel) throws Exception{
//		return new ResponseEntity<String>("User is loggedin", HttpStatus.OK);
	    
		authenticate(authmodel.getEmail(),authmodel.getPassword());
		
		//we need to generate the jwt token 
		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authmodel.getEmail());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		//SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
	}

    private void authenticate(String email, String password) throws Exception {
	
    	try {
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    	}catch(DisabledException e) {
    		throw new Exception("User disabled");
    	}catch(BadCredentialsException e) {
    		throw new Exception("Bad credentials");
    	}
    }
}
