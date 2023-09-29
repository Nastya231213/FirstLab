package com.elements;
import java.util.List;

public class Author {
    private int id;
    private String name;
    private String pseudonym;
    private List<Book> listOfBook;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPseudonym() {
		return pseudonym;
	}
	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}
	public List<Book> getListOfBook() {
		return listOfBook;
	}
	public void setListOfBook(List<Book> listOfBook) {
		this.listOfBook = listOfBook;
	}
    
}
