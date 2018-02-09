package com.entities;
import com.entities.Student;
import com.interfaces.Dao;
import java.sql.*;
import java.util.*;
import com.mysql.jdbc.Driver;

public class StudentDao  implements Dao {
	public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/student";
	public static final String LOGIN = "root"; 
	public static final String PASSWORD ="root";
	public static final String SELECT_ALL_SQL ="SELECT *from students";

	public StudentDao(){
		System.out.println("Registering JDBC driver...");
		try {
			Class.forName(DRIVER_CLASS_NAME);
			System.out.println("Connection success");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver can not be registered");
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException{
		return DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
	}

	@Override
	public List <Student> selectAll() throws SQLException{
		Connection conn = getConnection();
		Statement statement = null;
		ResultSet rs = null;

		try{
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			//conn.setAutoCommit(true);
			statement = conn.createStatement();
			rs=statement.executeQuery(SELECT_ALL_SQL);
			List <Student> list  = new ArrayList<Student>();
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
			//conn.commit();
			return list;
		}
		catch (SQLException e){
			conn.rollback();
			System.out.println("Error in connection");
			e.printStackTrace();
			return null;
		}
		 finally{
				try{
					conn.close();
					statement.close();
					rs.close();
				}
				catch (Exception e){
					System.out.println("Can't close a connection or iterator or statement");
					e.printStackTrace();
				}
								
		}
	}
}

