package com.example.demo.controller;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmailDTO;
import com.example.demo.dto.UsernameDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String welcome()
	{
		return "User Service";
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) throws Exception
	{
		System.out.println(userService.getUserByname(user.getUsername()));
		if(userService.getUserByname(user.getUsername()) != null)
		{
			return ResponseEntity.ok(userService.registerUser(user).toString());
		}
		return new ResponseEntity<>("User already exists",null, HttpStatus.SC_BAD_GATEWAY);
	}
	
	@PostMapping("/getuser")
	public ResponseEntity<List<User>> getUser(@RequestBody EmailDTO email)
	{
		return ResponseEntity.ok(userService.getUserByEmail(email.getEmail()));
	}
	
	@PostMapping("/getuserbyname")
	public ResponseEntity<User> getUserByname(@RequestBody UsernameDTO username)
	{
		return ResponseEntity.ok(userService.getUserByname(username.getUsername()));
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
	    userService.deleteUserByUsername(username);
	    return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user)
	{
		if(userService.getUserByname(user.getUsername()) != null)
		{
			return ResponseEntity.ok("Login successful");
		}
		return ResponseEntity.ok("Invalid login credentials");
	}

}
