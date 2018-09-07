package view;


import java.sql.*;

import application.Main;
import application.rsdb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Registration {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	@FXML private TextField usernamefild;
	@FXML private TextField passwordfild;
	@FXML private TextField typefild;

	public void sign(){
		try(
				Connection con = rsdb.sqlcon();
			Statement st = con.createStatement();
				){
			String signup = "INSERT INTO `log`(`username`, `pass`, `type`) "
					+ "VALUES ('"+usernamefild.getText()+"','"+passwordfild.getText()+"','"+typefild.getText()+"')";
	st.executeUpdate(signup);
	try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("/view/login.fxml"));
		AnchorPane anchorpane = loader.load();
		Scene scene = new Scene(anchorpane);
		Main.office.setScene(scene);
		Main.office.show();
	}catch(Exception e){
		e.printStackTrace();
}
		}catch(SQLException e){
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
	}
	public void can(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/login.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
	}
	}
}
