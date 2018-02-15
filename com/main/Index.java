package com.main;
import com.entities.*;
import com.interfaces.Dao;
import java.sql.SQLException;


public class Index{
	public static void main(String [] arg) throws SQLException{
		StudentDao dao = new StudentDao();
		
		for (Student student: dao.selectAll()){
			System.out.println(student.toString());
		}	
  }


}	