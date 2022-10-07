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

	public class PaymentTest {
		@InjectMocks
		Payment payment;
		
		Payment pay = new Payment();

		@Test
	public void idTest()
	{
		pay.setPaymentId(1);
		int id = pay.getPaymentId();
		assertEquals(1,id);

	}
		
		@Test
		public void bookidTest()
		{
			pay.setBookId(1);
			int id = pay.getBookId();
			assertEquals(1,id);

		}
		
		@Test
		public void readeridTest()
		{
			pay.setReaderId(1L);
			Long id = pay.getReaderId();
			assertEquals(1L,id);

		}
		
		
		
		@Test
		public void priceTest()
		{
			pay.setPrice(1.0);
			double id = pay.getPrice();
			assertEquals(1.0,id);

		}
		
	@Test
		public void emailTest()
		{
			pay.setEmail("ankita");
			String unm = pay.getEmail();
			assertEquals("ankita",unm);

		}
	
	
		


}
