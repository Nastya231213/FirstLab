package com.elements;

import java.util.List;

public class Character {
	private String name;
	private ParticipationLevel role;
	private List<Book> listOfBooks;

	public Character(String name, ParticipationLevel role, List<Book> listOfBooks) {
		this.name = name;
		this.role = role;
		this.listOfBooks = listOfBooks;
	}

	public Character(String name, ParticipationLevel role) {
		this.name = name;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ParticipationLevel getRole() {
		return role;
	}

	public void setRole(ParticipationLevel role) {
		this.role = role;
	}

	public List<Book> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

}
