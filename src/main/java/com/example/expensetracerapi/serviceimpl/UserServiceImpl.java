package com.example.expensetracerapi.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.expensetracerapi.entity.User;
import com.example.expensetracerapi.entity.userModel;
import com.example.expensetracerapi.exception.ItemsAlreadyExistsException;
import com.example.expensetracerapi.exception.ResourceNotFoundException;
import com.example.expensetracerapi.repository.UserRepository;
import com.example.expensetracerapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryEncoder;
	
	@Override
	public User createUser(userModel user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ItemsAlreadyExistsException("User is already registred with email" + user.getEmail());
		}
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(bcryEncoder.encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}

//	@Override
//	public User read(Long id) throws ResourceNotFoundException{
//		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found for the id " + id));
//	}
	
	@Override
	public User read(){
		Long userId = getLoggedInUser().getId();
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found for the id " + userId));
	}

//	@Override
//	public User update(userModel user, Long Id) throws ResourceNotFoundException{
//		
//		User updateduser = read(Id);
//		
//		updateduser.setName(user.getName() != null ? user.getName(): updateduser.getName());
//		updateduser.setEmail(user.getEmail() != null ? user.getEmail(): updateduser.getEmail());
//		updateduser.setPassword(user.getPassword() != null ?  bcryEncoder.encode(user.getPassword()): updateduser.getPassword());
//		updateduser.setAge(user.getAge() != 0.0 ? user.getAge(): updateduser.getAge());
//		
//		return userRepository.save(updateduser);
//	}

	@Override
	public User update(userModel user){
		
		User updateduser = read();
		
		updateduser.setName(user.getName() != null ? user.getName(): updateduser.getName());
		updateduser.setEmail(user.getEmail() != null ? user.getEmail(): updateduser.getEmail());
		updateduser.setPassword(user.getPassword() != null ?  bcryEncoder.encode(user.getPassword()): updateduser.getPassword());
		updateduser.setAge(user.getAge() != 0.0 ? user.getAge(): updateduser.getAge());
		
		return userRepository.save(updateduser);
	}
	
//	@Override
//	public void deleteUser(Long Id) {
//		User deletedUser = read(Id);
//		userRepository.delete(deletedUser);
//	}

	@Override
	public void deleteUser() {
		User deletedUser = read();
		userRepository.delete(deletedUser);
	}
	
	@Override
	public User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String email = authentication.getName();
		
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found for email" + email));
	}

}
