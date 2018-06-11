package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity(name = "User")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Column(name = "telegramId")
	private Long telegramId;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "user",
			fetch = FetchType.EAGER
			)
	private List<Messages> msgs = new ArrayList<>();
	
	public void addMessage(Messages message) {
		msgs.add(message);
		message.setUser(this);
	}
}

