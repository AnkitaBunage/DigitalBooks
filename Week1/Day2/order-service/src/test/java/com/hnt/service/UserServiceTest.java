package com.hnt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hnt.UserRepository;
import com.hnt.entity.User;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	@InjectMocks
	UserService service;
	
	@Mock
	UserRepository repository;
	@Test
	void testSave() {
		User user = new User();
		user.setId(1);
		user.setName("john");
		when(repository.save(user)).thenReturn(user);//mock
		service.save(user);
		assertEquals(1,	user.getId());
	}
//	404
	@Test
	void testSaveForException() {
		assertThrows(IllegalArgumentException.class, ()->{
			User user = new User();
			user.setName("Ram");
			service.save(user);
		});
	}

	@Test
	void testGetUser()
	{
		Iterable<User> user = new ArrayList<User>();
		
		when(repository.findAll()).thenReturn(user);
		Iterable<User> ituser=service.getUser();
		assertEquals(ituser,user);
		
	}
	
	@Test
	void testDeleteUser() {
		User user = new User();
		user.setId(1);
	repository.deleteById(user.getId());
	
	}
}
