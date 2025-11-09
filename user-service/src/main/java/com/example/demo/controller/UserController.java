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
	public ResponseEntity<User> registerUser(@RequestBody User user)
	{
		return new ResponseEntity<User>(userService.registerUser(user),null, 200);
	}
	
	@PostMapping("/getuser")
	public ResponseEntity<List<User>> getUser(@RequestBody EmailDTO email)
	{
		return ResponseEntity.ok(userService.getUserByEmail(email.getEmail()));
	}
	
	@PostMapping("/getuserbyname")
	public ResponseEntity<List<User>> getUserByname(@RequestBody UsernameDTO username)
	{
		return ResponseEntity.ok(userService.getUserByname(username.getUsername()));
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
		//System.out.println(username);
	    userService.deleteUserByUsername(username);
	    return ResponseEntity.noContent().build();
	}

}
