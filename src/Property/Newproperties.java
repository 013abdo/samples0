package Property;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.rsdb;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Newproperties implements Initializable{
	private Stage adddialog; 

	private boolean okClicked = false;
	@FXML private TextField property_numfild;
	@FXML private TextField property_namefild;
	@FXML private TextField property_typefild;
	@FXML private TextArea property_addressfild;
	@FXML private TextArea property_sizefild;
	@FXML private TextField property_cityfild;
	@FXML private TextField property_zipfild;
	@FXML private TextField floor_nofild;
	@FXML private TextField apartment_nofild;
	@FXML private TextField room_nofild;
	@FXML private TextField kitchen_nofild;
	@FXML private TextField toilet_nofild;
	@FXML private TextField hall_nofild;

	@FXML private ChoiceBox<Integer> card;

	public Stage getAdddialog() {
		return adddialog;
	}
	public void setAdddialog(Stage adddialog) {
		this.adddialog = adddialog;
	}


	public boolean isOkClicked() {
		return okClicked;
	}
	@FXML public void office(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				ResultSet 	rs = st.executeQuery("select office_num from office") ;
				){
			while(rs.next()){
			card.getItems().add(rs.getInt("office_num"));
			}
			}catch(Exception e){
					e.getMessage();
				}
	}

	public void save(){
		try(Connection con = rsdb.sqlcon();
				Statement st = con.createStatement();
				){
			String insert="	INSERT INTO `residential_units`( `property_name`, `property_type`, `property_address`, `property_city`, `property_zip`, `no_floor`, `no_apartment`, `no_room`, `no_hall`, `no_toilet`, `no_kitchen`, `office_num`) "
					+ "VALUES ('"+property_namefild.getText()+"','"+property_typefild.getText()+"','"+property_addressfild.getText()+"','"+property_cityfild.getText()+"','"+property_zipfild.getText()+"',"
							+ "'"+floor_nofild.getText()+"','"+apartment_nofild.getText()+"','"+room_nofild.getText()+"','"+hall_nofild.getText()+"','"+toilet_nofild.getText()+"','"+kitchen_nofild.getText()+"','"+card.getValue().toString()+"')";
			st.executeUpdate(insert);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		okClicked= true;
		adddialog.close();

	}
	public void cancel(){
		adddialog.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		office();
	}
}
//if(isvalid()){

/*real.setProperty_num(Integer.parseInt(property_numfild.getText()));
		real.setProperty_name(property_namefild.getText());
		real.setProperty_type(property_typefild.getText());
		real.setProperty_address(property_addressfild.getText());
		real.setProperty_size(Integer.parseInt(property_sizefild.getText()));
		real.setProperty_city(property_cityfild.getText());
		real.setProperty_street(property_streetfild.getText());
		real.setProperty_zip(property_zipfild.getText());
		real.setFloor_no(Integer.parseInt(floor_nofild.getText()));
		real.setApartment_no(Integer.parseInt(apartment_nofild.getText()));
		real.setRoom_no(Integer.parseInt(room_nofild.getText()));
		real.setKitchen_no(Integer.parseInt(kitchen_nofild.getText()));
		real.setToilet_no(Integer.parseInt(toilet_nofild.getText()));
		real.setHall_no(Integer.parseInt(hall_nofild.getText()));*/