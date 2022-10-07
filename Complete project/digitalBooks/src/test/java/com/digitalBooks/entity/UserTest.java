package com.digitalBooks.entity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.digitalBooks.entity.Book;
import com.digitalBooks.entity.Payment;
import com.digitalBooks.entity.User;
import com.digitalBooks.payload.request.UserBookRequest;
import com.digitalBooks.repository.BooksRepository;
import com.digitalBooks.repository.PaymentRepository;
import com.digitalBooks.repository.UserRepository;
import com.digitalBooks.payload.response.MessageResponse;

public class UserTest {
	@InjectMocks
	User user;
	
	User user1 = new User();
	//@Test
//	public void userTest(String username, String email, String password) {
//		String unm; 
//		String em;
//		String pwd;
//		unm =this. username;
//		this.email = email;
//		this.password = password;
//	}
	@Test
public void idTest()
{
	user1.setId(1L);
	Long id = user1.getId();
	assertEquals(1L,id);

}@Test
	public void usernameTest()
	{
		user1.setUsername("ankita");
		String unm = user1.getUsername();
		assertEquals("ankita",unm);
		

	}
@Test
	public void emailTest()
	{
		user1.setEmail("ankita");
		String unm = user1.getEmail();
		assertEquals("ankita",unm);

	}
@Test
	public void passwordTest()
	{
		user1.setPassword("xcvvv23$");
		String unm = user1.getPassword();
		assertEquals("xcvvv23$",unm);

	}
//@Test
//public void userTest(String username, String email, String password) {
//	user1.setUsername(username);
//	user1.setEmail(email);
//	user1.setPassword(password);
//	
//}
	
}
