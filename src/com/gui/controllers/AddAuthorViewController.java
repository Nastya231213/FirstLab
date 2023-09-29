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

public class AddAuthorViewController implements Initializable {
    @FXML
    private Button addButton;

    @FXML
    private TextArea annotationTextArea;

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
    private TextField titleTextField;

	DataBase db;


	@FXML
	private void addToDataBase(ActionEvent event) {
         String annotationText=annotationTextArea.getText();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String defaultSelector="Select the series";
		// reset of focus from the textinputs
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
