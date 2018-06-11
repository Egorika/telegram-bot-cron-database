package com.example.service;

import java.util.List;

import com.example.model.Messages;

public interface MessagesService {
	public Messages findById(int id);
	public List<Messages> findAllById(int id);
}
