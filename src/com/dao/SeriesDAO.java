package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.databaseConnection.Connect;
import com.entity.Series;
import com.entity.SeriesType;

public class SeriesDAO implements DatabaseDAO<Series> {
	private Connection connection;
	private PreparedStatement pst;
	private String sqlQuery;
	private ResultSet res;

	@Override
	public void create(Series entity) {
		connection = Connect.getConnection();
		sqlQuery = "insert into series(name,dateStart,description,type) values(?,?,?,?)";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setString(1, entity.getName());
			pst.setDate(2, java.sql.Date.valueOf(entity.getDateOfTheStart()));
			pst.setString(3, entity.getDescription());
			pst.setString(4, entity.getSeriesType().getSeriesTypeName());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		Connect.closeConnection(connection);
	}

	public List<Series> getAllTheSeries() {
		List<Series> listSeries = new ArrayList<Series>();
		connection = Connect.getConnection();
		sqlQuery = "select * from series";

		try {
			pst = connection.prepareStatement(sqlQuery);
			res = pst.executeQuery();
			while (res.next()) {
				Series newSeries = new Series();
				newSeries.setId(res.getInt(1));
				newSeries.setName(res.getString(2));
				newSeries.setDateOfTheStart(res.getDate(3).toLocalDate());
				newSeries.setDescription(res.getString(4));
				newSeries.setSeriesType(SeriesType.getSeriesTypeFromString(res.getString(5)));
				listSeries.add(newSeries);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSeries;
	}

	public int getTheLastID() {
		connection = Connect.getConnection();
		int lastIndex = 0;
		sqlQuery = "select max(id) from series";
		try {
			pst = connection.prepareStatement(sqlQuery);
			res = pst.executeQuery();
			lastIndex = res.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastIndex;

	}

	@Override
	public Series find(int id) {
		return null;
	}

	@Override
	public void update(Series entity) {
		connection = Connect.getConnection();
		sqlQuery = "update series set name=?, dateStart=?, description=?, type=? where id=?";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setString(1, entity.getName());
			pst.setDate(2, java.sql.Date.valueOf(entity.getDateOfTheStart()));
			pst.setString(3,entity.getDescription());
			pst.setString(4, entity.getSeriesType().getSeriesTypeName());
			pst.setInt(5, entity.getId());
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
		sqlQuery = "delete from series where id=?";
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
}
