package interfaces;
import entities.Student;
import java.util.*;
import java.sql.SQLException;

public interface Dao{
	public List <Student> selectAll() throws SQLException;

}