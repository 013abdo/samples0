package Leases;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.rsdb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Lease;

public class Updateleases  implements Initializable{
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	private Stage edition;
	private Lease lease;
	private boolean okClicked = false; 
	
	@FXML private TextField  lease_nofild;
	@FXML private TextField   receivefild;
	@FXML private TextField	  lodger_phonefild;
	@FXML private TextField   amountfild;
	@FXML private TextField	  moneyfild;

	@FXML private TextArea	  paidfild;
	@FXML private TextField	  apartment_nofild;
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
	public Stage getEdition() {
		return edition;
	}
	public void setEdition(Stage edition) {
		this.edition = edition;
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
				System.out.println(e);	
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
				
				System.out.println(e);
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
				System.out.println(e);
				}
	}
	@FXML
public void Ok(){
	try {
		con = rsdb.sqlcon();
		st = con.createStatement();
		String edit = "	UPDATE `leases` SET `receive`='"+receivefild.getText()+"',`lodger_phone`='"+lodger_phonefild.getText()+"',`amount`='"+amountfild.getText()+"',`money`='"+moneyfild.getText()+"',"
				+ "`payment_date`='"+payment_datefild.getText()+"',`paid`='"+paidfild.getText()+"',`apartment_no`='"+apartments.getValue()+"',`duration_from`='"+duration_fromfild.getText()+"',`duration_until`='"+duration_untilfild.getText()+"',"
						+ "`lodger_sign`='"+lodger_signfild.getText()+"',`renter_sign`='"+renter_signfild.getText()+"',"
								+ "`address`='"+addressfild.getText()+"',`office_name`='"+/*office_namefild.getText()*/office.getValue()+"',`office_tel`='"+/*office_telfild.getText()*/tel.getValue()+"'WHERE `lease_no`='"+lease_nofild.getText()+"'";
		st.executeUpdate(edit);

		okClicked = true;
		edition.close();
	}catch(Exception e){
System.out.println(e.getMessage());
}
}

public boolean isValid(){
	String error="";
	if(receivefild.getText() ==null || receivefild.getText().length()==0){
		error="Invalid Value (reciever)";
		System.out.println(error="Invalid Value (reciever)");
	}
	if(amountfild.getText() ==null || amountfild.getText().length()==0){
		error="Invalid Value (amount)";
		System.out.println(error="Invalid Value (amount)");
	}
	if(moneyfild.getText() ==null || moneyfild.getText().length()==0){
		error="Invalid Value (Money)";
		System.out.println(error="Invalid Value (Money)");
	}
	if(payment_datefild.getText() ==null || payment_datefild.getText().length()==0){
		error="Invalid Value";
		System.out.println(error="Invalid Value");
	}
	if(paidfild.getText() ==null || paidfild.getText().length()==0){
		error="Invalid Value(paid for)";
		System.out.println(error="Invalid Value (paid for)");
	}
	if(duration_fromfild.getText() ==null || duration_fromfild.getText().length()==0){
		error="Invalid Value";
		System.out.println(error="Invalid Value");
	}
	if(duration_untilfild.getText() ==null || duration_untilfild.getText().length()==0){
		error="Invalid Value";
		System.out.println(error="Invalid Value");
	}
	if(lodger_signfild.getText() ==null || lodger_signfild.getText().length()==0){
		error="Invalid Value (sign)";
		System.out.println(error="Invalid Value (sign)");
	}
	if(renter_signfild.getText() ==null || renter_signfild.getText().length()==0){
		error="Invalid Value (sign)";
		System.out.println(error="Invalid Value (sign)");
	}
	if(addressfild.getText() ==null || addressfild.getText().length()==0){
		error="Invalid Value (address)";
		System.out.println(error="Invalid Value (address)");
	}
	if(office_namefild.getText() ==null || office_namefild.getText().length()==0){
		error="Invalid Value (office_name)";
		System.out.println(error="Invalid Value (office_name)");
	}
	if(office_telfild.getText() ==null || office_telfild.getText().length()==0){
		error="Invalid Value";
		System.out.println(error="Invalid Value");
	}else{
		try{
			Integer.parseInt(office_telfild.getText());
		}catch(NumberFormatException e){
			error = "Wrong Value (It must be int)";
			System.out.println(error = "Wrong Value (It must be int)");
		}
	}
	if(error.length() == 0){
		return true;
	}else{
	return false;
	}
}
@FXML
public void cancel(){

	edition.close();
}
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
fax();
apartmentlist();
officer();
}
}
