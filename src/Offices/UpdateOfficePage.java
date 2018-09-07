package Offices;

import java.sql.*;


import application.rsdb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Office;

public class UpdateOfficePage {
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null;  
	private Stage officedialog;
	private boolean okClicked = false;
	private Office office;

	public Stage getOfficedialog() {
		return officedialog;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public Office getOffice() {
		return office;
	}
	public void setOfficedialog(Stage officedialog) {
		this.officedialog = officedialog;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	@FXML private TextField office_numfild;
	@FXML private TextField office_namefild;
	@FXML private TextField office_phonefild;
	@FXML private TextField office_telfild;
	@FXML private TextArea office_addressfild;
	@FXML private TextField office_cityfild;
	@FXML private TextField office_ownerfild;

	public void Save(){
		if(isValid()){
			office.setOffice_name(office_namefild.getText());
			office.setOffice_phone(Integer.parseInt(office_namefild.getText()));
			office.setOffice_tel(Integer.parseInt(office_namefild.getText()));
			office.setOffice_address(office_namefild.getText());
			office.setOffice_city(office_namefild.getText());
			office.setOffice_owner(office_namefild.getText());
		try{
			Connection con = rsdb.sqlcon();
			Statement stmt = con.createStatement();
			String edit = " UPDATE `office` SET `office_name`='"+office_namefild.getText()+"',`office_phone`='"+office_phonefild.getText()+"',`office_tel`='"+office_telfild.getText()+"',"
					+ "`office_address`='"+office_addressfild.getText()+"',`office_city`='"+office_cityfild.getText()+"',`office_owner`='"+office_ownerfild.getText()+"' WHERE `office_num`='"+office_numfild.getText()+"'";
			stmt.executeUpdate(edit);	
		}catch(Exception e){
			e.printStackTrace();
		}
		okClicked = true;
		officedialog.close();
	}
	}
	public void Cancel(){

		officedialog.close();
	}
	public void print(){}
	public void setOffice(Office bureau){
		this.office = bureau;
		office_numfild.setText(Integer.toString(bureau.getOffice_num()));
		office_namefild.setText(bureau.getOffice_name());
		office_phonefild.setText(Integer.toString(bureau.getOffice_phone()));
		office_telfild.setText(Integer.toString(bureau.getOffice_tel()));
		office_addressfild.setText(bureau.getOffice_address());
		office_cityfild.setText(bureau.getOffice_city());
		office_ownerfild.setText(bureau.getOffice_owner());
	}
	public boolean isValid(){
		String error ="";
		if(office_namefild.getText()==null||office_namefild.getText().length()==0){
			error ="Invalid name!!";
			System.out.println(error ="Invalid name!!");
		}
		if(office_phonefild.getText()==null||office_phonefild.getText().length()==0){
			error ="Invalid name!!";
			System.out.println(error ="Invalid phone!!");
		}	else{
			try{
				Integer.parseInt(office_phonefild.getText());
			}catch(NumberFormatException e){
				error = "Wrong Value (It must be int)";
				System.out.println(error = "Wrong Value (It must be int)");
			}
		}
		if(office_telfild.getText()==null||office_telfild.getText().length()==0){
			error ="Invalid tel munber!!";
			System.out.println(error ="Invalid tel number!!");
		}else{
			try{
				Integer.parseInt(office_telfild.getText());
			}catch(NumberFormatException e){
				error = "Wrong Value (It must be int)";
				System.out.println(error = "Wrong Value (It must be int)");
			}
		}
		if(office_addressfild.getText()==null||office_addressfild.getText().length()==0){
			error ="Invalid name!!";
			System.out.println(error ="Invalid address!!");
		}
		if(office_cityfild.getText()==null||office_cityfild.getText().length()==0){
			error ="Invalid name!!";
			System.out.println(error ="Invalid city!!");
		}
		if(office_ownerfild.getText()==null||office_ownerfild.getText().length()==0){
			error ="Invalid name!!";
			System.out.println(error ="Invalid owner name!!");
		}

		if(error.length()==0){
			return true;
		}else{
			return false;
		}
	}
}
