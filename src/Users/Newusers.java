package Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import application.rsdb;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class Newusers {
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null;  

	//dialogs for user progressing.... 
	private Stage adddialog;
	private boolean okClicked = false;
private User user;
	//@FXML private TextField idfild;
	@FXML private TextField firstnamefild;
	@FXML private TextField lastnamefild;;
	@FXML private TextField phonefild;
	@FXML private TextField emailfild;
	//@FXML private TextField jobfild;
	//@FXML private TextField genderfild;
	@FXML private TextField cityfild;
	@FXML private TextArea addressfild;
	@FXML private TextField zipfild;
	@FXML private TextField na_idfild;

	@FXML private RadioButton male;
	@FXML private RadioButton female;
	@FXML private RadioButton adm;
	@FXML private RadioButton emp;
	/*@FXML private RadioButton deputy;
	@FXML private RadioButton ps;*/

	public Stage getAdddialog() {
		return adddialog;
	}
	public void setAdddialog(Stage adddialog) {
		this.adddialog = adddialog;
	}
	public boolean isokClicked(){
		return okClicked;	
	}


	public void ok(){
		String sex;
		String job;
		if(this.male.isSelected()){
			sex = "male";
		}else {
			sex = "female";
		}
		if(this.adm.isSelected()){
			job = "admin";
		}else{
			job ="employee";
		}
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				){
			String add = "INSERT INTO `users`( `firstname`, `lastname`, `phone`, `email`, `job`, `gender`, `address`, `city`, `zip`, `na_id`) VALUES"
					+ " ('"+/*idfild.getText()+"','"+*/firstnamefild.getText()+"','"+lastnamefild.getText()+"','"+phonefild.getText()+ "','"+emailfild.getText()
					+"','"+job+"','"+sex
					+"','"+cityfild.getText()+"','"+addressfild.getText()+"','"+zipfild.getText()+"','"+na_idfild.getText()+"')";
			st.executeUpdate(add);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		okClicked = true;
		adddialog.close();
	}
		
	public void cancle(){
		adddialog.close();
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
			}
		}
		/*if(firstnamefild.getText() == null || firstnamefild.getText().length()==0){
			error = "Invalid firstname!";
		}
		if(firstnamefild.getText() == null || firstnamefild.getText().length()==0){
			error = "Invalid firstname!";
		}
		if(firstnamefild.getText() == null || firstnamefild.getText().length()==0){
			error = "Invalid firstname!";
		}
		if(firstnamefild.getText() == null || firstnamefild.getText().length()==0){
			error = "Invalid firstname!";
		}
		if(firstnamefild.getText() == null || firstnamefild.getText().length()==0){
			error = "Invalid firstname!";
		}
		if(firstnamefild.getText() == null || firstnamefild.getText().length()==0){
			error = "Invalid firstname!";
		}
		if(firstnamefild.getText() == null || firstnamefild.getText().length()==0){
			error = "Invalid firstname!";
		}*/
		return false;
		
	}
}
