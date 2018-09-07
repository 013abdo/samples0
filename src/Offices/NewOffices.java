package Offices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import application.Main;
import application.rsdb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewOffices {
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null;  
	private Stage deskdialog;
	private boolean okClicked = false;
	public Stage getDeskdialog() {
		return deskdialog;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setDeskdialog(Stage deskdialog) {
		this.deskdialog = deskdialog;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}

	

@FXML private TextField office_numfild;
@FXML private TextField office_namefild;
@FXML private TextField office_phonefild;
@FXML private TextField office_telfild;
@FXML private TextArea office_addressfild;
@FXML private TextField office_cityfild;
@FXML private TextField office_ownerfild;

public void Ok(){
	try(Connection con=  rsdb.sqlcon();
			Statement stmt = con.createStatement();
			) {
		String in = "INSERT INTO `office`( `office_name`, `office_phone`, `office_fax`, `office_address`, `office_city`, `office_owner`) "
				+ "VALUES ('"+office_namefild.getText()+"','"+office_phonefild.getText()+"','"+office_telfild.getText()+"','"+office_addressfild.getText()+"','"+office_cityfild.getText()+"','"+office_ownerfild.getText()+"')";
	stmt.executeUpdate(in);
		/*try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/Office.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	okClicked =true;
	deskdialog.close();
}
public void cancel(){
	/*try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("/view/Office.fxml"));
		AnchorPane anchorpane = loader.load();
		Scene scene = new Scene(anchorpane);
		Main.office.setScene(scene);
		Main.office.show();
	}catch(Exception e){
		e.printStackTrace();
	}*/
	deskdialog.close();
}
	
}
