package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserService {
	public User findById(int id);
	public User findByTelegramId(Long telegramId);
	public void save(User user);
	public List<User> findAll();
}
