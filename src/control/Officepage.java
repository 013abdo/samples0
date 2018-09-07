package control;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import Offices.NewOffices;
import Offices.UpdateOfficePage;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Office;


public class Officepage implements Initializable{

	private Stage arch;
	@FXML private TableView<Office> desk;
	@FXML private TableColumn<Office, String> office_nameCol;
	@FXML private TableColumn<Office, Integer> office_numCol;
	@FXML private TableColumn<Office, Integer> office_phoneCol;
	@FXML private TableColumn<Office, String> office_addressCol;
	@FXML private  TableColumn<Office, String>office_cityCol;
	@FXML private TableColumn<Office, String> office_streetCol;
	@FXML private TableColumn<Office, String> office_ownerCol;
	@FXML private TableColumn<Office, Integer> office_telCol;
	ObservableList<Office> booking = FXCollections.observableArrayList();
	@FXML private TextField office_namefild;
	@FXML private TextField office_numfild;
	@FXML private TextField office_phonefild;
	@FXML private TextField office_addressfild;
	@FXML private  TextField office_cityfild;
	@FXML private TextField office_ownerfild;
	@FXML private TextField office_telfild;
	@FXML private TextField filtring;
	@FXML private TextField deletefild;
	public Stage getArch() {
		return arch;
	}

	public void setArch(Stage arch) {
		this.arch = arch;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		office_numCol.setCellValueFactory(new PropertyValueFactory<Office, Integer>("office_num"));
		office_phoneCol.setCellValueFactory(new PropertyValueFactory<Office, Integer>("office_phone"));
		office_telCol.setCellValueFactory(new PropertyValueFactory<Office, Integer>("office_tel"));
		office_addressCol.setCellValueFactory(new PropertyValueFactory<Office, String>("office_address"));
		office_cityCol.setCellValueFactory(new PropertyValueFactory<Office, String>("office_city"));
		office_ownerCol.setCellValueFactory(new PropertyValueFactory<Office, String>("office_owner"));
		office_nameCol.setCellValueFactory(new PropertyValueFactory<Office, String>("office_name"));
		getdata();
		FilteredList<Office> filter = new FilteredList<>(booking, u -> true);
		filtring.textProperty().addListener((observable, oldValue, newValue) ->{
			filter.setPredicate(Office -> {
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter =  newValue.toLowerCase();
				if(Office.getOffice_name().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(Office.getOffice_owner().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(Office.getOffice_city().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<Office> sorted = new SortedList<>(filter);
		sorted.comparatorProperty().bind(desk.comparatorProperty());
		desk.setItems(sorted);
	}

	public void getdata(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from office");
				){
			while(rs.next()){
				desk.getItems().add(new Office(rs.getInt("office_num"),rs.getString("office_name"), rs.getInt("office_phone"),rs.getInt("office_tel")
						,rs.getString("office_address"),rs.getString("office_city"),rs.getString("office_owner")));
				desk.setItems(booking);	
			}
		}catch(Exception e){
			System.out.println(e.getMessage());	
			e.printStackTrace();
		}
	}

	public void newoffices() throws IOException{
		/*try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/NewRealEstateOffice.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		addoffice();	
	}
	public void editoffices() throws IOException{
		/*try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/RealEstateUpdateOfficePage.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		//updateoffice();
		Office chosen = desk.getSelectionModel().getSelectedItem();
		if(chosen != null){
			boolean okClicked = updateoffice(chosen);
			if(okClicked){
				presentoffices(chosen);
			}
		}
	}
	public boolean addoffice() throws IOException{
		
		FXMLLoader up = new FXMLLoader();
		up.setLocation(getClass().getResource("/view/NewRealEstateOffice.fxml"));
		AnchorPane anchorpane = up.load();
	Stage a = new Stage();
	a.setTitle("Office Update page");
	a.initModality(Modality.WINDOW_MODAL);
	a.initOwner(arch);
		Scene scene = new Scene(anchorpane);
		a.setScene(scene);
		a.show();
		NewOffices d = up.getController();
		d.setDeskdialog(a);
		
		return d.isOkClicked();
		
	}
	public boolean updateoffice(Office bureau) throws IOException{
		FXMLLoader release = new FXMLLoader();
		release.setLocation(getClass().getResource("/view/RealEstateUpdateOfficePage.fxml"));
		AnchorPane anchorpane = release.load();
	Stage e = new Stage();
	e.setTitle("Office Update page");
	e.initModality(Modality.WINDOW_MODAL);
	e.initOwner(arch);
		Scene scene = new Scene(anchorpane);
		e.setScene(scene);
		e.show();
		UpdateOfficePage o = release.getController();
		o.setOfficedialog(e);
		o.setOffice(bureau);
		return o.isOkClicked();
		}
	public void deleteoffices(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				){
			st.executeUpdate("delete from office where office_num = '"+deletefild.getText()+"'");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void presentoffices(Office office){
		if(office != null){
			office_namefild.setText(office.getOffice_name());
			office_numfild.setText(Integer.toString(office.getOffice_num()));
			office_phonefild.setText(Integer.toString(office.getOffice_phone()));
			office_addressfild.setText(office.getOffice_address());
			office_cityfild.setText(office.getOffice_city());
			office_ownerfild.setText(office.getOffice_owner());
			office_telfild.setText(Integer.toString(office.getOffice_tel()));
		}else{
			office_namefild.setText("");
			office_numfild.setText("");
			office_phonefild.setText("");
			office_addressfild.setText("");
			office_cityfild.setText("");
			office_ownerfild.setText("");
			office_telfild.setText("");
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
