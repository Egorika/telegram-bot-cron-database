package com.example.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public User findByTelegramId(Long telegramId) {
		return userRepository.findByTelegramId(telegramId);
	}

	@Override
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
