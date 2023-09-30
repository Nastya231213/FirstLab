package com.gui.controllers;

import java.io.IOException;
import java.util.Map;
import com.dao.AuthorDAO;
import com.dao.CharacterDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CharacterAddToTheBookViewController {
	@FXML
	private Button addButton;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnMinimize;

	@FXML
	private Label errorLabel;

	@FXML
	private TextField characterTextfield;

	@FXML
	private TextField bookIdTextField;

	@FXML
	private void addToDataBase(ActionEvent event) {
		String errorStr = "Something goes wrong..Correct your errors";
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";
		CharacterDAO characterDAO = new CharacterDAO();

		int bookId = 0;
		int characterId = 0;

		boolean checkValidation = true;
		try {
			bookId = Integer.parseInt(bookIdTextField.getText());
			characterId = Integer.parseInt(characterTextfield.getText());

		} catch (NumberFormatException e) {
			checkValidation = false;
		}
		Map<Integer, Integer> bookCharacters = characterDAO.findConnectedCharactersAndBooks(characterId, bookId);
		if (bookCharacters.containsKey(bookId) && bookCharacters.get(bookId) == characterId) {
			checkValidation = false;
			errorStr = "This character has already added in the book";
		}

		if (checkValidation) {

			characterDAO.addCharacterToTheBook(bookId, characterId);
			loadMainWindow();
			closeCurrentStage(event);

		} else {
			errorLabel.setText(errorStr);
		}

	}

	@FXML
	public void backToMain(ActionEvent e) {
		loadMainWindow();
		closeCurrentStage(e);

	}

	@FXML
	public void minimizeWindow(ActionEvent event) {
		Stage stage = (Stage) btnMinimize.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	public void exitFromTheApp(ActionEvent e) {
		closeCurrentStage(e);
	}

	private void loadMainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gui/fxmlWindows/MainWindow.fxml"));
			Parent parent = loader.load();

			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeCurrentStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage currentStage = (Stage) source.getScene().getWindow();
		currentStage.close();

	}

}
