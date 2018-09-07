package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class rsdb {
	private static String username = "root";
	private static String pass="";
//	private static String url ="jdbc:mysql://localhost/property";
	private static String url ="jdbc:sqlite:C:/WorkShops/JavaFX/realestate/property.sqlite";
	public static Connection sqlcon() throws SQLException{
		return DriverManager.getConnection(url, username, pass);
	}
}
