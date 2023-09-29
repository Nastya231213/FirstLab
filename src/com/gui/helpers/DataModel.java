package com.gui.helpers;

import java.util.Map;

import com.entity.Author;
import com.entity.Book;
import com.entity.Series;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

//This class we need to get the Id of the entity in another controller. In such a way we pass the information.
public class DataModel {
	private final static DataModel dataModel = new DataModel();
	private Author author;
	private Book book;
	private Series series;

	public Book getBook() {
		return book;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	public static DataModel getDataModel() {
		return dataModel;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
