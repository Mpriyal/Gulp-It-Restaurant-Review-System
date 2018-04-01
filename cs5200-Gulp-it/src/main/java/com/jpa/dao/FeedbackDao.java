package com.jpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FeedbackDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://cs5200-spring2018-mittal.c9fddtskt253.us-east-2.rds.amazonaws.com/GulpIt";
	final String USER = "Mpriyal";
	final String PASS = "Priyaldbms94!";
	static Connection conn = null;
	static PreparedStatement statement = null;
	static ResultSet resultset = null;

	public static FeedbackDao instance = null;

	public static FeedbackDao getInstance() {
		if (instance == null) {
			instance = new FeedbackDao();
		}
		return instance;
	}
	private FeedbackDao() {}
	
//	public int addNewFeedback() {
//		
//	}
}
