package com.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.dao.SeriesDAO;
import com.entity.Series;
import com.entity.SeriesType;
import com.gui.helpers.DataModel;
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

public class AddSeriesViewController implements Initializable {
	private SeriesDAO seriesDAO;

	@FXML
	private Button addButton;

	@FXML
	private Button btnClose;
	@FXML
	private DatePicker datePicker;

	@FXML
	private TextArea description;

	@FXML
	private TextField name;

	@FXML
	private Label errorLabel;

	@FXML
	private ChoiceBox<String> selectorOfTypeSeries;

	@FXML
	void exitFromTheApp(ActionEvent event) {
		closeCurrentStage(event);
	}
	@FXML
	private void addToDataBase(ActionEvent event) {
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";
		String nameOfSeries = name.getText();

		String descriptionOfSeries = description.getText();
		DataModel dataModel = DataModel.getDataModel();

		LocalDate dateOfStart = datePicker.getValue();
		SeriesType typeOfSeries = SeriesType.getSeriesTypeFromString(selectorOfTypeSeries.getValue());
		boolean checkValidation = true;

		// apply validation

		if (nameOfSeries.isEmpty()) {
			name.setStyle(styleError);
			new animatefx.animation.Shake(name).play();
			checkValidation = false;
		}

		if (descriptionOfSeries.isEmpty()) {
			description.setStyle(styleError);
			new animatefx.animation.Shake(description).play();
			checkValidation = false;

		}

		if (dateOfStart == null) {
			datePicker.setStyle(styleError);
			new animatefx.animation.Shake(datePicker).play();
			checkValidation = false;

		}
		if (typeOfSeries == null) {
			selectorOfTypeSeries.setStyle(descriptionOfSeries);
			new animatefx.animation.Shake(selectorOfTypeSeries).play();
			checkValidation = false;
		}

		if (checkValidation) {

			Series series = new Series(nameOfSeries, dateOfStart, descriptionOfSeries, typeOfSeries);
			seriesDAO.create(series);
			loadMainWindow();
			closeCurrentStage(event);
		} else {

			errorLabel.setText("Validation failed. Please correct the errors.");
		}
	}

	@FXML
	public void backToMain(ActionEvent e) {
		loadMainWindow();
		closeCurrentStage(e);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		description.setWrapText(true);

		String defaultValue = "Select type of the series";
		seriesDAO = new SeriesDAO();
		// reset of focus from the textinputs
		// Add all the series types to our choiceBox
		ObservableList<String> values = FXCollections.observableArrayList();
		values.add(defaultValue);
		for (SeriesType type : SeriesType.values()) {
			values.add(type.getSeriesTypeName());
		}

		selectorOfTypeSeries.getSelectionModel().select(defaultValue);
		selectorOfTypeSeries.setItems(values);

	}

	public void loadMainWindow() {
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

	public void closeCurrentStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage currentStage = (Stage) source.getScene().getWindow();
		currentStage.close();

	}

}
