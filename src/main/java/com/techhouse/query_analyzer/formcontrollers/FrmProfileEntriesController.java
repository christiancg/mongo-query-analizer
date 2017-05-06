package com.techhouse.query_analyzer.formcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import com.techhouse.datamodel.ProfileEntry;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FrmProfileEntriesController implements Initializable {

	private String dbName;
	private String collectionName;

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
		txtProfileEntry.setText(dbName);
	}

	void initData(String dbName, String collectionName) {
		this.dbName = dbName;
		this.collectionName = collectionName;
		txtProfileEntry.setText(dbName + " " + collectionName);
	}

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
	public void initialize(URL location, ResourceBundle resources) {
		loadEvents();
		loadTable();
	}

	private void loadEvents() {

	}

	private void loadTable() {

	}
}
