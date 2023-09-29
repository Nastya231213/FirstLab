package com.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.database.DataBase;
import com.elements.Series;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainViewController implements Initializable{
	
    @FXML
    private Label errorLabel;

    @FXML
    private Button addAuthor;

    @FXML
    private Button addBook;

    @FXML
    private Button addSeries;
    
    private DataBase db;

    @FXML
    public void exitFromTheApp(ActionEvent e) {
    	closeCurrentStage(e);
    }
    @FXML
    public void LoadAddSeriesApp(ActionEvent event) {
            loadWindow("/com/gui/fxmlWindows/AddSeriesWindow.fxml","New series");
            closeCurrentStage(event);
    }
    @FXML
    public void LoadAddBookApp(ActionEvent event) {
        loadWindow("/com/gui/fxmlWindows/AddBookWindow.fxml","New book");
        closeCurrentStage(event);
    }
   
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		   db=DataBase.getInstance();
	       List<Series> listOfSeries=db.getSeries()	;
	       if(listOfSeries.size()!=0) {
	           System.out.println(listOfSeries.get(0).getName());
	       }
	}
	
	public void fiilTableWithData() {
		
	}

    public void loadWindow(String loc,String title) {
    	try {
    		Parent parent=FXMLLoader.load(getClass().getResource(loc));
            Stage stage=new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            
    	}catch (IOException e) {
            e.printStackTrace();
    	}
    }
    private void closeCurrentStage(ActionEvent event) {
    	Node source=(Node)event.getSource();
    	Stage currentStage=(Stage)source.getScene().getWindow();
        currentStage.close();
    }
    

    
}
