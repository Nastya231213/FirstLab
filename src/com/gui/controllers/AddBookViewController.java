package com.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


import com.database.DataBase;
import com.elements.Author;
import com.elements.Series;
import com.elements.SeriesType;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddBookViewController implements Initializable {
    @FXML
    private Button addButton;

    @FXML
    private TextArea checkValidation;

    @FXML
    private DatePicker date;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField quantityOfPages;

    @FXML
    private ChoiceBox<String> selectorOfAuthor;

    @FXML
    private ChoiceBox<String> selectorOfSeria;
    
    @FXML
    private TextArea annotationTextArea;
    
    @FXML
    private TextField titleTextField;

	DataBase db;


	@FXML
	private void addToDataBase(ActionEvent event) {
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";

		boolean checkValidation=true;
        int quantityPages;

		String annotationText=annotationTextArea.getText();
        String nameText=nameTextField.getText();
		LocalDate dateOfPublishement=date.getValue();
		String title=titleTextField.getText();
		//We make the validation that all the necessary information about the book is chosen
		try {
			quantityPages=Integer.parseInt(quantityOfPages.getText());
		}catch(NumberFormatException e) {
			checkValidation=false;
		}
		if(annotationText.isEmpty()) {
			annotationTextArea.setStyle(styleError);
			new animatefx.animation.Shake(annotationTextArea).play();
			checkValidation=false;
		}
		if(nameText.isEmpty()) {
			nameTextField.setStyle(styleError);
			new animatefx.animation.Shake(nameTextField).play();
			checkValidation=false;
		}
		if(dateOfPublishement==null) {
			date.setStyle(styleError);
			new animatefx.animation.Shake(date).play();
			checkValidation=false;
		}
		if(title==null) {
			titleTextField.setStyle(styleError);
			new animatefx.animation.Shake(titleTextField).play();
			checkValidation=false;	
		}
		
		if(checkValidation) {
			
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String defaultSelector="Select the series";
		// Add all the series types to our choiceBox
		db = DataBase.getInstance();
		List<Series> seriesList=db.getSeries();
		ObservableList<String> valuesForSelectors =FXCollections.observableArrayList(defaultSelector);
        for(Series seriesParameter:seriesList) {
        	valuesForSelectors.add(seriesParameter.getName());
        	
        }
        selectorOfSeria.setItems(valuesForSelectors);
        selectorOfSeria.getSelectionModel().select(defaultSelector);
        
      
        //fill the second selector of authors
        defaultSelector="Select the authors";
        List<Author> authorsList=db.getAuthors();
        valuesForSelectors=FXCollections.observableArrayList(defaultSelector);
        for(Author authorParameter:authorsList) {
        	valuesForSelectors.add(authorParameter.getPseudonym());
        }
        selectorOfAuthor.setItems(valuesForSelectors);
        
	
	}

	public void loadMainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			Parent parent = loader.load();

			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeCurrentStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage currentStage = (Stage) source.getScene().getWindow();
		currentStage.close();

	}

}
