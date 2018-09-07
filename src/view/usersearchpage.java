package view;

import java.net.URL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.rsdb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;

public class usersearchpage implements Initializable{
	@FXML private TextField sorting;
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
	ObservableList<User> data = FXCollections.observableArrayList();
	
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
				
		/*		System.out.println((rs.getInt("id")+" "+ rs.getString ("firstname")+" "+rs.getString ("lastname") + " "+rs.getInt("phone")+ " "+rs.getString("email") +
						" "+rs.getString("job")+" "+rs.getString("gender")+" "+ rs.getString("address")+" "+rs.getString("city")+" "+rs.getString("zip")+ " "+rs.getInt("na_id")));*/
			}
		}catch(SQLException e){
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
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
		
	}


