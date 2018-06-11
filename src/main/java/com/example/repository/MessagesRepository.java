package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Messages;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Integer>{
	Messages findById(int id);
	
	@Query(value = "SELECT * FROM MESSAGES WHERE USER_ID = ?1", nativeQuery = true)
	List<Messages> findAllById(int id);
}
