package control;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

import Property.Newproperties;
import Property.Updateproperties;
import application.Main;
import application.rsdb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Realty;
import model.User;

public class PropertyPage  implements Initializable{

	private Stage unit;
	private Realty real; 
	private boolean okClicked = false; 

	@FXML private TableView<Realty>housing;
	//property information
	@FXML private TableColumn<Realty, Integer>property_numCol ;
	@FXML private TableColumn<Realty, String>property_nameCol ;
	@FXML private TableColumn<Realty, String>property_typeCol ;
	@FXML private TableColumn<Realty, String>property_addressCol ;
	@FXML private TableColumn<Realty, Integer>floor_noCol ;
	//unit address
	@FXML private TableColumn<Realty, String>property_cityCol ;
	@FXML private TableColumn<Realty, String>property_zipCol ;

	@FXML private TableColumn<Realty, Integer>office_numCol ;

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
	@FXML private TextField office_numfild;
	@FXML private TextField sorting;
	@FXML private TextField deletefild;

	ObservableList<Realty> place = FXCollections.observableArrayList();

	public Stage getUnit() {
		return unit;
	}
	public void setShowdialog(Stage unit) {
		this.unit = unit;
	}
	public Realty getReal() {
		return real;
	}
	public void setReal(Realty real) {
		this.real = real;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loaddata();
		showpropertydata(null);
		housing.getSelectionModel().selectedItemProperty().addListener((Observable, oldvalue, newvalue)-> showpropertydata( newvalue));
		property_numCol.setCellValueFactory(new PropertyValueFactory<Realty,Integer>("property_num")); 
		property_nameCol.setCellValueFactory(new PropertyValueFactory<Realty,String>("property_name")) ;
		property_typeCol.setCellValueFactory(new PropertyValueFactory<Realty,String>("property_type")) ;
		property_addressCol.setCellValueFactory(new PropertyValueFactory<Realty,String> ("property_address"));
		property_cityCol.setCellValueFactory(new PropertyValueFactory<Realty,String> ("property_city"));
		property_zipCol.setCellValueFactory(new PropertyValueFactory<Realty,String> ("property_zip"));
		floor_noCol.setCellValueFactory(new PropertyValueFactory<Realty,Integer> ("floor_no"));
		office_numCol.setCellValueFactory(new PropertyValueFactory<Realty,Integer>("office_num")); 
		FilteredList<Realty> filter = new FilteredList<>(place, u -> true);
		sorting.textProperty().addListener((observable, oldValue, newValue) ->{
			filter.setPredicate(Realty -> {
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter =  newValue.toLowerCase();
				if(Realty.getProperty_name().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(Realty.getProperty_type().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(Realty.getProperty_city().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(Realty.getProperty_address().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<Realty> sorted = new SortedList<>(filter);
		sorted.comparatorProperty().bind(housing.comparatorProperty());
		housing.setItems(sorted);
		}

	public void loaddata(){
		try(Connection	con = rsdb.sqlcon();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM `residential_units`");) {
			while(rs.next()){
				housing.getItems().add(new Realty(rs.getInt("property_num") , rs.getString("property_name"), rs.getString("property_type") ,
						rs.getString("property_address"),
						rs.getString("property_city"), rs.getString("property_zip"),
						rs.getInt("no_floor") ,rs.getInt("no_apartment"), rs.getInt("no_room"), rs.getInt("no_kitchen"), rs.getInt("no_toilet"), rs.getInt("no_hall"),rs.getInt("office_num")));
			housing.setItems(place);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showpropertydata(Realty real){
		if(real != null){
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
			office_numfild.setText(Integer.toString(real.getOffice_num()));
		}else{
			property_numfild.setText("");
			property_namefild.setText("");
			property_typefild.setText("");
			property_addressfild.setText("");
			property_cityfild.setText("");
			property_zipfild.setText("");
			floor_nofild.setText("");
			apartment_nofild.setText("");
			room_nofild.setText("");
			kitchen_nofild.setText("");
			toilet_nofild.setText("");
			hall_nofild.setText("");
			office_numfild.setText("");
		}
	}

	@FXML 	public void Newproperty() throws IOException{
		addproperty();
	}
	@FXML	public void Editproperty() throws IOException{
			Realty selectedreal = housing.getSelectionModel().getSelectedItem();
		if(selectedreal != null){
			boolean okClicked = updateproperty(selectedreal);
			if(okClicked){
				showpropertydata(selectedreal);
			}
		}
		//updateproperty(null);
	}
	public void Deleteroperty(){
		Alert al = new Alert(AlertType.WARNING);
		al.setHeaderText("Delete");
		al.setContentText("Are you sure from deleting this selection?");
		al.showAndWait();
		try(Connection con = rsdb.sqlcon();
			Statement st = con.createStatement();
				){
			st.executeUpdate("delete from residential_units where property_name = '"+deletefild.getText()+"'");
		}catch(Exception e){
			e.printStackTrace();
		}
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setHeaderText("delete");
		a.setContentText("the selected deleted");
	}


	public boolean addproperty() throws IOException{
		FXMLLoader newunit = new FXMLLoader();
		newunit.setLocation(getClass().getResource("/view/Propery Registration Form.fxml"));
		AnchorPane n = newunit.load();
		Stage site = new Stage();
		site.setTitle("New property!!");
		site.initModality(Modality.WINDOW_MODAL);
		site.initOwner(unit);
		Scene spot = new Scene(n);
		site.setScene(spot);
		site.show();
		Newproperties R = newunit.getController();
		R.setAdddialog(site);

		return R.isOkClicked();

	}
	public boolean updateproperty(Realty real) throws IOException{
		FXMLLoader editunit = new FXMLLoader();
		editunit.setLocation(getClass().getResource("/view/PropertyUpdatedialog.fxml"));
		AnchorPane u= editunit.load();
		Stage seat = new Stage();
		seat.setTitle("Edit property!!");
		seat.initModality(Modality.WINDOW_MODAL);
		seat.initOwner(unit);
		seat.getIcons().add(new Image("file:img/Wrench.png"));
		Scene space = new Scene(u);
		seat.setScene(space);
		seat.show();
		Updateproperties R = editunit.getController();
		R.setEditdialog(seat);
		R.setRealty(real);
		return R.isOkClicked();
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
