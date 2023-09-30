package com.gui.controllers;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import com.dao.CharacterDAO;
import com.entity.ParticipationLevel;
import com.entity.Character;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddCharacterViewController implements Initializable{
    @FXML
    private Button addButton;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private TextArea descriptionTextarea;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField nameTextfield;

    @FXML
    private ChoiceBox<String> seletorOfTheRole;

	@FXML
	private void addToDataBase(ActionEvent event) {
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";
		String errorStr="Something goes wrong..Correct your errors";
		String name=nameTextfield.getText();
		String description=descriptionTextarea.getText();
		ParticipationLevel partLevel=ParticipationLevel.getParticipationLevelByString(seletorOfTheRole.getValue());
        CharacterDAO characterDAO=new CharacterDAO();
		 boolean checkValidation=true;
		
		if(name.isEmpty()) {
			checkValidation=false;
		     nameTextfield.setStyle(styleError);
			new animatefx.animation.Shake(nameTextfield).play();
		}
		
		if(description.isEmpty()) {
			checkValidation=false;
			descriptionTextarea.setStyle(styleError);
			new animatefx.animation.Shake(descriptionTextarea).play();
		}
		if(checkValidation) {
			Character character=new Character(name,description,partLevel);
			characterDAO.create(character);
		    loadMainWindow();
		    closeCurrentStage(event);
		}else {
			errorLabel.setText(errorStr);
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
		String defaultValue="Select the role..";
		ObservableList<String> values = FXCollections.observableArrayList();
        values.add(defaultValue);
		for(ParticipationLevel type: ParticipationLevel.values()) {
		    values.add(type.getDisplayName());
		}
		seletorOfTheRole.getSelectionModel().select(defaultValue);
		seletorOfTheRole.setItems(values);
		
	}

}
