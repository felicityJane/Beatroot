package musicplayer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import musicplayer.DB_Connector;
import musicplayer.SceneManager;
import musicplayer.model.GlobalVariables;
import musicplayer.model.TrialUser;

public class ModifyUserController implements Initializable {
	@FXML
	Button modifyUsersButton;
	@FXML
	private Pane adminMenu;
	@FXML
	private TableView<TrialUser> trialUsersTable;
	final ObservableList<TrialUser> data = FXCollections.observableArrayList();
	private GlobalVariables variables = GlobalVariables.getInstance();
	private DB_Connector databaseConnector;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		variables.setModifyUserController(this);
		System.out.println(adminMenu.getChildren().toString());
		variables.getAdminMenuController().getModifyUsersButton().setText("Main page");
		databaseConnector = new DB_Connector(
				"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
		databaseConnector.getTrialUsersDetails();
		System.out.println(data.toString());

	}

	public void modifyUserButtonPressed(ActionEvent event) {
		try {
			SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public ObservableList<TrialUser> getData() {
		return data;
	}

	public TableView<TrialUser> getTrialUsersTable() {
		return trialUsersTable;
	}

}
