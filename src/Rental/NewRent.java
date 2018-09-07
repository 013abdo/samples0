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

public class NewRent implements Initializable {
	Connection con =null;
	Statement stmt = null;
	ResultSet rs = null;
	private Stage renting; 

	private boolean okClicked = false;
	@FXML private TextField floor_nofild;
	@FXML private TextField apartment_nofild;
	@FXML private TextField  lease_nofild;
	@FXML private TextField rent_idfild;
	@FXML private TextField property_numfild;
	@FXML private ComboBox<Integer> leases;
	@FXML private ComboBox<Integer> apartment;
	@FXML private ComboBox<Integer> property;
	ObservableList<Apartment> rooms = FXCollections.observableArrayList();
	ObservableList<Realty> house = FXCollections.observableArrayList();
	ObservableList<Lease> sheet = FXCollections.observableArrayList();

	@FXML private TextField rent_statefild;
	@FXML private RadioButton rented;
	 @FXML private RadioButton  free;
	
	public Stage getRenting() {
		return renting;
	}
	public void setRenting(Stage renting) {
		this.renting = renting;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
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
		if(rented.isSelected()){
			state ="Rented!";
		}else{
			state ="Free";
		}
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				){
			String rent ="INSERT INTO `rent`(`rent_id`, `property_num`, `floor_no`, `apartment_no`, `lease_no`, `rent_state`) "
					+ "VALUES ('"+floor_nofild.getText()+"','"+/*apartment_nofild.getText()*/apartment.getValue()+"','"+/*lease_nofild.getText()*/leases.getValue()+"','"+rent_idfild.getText()+"','"+/*property_numfild.getText()*/property.getValue()+"','"+state+"')";
		st.executeUpdate(rent);
	
		okClicked = true;
		renting.close();
		}catch(Exception r){
			r.getMessage();
		}
		
	}
	public void cancel(){

		renting.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
apartmentslist();
sheets();
propertylist();
	}
}
