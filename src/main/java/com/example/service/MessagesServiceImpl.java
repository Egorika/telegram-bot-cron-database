package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Messages;
import com.example.repository.MessagesRepository;

@Service
public class MessagesServiceImpl implements MessagesService{

	@Autowired
	private MessagesRepository messagesRepository;
	
	@Override
	public List<Messages> findAllById(int id) {
		return messagesRepository.findAllById(id);
	}

	@Override
	public Messages findById(int id) {
		return messagesRepository.findById(id);
	}
}
