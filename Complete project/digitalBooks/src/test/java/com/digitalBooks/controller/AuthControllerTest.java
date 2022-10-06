package com.digitalBooks.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.digitalBooks.controller.AuthController;
import com.digitalBooks.entity.ERole;
import com.digitalBooks.entity.User;
import com.digitalBooks.payload.request.*;
import com.digitalBooks.repository.RoleRepository;
import com.digitalBooks.repository.UserRepository;


public class AuthControllerTest {
	
	@Mock
	UserRepository userRepository;
	@Mock
	PasswordEncoder encoder;
	@Mock
	RoleRepository roleRepository;
	@Mock
	ERole role;
	@InjectMocks
	AuthController authController;
	
	
//	@Test
//	public void testRegisterUser1() {
//		//UserRepository userRepository = new UserRepository();
//		SignupRequest signupRequest = new SignupRequest();
//		
//		signupRequest.setEmail("fvgewdn@gmail.com");
//		signupRequest.setPassword("ppass@word1");
//		signupRequest.setUsername("nmfyuefencbuy");
//		//encoder.encode("sxdhwsxscvcsc");
//		
//		
//		
//		//when(encoder.encode(signupRequest.getPassword())).thenReturn("sxdhwsxscvcsc");
//		when(userRepository.existsByUsername(signupRequest.getUsername())).thenReturn(false);
//		when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(false);
//		
//		Set<String> strRoles = new HashSet<String>();
//		strRoles.add("ROLE_READER");
//		signupRequest.setRole(strRoles);
//		
//		ResponseEntity<?> r = new ResponseEntity("User registered successfully!" , HttpStatus.OK);
//		 
//		assertEquals(r, authController.registerUser(signupRequest));		
//	}


}