package application;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


import javafx.stage.Stage;

public class Main extends Application {
	public static Stage office;
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/home.fxml"));
			AnchorPane anc = loader.load();
			Scene scene = new Scene(anc);
			office = new Stage();
			office.setTitle("WELCOME TO RESIDENTAIL REALESTATE SYSTEM");
			office.getIcons().add(new Image("/img/apartments.png"));
			office.setScene(scene);
			/*Media s = new Media("file:///JavaFX/realestate/img/Passage.mp3");
			MediaPlayer p = new MediaPlayer(s);
			p.setAutoPlay(true);
			p.setVolume(0.4);*/
			office.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		launch(args);
		try (//closable 
				Connection con = rsdb.sqlcon(); 
				){
			System.out.println("Connected Successful!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
