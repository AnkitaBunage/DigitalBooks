package com.digitalBooks.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.constraints.NotBlank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.digitalBooks.controller.BookController;
import com.digitalBooks.repository.*;
import com.digitalBooks.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.digitalBooks.entity.*;
import com.digitalBooks.payload.request.UserBookRequest;
import com.digitalBooks.payload.response.MessageResponse;

@SpringBootTest
public class BookTestController {

    @Mock
    private BookService service;
    @Autowired
    private BooksRepository bookrepo;
    
    @InjectMocks
    private BookController controller;
    
    @Test
    public void testsavebook()
    {
        Book book=new Book(1, "XYZZZ", "ANKITAA", "HISTORY", "abc", 1234.5, "BUNAGE", true, false, "abcfdfg", "19/09/2022", "12/09/2022");
//       
       
        when(service.save(book)).thenReturn(book);// mocking
        
		ResponseEntity responseEntity = controller.saveBook(book);
		assertEquals(responseEntity.getStatusCode().value(),200);

    }
    @Test
    public void getAllBookstest()
    {
        List<Book> book=new ArrayList<>();
        
        
        when(service.searchBooks()).thenReturn(book);
		List<Book> books=controller.getAllBooks();
		assertEquals(books,book);
    }
//    @Test
//    public void searchbytest()
//    {
//        Book book=new Book();
//        book.get().setAuthor("author");
//        when(controller.searchbooksbyauthor("author")).thenReturn(null);
//        assertThat(bookservtest.byauthor("author"));
//         
//    }
//    @Test
//    public void searchbybooidtest()
//    {
//        Book book=new Book();
//        book.get().setBookID(1L);
//        when(bookcontroller.readbookbyid(1L)).thenReturn(book);
//        assertThat(bookservtest.byid(1L));
//    }
    @Test
    public void testSearchBooks() throws JsonProcessingException
    {
        
        List<Book> BooksList=new ArrayList<Book>();
        List<Book> result=new ArrayList<Book>();
        
      when(service.findByCatagoryAndAuthorAndPriceAndPublisher("catagory","author",150,"publisher")).thenReturn(BooksList);;
         result = controller.SearchBooks("author", "catagory", "275", "publisher");
assertThat(result.equals(BooksList));

}

   
    
    @Test
    public void readBookByEmailandBookidTest() throws JsonProcessingException {
    	Map<String, String> map = new HashMap<String, String>();
    	Book book=new Book();
    	map.put("book is generated::",String.valueOf(book.getId()));
    	when(service.readContent("anki@gmail.com", 1)).thenReturn(map);
    	ResponseEntity entity =controller.readBookByEmailandBookid("anki@gmail.com", "1");
    	assertThat(entity.equals(map));
    	
    }
    @Test
    public void getPurchasedBookByPaymentIdTest() throws JsonProcessingException {
    	Map<String, String> map = new HashMap<String, String>();
    	Book book=new Book();
    	when(service.findBookByPaymentId("anki@gmail.com", 1)).thenReturn(map);
    	ResponseEntity entity =controller.getPurchasedBookByPaymentId("anki@gmail.com", String.valueOf(1L));
    	assertThat(entity.equals(map));
    	
    }
    @Test
    public void updateBookTest()
    {
        Book book=new Book();
        book.setId(2);
        User user = new User();
		user.setId(1L);
		long authorId;
		int bookId;
        
		ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Book Updated Successfully"));
        when(service.updateBook(user.getId(),book.getId(),book)).thenReturn(result);
        ResponseEntity entity = controller.updateBook(1L, 2,book);
    assertEquals(result,entity);
         
    }
    
   
//    @Test
//    public void buyBooksTest() {
//    	UserBookRequest request= new UserBookRequest();
//        request.setBookId("11");
//        String bookid=request.getBookId();
//        request.setEmail("anki@gmail.com");
//        String email=request.getEmail();
//        request.setUsername("anki");
//        String username=request.getUsername();
//        int bookId=Integer.parseInt(request.getBookId());
//        when(service.isUserAvailable("siva")).thenReturn(true);
//
//        when(service.isBookAvailable(11)).thenReturn(true);
//        Optional<Book> book = service.getBookByBookId(bookId);
//        
//        book.get().setActive(true);
//        book.get().setAuthor("Marvel");
//        book.get().setCategory("Finance");
//        book.get().setContent("HHHHHHHHHHHHH");
//        book.get().setId(11);
//        book.get().setPrice(40);
//        book.get().setPublisher("lhlllll");
//        when(service.getBookByBookId(bookId)).thenReturn(book);
//        User user = new User();
//        user.setEmail(request.getEmail());
//        user.setUsername(request.getUsername());
//        user.setId(3L);
//        Optional<User> optionalUser=Optional.of(user);
//        when(service.getUserByName(request.getUsername())).thenReturn(optionalUser);
//        Payment payment=new Payment();
//        payment.setBookId(11);
//        payment.setReaderId(3L);
//        payment.setPaymentDate(new Date());
//        
//        when(service.save(payment)).thenReturn(payment);
//        Map<String,Integer> respayload= new HashMap<String,Integer>();
//
//        respayload.put("pamentId", payment.getPaymentId());
//        respayload.put("bookId", payment.getBookId());
//        
//    
//            
//    ResponseEntity responseEntity = new ResponseEntity(respayload , HttpStatus.OK);
//
//    ResponseEntity responseEntity1 = controller.buyBooks(request);
//    //assertThat(responseEntity1).isEqualTo(responseEntity);
//assertNotNull(responseEntity1);
//    
//    
//    }
    
    
    


}


   
    
      
    
