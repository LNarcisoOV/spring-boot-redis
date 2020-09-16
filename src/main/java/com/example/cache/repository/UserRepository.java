package com.example.cache.repository;

import java.util.Map;

import com.example.cache.model.User;

public interface UserRepository {
	void save(User user);

	Map<String, User> findAll();
	
	User findById(String id);

	void update(User user);

	void delete(String id);

}
