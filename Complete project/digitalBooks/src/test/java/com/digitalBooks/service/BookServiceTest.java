package com.digitalBooks.service;



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

//import com.DigitalBooks.models.Author;
//import com.DigitalBooks.models.Book;
//import com.DigitalBooks.models.Payment;
//import com.DigitalBooks.repository.AuthorRepository;
//import com.DigitalBooks.repository.BookRepository;
//import com.DigitalBooks.repository.PaymentRepository;

import com.digitalBooks.entity.Book;
import com.digitalBooks.entity.Payment;
import com.digitalBooks.entity.User;
import com.digitalBooks.payload.request.UserBookRequest;
import com.digitalBooks.repository.BooksRepository;
import com.digitalBooks.repository.PaymentRepository;
import com.digitalBooks.repository.UserRepository;
import com.digitalBooks.payload.response.MessageResponse;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	BookService service;
	@Mock
	BooksRepository bookRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	PaymentRepository paymentRepository;
	
	Payment payment=new Payment();
	
	@Test
	public void saveTest() {
		Book book=new Book();
		book.setId(1);
		book.setActive(true);
		book.setAuthor("anki");
		book.setCategory("general");
		book.setContent("Awesome");
		book.setImage("earth.jpg");
		book.setPrice(255);
		book.setPublishedDate("03 Octo 2022");
		book.setPublisher("bunage");
		book.setTitle("Earth");
		
		when(bookRepository.save(book)).thenReturn(book);
		Book book1=service.save(book);
		//assertThat(book1).isEqualTo(book);
		assertEquals("anki", book.getAuthor());
	}
	
	@Test
	public void searchBooks() {
		Book book1=new Book();
		book1.setId(1);
		book1.setActive(true);
		book1.setAuthor("anki");
		book1.setCategory("history");
		book1.setContent("Gandhiji");
		
		Book book2=new Book();
		book2.setId(1);
		book2.setActive(true);
		book2.setAuthor("anki");
		book2.setCategory("history");
		book2.setContent("Gandhiji");
		
		

		List<Book> book=new ArrayList<>();
		book.add(book1);
		book.add(book2);
		Mockito.when(bookRepository.findAll()).thenReturn(book);
		List<Book> book3=service.searchBooks();
		assertThat(book3).isEqualTo(book);
		
	}
	
	@Test
	public void updateBookTest() {
		Book book1=new Book();
		book1.setId(1);
		book1.setActive(true);
		book1.setAuthor("anki");
		book1.setCategory("general");
		book1.setContent("Awesome");
		Mockito.when(bookRepository.save(book1)).thenReturn(book1);
		book1.setAuthor("Bunage");
		ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Book Updated Successfully"));
		Mockito.when(bookRepository.save(book1)).thenReturn(book1);
		ResponseEntity rm=service.updateBook(1L,1,book1);
		
		assertEquals(result.getStatusCode(), rm.getStatusCode());
		
	}
	@Test
	public void findByCatagoryAndAuthorAndPriceAndPublisherTest() {
		Book book1=new Book();
		book1.setCategory("general");
		String catagory=book1.getCategory();
		book1.setAuthor("anki");
		String author=book1.getAuthor();
		book1.setPrice(125);
		double price=book1.getPrice();
		book1.setPublisher("Bunage");
		String publisher=book1.getPublisher();
		List<Book> book=new ArrayList<>();
		book.add(book1);
		when(bookRepository.findByCategoryAndAuthorAndPriceAndPublisher(catagory, author, price, publisher)).thenReturn(book);
		List<Book> bookser=service.findByCatagoryAndAuthorAndPriceAndPublisher("general","anki",125,"Bunage");
		assertEquals(bookser,book);
		}
	
	 @Test
	    public void getBookByBookId()
	    {
	        Optional<Book> book=Optional.ofNullable(new Book());
	        Book book1=new Book();
	       book1.setId(1);
	       int id=book1.getId();
	        when(bookRepository.findById(1)).thenReturn(book);
	        Optional<Book> book2=Optional.ofNullable(new Book());
	         book2=service.getBookByBookId(id);
	       assertEquals(book2,book);
	    }
	
	
	@Test
	public void getPaymentById() {
		
		payment.setPaymentId(1);
		int paymentid=payment.getPaymentId();
		when(paymentRepository.findByPaymentId(1)).thenReturn(payment);
		Payment payser=service.getPaymentById(paymentid);
		assertEquals(payser,payment);
		
	}

	
	@Test
	public void isUserAvailableTest() {
		String str="anki";
		when(userRepository.existsByUsername("anki")).thenReturn(true);
		assertTrue(service.isUserAvailable(str));
	}
	
	@Test
	public void isBookAvailableTest() {
		int bookid=1;
		when(bookRepository.existsById(1)).thenReturn(true);
		assertTrue(service.isBookAvailable(bookid));
	}
	
	@Test
	public void isPaymentAvailableByReaderIdTest() {
		Long readerId=1L;
		when(paymentRepository.existsByReaderId(1L)).thenReturn(true);
		assertTrue(service.isPaymentAvailableByReaderId(readerId));
	}
	@Test
	public void isUserAvailableByEmailTest() {
		String str="anki@gmail.com";
		when(userRepository.existsByEmail("anki@gmail.com")).thenReturn(true);
		assertTrue(service.isUserAvailableByEmail(str));
	}
	
	
	@Test
	public void findBookByPaymentIdTest() {
		String email="anki@gmail.com";
		 Boolean isuser = service.isUserAvailableByEmail(email);
		 Map<String, String> payload = new HashMap<String, String>();
		 if (isuser) {
			 int paymentid=11;
             Payment payment = service.getPaymentById(paymentid);
             int bookId = payment.getBookId();
             Optional<Book> book = service.getBookByBookId(bookId);
             payload.put("author", book.get().getAuthor());
             payload.put("catagory", book.get().getCategory());
             payload.put("publishedDate", book.get().getPublishedDate());
             payload.put("publisher", book.get().getPublisher());
             payload.put("title", book.get().getTitle());
             payload.put("price", String.valueOf(book.get().getPrice()));
         }
		 Map<String, String> payload1=service.findBookByPaymentId(email, 28);
		 assertEquals(payload1,payload);


		
	}
	
//	@Test
//	public void ReadBookTest() {
//		Book book=new Book();
//		book.setBookid(1);
//		int bookid=book.getBookId();
//		Optional<Book> optional=Optional.of(book);
//		
//		when(bookRepository.findById(1)).thenReturn(optional);
//		Book book1=service.readBook(bookid);
//		assertEquals(book1,book);
//		
//	}
	
	@Test
	public void getByEmailTest() {
		User author=new User();
		author.setEmail("anki@gmail.com");
		String email=author.getEmail();
		Optional<User> optional=Optional.of(author);
		
		when(userRepository.findByEmail("anki@gmail.com")).thenReturn(optional);
		Optional<User> optional1=service.getByEmail(email);
		assertEquals(optional1,optional);
		
	}
	
	@Test
	public void getUserByNameTest() {
		User author=new User();
		author.setUsername("anki");
		String username=author.getUsername();
		Optional<User> optional=Optional.of(author);
		
		when(userRepository.findByUsername("anki")).thenReturn(optional);
		Optional<User> optional1=service.getUserByName(username);
		assertEquals(optional1,optional);
		
	}


}
