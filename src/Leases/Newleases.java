package Leases;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Apartment;
import model.Office;


public class Newleases implements Initializable{
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	//Stage controller
	private Stage addition;
	private boolean okClicked = false;
		public Stage getAddition() {
	return addition;
}

public void setAddition(Stage addition) {
	this.addition = addition;
}

public boolean isOkClicked() {
	return okClicked;
}

public void setOkClicked(boolean okClicked) {
	this.okClicked = okClicked;
}
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
apartmentlist();
fax();
officer();
}
	//lease data
	@FXML private TextField  lease_nofild;
	@FXML private TextField   receivefild;
	@FXML private TextField	  lodger_phonefild;

	@FXML private TextField   amountfild;
	@FXML private TextField	  moneyfild;

	@FXML private TextArea	  paidfild;
	@FXML private TextField	 apartment_nofild;
	@FXML private TextField	  payment_datefild;
	@FXML private TextField	  duration_fromfild;
	@FXML private TextField  duration_untilfild;
	
	@FXML private TextArea	  lodger_signfild;
	@FXML private TextArea	  renter_signfild;
	
	@FXML private TextField	  addressfild;
	@FXML private TextField	  office_namefild;
	@FXML private TextField	  office_telfild;
	@FXML private ComboBox<Integer> apartments;
	@FXML private ComboBox<Integer> tel;
	@FXML private ComboBox<String> office;
	ObservableList<Apartment> rooms = FXCollections.observableArrayList();
	ObservableList<Office> phone = FXCollections.observableArrayList();
	ObservableList<Office> fax = FXCollections.observableArrayList();
	@FXML
	public void apartmentlist(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				ResultSet 	rs = st.executeQuery("select apartment_no  from apartments") ;
				){
			while(rs.next()){
			apartments.getItems().add(rs.getInt("apartment_no"));
			}
			}catch(Exception e){
					e.getMessage();
					
				}
	}
	public void officer(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				ResultSet 	rs = st.executeQuery("select office_name from office") ;
				){
			while(rs.next()){
			office.getItems().add(rs.getString("office_name"));
			}
			}catch(Exception e){
					e.getMessage();
				}
	}
	public void fax(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				ResultSet 	rs = st.executeQuery("select office_phone from office") ;
				){
			while(rs.next()){
			tel.getItems().add(rs.getInt("office_phone"));
			}
			}catch(Exception e){
					e.getMessage();
				}
	}
	public void ok(){

		try{
			con = rsdb.sqlcon();
			st = con.createStatement();
				String add = "INSERT INTO `leases`( `receive`,`lodger_phone`, `amount`, `money`, `payment_date`, `paid`,`apartment_no`, `duration_from`, `duration_until`, `lodger_sign`, `renter_sign`, `address`, `office_name`, `office_tel`)"
			 +"  VALUES ('"+ receivefild.getText()+"','"+amountfild.getText()+"','"+lodger_phonefild.getText()+"','"+moneyfild.getText()+"','"+payment_datefild.getText()+"',"
			 		+ "'"+paidfild.getText()+"','"+apartments.getValue()+"','"+duration_fromfild.getText()+"','"+duration_untilfild.getText()+"','"+lodger_signfild.getText()+"','"+renter_signfild.getText()+"','"+ addressfild.getText()+"','"+/*office_namefild.getText()*/office.getValue()+"','"+/*office_telfild.getText()*/tel.getValue()+"')";
				st.executeUpdate(add);
		
				okClicked = true;
				addition.close();
		}catch(Exception i){
			System.out.println(i.getMessage());
			i.getStackTrace();
		}

	}
	public void cencel(){
	
			/*try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass()
						.getResource("/view/Leases Information.fxml"));
				AnchorPane anchorpane = loader.load();
				Scene scene = new Scene(anchorpane);
				Main.office.setScene(scene);
				Main.office.show();
			}catch(Exception e){
				e.printStackTrace();
			}*/
		addition.close();
	}

	
}
