package com.digitalBooks.controller;
import java.lang.Double;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionRoot;
import com.digitalBooks.entity.Book;
import com.digitalBooks.entity.Payment;
import com.digitalBooks.entity.User;
import com.digitalBooks.payload.request.UserBookRequest;
import com.digitalBooks.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin
@RestController
@RequestMapping("/digitalBooks")
public class BookController {
	@Autowired // DI
	BookService bookService; // dependency

//	// @GetMapping("/searchBooks/{title}/{author}/{publisher}/{releasedDate}")
//	@GetMapping("/reader")
//	String get() {
//		return "Hello";
//	}


	@PreAuthorize("hasRole('READER')")
	@GetMapping("/searchAllBooks")
	List<Book> getAllBooks() {
		return bookService.searchBooks();
	}
	
	//@PreAuthorize("hasRole('READER')")
	//Anyone can search books content wont visible
	@GetMapping("/searchBooks") 
	@ResponseBody
	public List<Book> SearchBooks(@RequestParam String category,@RequestParam String author,@RequestParam String price,@RequestParam String publisher) throws JsonProcessingException {
		
		double priceInt=Double.parseDouble(price);
		List<Book>BooksList=bookService.findByCatagoryAndAuthorAndPriceAndPublisher(category,author,priceInt,publisher);
//			Map<String,String>result = new HashMap<>();
//			BooksList.forEach(Book->{
//				result.put("author",Book.getAuthor());
//				result.put("catagory",Book.getCategory());
//				result.put("publishedDate",Book.getPublishedDate());
//				result.put("publisher",Book.getPublisher());
//				result.put("title",Book.getTitle());
//				result.put("price",String.valueOf(Book.getPrice()));
//				System.out.println(Book.getId());
//				
//			});
//			String json = new ObjectMapper().writeValueAsString(result);
//			System.out.println(json);
//			System.out.println(BooksList.get(0).getId());
//		//
		
//		return ResponseEntity.status(HttpStatus.OK)
//		        .body("Book details are \n " + result);
//		
		
			return BooksList;
		}
	

	@PreAuthorize("hasRole('AUTHOR')")
	@PostMapping("/saveBook")
	public ResponseEntity saveBook(@Valid @RequestBody Book book) {
		bookService.save(book);// mock
		System.out.println("book saved");
		//return book.getId();
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Your Book with id" +  book.getId()+"is saved");
		
	}
	
	@PreAuthorize("hasRole('READER')")
	@PostMapping("/buyBooks") 
	@ResponseBody
	public ResponseEntity  buyBooks(@Valid @RequestBody UserBookRequest bookRequst)  {
		System.out.println("Hitting the Response entity@@@@@@@@");
		Integer bookId=Integer.parseInt(bookRequst.getBookId());
		Boolean isUserAvailable=bookService.isUserAvailable(bookRequst.getUsername());
		Boolean isBookAvailable=bookService.isBookAvailable(bookId);
		Map<String,Integer> respayload= new HashMap<String,Integer>();
		System.out.println("Hitting the Response entity11111111"+isBookAvailable);
		Payment payment = new Payment();
		if(isUserAvailable && isBookAvailable) {
			Optional<Book> book = bookService.getBookByBookId(bookId);
			Optional<User> optional = bookService.getUserByName(bookRequst.getUsername());
			
			System.out.println("------------------------------");
			System.out.println("Optionlal User::"+optional);
			System.out.println("Book retrive::"+book);
			if(optional.isPresent()&&book.isPresent())
			{User user =optional.get();
			
			payment.setPaymentDate(new Date());
			
			payment.setPrice(book.get().getPrice());
			payment.setBookId(book.get().getId());
			payment.setReaderId(user.getId());
			payment.setEmail(user.getEmail());
			
			  payment =bookService.save(payment);
			System.out.println(payment.getBookId()+"-------------------@@@@");
			System.out.println("userId"+user.getId()+"-------------------@@@@");
			System.out.println("book"+book.get().getId()+"-------------------@@@@");
			
			
			respayload.put("pamentId", payment.getPaymentId());
			respayload.put("bookId", payment.getBookId());
			System.out.println("respayload in controller:"+respayload);
		
			}}		
//		ResponseEntity responseEntity = new ResponseEntity(respayload , HttpStatus.OK);
//		
//		return responseEntity;
		
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Your Payment of " +  payment.getPrice()+"is done for your book "+bookRequst.getBookId());
		
	}
	
	
	
	@PreAuthorize("hasRole('READER')")
	@GetMapping("/allPurchasedBooks/{email}") 
	@ResponseBody
	public ResponseEntity getAllBooks(@PathVariable("email") String email)  {
		Map<String,Set<Integer>> bookList = null;
		Optional<User> optional = bookService.getByEmail(email);
		if(optional.isPresent())
		{User user =optional.get();
		
		Boolean isReaderPurchased = bookService.isPaymentAvailableByReaderId(user.getId());
		if(isReaderPurchased) {
			
		}
		
		bookList=bookService.getBookId(user.getId());
		}
//		ResponseEntity responseEntity = new ResponseEntity(bookList , HttpStatus.OK);
//		
//		return responseEntity;
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Your purchased books are:"+bookList);
		
	}
	
	@PreAuthorize("hasRole('READER')")
	@GetMapping("/readers/{email}/books/{id}") 
	@ResponseBody
	public ResponseEntity readBookByEmailandBookid(@PathVariable("email") String email,@PathVariable("id") String bookId) throws JsonProcessingException {
		System.out.println("BokkId"+bookId);
		Integer bookId1 = Integer.parseInt(bookId);
		Map<String,String> mapString = bookService.readContent(email,bookId1);
	
		ResponseEntity responseEntity = new ResponseEntity(mapString ,HttpStatus.OK);
		
		return responseEntity;
	}
	
	@PreAuthorize("hasRole('READER')")
	@GetMapping("/readers/{email}/books/payemntId/{paymentId}") 
	@ResponseBody
	public ResponseEntity getPurchasedBookByPaymentId(@PathVariable("email") String email,@PathVariable("paymentId") String paymentId)  {
		
		Integer paymentId1 = Integer.parseInt(paymentId);
		Map<String,String> mapString = bookService.findBookByPaymentId(email,paymentId1);
	
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Your purchased book is:"+mapString);
	}

	@PreAuthorize("hasRole('AUTHOR')")
	@PutMapping("{authorId}/books/{bookId}")
	public ResponseEntity<?>  updateBook(@PathVariable("authorId") Long authorId, @PathVariable("bookId") int bookId, @Valid @RequestBody Book bookModel) {
		return bookService.updateBook(authorId,bookId, bookModel);
	}
	
}
