package musicplayer.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import musicplayer.DB_Connector;
import musicplayer.SceneManager;
import musicplayer.model.Country;
import musicplayer.model.GlobalVariables;
import musicplayer.model.TrialUser;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.ResourceBundle;

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
	private TableColumn<TrialUser, String> trialUserStreetAddressColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserCityColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserPostalCodeColumn;
	@FXML
	private TableColumn<TrialUser, Country> trialUserCountryColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserPhoneNumberColumn;
	@FXML
	private TableColumn<TrialUser, Date> trialUserTrialEndColumn;
	@FXML
	private TableColumn<TrialUser, String> trialUserDescription;
	@FXML
	private TextField userName;
	@FXML
	private TextField userPassword;
	@FXML
	private TextField firstName, lastName, email, confirmEmail, phoneNumber, physicalAddress, postalCode, city;
	private ObservableList<TrialUser> data = FXCollections.observableArrayList();
	private ObservableList<Country> countryList = FXCollections.observableArrayList(Country.values());
	private GlobalVariables variables = GlobalVariables.getInstance();
	private DB_Connector databaseConnector;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
		trialUsersTable.setEditable(true);
		variables.setModifyUserController(this);
		System.out.println(adminMenu.getChildren().toString());
		variables.getAdminMenuController().getModifyUsersButton().setText("Main page");
		variables.getAdminMenuController().getModifyUsersButton().setOnAction((event) -> {
			try {
				SceneManager.getInstance().changeScene(event, "view/welcomeMenu.fxml");
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		});
		databaseConnector = new DB_Connector(
				"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
		databaseConnector.getTrialUsersDetails();
		System.out.println(data.get(0).getEmailAddress());
		System.out.println(data.get(0).getCountry());
		trialUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		trialUserDisplayNameColumn.setCellValueFactory(new PropertyValueFactory<>("displayName"));
		trialUserDisplayNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserDisplayNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`display_name`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setDisplayName(event.getNewValue());
				getTrialUsersTable().refresh();
				System.out.println(data.get(1).getDisplayName());
			}
		});
		trialUserPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		trialUserPasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserPasswordColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`password`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).setPassword(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});
		trialUserFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		trialUserFirstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserFirstNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`first_name`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setFirstName(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});
		trialUserLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		trialUserLastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserLastNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`last_name`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});
		trialUserDateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		trialUserDateOfBirthColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
			@Override
			public String toString(Date d) {
				if (d == null) {
					return "";
				} else {
					return sdf.format(d);
				}
			}

			@Override
			public Date fromString(String string) {
				try {
					Date date = java.sql.Date.valueOf(LocalDate.parse(string, dateFormatter));
					return date;
				} catch (DateTimeParseException ex) {
					ex.printStackTrace();
					return null;
				}
			}
		}

		));
		trialUserDateOfBirthColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, Date>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, Date> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`date_of_birth`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setDateOfBirth(event.getNewValue());
				getTrialUsersTable().refresh();
				System.out.println(data.get(1).getDateOfBirth());
			}
		});
		trialUserEmailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
		trialUserEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserEmailColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`email_address`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setEmailAddress(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});
		trialUserStreetAddressColumn.setCellValueFactory(new PropertyValueFactory<>("streetNameAndNumber"));
		trialUserStreetAddressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserStreetAddressColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`physical_address`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setStreetNameAndNumber(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});
		/**
		 * event.getTableView().getItems().get(event.getTablePosition().getRow())
		 * .setPhysicalAddress(new Address(event.getNewValue(),
		 * event.getTableView().getItems().get(event.getTablePosition().getRow())
		 * .getPhysicalAddress().getCity(),
		 * event.getTableView().getItems().get(event.getTablePosition().getRow())
		 * .getPhysicalAddress().getPostalCode(),
		 * event.getTableView().getItems().get(event.getTablePosition().getRow())
		 * .getPhysicalAddress().getCountry()));
		 */
		trialUserCityColumn.setCellValueFactory(new PropertyValueFactory<>("cityOfResidence"));
		trialUserCityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserCityColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`city_of_residence`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setCity(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});
		trialUserPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		trialUserPostalCodeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserPostalCodeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`postal_code`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setPostalCode(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});
		trialUserCountryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
		trialUserCountryColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(countryList));
		trialUserCountryColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, Country>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, Country> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`country`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setCountry(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});
		trialUserPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		trialUserPhoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserPhoneNumberColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`phone_number`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setPhoneNumber(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});
		trialUserTrialEndColumn.setCellValueFactory(new PropertyValueFactory<>("freeTrialEndDate"));
		trialUserTrialEndColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
			@Override
			public String toString(Date d) {
				if (d == null) {
					return "";
				} else {
					return sdf.format(d);
				}
			}

			@Override
			public Date fromString(String string) {
				try {
					Date date = java.sql.Date.valueOf(LocalDate.parse(string, dateFormatter));
					return date;
				} catch (DateTimeParseException ex) {
					ex.printStackTrace();
					return null;
				}
			}
		}

		));
		trialUserTrialEndColumn.setEditable(false);
		trialUserDescription.setCellValueFactory(new PropertyValueFactory<>("userDescription"));
		trialUserDescription.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserDescription.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`trial_user`", "`user_description`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setUserDescription(event.getNewValue());
				getTrialUsersTable().refresh();
			}
		});

		// data.addListener(new ListChangeListener<TrialUser>() {
		//
		// @Override
		// public void onChanged(javafx.collections.ListChangeListener.Change<?
		// extends TrialUser> change) {
		// while (change.next()) {
		// if (change.wasUpdated()) {
		// System.out.println(change);
		// }
		// }
		// }
		// });
	}

	public void modifyUserButtonPressed(ActionEvent event) {
		try {
			SceneManager.getInstance().changeScene(event, "view/welcomeMenu.fxml");
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
