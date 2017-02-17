package io.moorea.query_analyzer.formcontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import io.moorea.query_analizer.configuration.Configuration;
import io.moorea.query_analizer.database.DbConnectionSingleton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FrmConnectionController implements Initializable {
	
    @FXML
    private Button btnConnect;

    @FXML
    private Button btnClose;
    
    @FXML
    private CheckBox chkUsesAuthentication;
    
    @FXML
    private TextField txtUser;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private TextField txtHost;
    
    @FXML
    private TextField txtPort;
    
    @FXML
    private TextField txtAuthenticationDatabase;
    
    @FXML
    private Pane pnlAuthentication;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeChkAuthentication();
	}
	
	private void initializeChkAuthentication(){
		chkUsesAuthentication.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue)
					pnlAuthentication.setVisible(true);
				else
					pnlAuthentication.setVisible(false);
			}
		});
	}
	
	@FXML
	private void connectToMongoServer(ActionEvent event) {
		if(validateForm()){
			if(txtHost.getText().isEmpty())
				Configuration.getInstance().setDbConnUrl("localhost");
			else
				Configuration.getInstance().setDbConnUrl(txtHost.getText());
			if(txtPort.getText().isEmpty())
				Configuration.getInstance().setDbConnPort(27017);
			else
				Configuration.getInstance().setDbConnPort(Integer.parseInt(txtPort.getText()));
			if(chkUsesAuthentication.isSelected()){
				Configuration.getInstance().setDbUseAuthentication(true);
				if(txtAuthenticationDatabase.getText().isEmpty())
					Configuration.getInstance().setDbAuthenticationName("admin");
				else
					Configuration.getInstance().setDbAuthenticationName(txtAuthenticationDatabase.getText());
				Configuration.getInstance().setDbUser(txtUser.getText());
				Configuration.getInstance().setDbPassword(txtPassword.getText());
			}
			if(DbConnectionSingleton.getConnection() != null){
				Stage stage = (Stage) btnClose.getScene().getWindow();
				loadMainwindow(stage);
			}
		}
	}
	 
	@FXML
	private void closeProgram(ActionEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
	    stage.close();
	}
	
	private boolean validateForm(){
		if(chkUsesAuthentication.isSelected()){
			boolean result = true;
			Background bfr = new Background(new BackgroundFill(Color.LIGHTCORAL,CornerRadii.EMPTY,Insets.EMPTY));
			Background bft = new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY));
			if(txtUser.getText().isEmpty()){
				txtUser.setBackground(bfr);
				result=false;
			}else
				txtUser.setBackground(bft);
			if(txtPassword.getText().isEmpty()){
				txtPassword.setBackground(bfr);
				result=false;
			}else
				txtPassword.setBackground(bft);
			return result;
		}else
			return true;
	}

	private void loadMainwindow(Stage stage){
		FXMLLoader loader = new FXMLLoader();
		Pane pane = null;
		try {
			pane = FXMLLoader.load(getClass().getClassLoader().getResource("forms/FrmMain.fxml"));
			loader.setRoot(pane);
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			stage.setTitle("Mongo query analyzer");
			stage.setScene(scene);
			stage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
