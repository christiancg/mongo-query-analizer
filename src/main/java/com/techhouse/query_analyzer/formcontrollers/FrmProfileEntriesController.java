package com.techhouse.query_analyzer.formcontrollers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.techhouse.datamodel.ProfileEntry;
import com.techhouse.query_analizer.database.DbHelper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FrmProfileEntriesController implements Initializable {

	private String dbName;
	private String collectionName;

	@FXML
	private TableView<ProfileEntry> tblProfileEntries;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnSkipPreviousPage;

	@FXML
	private Button btnPreviousPage;

	@FXML
	private Button btnNextPage;

	@FXML
	private Button btnSkipNextPage;

	@FXML
	private TextField txtPage;

	@FXML
	private TextArea txtProfileEntry;

	@FXML
	private ComboBox<String> cmbCollection;

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	void initData(String dbName) {
		this.dbName = dbName;
		loadTable();
		loadCollectionCombo();
	}

	void initData(String dbName, String collectionName) {
		this.dbName = dbName;
		this.collectionName = collectionName;
		loadTable();
		loadCollectionCombo();
	}

	private void loadCollectionCombo() {
		for (String string : DbHelper.getCollections(dbName)) {
			cmbCollection.getItems().add(string);
		}
	}

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		loadEvents();
	}

	private void loadEvents() {
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				loadTable();
			}
		});
	}
	
	private void loadTable(){
		tblProfileEntries.getItems().clear();
		List<ProfileEntry> results = DbHelper.searchProfileEntries(dbName,collectionName);
		if(results!=null)
			tblProfileEntries.getItems().addAll(results);
	}
}
