package com.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.dao.BookDAO;
import com.entity.Book;
import com.gui.helpers.DataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditBookController implements Initializable {
	private DataModel dataModel;
	private BookDAO bookDAO;
	private Book book;

	@FXML
	private Button addButton;

	@FXML
	private TextArea annotationTextArea;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnMinimize;

	@FXML
	private DatePicker date;

	@FXML
	private Label errorLabel;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField quantityOfPages;

	@FXML
	private TextField titleTextField;


	@FXML
	private void addToDataBase(ActionEvent event) {
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";

		boolean checkValidation = true;
		int quantityPages = 0;

		String title = titleTextField.getText();
		String annotationText = annotationTextArea.getText();
		String nameText = nameTextField.getText();
		/*
		 * Series theChosenSeries =
		 * db.getSeriesByTheItsName(selectorOfSeria.getValue()); Author theChosenAuthor
		 * = db.getAuthorByPseudonym(selectorOfAuthor.getValue());
		 */
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
			book.setName(nameText);
			book.setShortAnnotation(annotationText);
			book.setQuantityOfPages(quantityPages);
			book.setTitle(title);
			book.setReleaseDate(dateOfPublishement);
			bookDAO.update(book);
			loadWindow("/com/gui/fxmlWindows/MainWindow.fxml", "Main");

			closeCurrentStage(event);
		}else {
			
		}
	}

	@FXML
	public void exitFromTheApp(ActionEvent e) {
		closeCurrentStage(e);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dataModel = DataModel.getDataModel();
		bookDAO = new BookDAO();
		book = dataModel.getBook();
		annotationTextArea.setWrapText(true);

		titleTextField.setText(book.getTitle());
		nameTextField.setText(book.getName());
		quantityOfPages.setText(String.valueOf(book.getQuantityOfPages()));
		annotationTextArea.setText(book.getShortAnnotation());
		date.setValue(book.getReleaseDate());
		
	}
	@FXML
	public void backToMain(ActionEvent e) {
		loadWindow("/com/gui/fxmlWindows/MainWindow.fxml", "Main");
		closeCurrentStage(e);

	}
	private void loadWindow(String loc, String title) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.setTitle(title);
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
