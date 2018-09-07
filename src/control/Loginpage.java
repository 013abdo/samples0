package control;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.rsdb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.User;

public class Loginpage implements Initializable{
@FXML private TextField usernamefild;
@FXML private PasswordField passwordfild;
@FXML private Label error;
@FXML private RadioButton emp;
@FXML private RadioButton admin;

public void loaddatabasedata(){
	try(
			Connection con = rsdb.sqlcon();
		Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from log");
			){
		while(rs.next()){}
	
	}catch(SQLException e){
		System.out.println(e.getSQLState());
		System.out.println(e.getMessage());
	}
}
public void go(){
	
	if(usernamefild.getText() == null && passwordfild.getText() == null){
		error.setText("Please fill the fields");
	}else if(usernamefild.getText().equals("admin")&& passwordfild.getText().equals("pass")){
			try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass()
						.getResource("/view/home.fxml"));
				AnchorPane anchorpane = loader.load();
				Scene scene = new Scene(anchorpane);
				Main.office.setScene(scene);
				Main.office.show();
			}catch(Exception e){
				e.printStackTrace();
		}
	}else if(usernamefild.getText().equals("fatah")&& passwordfild.getText().equals("12345")){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/users.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
	}
		}
}
public void signup(){
	try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("/view/signup.fxml"));
		AnchorPane anchorpane = loader.load();
		Scene scene = new Scene(anchorpane);
		Main.office.setScene(scene);
		Main.office.show();
	}catch(Exception e){
		e.printStackTrace();
}
	}
@Override
public void initialize(URL location, ResourceBundle resources) {
	if(usernamefild.getText() == null && passwordfild.getText() == null){
		error.setText("Please fill the fields");
	}else if(usernamefild.getText().equals("admin")&& passwordfild.getText().equals("pass")){
			try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass()
						.getResource("/view/home.fxml"));
				AnchorPane anchorpane = loader.load();
				Scene scene = new Scene(anchorpane);
				Main.office.setScene(scene);
				Main.office.show();
			}catch(Exception e){
				e.printStackTrace();
		}
	}else if(usernamefild.getText().equals("fatah")&& passwordfild.getText().equals("12345")){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/users.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
	}
		}
}

}
