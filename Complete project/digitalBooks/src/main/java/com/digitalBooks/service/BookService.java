package com.digitalBooks.service;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digitalBooks.entity.Book;
import com.digitalBooks.entity.Payment;
import com.digitalBooks.entity.User;
import com.digitalBooks.payload.request.UserBookRequest;
import com.digitalBooks.repository.BooksRepository;
import com.digitalBooks.repository.PaymentRepository;
import com.digitalBooks.repository.UserRepository;
import com.digitalBooks.payload.response.MessageResponse;

@Service
public class BookService {
	@Autowired
	BooksRepository booksrepo;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
//	public Iterable<Book> searchBooks(Book book) {
//		return booksrepo.findAll();
//	}

	public List<Book> searchBooks() {
		// TODO Auto-generated method stub
		return booksrepo.findAll();
	}

	public Book save(@Valid Book book) {
		return booksrepo.save(book);
		
	}

	public List<Book> findByCatagoryAndAuthorAndPriceAndPublisher(String category, String author, double priceInt,
			String publisher) {
		// TODO Auto-generated method stub
		return booksrepo.findByCategoryAndAuthorAndPriceAndPublisher( category,  author,  priceInt,
				 publisher);
	}

	

	//Check the books
	public Boolean isUserAvailable(String userName) {
		Boolean isUserAvailable = userRepository.existsByUsername(userName);
		return isUserAvailable;
	}

	//Check the books
		public Boolean isBookAvailable(Integer bookId) {
			Boolean isBookAvaiable = booksrepo.existsById(bookId);
			return isBookAvaiable;
		}
		
		//Check the book
				public Optional<Book> getBookByBookId(Integer bookId) {
					return booksrepo.findById(bookId);
				}
				public Optional<User> getUserByName(String username) {
					return userRepository.findByUsername(username);
				}
				
				public Optional<User> getByEmail(String email) {
					System.out.println("getByEmail in string email"+email);
					System.out.println("getByEmail in string email"+userRepository.findByEmail(email));
					return userRepository.findByEmail(email);
				}
				
				public Payment save(Payment payment) {
					return paymentRepository.save(payment);
				
			}
				
				//Check the books
				public Boolean  isPaymentAvailableByReaderId(Long readerid) {
					Boolean paymentAvaible = paymentRepository.existsByReaderId(readerid);
					return paymentAvaible;
				}
				
				public Map<String,Set<Integer>>  getBookId(Long readerid) {
					List<Payment> paymentList = paymentRepository.findAllByreaderId(readerid);
					Set<Integer>  bookIdList = new HashSet<Integer>();
					Map<String,Set<Integer>> map = new HashMap<String,Set<Integer>>();
					paymentList.forEach(payment->{
						bookIdList.add(payment.getBookId());
					});
					map.put("bookId", bookIdList);
					   return map;
				}
				
				public Boolean isUserAvailableByEmail(String email) {
					return userRepository.existsByEmail(email);
					
				}

				public Map<String,String> readContent(String email,Integer bookId) {
					Boolean isuser =isUserAvailableByEmail(email);
					Map<String,String> map = new HashMap<String,String>();
					if(isuser) {
						Optional<Book> book =  getBookByBookId(bookId); 
						if(book.isPresent())
						{System.out.println("book is generated::"+book.get().getAuthor());
						map.put("catagory", book.get().getCategory());
						map.put("content", book.get().getContent());
						map.put("Author", book.get().getAuthor());
						}}
					return map;	
				}
				
				public Payment getPaymentById(Integer paymentId) {
					Payment payment=paymentRepository.findByPaymentId(paymentId);
					return payment;
				}
				
				public Map<String,String> findBookByPaymentId(String email,Integer payemntId) {
					Boolean isuser = isUserAvailableByEmail(email);
					Map<String,String> payload = new HashMap<String,String>();
					if(isuser) {
						Payment payment = getPaymentById(payemntId); 
						Integer bookId = payment.getBookId();
						Optional<Book> book =  getBookByBookId(bookId); 
						if(book.isPresent())
						{	payload.put("author",book.get().getAuthor());
						payload.put("catagory",book.get().getCategory());
						payload.put("publishedDate",book.get().getPublishedDate());
						payload.put("publisher",book.get().getPublisher());
						payload.put("title",book.get().getTitle());
						payload.put("price",String.valueOf(book.get().getPrice()));
						}}
					return payload;	
				}
				
				
				public ResponseEntity<MessageResponse> updateBook(Long authorId, int bookId,Book book) {
					User user = new User();
					user.setId(authorId);
					
					 
					 
					Book book1 = new Book(book.getTitle(), book.getAuthor(),book.getCategory(),book.getPrice(), 
							book.getPublisher(), book.isActive(),book.isBlocked(), book.getContent(),book.getReleaseDate(),book.getPublishedDate());
					try {
						book1.setId(bookId);
						booksrepo.save(book);
						return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Book Updated Successfully"));
					} catch(Exception e) {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Please try after some time.."));
					}
					
				

}
}

	

