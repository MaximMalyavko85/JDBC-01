package com.entities;
import com.entities.Student;
import com.interfaces.Dao;
import java.sql.*;
import java.util.*;
import com.mysql.jdbc.Driver;
import java.io.*;

public class StudentDao implements Dao {
	Properties prop;
	
	public StudentDao() throws Exception{
		prop = new Properties();
		
		try {
			prop.load(new FileInputStream ("com/properties/setDb.properties"));
			Class.forName(prop.getProperty("DRIVER_CLASS_NAME"));
			System.out.println("Connection success");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private Connection getConnection() throws SQLException{
		return DriverManager.getConnection(prop.getProperty("JDBC_URL"), prop.getProperty("LOGIN"), prop.getProperty("PASSWORD"));
	}

	@Override
	public List <Student> selectAll() throws Throwable{
		ResultSet rs = null;
		Connection conn = null;
		Statement statement = null;
		List <Student> list  = new ArrayList<Student>();
		try 
		{	
			conn = getConnection();			
			statement = conn.createStatement();
			rs=statement.executeQuery(prop.getProperty("SELECT_ALL_SQL"));
						
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
				try{
					closeResurs( rs,  statement,  conn);
				}catch(Exception e){
					System.out.println("Родительский");
					e.printStackTrace();
				}
		}

		return list;			
	}

	
	private void closeResurs(ResultSet rs, Statement statement, Connection conn) throws Exception{
			SQLException resourceCloseError=null;
			if(rs!=null){
				try {
					rs.close();
				}catch(SQLException e){
					resourceCloseError = e;
				}
			}
					
			if(statement!=null){					
				try {
					statement.close();
				}catch(SQLException e){
					resourceCloseError = e;
				}
			}

			if(conn!=null){	
				try {
					conn.close();
				}catch(SQLException e){
					resourceCloseError = e;
				}
			}

			if (resourceCloseError!=null){
				throw resourceCloseError;
			}	
	}	
			
}

