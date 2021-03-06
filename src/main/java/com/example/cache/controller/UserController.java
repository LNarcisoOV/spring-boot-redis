package com.example.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.model.User;
import com.example.cache.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public ResponseEntity<Object> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
		} catch(RuntimeException r) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r.getMessage());
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Object> save(@RequestBody User user) {
		try {
			userRepository.save(user);
			return getAll();
		} catch(RuntimeException r) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r.getMessage());
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Object> update(@RequestBody User user) {
		try {
			userRepository.update(user);
			return getAll();
		} catch(RuntimeException r) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") final String id) {
		try {
			userRepository.delete(id);
			return getAll();
		} catch(RuntimeException r) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r.getMessage());
		}
	}
}
