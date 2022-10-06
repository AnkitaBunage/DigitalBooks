package com.digitalBooks.payload.request;

import javax.validation.constraints.*;

import com.digitalBooks.entity.User;

public class UserBookRequest {
	@NotBlank
    @Size(min = 3, max = 20)
    private String username;
	@NotBlank
    @Size(max = 50)
    @Email
    private String email;
	private String  bookId;
	
 
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}  
    public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}



}
