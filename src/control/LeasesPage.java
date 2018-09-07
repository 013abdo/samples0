package control;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ResourceBundle;

import Leases.Leases;
import Leases.Newleases;
import Leases.Renewleases;
import Leases.Updateleases;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Lease;


public class LeasesPage implements Initializable{
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	private Stage sheet;
	private boolean okClicked = false; 
	@FXML private TableView<Lease> orders;
	@FXML private TableColumn<Lease,Integer>  lease_noCol;
	@FXML private TableColumn<Lease,String>   receiveCol;
	@FXML private TableColumn<Lease,String>  amountCol;
	@FXML private TableColumn<Lease,Integer>  moneyCol;
	@FXML private TableColumn<Lease,Integer>  lodger_phoneCol;
	@FXML private TableColumn<Lease, Integer> apartment_noCol;
	@FXML private TableColumn<Lease,String>   duration_fromCol;
	@FXML private TableColumn<Lease,String>   duration_untilCol;
	@FXML private TableColumn<Lease,String>  addressCol;
	@FXML private TableColumn<Lease,String>  office_nameCol;
	@FXML private TableColumn<Lease,Integer>  office_telCol;
	ObservableList<Lease> bill = FXCollections.observableArrayList();
	
	@FXML private TextField  lease_nofild;
	@FXML private TextField   receivefild;
	@FXML private TextField   amountfild;
	@FXML private TextField	  moneyfild;

	@FXML private TextArea	  paidfild;
	@FXML private TextField	  payment_datefild;
	@FXML private TextField	  duration_fromfild;
	@FXML private TextField  duration_untilfild;
	@FXML private TextField  lodger_phonefild;
	@FXML private TextArea	  lodger_signfild;
	@FXML private TextArea	  renter_signfild;
	
	@FXML private TextField	  addressfild;
	@FXML private TextField	  office_namefild;
	@FXML private TextField	  office_telfild;
	@FXML private TextField deletefild;
	@FXML private TextField find;
	public Stage getSheet() {
		return sheet;
	}


	public void setSheet(Stage sheet) {
		this.sheet = sheet;
	}


