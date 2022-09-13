package com.hnt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.hnt.entity.User;
import com.hnt.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	@Mock
	UserService service;
	@InjectMocks
	UserController controller;
	
	@Test
	void testGetUser()
	{
		Iterable<User> user = new ArrayList<User>();
		
		when(service.getUser()).thenReturn(user);
		Iterable<User> ituser=controller.getUser();
		assertEquals(ituser,user);
		
	}

	@Test
	void testSaveUser1() {
		User user = new User();
		user.setId(1);
		when(service.save(user)).thenReturn(user);// mocking
		Integer savedUserId = controller.saveUser1(user);
		assertEquals(1, savedUserId);
	}
	
	@Test
	void testSaveUser() {
		User user = new User();
		user.setId(2);
		when(service.save(user)).thenReturn(user);// mocking
		ResponseEntity savedUserId = controller.saveUser(user, 1,5);
		assertEquals(201, savedUserId.getStatusCodeValue());
	}
	@Test
	void testDeleteUser() {
		User user = new User();
		user.setId(1);
	service.delete(user.getId());
	
	}


}
