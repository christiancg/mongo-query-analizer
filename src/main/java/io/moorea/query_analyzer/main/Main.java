package io.moorea.query_analyzer.main;


import java.io.IOException;

import io.moorea.query_analyzer.formcontrollers.FrmConnectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String args[]){
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		showConnWindow(primaryStage);
	}
	
	private void showConnWindow(Stage stage){
		FXMLLoader loader = new FXMLLoader();
		Pane pane = null;
		try {
			pane = FXMLLoader.load(getClass().getClassLoader().getResource("forms/FrmConnection.fxml"));
			loader.setRoot(pane);
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			stage.setTitle("Connect to Mongo Host");
			stage.setScene(scene);
			stage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
