package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.AppInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StarterController {
    @FXML
    private AnchorPane root;

    @FXML
    void startbtnOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/board.fxml"));
        root.getChildren().clear();
        root.getChildren().add(fxmlLoader.load());
    }

}
