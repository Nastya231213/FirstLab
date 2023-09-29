package com.gui.controllers;

import java.io.IOException;
import java.util.Map;
import com.dao.AuthorDAO;
import com.dao.BookDAO;
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

public class BookAddToTheSeriesViewController {
	@FXML
	private Button addButton;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnMinimize;

	@FXML
	private Label errorLabel;

	@FXML
	private TextField seriesIdTextfield;

	@FXML
	private TextField bookIdTextfield;

	@FXML
	private void addToDataBase(ActionEvent event) {
		String errorStr = "Something goes wrong..Correct your errors";
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";
		String bookdIdStr = bookIdTextfield.getText();
		String seriesrIdStr = seriesIdTextfield.getText();
		BookDAO bookDAO = new BookDAO();
		int bookId = 0;
		int seriesId = 0;
		boolean checkValidation = true;

		if (bookdIdStr.isEmpty()) {
			bookIdTextfield.setStyle(errorStr);
			new animatefx.animation.Shake(bookIdTextfield).play();
			checkValidation = false;

		} else if (seriesrIdStr.isEmpty()) {
			seriesIdTextfield.setStyle(errorStr);
			new animatefx.animation.Shake(seriesIdTextfield).play();
			checkValidation = false;
		} else {

			try {
				bookId = Integer.parseInt(bookdIdStr);
				seriesId = Integer.parseInt(seriesrIdStr);
			} catch (NumberFormatException e) {
				checkValidation = false;
			}

			Map<Integer, Integer> bookCharacters = bookDAO.findConnectedCharactersAndBooks(seriesId, bookId);
			if (bookCharacters.containsKey(bookId) && bookCharacters.get(bookId) == seriesId) {
				checkValidation = false;
				errorStr = "This character has already added in the book";
			}

		}
		if (checkValidation) {

			bookDAO.addBookToTheSeries(bookId, seriesId);
			loadMainWindow();
			closeCurrentStage(event);

		} else {
			errorLabel.setText(errorStr);
		}

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
