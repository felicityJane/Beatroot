package musicplayer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import musicplayer.DialogBoxManager;
import musicplayer.model.GlobalVariables;
import static musicplayer.SceneManager.sceneManager;

public class MainMenuController implements Initializable{

	@FXML private MenuBar menuBar;
	@FXML private MenuItem logoutMenu, settingsMenu, aboutMenu,faqsMenu, helpMenuItem;
	private GlobalVariables globalVariables = GlobalVariables.getInstance();
	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		globalVariables.setMainMenuController(this);
	}
	 void enableMenuItemsAboutPage(){
		logoutMenu.setDisable(false);
		settingsMenu.setDisable(false);
		aboutMenu.setDisable(true);
	}
	void enableMenuItems(){
		logoutMenu.setDisable(false);
		settingsMenu.setDisable(false);
	}
	 void enableMenuItemsFaqsPage(){
		logoutMenu.setDisable(false);
		settingsMenu.setDisable(false);
		faqsMenu.setDisable(true);
	}
	//TODO <fx:include fx:id="adminMenu" source="AdminMenu.fxml"/>
	void menuBarFitToParent(AnchorPane parentAnchor){
		menuBar.prefWidthProperty().bind(parentAnchor.widthProperty());
	}

	@FXML
	private void logOutMenuOption(){
		boolean answer = DialogBoxManager.confirmationDialogBox("Are you sure you want to log out?","click ok to continue");
		if (answer){
			try {
				GlobalVariables.getInstance().getContactList().clear();
				sceneManager.changeSceneMenuBar(menuBar,"view/logInMenu.fxml");
			}catch (Exception e){
				DialogBoxManager.errorDialogBox("Error occurred","logging out, please try again");
				e.printStackTrace();
			}
		}
	}
	@FXML
	private void exitMenuOption(){
		System.exit(0);
	}
	@FXML
	private void settingsMenuOption(){
		try {
			sceneManager.popUpWindow("view/settingsPage.fxml");
		} catch (IOException e) {
			DialogBoxManager.errorDialogBox("Error occurred","Switching to settings page from menu bar selection, please try again");
			e.printStackTrace();
		}
	}
	@FXML
	private void aboutMenuOption(){
		try {
			sceneManager.popUpWindow("view/aboutPage.fxml");
		} catch (IOException e) {
			DialogBoxManager.errorDialogBox("Error occurred","Switching to about page from menu bar selection, please try again");
			e.printStackTrace();
		}
	}
	@FXML
	private void faqsMenuOption(){
		try {
			sceneManager.popUpWindow("view/faqsPage.fxml");
		} catch (IOException e) {
			DialogBoxManager.errorDialogBox("Error occurred","Switching to faqs page from menu bar selection, please try again");
			e.printStackTrace();
		}
	}
	@FXML
	private void setHelpMenuItem(){
		try {
			sceneManager.popUpWindow("view/helpPage.fxml");
		} catch (IOException e) {
			DialogBoxManager.errorDialogBox("Error occurred","Switching to faqs page from menu bar selection, please try again");
			e.printStackTrace();
		}
	}
}