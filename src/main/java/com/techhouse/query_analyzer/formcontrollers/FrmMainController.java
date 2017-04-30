package com.techhouse.query_analyzer.formcontrollers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.mongodb.client.MongoIterable;
import com.techhouse.datamodel.CollectionStats;
import com.techhouse.datamodel.ProfilingLevel;
import com.techhouse.query_analizer.database.DbHelper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	private Button btnDeleteProfile;

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

	@FXML
	private Button btnRefreshTreeView;

	private String selectedDbName = "";

	private String selectedCollectionName = "";

	// Panel stats and contents
	@FXML
	private Pane pnlStats;

	@FXML
	private TextField txtSize;

	@FXML
	private TextField txtDocumentCount;

	@FXML
	private TextField txtAvgObjSize;

	@FXML
	private TextField txtStorageSize;

	@FXML
	private TextField txtTotalIndexSize;

	@FXML
	private CheckBox chkIsCapped;
	
	@FXML
	private TextArea txtIndexes;

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		loadEvents();
		loadTreeView();
	}

	private void loadEvents() {
		profilingToggle();
		rdbLevel1();
		sliderChange();
		treeViewSelectCollection();
		enableProfiling();
		deleteProfile();
		refreshTreeView();
	}

	private void refreshTreeView() {
		btnRefreshTreeView.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				loadTreeView();
			}
		});
	}

	private void deleteProfile() {
		btnDeleteProfile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				DbHelper.deleteProfilingInfo(selectedDbName);
				loadTreeView();
			}
		});
	}

	private void enableProfiling() {
		btnProfile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				int option = rdbProfile1.isSelected() == true ? 1 : 2;
				int millis = (int) sldThreshold.getValue();
				DbHelper.setProfilingLevel(selectedDbName, option, millis);
			}
		});
	}

	private void treeViewSelectCollection() {
		trvDBandCollections.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<TreeItem<String>>() {
					public void changed(ObservableValue<? extends TreeItem<String>> observable,
							TreeItem<String> oldValue, TreeItem<String> newValue) {
						if (newValue != null) {
							String dbName = "";
							if (newValue.isLeaf() && !newValue.getValue().equals("Databases")) {
								dbName = newValue.getParent().getValue();
								selectedDbName = dbName;
								selectedCollectionName = newValue.getValue();
								setCollectionStatsView(selectedDbName, selectedCollectionName);
							} else if (newValue.getParent() != null) {
								dbName = newValue.getValue();
								selectedDbName = dbName;
								selectedCollectionName = "";
							}
							if (!dbName.isEmpty()) {
								ProfilingLevel result = DbHelper.getProfilingLevel(dbName);
								if (result != null) {
									switchToggle(result.getProfileLevel());
									selectRadioButton(result.getProfileLevel());
									sldThreshold.setValue(result.getSlowMs());
								}
							}
						}
					}
				});
	}

	private void setCollectionStatsView(String dbName, String collectionName) {
		CollectionStats colStats = DbHelper.getCollectionStats(dbName, collectionName);
		if (colStats != null) {
			txtSize.setText(String.valueOf(colStats.getSize()));
			txtAvgObjSize.setText(String.valueOf(colStats.getAvgObjectSize()));
			txtDocumentCount.setText(String.valueOf(colStats.getDocumentCount()));
			txtStorageSize.setText(String.valueOf(colStats.getStorageSize()));
			txtTotalIndexSize.setText(String.valueOf(colStats.getTotalIndexSize()));
			chkIsCapped.setSelected(colStats.isCapped());
			txtIndexes.clear();
			for (Map.Entry<String, Integer> entry : colStats.getlIndexSize().entrySet()) 
				txtIndexes.appendText(entry.getKey() + ": " + entry.getValue() + "\n");
		}
	}

	private void switchToggle(int level) {
		switch (level) {
		case 0:
			tgEnableProfiling.setSelected(false);
			break;
		case 1:
		case 2:
			tgEnableProfiling.setSelected(true);
		default:
			break;
		}
	}

	private void selectRadioButton(int level) {
		switch (level) {
		case 0:
			break;
		case 1:
			rdbProfile1.setSelected(true);
			lblQueryThreshold.setVisible(true);
			lblThresholdValue.setVisible(true);
			sldThreshold.setVisible(true);
			break;
		case 2:
			rdbProfile2.setSelected(true);
			lblQueryThreshold.setVisible(false);
			lblThresholdValue.setVisible(false);
			sldThreshold.setVisible(false);
		default:
			break;
		}
	}

	private void profilingToggle() {
		tgEnableProfiling.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					pnlProfilingOptions.setVisible(true);
					btnDeleteProfile.setVisible(true);
				} else {
					if (oldValue)
						DbHelper.setProfilingLevel(selectedDbName, 0, (int) sldThreshold.getValue());
					pnlProfilingOptions.setVisible(false);
					btnDeleteProfile.setVisible(false);
				}
			}
		});
	}

	private void rdbLevel1() {
		rdbProfile1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					lblQueryThreshold.setVisible(true);
					sldThreshold.setVisible(true);
					lblThresholdValue.setVisible(true);
					int value = (int) sldThreshold.getValue();
					lblThresholdValue.setText(String.valueOf(value));
				} else {
					lblQueryThreshold.setVisible(false);
					sldThreshold.setVisible(false);
					lblThresholdValue.setVisible(false);
				}
			}
		});
	}

	private void sliderChange() {
		sldThreshold.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				lblThresholdValue.setText(String.valueOf(newValue.intValue()));
			}
		});
	}

	private void loadTreeView() {
		loadDatabases();
		rootNode.getChildren().clear();
		for (String string : DBs) {
			TreeItem<String> item = new TreeItem<String>(string);
			rootNode.getChildren().add(item);
			MongoIterable<String> collections = DbHelper.getCollections(string);
			if (collections != null)
				for (String string2 : collections) {
					TreeItem<String> child = new TreeItem<String>(string2);
					item.getChildren().add(child);
				}
		}
		trvDBandCollections.setRoot(rootNode);
	}

	private void loadDatabases() {
		DBs = new ArrayList<String>();
		MongoIterable<String> rdb = DbHelper.getDatabases();
		if (rdb != null)
			for (String string : rdb)
				DBs.add(string);
	}
}
