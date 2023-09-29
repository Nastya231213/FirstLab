package com.databaseConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connect {
	private static Connection connection;
	private static Properties properties = new Properties();

	public static Connection getConnection() {
		try {
			InputStream input = Connect.class.getResourceAsStream("config.properties");
			properties.load(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String userName = properties.getProperty("db.username");
		String password = properties.getProperty("db.password");
		String URL = properties.getProperty("db.url");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, userName, password);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
