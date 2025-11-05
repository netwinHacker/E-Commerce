package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User registerUser(User user)
	{
		User userR = userRepository.save(user);
		System.out.println(userR);
		return userR;
	}
	
	public List<User> getUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
	
	
}
