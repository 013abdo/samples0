package Apartments;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import application.Main;
import application.rsdb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Apartment;

public class UpdateApartmentDialog {
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null;  

	private Stage datingialog;
	private Apartment apart;
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
	public Stage getDatingialog() {
		return datingialog;
	}
	public void setDatingialog(Stage datingialog) {
		this.datingialog = datingialog;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}

	public void update(){
		if(isValid()){
		String state;
		apart.setApartment_no(Integer.parseInt(apartment_nofild.getText()));
		apart.setRoom_no(Integer.parseInt(room_nofild.getText()));
		apart.setKitchen_no(Integer.parseInt(kitchen_nofild.getText()));
		apart.setToilet_no(Integer.parseInt(toilet_nofild.getText()));
		apart.setHall_no(Integer.parseInt(hall_nofild.getText()));
		apart.setInsurance(insurancefild.getText());
		apart.setAmount(amountfild.getText());
		apart.setPrice(Integer.parseInt(pricefild.getText()));
		if(this.rented.isSelected()){
			state ="Rented!";
		}else{
			state="Free";
		}
		
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				){
			String edit = "UPDATE `apartments` SET `no.room`='"+room_nofild.getText()+"',`no.hall`='"+hall_nofild.getText()+"',`no.toilet`='"+toilet_nofild.getText()+"',`no.kitchen`='"+kitchen_nofild.getText()+"',"
					+ "`amount`='"+amountfild.getText()+"',`rent_state`='"+state+"',`insurance`='"+insurancefild.getText()+"',`price`='"+pricefild.getText()+"' WHERE `apartment_no`='"+apartment_nofild.getText()+"'";
			st.executeUpdate(edit);	
			okClicked = true;
			datingialog.close();
		}catch(Exception e){
			System.out.println(e);		
			}
		}
	}
	public boolean isValid(){
		String error = "";
		if(apartment_nofild.getText()==null||apartment_nofild.getText().length()==0){
			error = "Invalid Value";
			System.out.println(error = "Invalid Value");
		}else{
			try{
				Integer.parseInt(apartment_nofild.getText());
			}catch(NumberFormatException n){
				error = "Invalid Value Must it be an Int!!";
				System.out.println(error = "Invalid Value Must it be an Int!!");
			}
		}
		if(room_nofild.getText()==null||room_nofild.getText().length()==0){
			error = "Invalid Value";
		}else{
			try{
				Integer.parseInt(room_nofild.getText());
			}catch(NumberFormatException n){
				error = "Invalid Value Must it be an Int!!";
				System.out.println(error = "Invalid Value Must it be an Int!!");
			}
		}
		if(kitchen_nofild.getText()==null||kitchen_nofild.getText().length()==0){
			error = "Invalid Value";
		}else{
			try{
				Integer.parseInt(kitchen_nofild.getText());
			}catch(NumberFormatException n){
				error = "Invalid Value Must it be an Int!!";
				System.out.println(error = "Invalid Value Must it be an Int!!");
			}
		}
		if(hall_nofild.getText()==null||hall_nofild.getText().length()==0){
			error = "Invalid Value";
		}else{
			try{
				Integer.parseInt(hall_nofild.getText());
			}catch(NumberFormatException n){
				error = "Invalid Value Must it be an Int!!";
				System.out.println(error = "Invalid Value Must it be an Int!!");
			}
		}
		if(toilet_nofild.getText()==null||toilet_nofild.getText().length()==0){
			error = "Invalid Value";
		}else{
			try{
				Integer.parseInt(toilet_nofild.getText());
			}catch(NumberFormatException n){
				error = "Invalid Value Must it be an Int!!";
				System.out.println(error = "Invalid Value Must it be an Int!!");
			}
		}
		if(pricefild.getText()==null||pricefild.getText().length()==0){
			error = "Invalid Value";
		}else{
			try{
				Integer.parseInt(pricefild.getText());
			}catch(NumberFormatException n){
				error = "Invalid Value Must it be an Int!!";
				System.out.println(error = "Invalid Value Must it be an Int!!");
			}
		}
	if(amountfild.getText() == null || amountfild.getText().length()==0){
		error = "Invalid Value";
		System.out.println(error = "Invalid Value");
	}
	if(insurancefild.getText() == null || insurancefild.getText().length()==0){
		error = "Invalid Value";
		System.out.println(error = "Invalid Value");
	}
		if(error.length()==0){
			return true;
		}else{
		return false;
		}
	}
	public void cancel(){
/*		try{
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
		datingialog.close();
	}
	public Apartment getApart() {
		return apart;
	}
	public void setApart(Apartment apart) {
		this.apart = apart;
		apartment_nofild.setText(Integer.toString(apart.getApartment_no()));
		room_nofild.setText(Integer.toString(apart.getRoom_no()));
		kitchen_nofild.setText(Integer.toString(apart.getKitchen_no()));
		toilet_nofild.setText(Integer.toString(apart.getToilet_no()));
		hall_nofild.setText(Integer.toString(apart.getHall_no()));
		insurancefild.setText(apart.getInsurance());
		amountfild.setText(apart.getAmount());
		pricefild.setText(Integer.toString(apart.getPrice()));
	}

}
