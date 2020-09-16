package com.example.cache.repository.impl;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.cache.model.User;
import com.example.cache.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final String USER_CACHE_KEY = "USER_CACHE_KEY";
	private static final String USER_HASH_KEY = "user";

	private HashOperations<String, String, User> hashOperations;

	public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(User user) {
		hashOperations.put(USER_CACHE_KEY, USER_HASH_KEY, user);
	}

	@Override
	public Map<String, User> findAll() {
		return hashOperations.entries(USER_CACHE_KEY);
	}

	@Override
	public User findById(String id) {
		return hashOperations.get(USER_CACHE_KEY, id);
	}

	@Override
	public void update(User user) {
		save(user);
	}

	@Override
	public void delete(String id) {
		hashOperations.delete(USER_CACHE_KEY, id);
	}

}
