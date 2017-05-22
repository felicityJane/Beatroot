package musicplayer.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

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
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import musicplayer.DB_Connector;
import musicplayer.DialogBoxManager;
import musicplayer.SceneManager;
import musicplayer.model.Country;
import musicplayer.model.Gender;
import musicplayer.model.GlobalVariables;
import musicplayer.model.PaymentMethod;
import musicplayer.model.PremiumUser;
import musicplayer.model.TrialUser;

public class ModifyUserController implements Initializable {
	@FXML
	private Button modifyUsersButton;
	@FXML
	private Pane adminMenu;
	// Trial users tables and columns
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
	// @FXML
	// private TextField userName;
	// @FXML
	// private TextField userPassword;
	// @FXML
	// private TextField firstName, lastName, email, confirmEmail, phoneNumber,
	// physicalAddress, postalCode, city;
	private ObservableList<TrialUser> data = FXCollections.observableArrayList();
	private ObservableList<Country> countryList = FXCollections.observableArrayList(Country.values());
	private ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.values());
	private GlobalVariables variables = GlobalVariables.getInstance();
	private DB_Connector databaseConnector;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private ArrayList<TrialUser> toRemove = new ArrayList<TrialUser>();
	// Premium users table and columns
	/**
	 * <TableColumn fx:id="premUserNameCol" prefWidth="75.0" text="User name" />
	 * <TableColumn fx:id="premDisplayNameCol" prefWidth="75.0" text="Display
	 * name" />
	 * <TableColumn fx:id="premPasswordCol" prefWidth="75.0" text="Password" />
	 * <TableColumn fx:id="premUserDescriptionCol" prefWidth="75.0" text="User
	 * description" />
	 * <TableColumn fx:id="premFirstNameCol" prefWidth="75.0" text="First name"
	 * />
	 * <TableColumn fx:id="premLastNameCol" prefWidth="75.0" text="Last name" />
	 * <TableColumn fx:id="premDateOfBirthCol" prefWidth="75.0" text="Date of
	 * birth" />
	 * <TableColumn fx:id="premEmailCol" prefWidth="75.0" text="Email address"
	 * /> <TableColumn fx:id="premPhoneNumberCol" prefWidth="75.0" text="Phone
	 * number" />
	 * <TableColumn fx:id="premPhysicalAddressCol" prefWidth="75.0" text=
	 * "Physical address" />
	 * <TableColumn fx:id="premCityCol" prefWidth="75.0" text="City" />
	 * <TableColumn fx:id="premPostalCodeCol" prefWidth="75.0" text="Postal
	 * code" />
	 * <TableColumn fx:id="premCountryCol" prefWidth="75.0" text="Country" />
	 * <TableColumn fx:id="premGenderCol" prefWidth="75.0" text="Gender" />
	 * <TableColumn fx:id="premPhoneNumberCol" prefWidth="75.0" text="Phone
	 * number" />
	 * <TableColumn fx:id="premBillBankCardCol" prefWidth="75.0" text="Bank card
	 * number" />
	 * <TableColumn fx:id="billExpirationDateCol" prefWidth="75.0" text=
	 * "Expiration date" />
	 * <TableColumn fx:id="premBillPaymentMethodCol" prefWidth="75.0" text=
	 * "Payment method" />
	 * <TableColumn fx:id="premBankCardPrintNameCol" prefWidth="75.0" text=
	 * "Bankcard print name" />
	 * <TableColumn fx:id="premBillingAddressCol" prefWidth="75.0" text="Billing
	 * address" />
	 * <TableColumn fx:id="premBillingCityCol" prefWidth="75.0" text="Billing
	 * city" />
	 * <TableColumn fx:id="premBillingPostalCodeCol" prefWidth="75.0" text=
	 * "Billing postal code" />
	 * <TableColumn fx:id="premBillingCountryCol" prefWidth="75.0" text="Billing
	 * country" />
	 * <TableColumn fx:id="premBillingPhoneNumberCol" prefWidth="75.0" text=
	 * "Billing phone number" />
	 */

	@FXML
	private TableView<PremiumUser> premiumUsersTable;
	@FXML
	private TableColumn<PremiumUser, String> premUserNameCol;
	@FXML
	private TableColumn<PremiumUser, String> premDisplayNameCol;
	@FXML
	private TableColumn<PremiumUser, String> premPasswordCol;
	@FXML
	private TableColumn<PremiumUser, String> premUserDescriptionCol;
	@FXML
	private TableColumn<PremiumUser, String> premFirstNameCol;
	@FXML
	private TableColumn<PremiumUser, String> premLastNameCol;
	@FXML
	private TableColumn<PremiumUser, Date> premDateOfBirthCol;
	@FXML
	private TableColumn<PremiumUser, String> premEmailCol;
	@FXML
	private TableColumn<PremiumUser, String> premPhysicalAddressCol;
	@FXML
	private TableColumn<PremiumUser, String> premCityCol;
	@FXML
	private TableColumn<PremiumUser, String> premPostalCodeCol;
	@FXML
	private TableColumn<PremiumUser, Country> premCountryCol;
	@FXML
	private TableColumn<PremiumUser, Gender> premGenderCol;
	@FXML
	private TableColumn<PremiumUser, String> premPhoneNumberCol;
	@FXML
	private TableColumn<PremiumUser, String> premBillBankCardCol;
	@FXML
	private TableColumn<PremiumUser, Date> premBillExpirationDateCol;
	@FXML
	private TableColumn<PremiumUser, PaymentMethod> premBillPaymentMethodCol;
	@FXML
	private TableColumn<PremiumUser, String> premBankCardPrintNameCol;
	@FXML
	private TableColumn<PremiumUser, String> premBillingAddressCol;
	@FXML
	private TableColumn<PremiumUser, String> premBillingCityCol;
	@FXML
	private TableColumn<PremiumUser, String> premBillingPostalCodeCol;
	@FXML
	private TableColumn<PremiumUser, Country> premBillingCountryCol;
	@FXML
	private TableColumn<PremiumUser, String> premBillingPhoneNumberCol;
	private ObservableList<PremiumUser> premiumData = FXCollections.observableArrayList();
	private ArrayList<PremiumUser> toRemovePremium = new ArrayList<PremiumUser>();
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
		premiumUsersTable.setEditable(true);
		trialUsersTable.setVisible(true);
		premiumUsersTable.setVisible(false);
		variables.getAdminMenuController().getModifyUsersButton().setText("Main page");
		variables.getAdminMenuController().getModifyUsersButton().setOnAction((event) -> {
			try {
				SceneManager.sceneManager.changeScene(event, "view/welcomeMenu.fxml");
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		});
		databaseConnector = new DB_Connector(
				"jdbc:mysql://127.0.0.1:3306/beatroot?user=root&password=root&useSSL=false");
		databaseConnector.getTrialUsersDetails();
		databaseConnector.getPremiumUsersDetails();
		trialUserNameColumn.setEditable(true);
		trialUserNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		trialUserNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		trialUserNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TrialUser, String>>() {
			@Override
			public void handle(CellEditEvent<TrialUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';

				if (!event.getNewValue().equalsIgnoreCase("delete")) {
					trialUserNameColumn.setText(event.getOldValue());
					getTrialUsersTable().refresh();
					DialogBoxManager.errorDialogBox("You can only delete",
							"Editing the user name column can only be done with the delete word to delete the entire row");
					// event.getTableView().getItems().get(event.getTablePosition().getRow())
				} else {
					databaseConnector.delete("`beatroot`.`trial_user`", "`user_name`='"
							+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
							+ "'");
					databaseConnector.delete("`beatroot`.`playlist`", "`owner`='"
							+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
							+ "'");
					databaseConnector.delete("`beatroot`.`user_link`", "`user`='"
							+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
							+ "'");
					for (TrialUser trial : data) {
						if (trial.getUserName().equalsIgnoreCase(
								event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName())) {
							toRemove.add(trial);
						}

					}
					System.out.println(toRemove.size());
					System.out.println(toRemove.get(0).getDisplayName());
					data.removeAll(toRemove);
					System.out.println(toRemove.get(0).getDisplayName());
					// System.out.println(data.contains(
					// event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()));
					toRemove.remove(0);

					getTrialUsersTable().refresh();
				}
			}
		});
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

		premUserNameCol.setEditable(true);
		premUserNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
		premUserNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premUserNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';

				if (!event.getNewValue().equalsIgnoreCase("delete")) {
					premUserNameCol.setText(event.getOldValue());
					getTrialUsersTable().refresh();
					DialogBoxManager.errorDialogBox("You can only delete",
							"Editing the user name column can only be done with the delete word to delete the entire row");
					// event.getTableView().getItems().get(event.getTablePosition().getRow())
				} else {
					databaseConnector.delete("`beatroot`.`premium_user`", "`user_name`='"
							+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
							+ "'");
					databaseConnector.delete("`beatroot`.`playlist`", "`owner`='"
							+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
							+ "'");
					databaseConnector.delete("`beatroot`.`user_link`", "`user`='"
							+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
							+ "'");
					for (PremiumUser prem : premiumData) {
						if (prem.getUserName().equalsIgnoreCase(
								event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName())) {
							toRemovePremium.add(prem);
						}

					}
					System.out.println(toRemovePremium.size());
					System.out.println(toRemovePremium.get(0).getDisplayName());
					premiumData.removeAll(toRemovePremium);
					System.out.println(toRemovePremium.get(0).getDisplayName());
					// System.out.println(data.contains(
					// event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()));
					toRemovePremium.remove(0);

					getPremiumUsersTable().refresh();
				}
			}
		});
		premUserDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("userDescription"));
		premUserDescriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premUserDescriptionCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`user_description`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setUserDescription(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premDisplayNameCol.setCellValueFactory(new PropertyValueFactory<>("displayName"));
		premDisplayNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premDisplayNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`display_name`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setDisplayName(event.getNewValue());
				getPremiumUsersTable().refresh();
				System.out.println(premiumData.get(1).getDisplayName());
			}
		});
		premPasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
		premPasswordCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premPasswordCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`password`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).setPassword(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		premFirstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premFirstNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`first_name`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setFirstName(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		premLastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premLastNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`last_name`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).setLastName(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premDateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		premDateOfBirthCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
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
		premDateOfBirthCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, Date>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, Date> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`date_of_birth`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setDateOfBirth(event.getNewValue());
				getPremiumUsersTable().refresh();
				System.out.println(premiumData.get(1).getDateOfBirth());
			}
		});
		premEmailCol.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
		premEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premEmailCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`email_address`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setEmailAddress(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premPhysicalAddressCol.setCellValueFactory(new PropertyValueFactory<>("streetNameAndNumber"));
		premPhysicalAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premPhysicalAddressCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`physical_address`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setStreetNameAndNumber(event.getNewValue());
				getPremiumUsersTable().refresh();
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
		premCityCol.setCellValueFactory(new PropertyValueFactory<>("cityOfResidence"));
		premCityCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premCityCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`city_of_residence`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setCity(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		premPostalCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premPostalCodeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`postal_code`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setPostalCode(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
		premGenderCol.setCellFactory(ChoiceBoxTableCell.forTableColumn(genderList));
		premGenderCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, Gender>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, Gender> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				System.out.println(Gender.fromString(event.getNewValue().toString()));

				databaseConnector.update("`beatroot`.`premium_user`", "`gender_gender_id`",
						"'" + Gender.fromString(event.getNewValue().toString()) + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).setGender(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
		premCountryCol.setCellFactory(ChoiceBoxTableCell.forTableColumn(countryList));
		premCountryCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, Country>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, Country> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`country`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setCountry(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		premPhoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premPhoneNumberCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`phone_number`", "'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setPostalCode(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premBankCardPrintNameCol.setCellValueFactory(new PropertyValueFactory<>("cardHolderName"));
		premBankCardPrintNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premBankCardPrintNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`billing_account_owner_name`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow()).getPhysicalAddress()
						.setPostalCode(event.getNewValue());
				getPremiumUsersTable().refresh();
			}
		});
		premBillBankCardCol.setCellValueFactory(new PropertyValueFactory<>("bankCardNumber"));
		premBillBankCardCol.setCellFactory(TextFieldTableCell.forTableColumn());
		premBillBankCardCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PremiumUser, String>>() {
			@Override
			public void handle(CellEditEvent<PremiumUser, String> event) {
				// UPDATE `beatroot`.`trial_user` SET `display_name`='nero'
				// WHERE `user_name`='nero';
				databaseConnector.update("`beatroot`.`premium_user`", "`bank_card_number`",
						"'" + event.getNewValue() + "'",
						"`user_name`=" + "'"
								+ event.getTableView().getItems().get(event.getTablePosition().getRow()).getUserName()
								+ "'");
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setPhoneNumber(event.getNewValue());
				getPremiumUsersTable().refresh();
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

	public ArrayList<TrialUser> getToRemove() {
		return toRemove;
	}

	public void setToRemove(ArrayList<TrialUser> toRemove) {
		this.toRemove = toRemove;
	}

	public void loadPremiumUsers() {
		getTrialUsersTable().setVisible(false);
		getPremiumUsersTable().setVisible(true);
	}

	public void loadTrialUsers() {
		getPremiumUsersTable().setVisible(false);
		getTrialUsersTable().setVisible(true);
	}

	public TableView<PremiumUser> getPremiumUsersTable() {
		return premiumUsersTable;
	}

	public void setPremiumUsersTable(TableView<PremiumUser> premiumUsersTable) {
		this.premiumUsersTable = premiumUsersTable;
	}

	public ArrayList<PremiumUser> getToRemovePremium() {
		return toRemovePremium;
	}

	public void setToRemovePremium(ArrayList<PremiumUser> toRemovePremium) {
		this.toRemovePremium = toRemovePremium;
	}

	public ObservableList<PremiumUser> getPremiumData() {
		return premiumData;
	}

	public void setPremiumData(ObservableList<PremiumUser> premiumData) {
		this.premiumData = premiumData;
	}
}
