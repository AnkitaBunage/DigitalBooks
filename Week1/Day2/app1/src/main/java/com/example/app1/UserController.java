package com.example.app1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.app1.model.User;

@RestController
public class UserController {
	
	@GetMapping("/user")
	String get() {
		return  "Hello";
	}
	
	@PostMapping("/user")
	int createUser(@RequestBody User user) {
		System.out.println(user.getName());
		System.out.println(user.getDob());
		System.out.println(user.getAddress().getCity());
		return 1;
	}
	

}
