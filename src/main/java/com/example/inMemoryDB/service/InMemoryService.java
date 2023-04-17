package com.example.inMemoryDB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener; 	
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;

@Service
public class InMemoryService {
	
	@Autowired
	private BookRepository repository;
   
	public static Map<Integer, Book> inMemoryStore = new ConcurrentHashMap<>();
	public static Set<Integer> persistMarker = new HashSet<>();
	public static int key = 0;
	
	public void addBook(String title, String name) {
		Book book = new Book(++key, title, name);
		inMemoryStore.put(key, book);
		persistMarker.add(key);
	}
	
	public Book loadBook(int key) {
		return inMemoryStore.get(key);
	}
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		List<Book> books = repository.findAll();
		for(Book book : books) {
			inMemoryStore.put(book.getId(), book);
		}
	}
	
	public void removeBook(Integer key) {
		Book book = inMemoryStore.get(key);
		inMemoryStore.remove(key);
		repository.delete(book);
	}
	
	@Scheduled(fixedDelay = 10000)
	public void persist() {
		List<Book> books = new ArrayList<>();
		for(Entry<Integer, Book> e :  inMemoryStore.entrySet()) {
			if(persistMarker.contains(e.getKey()))
				books.add(e.getValue());
		}
		repository.saveAll((Iterable)books);
		persistMarker.clear();
	}
}
