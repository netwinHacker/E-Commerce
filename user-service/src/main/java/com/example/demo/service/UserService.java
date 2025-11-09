package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.SecurityConfig;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SecurityConfig securityConfig;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserService(PasswordEncoder passwordEncoder)
	{
		this.passwordEncoder = passwordEncoder;
	}
	
	public User registerUser(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public List<User> getUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	public User getUserByname(String email)
	{
		return userRepository.findByUsername(email);
	}
	
	public void deleteUserByUsername(String username)
	{
		User usersTodel = getUserByname(username);
		userRepository.delete(usersTodel);
	}
	
	
}
