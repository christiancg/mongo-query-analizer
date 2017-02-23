package io.moorea.query_analyzer.formcontrollers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mongodb.client.MongoIterable;

import io.moorea.query_analizer.database.DbHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

public class FrmMainController implements Initializable {

	private List<String> DBs = new ArrayList<String>();
	
    TreeItem<String> rootNode = new TreeItem<String>("Databases");
	
	@FXML
	private TreeView<String> trvDBandCollections;
	
	@FXML
	private ToggleButton tgEnableProfiling;
	
	@FXML
	private Pane pnlProfilingOptions;
	
	@FXML
	private Button btnProfile;
	
	@FXML
	private Label lblQueryThreshold;
	
	@FXML
	private RadioButton rdbProfile1;
	
	@FXML
	private RadioButton rdbProfile2;
	
	@FXML
	private Slider sldThreshold;
	
	@FXML
	private Label lblThresholdValue;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadEvents();
		loadDatabases();
		writeIntoTreeView();
	}
	
	private void loadEvents(){
		profilingToggle();
		rdbLevel1();
		sliderChange();
	}
	
	private void profilingToggle(){
		tgEnableProfiling.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue)
					pnlProfilingOptions.setVisible(true);
				else
					pnlProfilingOptions.setVisible(false);
			}
		});
	}
	
	private void rdbLevel1(){
		rdbProfile1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue){
					lblQueryThreshold.setVisible(true);
					sldThreshold.setVisible(true);
					lblThresholdValue.setVisible(true);
					int value = (int)sldThreshold.getValue();
					lblThresholdValue.setText(String.valueOf(value));
					btnProfile.setVisible(true);
				}else{
					lblQueryThreshold.setVisible(false);
					sldThreshold.setVisible(false);
					lblThresholdValue.setVisible(false);
					btnProfile.setVisible(false);
				}
			}
		});
	}
	
	private void sliderChange(){
		sldThreshold.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				lblThresholdValue.setText(String.valueOf(newValue.intValue()));
			}
		});
	}

	private void writeIntoTreeView(){
		for (String string : DBs) {
			TreeItem<String> item = new TreeItem<String>(string);
			rootNode.getChildren().add(item);
			MongoIterable<String> collections = DbHelper.getCollections(string);
			if(collections!=null)
				for (String string2 : collections) {
					TreeItem<String> child = new TreeItem<String>(string2);
					item.getChildren().add(child);
				}
		}
		trvDBandCollections.setRoot(rootNode);
	}
	
	private void loadDatabases(){
		MongoIterable<String> rdb = DbHelper.getDatabases();
		if(rdb!=null)
			for (String string : rdb)
				DBs.add(string);
	}
}
