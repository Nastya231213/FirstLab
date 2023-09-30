package com.gui.controllers;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.dao.AuthorDAO;
import com.entity.Author;

import com.gui.helpers.DataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditAuthorViewController implements Initializable {
	
	private Author author;
    @FXML
    private Button addButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField nameTextfield;

    @FXML
    private TextField pseudonymTextfield;

    private AuthorDAO authorDAO=new AuthorDAO();

	@FXML
	private void addToDataBase(ActionEvent event) {
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";
		String name=nameTextfield.getText();
		String pseudonym=pseudonymTextfield.getText();
		 boolean checkValidation=true;
		
		if(name.isEmpty()) {
			checkValidation=false;
		     nameTextfield.setStyle(styleError);
			new animatefx.animation.Shake(nameTextfield).play();
		}
		if(pseudonym.isEmpty()) {
			checkValidation=false;
			pseudonymTextfield.setStyle(pseudonym);
			new animatefx.animation.Shake(pseudonymTextfield).play();
		}
		
		if(checkValidation) {
            author.setFullname(name);
            author.setPseudonym(pseudonym);
            authorDAO.update(author);
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        DataModel dataModel=DataModel.getDataModel();
        author=dataModel.getAuthor();
        nameTextfield.setText(author.getFullname());
        pseudonymTextfield.setText(author.getPseudonym());
	}

}
