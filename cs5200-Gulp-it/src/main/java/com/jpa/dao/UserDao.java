package com.jpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://cs5200-spring2018-rayat.crjlq7zubuca.us-east-2.rds.amazonaws.com/hw2_rayat_aman_spring_2018";

		final String USER = "rayat";
		final String PASS = "Aman1956";
		static Connection conn = null;
		static PreparedStatement statement = null;
		static ResultSet resultset = null;
		
		public static UserDao instance = null;
		
		public static UserDao getInstance() {
			if (instance == null) {
				instance = new UserDao();
			}
			return instance;
		}
		private UserDao() {}

		

}
