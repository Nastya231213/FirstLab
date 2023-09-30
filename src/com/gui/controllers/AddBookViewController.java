package com.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.dao.AuthorDAO;
import com.dao.BookDAO;
import com.entity.Author;
import com.entity.Book;
import com.entity.Series;
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
	private Button btnMinimize;
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
	private TextArea annotationTextArea;

	@FXML
	private TextField titleTextField;

	private BookDAO bookDAO;
	private AuthorDAO authorDAO;
	@FXML
	public void minimizeWindow(ActionEvent event) {
		Stage stage = (Stage) btnMinimize.getScene().getWindow();
		stage.setIconified(true);
	}
	@FXML
	private void addToDataBase(ActionEvent event) {
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";

		boolean checkValidation = true;
		int quantityPages = 0;

		String title = titleTextField.getText();
		String annotationText = annotationTextArea.getText();
		String nameText = nameTextField.getText();
		int authorId = -1;

		LocalDate dateOfPublishement = date.getValue();

		// We make the validation that all the necessary information about the book is
		// chosen
		try {
			quantityPages = Integer.parseInt(quantityOfPages.getText());
		} catch (NumberFormatException e) {
			checkValidation = false;
		}
		if (annotationText.isEmpty()) {
			annotationTextArea.setStyle(styleError);
			new animatefx.animation.Shake(annotationTextArea).play();
			checkValidation = false;
		}
		if (nameText.isEmpty()) {
			nameTextField.setStyle(styleError);
			new animatefx.animation.Shake(nameTextField).play();
			checkValidation = false;
		}
		if (dateOfPublishement == null) {
			date.setStyle(styleError);
			new animatefx.animation.Shake(date).play();
			checkValidation = false;
		}
		if (title == null) {
			titleTextField.setStyle(styleError);
			new animatefx.animation.Shake(titleTextField).play();
			checkValidation = false;
		}

		if (checkValidation) {
			Book newBook = new Book(title, nameText, dateOfPublishement, quantityPages, annotationText, authorId);
			bookDAO.create(newBook);
			loadMainWindow();
			closeCurrentStage(event);
		}

	}

	@FXML
	public void backToMain(ActionEvent e) {
		loadMainWindow();
		closeCurrentStage(e);

	}

	@FXML
	public void exitFromTheApp(ActionEvent e) {
		closeCurrentStage(e);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		annotationTextArea.setWrapText(true);
		String defaultSelector = "Select the series";
		bookDAO = new BookDAO();
		authorDAO = new AuthorDAO();
		// Add all the series types to our choiceBox
		List<Series> seriesList = new ArrayList<Series>();
		ObservableList<String> valuesForSelectors = FXCollections.observableArrayList(defaultSelector);
		for (Series seriesParameter : seriesList) {
			valuesForSelectors.add(seriesParameter.getName());
		}
		// fill the second selector of authors
		defaultSelector = "Select the authors";
		List<Author> authorsList = authorDAO.getAllAuthors();
		valuesForSelectors = FXCollections.observableArrayList(defaultSelector);
		for (Author authorParameter : authorsList) {
			valuesForSelectors.add(authorParameter.getPseudonym());
		}
		selectorOfAuthor.setItems(valuesForSelectors);
		selectorOfAuthor.getSelectionModel().select(defaultSelector);

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
