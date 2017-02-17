package io.moorea.query_analyzer.formcontrollers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mongodb.client.MongoIterable;

import io.moorea.query_analizer.database.DbHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class FrmMainController implements Initializable {

	private List<String> DBs = new ArrayList<String>();
	
    TreeItem<String> rootNode = new TreeItem<String>("Databases");
	
	@FXML
	private TreeView<String> trvDBandCollections;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadEvents();
		loadDatabases();
		writeIntoTreeView();
	}
	
	private void loadEvents(){
		
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
