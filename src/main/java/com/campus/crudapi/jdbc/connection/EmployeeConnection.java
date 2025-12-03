package com.campus.crudapi.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class EmployeeConnection {

	public static Connection getEmployeeConnection() {
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			String url="jdbc:mysql://localhost:3306/jdbc-a3";
			String username="root";
			String password="root";
			
			return DriverManager.getConnection(url,username,password);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
