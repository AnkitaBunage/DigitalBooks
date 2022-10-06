package com.digitalBooks.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

//import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
	@NotBlank
private String title;
	
private String author;
private String category;
private String image;
private double price;

private String publisher;
private boolean active;
private boolean isBlocked;
public String getReleaseDate() {
	return releaseDate;
}

private String content;
private String releaseDate;
public boolean isBlocked() {
	return isBlocked;
}
public void setBlocked(boolean isBlocked) {
	this.isBlocked = isBlocked;
}
public String getPublishedDate() {
	return publishedDate;
}
public void setPublishedDate(String publishedDate) {
	this.publishedDate = publishedDate;
}
@NotBlank
private String publishedDate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public Book(int id, @NotBlank String title, @NotBlank String author, String category, String image, double price,
		@NotBlank String publisher, boolean active, boolean isBlocked, @NotBlank String content, String releaseDate,
		@NotBlank String publishedDate) {
	super();
	this.id = id;
	this.title = title;
	this.author = author;
	this.category = category;
	this.image = image;
	this.price = price;
	this.publisher = publisher;
	this.active = active;
	this.isBlocked = isBlocked;
	this.content = content;
	this.releaseDate = releaseDate;
	this.publishedDate = publishedDate;
}

public Book( @NotBlank String title, @NotBlank String author, String category, double price,
		@NotBlank String publisher, boolean active, boolean isBlocked, @NotBlank String content, String releaseDate,
		@NotBlank String publishedDate) {
	super();
	
	this.title = title;
	this.author = author;
	this.category = category;
	
	this.price = price;
	this.publisher = publisher;
	this.active = active;
	this.isBlocked = isBlocked;
	this.content = content;
	this.releaseDate = releaseDate;
	this.publishedDate = publishedDate;
}
public Book() {
	// TODO Auto-generated constructor stub
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getPublisher() {
	return publisher;
}
@Override
public String toString() {
	return "Book [price=" + price + "]";
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public void setReleaseDate(String string) {
	// TODO Auto-generated method stub
	
}


}
