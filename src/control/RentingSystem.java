package control;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import Rental.NewRent;
import Rental.Updaterenting;
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

import model.Rent;

public class RentingSystem implements Initializable {
	/*Connection con =null;
	Statement stmt = null;
	ResultSet rs = null;*/
	private Stage Rental;
	@FXML private TableView<Rent> floor;
	@FXML private TableColumn<Rent,Integer> floor_noCol;
	@FXML private TableColumn<Rent,Integer> apartment_noCol;
	@FXML private TableColumn<Rent,Integer>  lease_noCol;
	@FXML private TableColumn<Rent,Integer> rent_idCol;
	@FXML private TableColumn<Rent,Integer>property_numCol;
	@FXML private TableColumn<Rent,String> rent_stateCol;
	ObservableList<Rent> flat =FXCollections.observableArrayList();
	@FXML private TextField floor_nofild;
	@FXML private TextField apartment_nofild;
	@FXML private TextField  lease_nofild;
	@FXML private TextField rent_idfild;
	@FXML private TextField property_numfild;
	@FXML private TextField rent_statefild;
	@FXML private TextField deletefild;
	@FXML private TextField find;

	public Stage getRental() {
		return Rental;
	}

	public void setRental(Stage rental) {
		Rental = rental;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		floor_noCol.setCellValueFactory(new PropertyValueFactory<Rent,Integer> ("floor_no"));
		apartment_noCol.setCellValueFactory(new PropertyValueFactory<Rent,Integer> ("apartment_no"));
		lease_noCol.setCellValueFactory(new PropertyValueFactory<Rent,Integer> ("lease_no"));
		property_numCol.setCellValueFactory(new PropertyValueFactory<Rent,Integer> ("property_num"));
		rent_idCol.setCellValueFactory(new PropertyValueFactory<Rent,Integer> ("rent_id"));
		rent_stateCol.setCellValueFactory(new PropertyValueFactory<Rent,String> ("rent_state"));
		load();
	}

	public void load(){
		try(	Connection con = rsdb.sqlcon();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM `rent`");
				){
			while(rs.next()){
				floor.getItems().add(new Rent(rs.getInt("rent_id"),rs.getInt("property_num"),rs.getInt("floor_no"),rs.getInt("apartment_no"),rs.getInt("lease_no"),rs.getString("rent_state")));
				floor.setItems(flat);
			}
		}catch(Exception e){
			e.getMessage();
		}
	}
	public void showDetails(Rent rent){
		if(rent != null){
			floor_nofild.setText(Integer.toString(rent.getFloor_no()));
			apartment_nofild.setText(Integer.toString(rent.getApartment_no()));
			lease_nofild.setText(Integer.toString(rent.getLease_no()));
			rent_idfild.setText(Integer.toString(rent.getRent_id()));
			property_numfild.setText(Integer.toString(rent.getProperty_num()));
			//	rent_statefild.setText(rent.getRent_state());	
		}else{
			floor_nofild.setText("");
			apartment_nofild.setText("");
			lease_nofild.setText("");
			rent_idfild.setText("");
			property_numfild.setText("");
		}


	}

	public void add() throws IOException{
		dialog();
	}
	public boolean dialog() throws IOException{

		FXMLLoader rentdialog = new FXMLLoader();
		rentdialog.setLocation(getClass().getResource("/view/Newsheet.fxml"));
		AnchorPane anchor = rentdialog.load();
		Stage newrent = new Stage(); 
		newrent.setTitle("Add Rent sheets");
		newrent.initModality(Modality.WINDOW_MODAL);
		newrent.initOwner(Rental);
		Scene scene = new Scene(anchor);
		newrent.setScene(scene);
		newrent.show();
		NewRent r = rentdialog.getController();  
		r.setRenting(newrent);
		return r.isOkClicked();
	}
	public void edit() throws IOException{
		Rent selectedrent= floor.getSelectionModel().getSelectedItem();
		if(selectedrent != null){
			boolean okClicked = redev(selectedrent);
			if(okClicked){
				showDetails(selectedrent);
			}
		}
	}
	public boolean redev(Rent rent) throws IOException{
		FXMLLoader devel = new FXMLLoader();
		devel.setLocation(getClass().getResource("/view/UpdateSheet.fxml"));
		AnchorPane anchorpane = devel.load();
		Stage editrent = new Stage(); 
		editrent.setTitle("edit Rent sheets");
		editrent.initModality(Modality.WINDOW_MODAL);
		editrent.initOwner(Rental);
		Scene scene = new Scene(anchorpane);
		editrent.setScene(scene);
		editrent.show();
		Updaterenting t = devel.getController();  
		t.setEditing(editrent);
		t.setRent(rent);
		return t.isOkClicked();

	}
	
	public void delete(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
					){
				st.executeUpdate("delete from rent where rent_id = '"+deletefild.getText()+"'");
			}catch(Exception e){
				e.printStackTrace();
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
