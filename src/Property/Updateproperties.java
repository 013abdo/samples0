package Property;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import application.rsdb;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Realty;

public class Updateproperties implements Initializable {
	Connection con = null;
	Statement st = null;
	ResultSet rs  = null;
	private Stage editdialog; 
	private Realty real;
	private boolean okClicked = false;

	@FXML private TextField property_numfild;
	@FXML private TextField property_namefild;
	@FXML private TextField property_typefild;
	@FXML private TextArea property_addressfild;
	@FXML private TextField property_cityfild;
	@FXML private TextField property_zipfild;
	@FXML private TextField floor_nofild;
	@FXML private TextField apartment_nofild;
	@FXML private TextField room_nofild;
	@FXML private TextField kitchen_nofild;
	@FXML private TextField toilet_nofild;
	@FXML private TextField hall_nofild;

	@FXML private ChoiceBox<Integer> card;

	public void setEditdialog(Stage editdialog) {
		this.editdialog = editdialog;
	}
	public boolean isOkClicked() {
		return okClicked;
	}


	public void setRealty(Realty real){
		this.real = real;
		property_numfild.setText(Integer.toString(real.getProperty_num()));
		property_namefild.setText(real.getProperty_name());
		property_typefild.setText(real.getProperty_type());
		property_addressfild.setText(real.getProperty_address());
		property_cityfild.setText(real.getProperty_city());
		property_zipfild.setText(real.getProperty_zip());
		floor_nofild.setText(Integer.toString(real.getFloor_no()));
		apartment_nofild.setText(Integer.toString(real.getApartment_no()));
		room_nofild.setText(Integer.toString(real.getRoom_no()));
		kitchen_nofild.setText(Integer.toString(real.getKitchen_no()));
		toilet_nofild.setText(Integer.toString(real.getToilet_no()));
		hall_nofild.setText(Integer.toString(real.getHall_no()));	
	}
	@FXML public void office(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				ResultSet 	rs = st.executeQuery("select office_num from office") ;
				){
			while(rs.next()){
			card.getItems().add(rs.getInt("office_num"));
			}
			}catch(Exception e){
					e.getMessage();
				}
	}

	public void save(){
if(isValid()){
		real.setProperty_num(Integer.parseInt(property_numfild.getText()));
		real.setProperty_name(property_namefild.getText());
		real.setProperty_type(property_typefild.getText());
		real.setProperty_address(property_addressfild.getText());
		real.setProperty_city(property_cityfild.getText());
		real.setProperty_zip(property_zipfild.getText());
		real.setFloor_no(Integer.parseInt(floor_nofild.getText()));
		real.setApartment_no(Integer.parseInt(apartment_nofild.getText()));
		real.setRoom_no(Integer.parseInt(room_nofild.getText()));
		real.setKitchen_no(Integer.parseInt(kitchen_nofild.getText()));
		real.setToilet_no(Integer.parseInt(toilet_nofild.getText()));
		real.setHall_no(Integer.parseInt(hall_nofild.getText()));
		
		try(
				Connection con = rsdb.sqlcon();
				Statement stmt = con.createStatement();
				){
			String edit = "UPDATE `residential_units` SET `property_name`='"+property_namefild.getText()+"',`property_type`='"+property_typefild.getText()+"',`property_address`='"+property_addressfild.getText()+"',`property_city`='"+property_cityfild.getText()+"',`property_zip`='"+property_zipfild.getText()+"'"
					+ ",`no_floor`='"+floor_nofild.getText()+"',`no_apartment`='"+apartment_nofild.getText()+"',`no_room`='"+room_nofild.getText()+"',`no_hall`='"+hall_nofild.getText()+"',`no_toilet`='"+toilet_nofild.getText()+"',`no_kitchen`='"+kitchen_nofild.getText()+"',`office_num`='"+card.getValue().toString()+"' WHERE `property_num`='"+property_numfild.getText()+"'";
			stmt.executeUpdate(edit);
		}catch(Exception e){
			e.printStackTrace();
		}
		okClicked = true;
		editdialog.close();
	}
	}
	public void cancel(){
		editdialog.close();
	}
	
	private boolean isValid() {
String error="";
if(property_namefild.getText() == null || property_namefild.getText().length()==0){
	error = "Invalid Value of Property_name!!";
	System.out.println(error = "Invalid Value of Property_name!!");
}
if(property_typefild.getText() == null || property_typefild.getText().length()==0){
	error = "Invalid Value of Property_type!!";
	System.out.println(error = "Invalid Value of Property_type!!");
}
if(property_cityfild.getText() == null || property_cityfild.getText().length()==0){
	error = "Invalid Value of Property_city!!";
	System.out.println(error = "Invalid Value of Property_city!!");
}
if(property_addressfild.getText() == null || property_addressfild.getText().length()==0){
	error = "Invalid Value of Property_address!!";
	System.out.println(error = "Invalid Value of Property_address!!");
}
if(property_zipfild.getText() == null || property_zipfild.getText().length()==0){
	error = "Invalid Value of Property_zip!!";
	System.out.println(error = "Invalid Value of Property_zip!!");
}
//the size (number of components in one property) 
if(floor_nofild.getText() == null || floor_nofild.getText().length()==0){
	error = "Invalid Value for floors numbers!!";
	System.out.println(error = "Invalid Value for floors numbers!!");
}else{
	try{
		Integer.parseInt(floor_nofild.getText());
	}catch(NumberFormatException n){
		error = "Invalid Value Must it be an Int!!";
		System.out.println(error = "Invalid Value Must it be an Int!!");
	}
}
if(apartment_nofild.getText() == null || apartment_nofild.getText().length()==0){
	error = "Invalid Value Must it be an Int!!";
	System.out.println(error = "Invalid Value Must it be an Int!!");
}else{
	try{
		Integer.parseInt(apartment_nofild.getText());
	}catch(NumberFormatException n){
		error = "Invalid Value Must it be an Int!!";
		System.out.println(error = "Invalid Value Must it be an Int!!");
	}
}
if(room_nofild.getText() == null || room_nofild.getText().length()==0){
	error = "Invalid Value for rooms numbers!!";
	System.out.println(error = "Invalid Value for room numbers!!");
}else{
	try{
		Integer.parseInt(room_nofild.getText());
	}catch(NumberFormatException n){
		error = "Invalid Value Must it be an Int!!";
		System.out.println(error = "Invalid Value Must it be an Int!!");
	}
}
if(hall_nofild.getText() == null || hall_nofild.getText().length()==0){
	error = "Invalid Value for halls numbers!!";
	System.out.println(error = "Invalid Value for halls numbers!!");
}else{
	try{
		Integer.parseInt(hall_nofild.getText());
	}catch(NumberFormatException n){
		error = "Invalid Value Must it be an Int!!";
		System.out.println(error = "Invalid Value Must it be an Int!!");
	}
}
if(kitchen_nofild.getText() == null || kitchen_nofild.getText().length()==0){
	error = "Invalid Value for kitchens numbers!!";
	System.out.println(error = "Invalid Value for kitchens numbers!!");
}else{
	try{
		Integer.parseInt(kitchen_nofild.getText());
	}catch(NumberFormatException n){
		error = "Invalid Value Must it be an Int!!";
		System.out.println(error = "Invalid Value Must it be an Int!!");
	}
}
if(toilet_nofild.getText() == null || toilet_nofild.getText().length()==0){
	error = "Invalid Value for toilets numbers!!";
	System.out.println(error = "Invalid Value for toilets numbers!!");
}else{
	try{
		Integer.parseInt(toilet_nofild.getText());
	}catch(NumberFormatException n){
		error = "Invalid Value Must it be an Int!!";
		System.out.println(error = "Invalid Value Must it be an Int!!");
	}
}
if(error.length()==0){
	return true;
}else{
		return false;
	}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		office();
	}
}
