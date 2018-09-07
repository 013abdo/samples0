package Rental;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.rsdb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Apartment;
import model.Lease;
import model.Realty;
import model.Rent;

public class Updaterenting implements Initializable{
	Connection con =null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private Stage editing; 
	private Rent rent;
	private boolean okClicked = false;
	
	@FXML private TextField floor_nofild;
	@FXML private TextField apartment_nofild;
	@FXML private TextField  lease_nofild;
	@FXML private TextField rent_idfild;
	@FXML private TextField property_numfild;
	@FXML private TextField rent_statefild;
	
	@FXML private ComboBox<Integer> leases;
	@FXML private ComboBox<Integer> apartment;
	@FXML private ComboBox<Integer> property;
	ObservableList<Apartment> rooms = FXCollections.observableArrayList();
	ObservableList<Realty> house = FXCollections.observableArrayList();
	ObservableList<Lease> sheet = FXCollections.observableArrayList();
	
	@FXML private RadioButton rented;
	@FXML private RadioButton  free;

	public Stage getEditing() {
		return editing;
	}
	public Rent getRent() {
		return rent;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setEditing(Stage editing) {
		this.editing = editing;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	public void setRent(Rent rent){
		this.rent = rent;
		floor_nofild.setText(Integer.toString(rent.getFloor_no()));
//		apartment_nofild.setText(Integer.toString(rent.getApartment_no()));
//		lease_nofild.setText(Integer.toString(rent.getLease_no()));
		rent_idfild.setText(Integer.toString(rent.getRent_id()));
//		property_numfild.setText(Integer.toString(rent.getProperty_num()));
	//	rent_statefild.setText(rent.getRent_state());
	}
	public void sheets(){
		try(Connection con = rsdb.sqlcon();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select lease_no from leases")
				){
			while(rs.next()){
				leases.getItems().add(rs.getInt("lease_no"));
			}
		}catch(Exception e){
		e.getMessage();	
		}
	}
	public void apartmentslist(){
		try(Connection con = rsdb.sqlcon();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select apartment_no from apartments")
				){
			while(rs.next()){
			apartment.getItems().add(rs.getInt("apartment_no"));
			}
		}catch(Exception e){
		e.getMessage();	
		}
	}
	public void propertylist(){
		try(Connection con = rsdb.sqlcon();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select property_num from residential_units")
				){
			while(rs.next()){
			property.getItems().add(rs.getInt("property_num"));
			}
		}catch(Exception e){
		e.getMessage();	
		}
	}
	public void Ok(){
		String state;
//		if(isValid()){
		if(rented.isSelected()){
			state ="Rented!";
		}else{
			state ="Free";
		}
	/*	rent.setFloor_no(Integer.parseInt(floor_nofild.getText()));
		rent.setApartment_no(Integer.parseInt(apartment_nofild.getText()));
		rent.setLease_no(Integer.parseInt(lease_nofild.getText()));
		rent.setRent_id(Integer.parseInt(rent_idfild.getText()));
		rent.setProperty_num(Integer.parseInt(property_numfild.getText()));*/
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				){
			String edit ="UPDATE `rent` SET `property_num`='"+property.getValue()+"',`floor_no`='"+floor_nofild.getText()+"',`apartment_no`='"+apartment.getValue()+"',`lease_no`='"+leases.getValue()+"',`rent_state`='"+state+"' WHERE `rent_id`='"+rent_idfild.getText()+"'";
			st.executeUpdate(edit);
			okClicked = true;
			editing.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		}
//	}
public boolean isValid(){
	String error ="";
	if(floor_nofild.getText()==null||floor_nofild.getText().length()==0){
		error = "Invalid Value";
		System.out.println(error = "Invalid Value");
	}else{
		try{
			Integer.parseInt(floor_nofild.getText());
		}catch(NumberFormatException n){
			error = "Invalid Value Must it be an Int!!";
			System.out.println(error = "Invalid Value Must it be an Int!!");
		}
	}
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
	if(lease_nofild.getText()==null||lease_nofild.getText().length()==0){
		error = "Invalid Value";
		System.out.println(error = "Invalid Value");
	}else{
		try{
			Integer.parseInt(lease_nofild.getText());
		}catch(NumberFormatException n){
			error = "Invalid Value Must it be an Int!!";
			System.out.println(error = "Invalid Value Must it be an Int!!");
		}
	}	
	if(rent_idfild.getText()==null||rent_idfild.getText().length()==0){
		error = "Invalid Value";
		System.out.println(error = "Invalid Value");
	}else{
		try{
			Integer.parseInt(rent_idfild.getText());
		}catch(NumberFormatException n){
			error = "Invalid Value Must it be an Int!!";
			System.out.println(error = "Invalid Value Must it be an Int!!");
		}
	}	
	if(property_numfild.getText()==null||property_numfild.getText().length()==0){
		error = "Invalid Value";
		System.out.println(error = "Invalid Value");
	}else{
		try{
			Integer.parseInt(property_numfild.getText());
		}catch(NumberFormatException n){
			error = "Invalid Value Must it be an Int!!";
			System.out.println(error = "Invalid Value Must it be an Int!!");
		}
	}
	return false;
	
}
	public void cancel(){
		editing.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
apartmentslist();
sheets();
propertylist();
	}
}
