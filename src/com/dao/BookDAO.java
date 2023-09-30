package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.databaseConnection.Connect;
import com.entity.Book;

public class BookDAO implements DatabaseDAO<Book> {
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rst;
	private String sqlQuery;

	@Override
	public void create(Book entity) {
		connection = Connect.getConnection();
		sqlQuery = "insert into book (title,name,releaseDate,quantityOfPages,shortAnnotation,authorId) values(?,?,?,?,?,?)";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setString(1, entity.getTitle());
			pst.setString(2, entity.getName());
			pst.setDate(3, java.sql.Date.valueOf(entity.getReleaseDate()));
			pst.setInt(4, entity.getQuantityOfPages());
			pst.setString(5, entity.getShortAnnotation());
			pst.setInt(6, entity.getAuthorId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}
	}

	public List<Book> getAllBooks() {
		List<Book> listBooks = new ArrayList<Book>();
		connection = Connect.getConnection();
		sqlQuery = "select * from book";
		try {
			pst = connection.prepareStatement(sqlQuery);
			rst = pst.executeQuery();
			while (rst.next()) {
				Book book = new Book();
				book.setId(rst.getInt(1));
				book.setTitle(rst.getString(2));
				book.setName(rst.getString(3));
				book.setReleaseDate(rst.getDate(4).toLocalDate());
				book.setQuantityOfPages(rst.getInt(5));
				book.setShortAnnotation(rst.getString(6));
				book.setAuthorId(book.getAuthorId());
				listBooks.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}
		return listBooks;

	}

	@Override
	public Book find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Book entity) {
		connection = Connect.getConnection();
		sqlQuery = "update book set title=?, name=?,releaseDate=?, quantityOfPages=?, shortAnnotation=?, authorId=? where id=?";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setString(1, entity.getTitle());
			pst.setString(2, entity.getName());
			pst.setDate(3, java.sql.Date.valueOf(entity.getReleaseDate()));
			pst.setInt(4, entity.getQuantityOfPages());
			pst.setString(5, entity.getShortAnnotation());
			pst.setInt(6, entity.getAuthorId());
			pst.setInt(7, entity.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}

	}

	@Override
	public void delete(int id) {
		connection = Connect.getConnection();
		sqlQuery = "delete from book where id=?";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setInt(1, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}
	}

	public Map<Integer, Integer> findConnectedCharactersAndBooks(int characterId, int bookId) {
		Map<Integer, Integer> bookAndSeriesIds = new HashMap<>();
		connection = Connect.getConnection();
		sqlQuery = "select * from  booksandseries where bookId=? and seriesId=?";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setInt(1, characterId);
			pst.setInt(2, bookId);
			rst = pst.executeQuery();
			while (rst.next()) {
				bookAndSeriesIds.put(rst.getInt(1), rst.getInt(2));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}

		return bookAndSeriesIds;
	}

	public void addBookToTheSeries(int bookId, int seriesId) {
		connection = Connect.getConnection();
		sqlQuery = "insert into booksandseries(bookId,seriesId) values(?,?)";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setInt(1, bookId);
			pst.setInt(2, seriesId);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}

	}
}
