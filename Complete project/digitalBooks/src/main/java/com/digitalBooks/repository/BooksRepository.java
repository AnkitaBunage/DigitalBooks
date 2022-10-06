package com.digitalBooks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.digitalBooks.entity.Book;
import com.digitalBooks.entity.User;

public interface BooksRepository extends JpaRepository<Book, Integer>{

	List<Book> findByCategoryAndAuthorAndPriceAndPublisher(String category, String author, double priceInt, String publisher);

	//Boolean existsByUsername(String userName);

	Boolean existsById(Long bookId);
	Book  findById(Long bookId);

	//boolean existById(long id);
	//boolean existByUsername(User username);
	

}
