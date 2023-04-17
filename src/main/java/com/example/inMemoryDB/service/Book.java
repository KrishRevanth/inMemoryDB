package com.example.inMemoryDB.service;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Book {
   
	@org.springframework.data.annotation.Id
	private int id;
	private String title;
	private String borrower;
	public Book(int id, String title, String borrower) {
		super();
		this.id = id;
		this.title = title;
		this.borrower = borrower;
	}
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
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", borrower=" + borrower + "]";
	}
	
	
}
