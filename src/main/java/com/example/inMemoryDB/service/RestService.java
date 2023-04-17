package com.example.inMemoryDB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestService {
	
	@Autowired
	private InMemoryService inMemoryService;
    
	@GetMapping("/add") 
	public String add(@RequestParam String title, @RequestParam String name ) {
		inMemoryService.addBook(title, name);
		return "done";
		
	}
	
	@GetMapping("/remove/{id}") 
	public String add(@PathVariable Integer id ) {
		inMemoryService.removeBook(id);
		return "done";
		
	}
	
	@GetMapping("/load/{id}")
	public String load(@PathVariable Integer id) {
		return inMemoryService.loadBook(id).toString();
		
	}
}
