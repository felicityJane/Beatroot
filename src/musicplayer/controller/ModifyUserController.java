package musicplayer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import musicplayer.DB_Connector;
import musicplayer.SceneManager;
import musicplayer.model.Address;
import musicplayer.model.GlobalVariables;
import musicplayer.model.TrialUser;

public class ModifyUserController implements Initializable {
	@FXML
	private Button modifyUsersButton;
	@FXML
	private Pane adminMenu;
	@FXML
	private TableView<TrialUser> trialUsersTable;
	@FXML
	private TableColumn<TrialUser, String> trialUserNameColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserDisplayNameColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserPasswordColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserFirstNameColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserLastNameColumn;
	@FXML
	private TableColumn<TrialUser, Date> trialUserDateOfBirthColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserEmailColumn;
	@FXML
	private TableColumn<Address, String> trialUserStreetAddressColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserCityColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserPostalCodeColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserCountryColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserPhoneNumberColumn;
	@FXML
	private TableColumn<TrialUser, Date> trialUserTrialEndColumn;
	final ObservableList<TrialUser> data = FXCollections.observableArrayList();
	private GlobalVariables variables = GlobalVariables.getInstance();
	private DB_Connector databaseConnector;
	/*
	 * <TableColumn fx:id="trialUserNameColumn" prefWidth="75.0"
	 * text="User name" /> <TableColumn fx:id="trialUserDisplayNameColumn"
	 * prefWidth="85.0" text="Display name" /> <TableColumn
	 * fx:id="trialUserPasswordColumn" prefWidth="65.0" text="Password" />
	 * <TableColumn fx:id="trialUserFirstNameColumn" prefWidth="75.0"
	 * text="First name" /> <TableColumn fx:id="trialUserLastNameColumn"
	 * prefWidth="75.0" text="Last name" /> <TableColumn
	 * fx:id="trialUserDateOfBirthColumn" prefWidth="92.0" text="Date of birth"
	 * /> <TableColumn fx:id="trialuserEmailColumn" text="Email" /> <TableColumn
	 * fx:id="trialUserStreetAddressColumn" prefWidth="75.0"
	 * text="Street address" /> <TableColumn fx:id="trialUserCityColumn"
	 * prefWidth="75.0" text="City" /> <TableColumn
	 * fx:id="trialUserPostalCodeColumn" prefWidth="75.0" text="Postal code" />
	 * <TableColumn fx:id="trialUserCountryColumn" prefWidth="75.0"
	 * text="Country" /> <TableColumn fx:id="trialUserPhoneNumberColumn"
	 * prefWidth="75.0" text="Phone number" /> <TableColumn
	 * fx:id="trialUserTrialEndColumn" prefWidth="75.0" text="Trial end date"
	 * />(non-Javadoc)
	 */

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		variables.setModifyUserController(this);
		System.out.println(adminMenu.getChildren().toString());
		variables.getAdminMenuController().getModifyUsersButton().setText("Main page");
		databaseConnector = new DB_Connector(
				"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
		databaseConnector.getTrialUsersDetails();
		System.out.println(data.get(0).getEmailAddress());
		System.out.println(data.get(0).getCountry());
		trialUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		trialUserDisplayNameColumn.setCellValueFactory(new PropertyValueFactory<>("displayName"));
		trialUserPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		trialUserFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		trialUserLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		trialUserDateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		trialUserEmailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
		trialUserStreetAddressColumn.setCellValueFactory(new PropertyValueFactory<>("streetNameAndNumber"));
		trialUserCityColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfResidence"));
		trialUserPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		trialUserCountryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
		trialUserPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		trialUserTrialEndColumn.setCellValueFactory(new PropertyValueFactory<>("freeTrialEndDate"));
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
