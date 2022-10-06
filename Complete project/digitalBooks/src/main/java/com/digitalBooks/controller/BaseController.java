package com.digitalBooks.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.digitalBooks.payload.response.MessageResponse;

public class BaseController {

	public BaseController() {
		super();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	Map<String, String> handleException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String message = ((FieldError) error).getDefaultMessage();
			errors.put(fieldname, message);
		});
		return errors;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	void handleOrderNotFound(Exception ex) {

	}
	
	@ExceptionHandler(value
            = NoSuchElementException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public @ResponseBody MessageResponse
handleException(NoSuchElementException ex)
{
return new MessageResponse(
  HttpStatus.NOT_FOUND.value(), ex.getMessage());
}
	
	@ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<MessageResponse> handleEntityNotFound(EntityNotFoundException ex){
		MessageResponse error = new MessageResponse(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}