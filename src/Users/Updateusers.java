package Users;

import java.sql.*;

import application.rsdb;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class Updateusers {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	private Stage updatedialog;
	private User user;
	private boolean okClicked = false;
	@FXML private TextField idfild;
	@FXML private TextField firstnamefild;
	@FXML private TextField lastnamefild;;
	@FXML private TextField phonefild;
	@FXML private TextField emailfild;
	@FXML private TextField jobfild;
	@FXML private TextField genderfild;
	@FXML private TextField na_idfild;
	@FXML private TextArea addressfild;
	@FXML private TextField cityfild;
	@FXML private TextField zipfild;
	@FXML private RadioButton administrator;
	@FXML private RadioButton male;
	@FXML private RadioButton female;
	@FXML private RadioButton emp;
	/*@FXML private RadioButton deputy;
	@FXML private RadioButton ps;*/

	/*public Stage getUpdatedialog() {
		return updatedialog;
	}*/
	public void setUpdatedialog(Stage updatedialog) {
		this.updatedialog = updatedialog;
	}
	public boolean isOkClicked() {
		return okClicked;
	}


	public void save(){
		String sex;
		String job;
		if(isValid()){
			user.setId(Integer.parseInt(idfild.getText()));
			user.setFirstname(firstnamefild.getText());
			user.setLastname(lastnamefild.getText());
			user.setPhone(Integer.parseInt(phonefild.getText()));
			user.setEmail(emailfild.getText());
			if(this.male.isSelected()){
				sex = "male";
			}else{
				sex = "female";
			}
			if(this.administrator.isSelected()){
				job = "admin";
			}/*else if(this.deputy.isSelected()){
			job= "Deputy";
		}else if(this.ps.isSelected()){
			job = "purchases&sales";				
		} */else {
			job = "Employee";
		}
			user.setCity(cityfild.getText());
			user.setAddress(addressfild.getText());
			user.setZip(zipfild.getText());
			user.setNa_id(Integer.parseInt(na_idfild.getText()));
			try{
				con = rsdb.sqlcon();
				st = con.createStatement();
				st.executeUpdate("UPDATE `users` SET `firstname`='"+firstnamefild.getText()+"',`lastname`='"+lastnamefild.getText()+
						"',`phone`='"+phonefild.getText()+"',`email`='"+emailfild.getText()+
						"',`job`='"+job+"',`gender`='"+sex
						+"',`city`='"+cityfild.getText()+"',`address`='"+addressfild.getText()+"',`zip`='"+zipfild.getText()+
						"',`na_id`='"+na_idfild.getText()+"' WHERE `id`='"+idfild.getText()+"'");
			} catch(Exception s){
				s.printStackTrace();
			}
			okClicked = true;
			updatedialog.close();
		}
	}

	public boolean isValid(){
		String error="";
		if(firstnamefild.getText() == null || firstnamefild.getText().length()==0){
			error = "Invalid firstname!";
		}
		if(lastnamefild.getText() == null || lastnamefild.getText().length()==0){
			error = "Invalid lastname!";
		}
		if(phonefild.getText() == null || phonefild.getText().length()==0){
			error = "Invalid phone!";
		}else{
			try{
				Integer.parseInt(phonefild.getText());
			}catch(NumberFormatException e){
				error = "Wrong Value (It must be int)";
			}
		}
		if(emailfild.getText() == null || emailfild.getText().length()==0){
			error = "Invalid email!";
		}else{
			if(!emailfild.getText().contains("@")){
				error ="should it have the @ mark";
				System.out.println(error ="should it have the @ mark");
			}
		}
		if(cityfild.getText() == null || cityfild.getText().length()==0){
			error = "Invalid city!";
		}
		if(addressfild.getText() == null || addressfild.getText().length()==0){
			error = "Invalid address!";
		}
		if(zipfild.getText() == null || zipfild.getText().length()==0){
			error = "Invalid zip!";
		}
		if(na_idfild.getText() == null || na_idfild.getText().length()==0){
			error = "Invalid na_id!";
		}	else{
			try{
				Integer.parseInt(na_idfild.getText());
			}catch(NumberFormatException e){
				error = "Wrong Value (It must be int)";
			}
		}
		if(error.length()==0){
			return true;
		}else{
			return false;
		}
	}
	public void cancel(){
		updatedialog.close();
	}
	public void setUser(User user){

		this.user= user;  
		idfild.setText(Integer.toString(user.getId()));
		firstnamefild.setText(user.getFirstname());
		lastnamefild.setText(user.getLastname());
		phonefild.setText(Integer.toString(user.getPhone()));
		emailfild.setText(user.getEmail());
		//	jobfild.setText(user.getJob());
		//		genderfild.setText(user.getGender());
		cityfild.setText(user.getCity());
		addressfild.setText(user.getAddress());
		zipfild.setText(user.getZip());
		na_idfild.setText(Integer.toString(user.getNa_id()));
	}
}
