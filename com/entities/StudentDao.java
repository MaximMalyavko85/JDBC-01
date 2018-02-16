package com.entities;
import com.entities.Student;
import com.interfaces.Dao;
import java.sql.*;
import java.util.*;
import com.mysql.jdbc.Driver;
import java.io.*;

public class StudentDao implements Dao {
	Properties prop;
	public static String JDBC_URL;
	public static String LOGIN;
	public static String PASSWORD;
	public static String SELECT_ALL;
	
	public StudentDao(){
		try{
			prop = new Properties();
			prop.load(new FileInputStream ("com/properties/setDb.properties"));
			this.JDBC_URL = prop.getProperty("JDBC_URL");
			this.LOGIN = prop.getProperty("LOGIN");
			this.PASSWORD = prop.getProperty("PASSWORD");
			this.SELECT_ALL = prop.getProperty("SELECT_ALL_SQL");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			Class.forName(prop.getProperty("DRIVER_CLASS_NAME"));
			System.out.println("Connection success");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver can not be registered");
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException{
		return DriverManager.getConnection(this.JDBC_URL, this.LOGIN, this.PASSWORD);
	}

	@Override
	public List <Student> selectAll() throws SQLException{
		ResultSet rs = null;
		Connection conn = null;
		Statement statement = null;
		List <Student> list  = new ArrayList<Student>();
		try 
		{	
			conn = getConnection();			
			statement = conn.createStatement();
			rs=statement.executeQuery(this.SELECT_ALL);
						
			while (rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				int cource = rs.getInt("cource");

				Student student = new Student();
				student.setId(id);
				student.setName(name);
				student.setAge(age);
				student.setCource(cource);
				list.add(student);
			}
						
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			if (rs != null){
		    	try {
		    		rs.close();
		    	}
		    	catch (SQLException e) {
		    		e.printStackTrace();
		    	}
			}
			if (statement != null){
		    	try {
		    		statement.close();
		    	}
		    	catch (SQLException e) {
		    		e.printStackTrace(); 
		    	}
		    }
			if (conn != null){
			    try {
			    	conn.close();
			    }
			    catch (SQLException e) {
			    	e.printStackTrace();
			    }
			}
		}

		return list;			
	}
}

