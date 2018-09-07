package Leases;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.rsdb;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Lease;

public class Renewleases implements Initializable  {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	private Stage Renew;
	private Lease lease;
	private boolean okClicked = false; 
	
	@FXML private TextField  lease_nofild;
	@FXML private TextField   receivefild;
	@FXML private TextField lodger_phonefild;
	@FXML private TextField   amountfild;
	@FXML private Label	  moneyfild;

	@FXML private Label	  paidfild;
	@FXML private TextField	  apartment_nofild;
	@FXML private TextField	  payment_datefild;
	@FXML private TextField	  duration_fromfild;
	@FXML private TextField  duration_untilfild;
	@FXML private DatePicker from;
	@FXML private DatePicker until;
	@FXML private DatePicker DOP;
	
	@FXML private Label	  lodger_signfild;
	@FXML private Label	  renter_signfild;
	
	@FXML private Label	  addressfild;
	@FXML private Label	  office_namefild;
	@FXML private Label	  office_telfild;
	@FXML private ComboBox<Integer> apartments;
	@FXML private ComboBox<Integer> tel;
	@FXML private ComboBox<String> office;
	public Stage getRenew() {
		return Renew;
	}
	public void setRenew(Stage Renew) {
		this.Renew = Renew;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	public void setLease(Lease lease){
		this.lease = lease;
					lease_nofild.setText(Integer.toString(lease.getLease_no()));
					receivefild.setText(lease.getReceive());
					amountfild.setText(lease.getAmount());
					moneyfild.setText(lease.getMoney());
					payment_datefild.setText(lease.getPayment_date());
					paidfild.setText(lease.getPaid());
				//	apartment_nofild.setText(Integer.toString(lease.getApartment_no()));
					duration_fromfild.setText(lease.getDuration_from());
					duration_untilfild.setText(lease.getDuration_until());
					lodger_signfild.setText(lease.getLodger_sign());
					renter_signfild.setText(lease.getRenter_sign());
					addressfild.setText(lease.getAddress());
					office_namefild.setText(lease.getOffice_name());
					office_telfild.setText(Integer.toString(lease.getOffice_tel()));
	}
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
					
				}
	}
	/*public void officer(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				ResultSet 	rs = st.executeQuery("select office_name from office") ;
				){
			while(rs.next()){
			office.getItems().add(rs.getString("office_name"));
			}
			}catch(Exception e){
					
				}
	}*/
	/*public void fax(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				ResultSet 	rs = st.executeQuery("select office_phone from office") ;
				){
			while(rs.next()){
			tel.getItems().add(rs.getInt("office_phone"));
			}
			}catch(Exception e){
					
				}
	}*/
public void Ok(){
	try {
		con = rsdb.sqlcon();
		st = con.createStatement();
		String ren = "	UPDATE `leases` SET `receive`='"+receivefild.getText()+"',`lodger_phone`='"+lodger_phonefild.getText()+"'"
				+ ",`amount`='"+amountfild.getText()+"`payment_date`='"+payment_datefild.getText()+"',`paid`='"+paidfild.getText()+"',`apartment_no`='"+apartments.getValue()+"'"
						+ ",`duration_from`='"+duration_fromfild.getText()+"',`duration_until`='"+duration_untilfild.getText()+"'WHERE `lease_no`='"+lease_nofild.getText()+"'";
		st.executeUpdate(ren);
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
		okClicked = true;
		Renew.close();
	}catch(Exception e){
e.printStackTrace();	
}

}
public void cancel(){
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
	Renew.close();
}
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
//fax();
apartmentlist();
//officer();
}
}
