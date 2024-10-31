package com.assignment.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/starter.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tic Tac Toe");
        stage.setResizable(false);

        Image icon = new Image(getClass().getResourceAsStream("/view/assets/tt-removebg-preview.png"));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}