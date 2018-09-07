package Leases;

import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

import application.rsdb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Lease;

public class Leases implements Initializable {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	Stage preview;
	private Lease lease;
	private boolean okClicked = false; 
	
	@FXML private Label  lease_nofild;
	@FXML private Label   receivefild;
	@FXML private Label	  lodger_phonefild;

	@FXML private Label   amountfild;
	@FXML private Label	  moneyfild;

	@FXML private Label	  paidfild;
	@FXML private Label	 apartment_nofild;
	@FXML private Label	  payment_datefild;
	@FXML private Label	  duration_fromfild;
	@FXML private Label  duration_untilfild;
	
	@FXML private Label	  lodger_signfild;
	@FXML private Label	  renter_signfild;
	
	@FXML private Label	  addressfild;
	@FXML private Label	  office_namefild;
	@FXML private Label	  office_telfild;


	//ObservableList<Lease> bill = FXCollections.observableArrayList();

	public Stage getPreview() {
		return preview;
	}
	public void setPreview(Stage preview) {
		this.preview = preview;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
//	loaddatabase();
	}


	public void setLease(Lease lease){
		this.lease = lease;
			lease_nofild.setText(Integer.toString(lease.getLease_no()));
			receivefild.setText(lease.getReceive());
			lodger_phonefild.setText(Integer.toString(lease.getLodger_phone()));
			amountfild.setText(lease.getAmount());
			moneyfild.setText(lease.getMoney());
			payment_datefild.setText(lease.getPayment_date());
			paidfild.setText(lease.getPaid());
			duration_fromfild.setText(lease.getDuration_from());
			duration_untilfild.setText(lease.getDuration_until());
			lodger_signfild.setText(lease.getLodger_sign());
			renter_signfild.setText(lease.getRenter_sign());
			addressfild.setText(lease.getAddress());
			office_namefild.setText(lease.getOffice_name());
			office_telfild.setText(Integer.toString(lease.getOffice_tel()));
		}
	public void cancel(){
		preview.close();
	}
	}



