package com.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.dao.AuthorDAO;
import com.dao.BookDAO;
import com.dao.CharacterDAO;
import com.dao.SeriesDAO;
import com.entity.*;
import com.entity.Character;
import com.gui.helpers.DataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainViewController implements Initializable {
	private BookDAO bookDAO;
	private AuthorDAO authorDAO;
	private SeriesDAO seriesDAO;
	private CharacterDAO characterDAO;

	@FXML
	private Button addAuthor;

	@FXML
	private Button addBook;

	@FXML
	private Button addSeries;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnMinimize;

	@FXML
	private TableColumn<Book, Integer> idCol;

	@FXML
	private TableColumn<Book, String> nameCol;

	@FXML
	private TableColumn<Book, Integer> quantityCol;

	@FXML
	private TableColumn<Book, LocalDate> releaseDateCol;

	@FXML
	private TableColumn<Book, String> seriesCol;

	@FXML
	private TableColumn<Book, Void> actionCol;

	@FXML
	private TableView<Book> tableViewOfBooks;
	@FXML
	private TextField searchTextField;

	@FXML
	private TableColumn<Book, String> titleCol;
	// Void means that this column won't display the information from the objects of
	// type Book
	@FXML
	private TableColumn<Book, String> annotationCol;

	@FXML
	private TableView<Author> tableViewAuthors;

	@FXML
	private TableColumn<Author, Integer> idAuthorCol;

	@FXML
	private TableColumn<Author, Void> actionCol_;
	@FXML
	private TableColumn<Author, String> authorFullnameCol;

	@FXML
	private TableColumn<Author, String> pseudonymCol;

	@FXML
	private TableView<Series> tableViewSeries;

	@FXML
	private TableColumn<Series, String> descriptionCol;
	@FXML
	private TableColumn<Series, String> seriesNameCol;
	@FXML
	private TableColumn<Series, LocalDate> dateOfStartCol;
	@FXML
	private TableColumn<Series, String> typeCol;
	@FXML
	private TableColumn<Series, Integer> idSeriesCol;
	@FXML
	private TableColumn<Series, Void> actionSeriesCol;
	@FXML
	private TableView<Character> tableViewCharacter;
	@FXML
	private TableColumn<Character, String> roleCharacterCol;
	@FXML
	private TableColumn<Character, String> nameCharacterCol;
	@FXML
	private TableColumn<Character, Integer> idCharacterId;
	@FXML
	private TableColumn<Character, String> descriptionCharacterCol;
    @FXML
    private TextField bookIdTextField;
	@FXML
	public void exitFromTheApp(ActionEvent e) {
		closeCurrentStage(e);
	}
	@FXML
	public void minimizeWindow(ActionEvent event) {
		Stage stage=(Stage)btnMinimize.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void LoadAddSeriesApp(ActionEvent event) {
		loadWindow("/com/gui/fxmlWindows/AddSeriesWindow.fxml", "New series");
		closeCurrentStage(event);
	}

	@FXML
	private void LoadAddBookApp(ActionEvent event) {
		loadWindow("/com/gui/fxmlWindows/AddBookWindow.fxml", "New book");
		closeCurrentStage(event);
	}
	@FXML
	private void LoadCharacterToTheBookApp(ActionEvent event) {
		loadWindow("/com/gui/fxmlWindows/CharacterAddToTheBookWindow.fxml", "Add the character to the book");
		closeCurrentStage(event);
	}
	@FXML
	private void LoadBookToTheSeriesApp(ActionEvent event) {
		loadWindow("/com/gui/fxmlWindows/BookAddToTheBookSeries.fxml", "Add the book to the series");
		closeCurrentStage(event);
	}
	@FXML
	private void LoadAddAuthorApp(ActionEvent event) {
		loadWindow("/com/gui/fxmlWindows/AddAuthorWindow.fxml", "New author");
		closeCurrentStage(event);
	}

	@FXML
	public void LoadAddCharacterApp(ActionEvent event) {
		loadWindow("/com/gui/fxmlWindows/AddCharacterWindow.fxml", "New character");
		closeCurrentStage(event);
	}

	@FXML
	private void showCharactersOfThebook() {
		String styleError = "-fx-border-color:red; -fx-border-width:0.5px;";

		if(!bookIdTextField.getText().isEmpty()) {
	         int bookId=Integer.parseInt(bookIdTextField.getText());
	 		ObservableList<Character>  characterList  = FXCollections.observableArrayList(characterDAO.findCharactersOfTheBook(bookId));
	 		
	 		 refreshDateToTableCharacters( characterList );
		}else {
			bookIdTextField.setStyle(styleError);
			new animatefx.animation.Shake(bookIdTextField).play();
		}
         
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bookDAO = new BookDAO();
		authorDAO = new AuthorDAO();
		seriesDAO = new SeriesDAO();
		characterDAO = new CharacterDAO();
		refreshDateToTableAuthors();
		refreshDateToTableBooks();
		refreshDateToTableSeries();
		refreshDateToTableCharacters();
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

	private void closeCurrentStage(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage currentStage = (Stage) source.getScene().getWindow();
		currentStage.close();
	}

	private void refreshDateToTableAuthors() {
		ObservableList<Author> authorList = FXCollections.observableArrayList(authorDAO.getAllAuthors());
		idAuthorCol.setCellValueFactory(new PropertyValueFactory<>("authorId"));
		authorFullnameCol.setCellValueFactory(new PropertyValueFactory<>("fullname"));
		pseudonymCol.setCellValueFactory(new PropertyValueFactory<>("pseudonym"));
		actionCol_.setCellFactory(param -> new TableCell<Author, Void>() {
			// initialization of the instances of the class
			private final HBox buttonsBox = new HBox();

			private final Button editButton = new Button("Edit");
			private final Button deleteButton = new Button("Delete");
			{
				editButton.getStyleClass().add("button_edit");
				deleteButton.getStyleClass().add("button_delete");

				editButton.setOnAction(event -> {
					Author author = getTableView().getItems().get(getIndex());
                    DataModel dataModel=DataModel.getDataModel();
                    dataModel.setAuthor(author);
					loadWindow("/com/gui/fxmlWindows/EditAuthorWindow.fxml", "Edit Author");
					closeCurrentStage(event);
				});

				deleteButton.setOnAction(event -> {
					Author author = getTableView().getItems().get(getIndex());
					deleteAuthor(author.getAuthorId());
				});
				buttonsBox.getChildren().addAll(editButton, deleteButton);
				buttonsBox.setAlignment(Pos.CENTER);

			}
			// update

			protected void updateItem(Void item, boolean empty) {

				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(buttonsBox);
				}
			}

		});
		tableViewAuthors.setItems(authorList);

	}

	private void refreshDateToTableCharacters() {
		ObservableList<Character> characterList = FXCollections.observableArrayList(characterDAO.getAllCharacters());

		roleCharacterCol.setCellValueFactory(new PropertyValueFactory<>("parcitipationLevelStr"));
		nameCharacterCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		idCharacterId.setCellValueFactory(new PropertyValueFactory<>("id"));
		descriptionCharacterCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		tableViewCharacter.setItems(characterList);

	}
	private void refreshDateToTableCharacters(ObservableList<Character> characterList ) {

		roleCharacterCol.setCellValueFactory(new PropertyValueFactory<>("parcitipationLevelStr"));
		nameCharacterCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		idCharacterId.setCellValueFactory(new PropertyValueFactory<>("id"));
		descriptionCharacterCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		tableViewCharacter.setItems(characterList);

	}
	private void refreshDateToTableSeries() {
		// get the data from the database where all the series are stored and then get
		// ArrayList and make from it ObservableList
		ObservableList<Series> seriesList = FXCollections.observableArrayList(seriesDAO.getAllTheSeries());
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		typeCol.setCellValueFactory(new PropertyValueFactory<>("seriesNameInString"));
		dateOfStartCol.setCellValueFactory(new PropertyValueFactory<>("dateOfTheStart"));
		seriesNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		idSeriesCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		// add the buttons to edit or delete the information to the last column of each
		// row

		actionSeriesCol.setCellFactory(param -> new TableCell<Series, Void>() {
			// initialization of the instances of the class
			private final HBox buttonsBox = new HBox();

			private final Button editButton = new Button("Edit");
			private final Button deleteButton = new Button("Delete");
			{
				editButton.getStyleClass().add("button_edit");
				deleteButton.getStyleClass().add("button_delete");

				editButton.setOnAction(event -> {
					Series series = getTableView().getItems().get(getIndex());
					DataModel dataModel=DataModel.getDataModel();
					dataModel.setSeries(series);
					loadWindow("/com/gui/fxmlWindows/EditSeriesWindow.fxml", "Edit Series");
					closeCurrentStage(event);

				});

				deleteButton.setOnAction(event -> {
					Series series = getTableView().getItems().get(getIndex());

                    seriesDAO.delete(series.getId());
				});
				buttonsBox.getChildren().addAll(editButton, deleteButton);
				buttonsBox.setAlignment(Pos.CENTER);

			}
			// update

			protected void updateItem(Void item, boolean empty) {

				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(buttonsBox);
				}
			}

		});
		tableViewSeries.setItems(seriesList);

	}

	private void refreshDateToTableBooks() {
		// get the data from the database where all the books are stored and then get
		// ArrayList and make from it ObservableList
		ObservableList<Book> booksList = FXCollections.observableArrayList(bookDAO.getAllBooks());
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantityOfPages"));
		annotationCol.setCellValueFactory(new PropertyValueFactory<>("shortAnnotation"));
		releaseDateCol.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

		// add the buttons to edit or delete the information to the last column of each
		// row

		actionCol.setCellFactory(param -> new TableCell<Book, Void>() {
			// initialization of the instances of the class
			private final HBox buttonsBox = new HBox();
			private final Button editButton = new Button("Edit");
			private final Button deleteButton = new Button("Delete");
			{
				editButton.getStyleClass().add("button_edit");
				deleteButton.getStyleClass().add("button_delete");

				editButton.setOnAction(event -> {
					Book book = getTableView().getItems().get(getIndex());
					DataModel dataModel = DataModel.getDataModel();
					dataModel.setBook(book);
					loadWindow("/com/gui/fxmlWindows/EditBookWindow.fxml", "Edit Book");
					closeCurrentStage(event);

				});

				deleteButton.setOnAction(event -> {
					Book book = getTableView().getItems().get(getIndex());
					deleteBook(book.getId());

				});
				buttonsBox.getChildren().addAll(editButton, deleteButton);
				buttonsBox.setAlignment(Pos.CENTER);

			}
			// update

			protected void updateItem(Void item, boolean empty) {

				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(buttonsBox); // Customize the layout as needed
				}
			}

		});

		tableViewOfBooks.setItems(booksList);
		FilteredList<Book> filteredData = new FilteredList<>(booksList, b -> true);
		searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(book -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (book.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(book.getQuantityOfPages()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (book.getShortAnnotation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(book.getReleaseDate()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (String.valueOf(book.getId()).indexOf(lowerCaseFilter) != -1) {
					return true;
				} else {
					return false;
				}
			});
		});
		SortedList<Book> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableViewOfBooks.comparatorProperty());
		tableViewOfBooks.setItems(sortedData);
	}
	
	private void deleteAuthor(int authorId) {
		bookDAO.delete(authorId);
		refreshDateToTableBooks();
	}

	private void deleteBook(int bookId) {
		bookDAO.delete(bookId);
		refreshDateToTableBooks();
	}

}
