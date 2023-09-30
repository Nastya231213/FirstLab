package com.gui.controllers;

import java.io.IOException;
import com.dao.AuthorDAO;
import com.entity.Author;
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

public class AddAuthorViewController {
	@FXML
	private Button addButton;

	@FXML
	private Label errorLabel;
	@FXML
	private Button btnMinimize;
	@FXML
	private TextField nameTextfield;

	@FXML
	private TextField pseudonymTextfield;

	private AuthorDAO authorDAO = new AuthorDAO();

	@FXML
	public void minimizeWindow(ActionEvent event) {
		Stage stage = (Stage) btnMinimize.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void addToDataBase(ActionEvent event) {
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";
		String name = nameTextfield.getText();
		String pseudonym = pseudonymTextfield.getText();

		boolean checkValidation = true;

		if (name.isEmpty()) {
			checkValidation = false;
			nameTextfield.setStyle(styleError);
			new animatefx.animation.Shake(nameTextfield).play();
		}
		if (pseudonym.isEmpty()) {
			checkValidation = false;
			pseudonymTextfield.setStyle(pseudonym);
			new animatefx.animation.Shake(pseudonymTextfield).play();
		}

		if (checkValidation) {
			Author newAuthor = new Author(name, pseudonym);
			authorDAO.create(newAuthor);
			loadMainWindow();
			closeCurrentStage(event);
		}

	}

	@FXML
	public void exitFromTheApp(ActionEvent e) {
		closeCurrentStage(e);
	}

	@FXML
	public void backToMain(ActionEvent e) {
		loadMainWindow();
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
