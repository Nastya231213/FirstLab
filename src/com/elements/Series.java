package com.elements;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Series {
    private int id;
	private String name;
	private LocalDate dateOfTheStart;
	private String description;
	private SeriesType seriesType;
	private List<Book> books;
    
	public Series(int id,String name, LocalDate dateOfTheStart, String description, SeriesType seriesType) {
		this.id=id;
		this.name = name;
		this.dateOfTheStart = dateOfTheStart;
		this.description = description;
		this.seriesType = seriesType;
		this.books = books;
	}

	public Series(String name, LocalDate dateOfTheStart, String description, SeriesType seriesType) {
		super();
		this.name = name;
		this.dateOfTheStart = dateOfTheStart;
		this.description = description;
		this.seriesType = seriesType;
		this.books=new ArrayList<Book>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getDateOfTheStart() {
		return dateOfTheStart;
	}

	public void setDateOfTheStart(LocalDate dateOfTheStart) {
		this.dateOfTheStart = dateOfTheStart;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public SeriesType getSeriesType() {
		return seriesType;
	}

	public void setSeriesType(SeriesType seriesType) {
		this.seriesType = seriesType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
