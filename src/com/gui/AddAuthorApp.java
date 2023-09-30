package com.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddAuthorApp extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root=FXMLLoader.load(getClass().getResource("/com/gui/fxmlWindows/AddAuthorWindow.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
