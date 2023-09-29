package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.databaseConnection.Connect;
import com.entity.Author;

public class AuthorDAO implements DatabaseDAO<Author> {
	private ResultSet res;
	private Connection connection;
	private PreparedStatement pst;
	private String sqlQuery;

	@Override
	public void create(Author entity) {
		connection = Connect.getConnection();
		sqlQuery = "insert into author(fullname,pseudonym) values(?,?)";
		try {
			pst = connection.prepareStatement(sqlQuery);

			pst.setString(1, entity.getFullname());
			pst.setString(2, entity.getPseudonym());
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}

	}

	public List<Author> getAllAuthors() {
		List<Author> authorList = new ArrayList<Author>();
		connection = Connect.getConnection();
		sqlQuery = "select * from author";

		try {
			pst = connection.prepareStatement(sqlQuery);
			res = pst.executeQuery();
			while (res.next()) {
				Author author = new Author();
				author.setAuthorId(res.getInt(1));
				author.setFullname(res.getString(2));
				author.setPseudonym(res.getString(3));
				authorList.add(author);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}
		return authorList;
	}

	@Override
	public void update(Author entity) {
		connection = Connect.getConnection();
		sqlQuery = "update author set fullname=?, pseudonym=? where id=?";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setString(1, entity.getFullname());
			pst.setString(2, entity.getPseudonym());
			pst.setInt(3, entity.getAuthorId());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}
	}

	@Override
	public void delete(int id) {
	    connection = Connect.getConnection();
	    sqlQuery = "delete from author where id=?";
	    try {
	        pst = connection.prepareStatement(sqlQuery);
	        pst.setInt(1, id);
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Connect.closeConnection(connection);
	    }
	}
	@Override
	public Author find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
