package com.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddSeriesApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root=FXMLLoader.load(getClass().getResource("/com/gui/fxmlWindows/AddSeriesWindow.fxml"));
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
