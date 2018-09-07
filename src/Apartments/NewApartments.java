package Apartments;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.rsdb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class NewApartments implements Initializable{
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null;  
	
	private Stage adddialog;
	private boolean okClicked = false;
	
	@FXML private TextField apartment_nofild;
	@FXML private TextField  room_nofild;
	@FXML private TextField  kitchen_nofild;
	@FXML private TextField  toilet_nofild;
	@FXML private TextField  hall_nofild;
	@FXML private TextField  insurancefild;
	@FXML private TextField  amountfild;
	@FXML private TextField  pricefild;
	@FXML private TextField  rent_statefild;
	@FXML private RadioButton free;
	  @FXML private RadioButton rented;
	  @FXML private ComboBox<String> names;
	  
	  public void housename(){
		  try(Connection con = rsdb.sqlcon();
					Statement st = con.createStatement();
					ResultSet 	rs = st.executeQuery("select property_name from residential_units") ;
					){
			  while(rs.next()){
					names.getItems().add(rs.getString("property_name"));
					}
		  }catch(Exception e)
		  {
			  e.getMessage();  
		  } 
	  }

	public Stage getAdddialog() {
		return adddialog;
	}
	public void setAdddialog(Stage adddialog) {
		this.adddialog = adddialog;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	
	
	public void add(){
		String state;
		if(this.rented.isSelected()){
			state ="Rented!";
		}else{
			state="Free";
		}
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				){
	String section="INSERT INTO `apartments`(`apartment_no`, `no.room`, `no.hall`, `no.toilet`, `no.kitchen`, `amount`, `rent_state`, `insurance`, `price`,`property_name`)"+ 
	" VALUES "
	   +"('"+apartment_nofild.getText()+"','"+room_nofild.getText()+"','"+hall_nofild.getText()+"','"+toilet_nofild.getText()+"','"+kitchen_nofild.getText()+"','"+amountfild.getText()+"','"+state+"','"+insurancefild.getText()+"','"+pricefild.getText()+"','"+names.getValue().toString()+"')";
	st.executeUpdate(section);
		
	/*	try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/ApartmentsPage.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			System.out.println(e);
		}*/
	okClicked = true;
	adddialog.close();
	}
	catch(Exception e){
	System.out.println(e);
	}
		
	}
	public void cancel(){
	/*	try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/ApartmentsPage.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		adddialog.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		housename();
	}
	}

