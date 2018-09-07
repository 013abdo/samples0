package control;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import Apartments.NewApartments;
import Apartments.UpdateApartmentDialog;
import application.Main;
import application.rsdb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Apartment;
import model.Lease;
import model.Realty;



public class Apartments implements Initializable {
	Connection con =null;
	Statement stmt = null;
	ResultSet rs = null;
private Stage section;

	@FXML private TableView<Apartment> flat;
	@FXML private TableColumn<Apartment, String> property_nameCol ;
	@FXML private TableColumn<Apartment, Integer> apartment_noCol;
	@FXML private TableColumn<Apartment, Integer> room_noCol;
	@FXML private TableColumn<Apartment, Integer> kitchen_noCol;
	@FXML private TableColumn<Apartment, Integer> toilet_noCol;
	@FXML private TableColumn<Apartment, Integer> hall_noCol;
	@FXML private TableColumn<Apartment, String> insuranceCol;
    @FXML private TableColumn<Apartment, Integer> priceCol;
	@FXML private TableColumn<Apartment, String> amountCol;
	@FXML private TableColumn<Apartment, String> rent_stateCol;
	
	@FXML private TextField apartment_nofild;
	@FXML private TextField  room_nofild;
	@FXML private TextField  kitchen_nofild;
	@FXML private TextField  toilet_nofild;
	@FXML private TextField  hall_nofild;
	@FXML private TextField  insurancefild;
	@FXML private TextField  amountfild;
	@FXML private TextField  pricefild;
	@FXML private TextField  rent_statefild;
	@FXML private TextField filtring;
	@FXML private TextField deletefild;

	ObservableList<Apartment> renting = FXCollections.observableArrayList();

	public Stage getSection() {
		return section;
	}

	public void setSection(Stage section) {
		this.section = section;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loaddata();
		property_nameCol.setCellValueFactory(new PropertyValueFactory<Apartment,String>("property_name")) ;
		apartment_noCol.setCellValueFactory(new PropertyValueFactory<Apartment,Integer> ("apartment_no"));
		room_noCol.setCellValueFactory(new PropertyValueFactory<Apartment,Integer> ("room_no"));
		kitchen_noCol.setCellValueFactory(new PropertyValueFactory<Apartment,Integer> ("kitchen_no"));
		toilet_noCol.setCellValueFactory(new PropertyValueFactory<Apartment,Integer> ("toilet_no"));
		hall_noCol.setCellValueFactory(new PropertyValueFactory<Apartment,Integer> ("hall_no"));
		insuranceCol.setCellValueFactory(new PropertyValueFactory<Apartment,String> ("insurance"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Apartment,Integer> ("price"));
		amountCol.setCellValueFactory(new PropertyValueFactory<Apartment,String> ("amount"));
		rent_stateCol.setCellValueFactory(new PropertyValueFactory<Apartment,String> ("rent_state"));
	}

	public void loaddata(){
		try(	Connection con = rsdb.sqlcon();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM `apartments`");
				){
			while(rs.next()){
				flat.getItems().add(new Apartment(rs.getInt("apartment_no"),rs.getInt("no.room"),rs.getInt("no.kitchen"), rs.getInt("no.toilet"),rs.getInt("no.hall"),
						rs.getString("amount"),rs.getInt("price"),rs.getString("insurance"), rs.getString("rent_state"),rs.getString("property_name")));
				flat.setItems(renting);
			}
		}catch(Exception e){
			e.getMessage();
		}
	}
	public void floor(){
			try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("/view/floor.fxml"));
		AnchorPane anchorpane = loader.load();
		Scene scene = new Scene(anchorpane);
		Main.office.setScene(scene);
		Main.office.show();
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	public void Newapartment() throws IOException{
	/*	try{
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass()
			.getResource("/view/Newapartments.fxml"));
	AnchorPane anchorpane = loader.load();
	Scene scene = new Scene(anchorpane);
	Main.office.setScene(scene);
	Main.office.show();
}catch(Exception e){
	e.printStackTrace();
}*/
		add();
	}
	public void editapartment() throws IOException{
	/*	try{
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass()
			.getResource("/view/UpdateApartment.fxml"));
	AnchorPane anchorpane = loader.load();
	Scene scene = new Scene(anchorpane);
	Main.office.setScene(scene);
	Main.office.show();
}catch(Exception e){
	e.printStackTrace();
}*/
		Apartment selectedplace = flat.getSelectionModel().getSelectedItem();
		if(selectedplace !=null){
			boolean okClicked = edit(selectedplace);
			if(okClicked){
				shwaprtmentdetail(selectedplace);
			}
		}
	}
	public boolean add() throws IOException{
		FXMLLoader registdialog = new FXMLLoader();
		registdialog.setLocation(getClass().getResource("/view/Newapartments.fxml"));
		AnchorPane anc = registdialog.load();
		Stage regist = new Stage();
		regist.setTitle("New apartment");
		regist.initModality(Modality.WINDOW_MODAL);
		regist.initOwner(section);
		Scene n = new Scene(anc);
		regist.setScene(n);
		regist.show();
		NewApartments a = registdialog.getController(); 
		a.setAdddialog(regist);
		return a.isOkClicked();
	}
	public boolean edit(Apartment apart) throws IOException{
		FXMLLoader resetdialog = new FXMLLoader();
		resetdialog.setLocation(getClass().getResource("/view/UpdateApartment.fxml"));
		AnchorPane anc = resetdialog.load();
		Stage reset = new Stage();
		reset.setTitle("Edit apartment");
		reset.initModality(Modality.WINDOW_MODAL);
		reset.initOwner(section);
		Scene n = new Scene(anc);
		reset.setScene(n);
		reset.show();
		UpdateApartmentDialog u = resetdialog.getController(); 
		u.setDatingialog(reset);
		u.setApart(apart);
		return u.isOkClicked();
	}
	public void delete() {
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				){
			st.executeUpdate("delete from apartments where price = '"+deletefild.getText()+"'");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void shwaprtmentdetail(Apartment apart){
	if(apart!= null){
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
	public void back(){
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
	}
}
