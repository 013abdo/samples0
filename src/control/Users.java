package control;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;

import java.util.ResourceBundle;

import Users.Newusers;
import Users.Updateusers;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;

public class Users implements Initializable{
	//dialogs for user progressing.... 


//	private Stage preview;
	private Stage resident;
	
	//private boolean okClicked = true;

	/* the GUI component!*/
	//the table of users...
	@FXML private TableView<User> info;
	@FXML private TableColumn<User, Integer> idCol;
	@FXML private TableColumn<User, String> firstnameCol;
	@FXML private TableColumn<User, String> lastnameCol;
	@FXML private TableColumn<User, Integer> phoneCol;
	@FXML private TableColumn<User, String> emailCol;
	@FXML private TableColumn<User, String> jobCol;
	@FXML private TableColumn<User, String> genderCol;
	@FXML private TableColumn<User, String> addressCol;
	@FXML private TableColumn<User, String> cityCol;
	@FXML private TableColumn<User, String> zipCol;
	@FXML private TableColumn<User, Integer> na_idCol;

	//fields of user data 
	@FXML private TextField idfild;
	@FXML private TextField firstnamefild;
	@FXML private TextField lastnamefild;;
	@FXML private TextField phonefild;
	@FXML private TextField emailfild;
	@FXML private TextField jobfild;
	@FXML private TextField genderfild;
	@FXML private TextField na_idfild;
	@FXML private TextField addressfild;
	@FXML private TextField cityfild;
	@FXML private TextField zipfild;
	@FXML private TextField sorting;
	@FXML private TextField deletefild;

 
	ObservableList<User> data = FXCollections.observableArrayList();

	public Stage getResident() {
		return resident;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loaddatabasedata();
		idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
		firstnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
		lastnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone"));
		emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		jobCol.setCellValueFactory(new PropertyValueFactory<User, String>("job"));
		genderCol.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
		cityCol.setCellValueFactory(new PropertyValueFactory<User, String>("city"));
		addressCol.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
		zipCol.setCellValueFactory(new PropertyValueFactory<User, String>("zip"));
		na_idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("na_id"));
		FilteredList<User> filter = new FilteredList<>(data, u -> true);
		sorting.textProperty().addListener((observable, oldValue, newValue) ->{
			filter.setPredicate(User -> {
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter =  newValue.toLowerCase();
				if(User.getFirstname().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(User.getLastname().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(User.getCity().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<User> sorted = new SortedList<>(filter);
		sorted.comparatorProperty().bind(info.comparatorProperty());
		info.setItems(sorted);
		}
	

	/* processing buttons*/
	public void Newuser() throws IOException {
		adduserdialog();
	}
	public void Updateuser() throws IOException{
		User selected = info.getSelectionModel().getSelectedItem();
		if(selected != null){
			boolean okClicked = edituserdialog(selected);
			if(okClicked){
				showuserdata(selected);
			}
		}
		
	}

	public void deleteuser(){
	try(Connection con = rsdb.sqlcon();
			Statement st = con.createStatement();
			){
		st.executeUpdate("delete from users where id = '"+deletefild.getText()+"'");
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	}
	

	public void showuserdata(User user){
		if(user != null){
			 idfild.setText(Integer.toString(user.getId()));
			 firstnamefild.setText(user.getFirstname());
			 lastnamefild.setText(user.getLastname());
			 phonefild.setText(Integer.toString(user.getPhone()));
			 emailfild.setText(user.getEmail());
			 jobfild.setText(user.getJob());
			 genderfild.setText(user.getGender());
			 cityfild.setText(user.getCity());
			 addressfild.setText(user.getAddress());
			 zipfild.setText(user.getZip());
			 na_idfild.setText(Integer.toString(user.getNa_id()));
		}else{
			 idfild.setText("");
			 firstnamefild.setText("");
			 lastnamefild.setText("");
			 phonefild.setText("");
			 emailfild.setText("");
			 jobfild.setText("");
			 genderfild.setText("");
			 cityfild.setText("");
			 addressfild.setText("");
			 zipfild.setText("");
			 na_idfild.setText("");
		}
	}

	public void loaddatabasedata(){
		try(
				Connection con = rsdb.sqlcon();
			Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from users");
				){
			while(rs.next()){
				info.getItems().add(new User(rs.getInt("id"), rs.getString("firstname"),rs.getString("lastname") , rs.getInt("phone"), rs.getString("email")
						,rs.getString("job"),rs.getString("gender"), 
						rs.getString("city"),rs.getString("address"),rs.getString("zip"), rs.getInt("na_id")));
				info.setItems(data);
				
			//	System.out.println((rs.getInt("id")+" "+ rs.getString ("firstname")+" "+rs.getString ("lastname") + " "+rs.getInt("phone")+ " "+rs.getString("email") +
				//		" "+rs.getString("job")+" "+rs.getString("gender")+" "+ rs.getString("address")+" "+rs.getString("city")+" "+rs.getString("street")+" "+rs.getString("zip")+ " "+rs.getInt("na_id")));
			}
		}catch(SQLException e){
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
	}

	public boolean adduserdialog() throws IOException{
		FXMLLoader newdialog = new FXMLLoader();
		newdialog.setLocation(getClass().getResource("/view/NewUserdialog.fxml"));
		AnchorPane anc = newdialog.load();
		Stage add = new Stage();
		add.setTitle("New Users");
		add.getIcons().add(new Image("file:///C:/JavaFX/realestate/img/add%20user.png"));
		add.initModality(Modality.WINDOW_MODAL);
		add.initOwner(resident);
		Scene n = new Scene(anc);
		add.setScene(n);
		add.show();
		Newusers a = newdialog.getController(); 
		a.setAdddialog(add);
		return a.isokClicked();
	}
	public boolean edituserdialog(User user) throws IOException{
		FXMLLoader editdialog = new FXMLLoader();
		editdialog.setLocation(getClass().getResource("/view/Real_Estate User Edit Form.fxml"));
		AnchorPane an = editdialog.load();
		Stage edit = new Stage();
		edit.setTitle("Update Real_Estate Users");
		edit.getIcons().add(new Image("file:///C:/JavaFX/realestate/img/edit-user-icon.png"));
		edit.initModality(Modality.WINDOW_MODAL);
		edit.initOwner(resident);
		Scene e = new Scene(an);
		edit.setScene(e);
		edit.show();
		Updateusers u = editdialog.getController(); 
		u.setUpdatedialog(edit);
		u.setUser(user);
		return u.isOkClicked();

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
