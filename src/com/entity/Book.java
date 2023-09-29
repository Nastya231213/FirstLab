package com.entity;

import java.time.LocalDate;

public class Book {
	private int id;
	private String title;
	private String name;
	private LocalDate releaseDate;
	private int quantityOfPages;
	private String shortAnnotation;
	private int authorId;

	public Book() {

	}

	public Book(String title, String name, LocalDate releaseDate, int quantityOfPages, String shortAnnotation,
			int bookSeriesId, int authorId) {
		super();
		this.title = title;
		this.name = name;
		this.releaseDate = releaseDate;
		this.quantityOfPages = quantityOfPages;
		this.shortAnnotation = shortAnnotation;
		this.authorId = authorId;
	}

	public Book(String title, String name, LocalDate releaseDate, int quantityOfPages, String shortAnnotation,
			int authorId) {
		super();
		this.title = title;
		this.name = name;
		this.releaseDate = releaseDate;
		this.quantityOfPages = quantityOfPages;
		this.shortAnnotation = shortAnnotation;
		this.authorId = authorId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getQuantityOfPages() {
		return quantityOfPages;
	}

	public void setQuantityOfPages(int quantityOfPages) {
		this.quantityOfPages = quantityOfPages;
	}

	public String getShortAnnotation() {
		return shortAnnotation;
	}

	public void setShortAnnotation(String shortAnnotation) {
		this.shortAnnotation = shortAnnotation;
	}

}