	public boolean isOkClicked() {
		return okClicked;
	}


	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lease_noCol.setCellValueFactory(new PropertyValueFactory<Lease, Integer>("lease_no"));
		receiveCol.setCellValueFactory(new PropertyValueFactory<Lease, String>("receive"));
		amountCol.setCellValueFactory(new PropertyValueFactory<Lease, String>("amount"));
		moneyCol.setCellValueFactory(new PropertyValueFactory<Lease, Integer>("money"));
		lodger_phoneCol.setCellValueFactory(new PropertyValueFactory<Lease, Integer>("lodger_phone"));
		apartment_noCol.setCellValueFactory(new PropertyValueFactory<Lease, Integer>("apartment_no"));
		duration_fromCol.setCellValueFactory(new PropertyValueFactory<Lease, String>("duration_from"));
		duration_untilCol.setCellValueFactory(new PropertyValueFactory<Lease, String>("duration_until"));
		addressCol.setCellValueFactory(new PropertyValueFactory<Lease, String>("address"));
		office_nameCol.setCellValueFactory(new PropertyValueFactory<Lease, String>("office_name"));
		office_telCol.setCellValueFactory(new PropertyValueFactory<Lease, Integer>("office_tel"));
	loaddatabase();
	FilteredList<Lease> filter = new FilteredList<>(bill, u -> true);
	find.textProperty().addListener((observable, oldValue, newValue) ->{
		filter.setPredicate(Lease -> {
			if(newValue == null || newValue.isEmpty()){
				return true;
			}
			String lowerCaseFilter =  newValue.toLowerCase();
			if(Lease.getReceive().toLowerCase().contains(lowerCaseFilter)){
				return true;
			}else if(Lease.getAmount().toLowerCase().contains(lowerCaseFilter)){
				return true;
			}
			return false;
		});
	});
	SortedList<Lease> sorted = new SortedList<>(filter);
	sorted.comparatorProperty().bind(orders.comparatorProperty());
	orders.setItems(sorted);
//	presentdata(null);
	//orders.getSelectionModel().selectedItemProperty().addListener((Observable, oldvalue, newvalue)-> presentdata(newvalue));
	}
	

	public void loaddatabase(){
		try {
			con = rsdb.sqlcon();
			st = con.createStatement();
			rs = st.executeQuery("select * from leases");
while(rs.next()){
	orders.getItems().add(new Lease(rs.getInt("lease_no"),rs.getString("receive"),rs.getInt("lodger_phone"),rs.getString("amount"),rs.getString("money"),
		rs.getString("payment_date"),	rs.getString("paid"),rs.getInt("apartment_no"),rs.getString("duration_from"),rs.getString("duration_until"),
			rs.getString("lodger_sign"),rs.getString("renter_sign"),
			rs.getString("address"),rs.getString("office_name"),rs.getInt("office_tel")));
	orders.setItems(bill);
}
		}catch(Exception e){
System.out.println(e.getMessage());
		}
	}
	public void presentdata(Lease sheets){
		if(sheets != null){
		lease_nofild.setText(Integer.toString(sheets.getLease_no()));
		receivefild.setText(sheets.getReceive());
		 lodger_phonefild.setText(Integer.toString(sheets.getLodger_phone()));
		amountfild.setText(sheets.getAmount());
		moneyfild.setText(sheets.getMoney());
		payment_datefild.setText(sheets.getPayment_date());
		paidfild.setText(sheets.getPaid());
		duration_fromfild.setText(sheets.getDuration_from());
		duration_untilfild.setText(sheets.getDuration_until());
		lodger_signfild.setText(sheets.getLodger_sign());
		renter_signfild.setText(sheets.getRenter_sign());
		addressfild.setText(sheets.getAddress());
		office_namefild.setText(sheets.getOffice_name());
		office_telfild.setText(Integer.toString(sheets.getOffice_tel()));
	}else{
		lease_nofild.setText("");
		receivefild.setText("");
		 lodger_phonefild.setText("");
		amountfild.setText("");
		moneyfild.setText("");
		payment_datefild.setText("");
		paidfild.setText("");
		duration_fromfild.setText("");
		duration_untilfild.setText("");
		lodger_signfild.setText("");
		renter_signfild.setText("");
		addressfild.setText("");
		office_namefild.setText("");
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
	public void leaseview() throws IOException{
		/*try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/lease.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		Lease showdetail = orders.getSelectionModel().getSelectedItem();
		if(showdetail !=null){
			boolean okClicked = View(showdetail);
			if(okClicked){
				presentdata(showdetail);
			}
		}
	}
	public boolean View(Lease lease) throws IOException{
		FXMLLoader viewdialog = new FXMLLoader();
		viewdialog.setLocation(getClass().getResource("/view/lease.fxml"));
		AnchorPane anc = viewdialog.load();
		Stage show = new Stage();
		show.setTitle("Edit lease");
		show.initModality(Modality.WINDOW_MODAL);
		show.initOwner(sheet);
		Scene n = new Scene(anc);
		show.setScene(n);
		show.show();
		Leases s = viewdialog.getController(); 
		s.setPreview(show);
		s.setLease(lease);
		return s.isOkClicked();
	}
	public void newlease() throws IOException{
	/*	try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/Addlease.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		addlease();
	}
	
	public boolean addlease() throws IOException{
		FXMLLoader newdialog = new FXMLLoader();
		newdialog.setLocation(getClass().getResource("/view/newlease.fxml"));
		AnchorPane anc = newdialog.load();
		Stage add = new Stage();
		add.setTitle("New lease");
		add.initModality(Modality.WINDOW_MODAL);
		add.initOwner(sheet);
		Scene n = new Scene(anc);
		add.setScene(n);
		add.show();
		Newleases l = newdialog.getController(); 
		l.setAddition(add);
		return l.isOkClicked();
		}
	public void editlease() throws IOException{
	/*	try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/UpdateLeases.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
	//	update(null);
		Lease selectedsheet = orders.getSelectionModel().getSelectedItem();
		if(selectedsheet !=null){
			boolean okClicked = update(selectedsheet);
			if(okClicked){
				presentdata(selectedsheet);
			}
		}
	}
	public boolean update(Lease leases) throws IOException{
		FXMLLoader editdialog = new FXMLLoader();
		editdialog.setLocation(getClass().getResource("/view/UpdateLeases.fxml"));
		AnchorPane anc = editdialog.load();
		Stage change = new Stage();
		change.setTitle("Edit lease");
		change.initModality(Modality.WINDOW_MODAL);
		change.initOwner(sheet);
		Scene n = new Scene(anc);
		change.setScene(n);
		change.show();
		Updateleases s = editdialog.getController(); 
		s.setEdition(change);
		s.setLease(leases);
		return s.isOkClicked();
		}
		
	public void renewlease() throws IOException{
	/*	try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/view/RenewLease.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.office.setScene(scene);
			Main.office.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		Lease renewsheet = orders.getSelectionModel().getSelectedItem();
		if(renewsheet !=null){
			boolean okClicked = Renew(renewsheet);
			if(okClicked){
				presentdata(renewsheet);
			}
		}
	}
	public boolean Renew(Lease leases) throws IOException{
		FXMLLoader renewal = new FXMLLoader();
		renewal.setLocation(getClass().getResource("/view/RenewLease.fxml"));
		AnchorPane anc = renewal.load();
		Stage change = new Stage();
		change.setTitle("renew lease");
		change.initModality(Modality.WINDOW_MODAL);
		change.initOwner(sheet);
		Scene n = new Scene(anc);
		change.setScene(n);
		change.show();
		Renewleases s = renewal.getController(); 
		s.setRenew(change);
		s.setLease(leases);
		return s.isOkClicked();
		}
public void delete(){
	try(Connection con = rsdb.sqlcon();
			Statement st = con.createStatement();
				){
			st.executeUpdate("delete from leases where lease_no = '"+deletefild.getText()+"'");
		}catch(Exception e){
			e.printStackTrace();
		}
}
}
