package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardController implements BoardUI {

    Player humanPlayer;
    Player aiPlayer;
    Board board;
    @FXML
    private GridPane grid;

    public void initialize() {
        board = new BoardImpl(this);
        humanPlayer = new HumanPlayer(board);
        aiPlayer = new AiPlayer(board);
    }

    @FXML
    void gClickedOnAction(ActionEvent event) {
        Button btn = (Button) event.getSource();

        int btnId = Integer.parseInt(btn.getId().substring(1)) - 1;
        int row = btnId / 3;
        int col = btnId % 3;

        update(row, col, true);

    }

    @Override
    public void update(int row, int col, boolean isHuman) {
        String btnId = "#g" + (row * 3 + col + 1);

        Button button = (Button) grid.lookup(btnId);
        if (button.getText().isEmpty()) {
            if (isHuman) {
                humanPlayer.move(row, col);
                button.setText("X");
                if (board.checkWinner() != null) {
                    notifyWinner();
                    return;
                }
                aiPlayer.move(-1, -1);

            } else {
                button.setText("O");
                notifyWinner();
            }
        }
    }

    @Override
    public void notifyWinner() {
        Winner winner = board.checkWinner();
        if (winner != null) {
            if (winner.winningPiece == Piece.X) {
                highlightWinningPieces(winner,"red");
                showAndReset(Alert.AlertType.INFORMATION, "Human wins");
                highlightWinningPieces(winner,"white");
            } else if (winner.winningPiece == Piece.O) {
                highlightWinningPieces(winner,"red");
                showAndReset(Alert.AlertType.INFORMATION, "AI wins");
                highlightWinningPieces(winner,"white");
            } else if (winner.winningPiece == Piece.EMPTY) {
                showAndReset(Alert.AlertType.INFORMATION, "It's a draw");
            }
        }

    }

    private void highlightWinningPieces(Winner winner, String color) {
        Button[] btns = new Button[3];

        String btnId1 = "#g" + (winner.row1 * 3 + winner.col1 + 1);
        System.out.println(btnId1);
        String btnId2 = "#g" + (winner.row2 * 3 + winner.col2 + 1);
        System.out.println(btnId2);
        String btnId3 = "#g" + (winner.row3 * 3 + winner.col3 + 1);
        System.out.println(btnId3);

        btns[0] = (Button) grid.lookup(btnId1);
        btns[1] = (Button) grid.lookup(btnId2);
        btns[2] = (Button) grid.lookup(btnId3);

        for (Button btn : btns) {

            if (btn != null) {
                btn.setStyle("-fx-text-fill:"+color+";"); // Changes background to red and text color to white
            }
        }

    }

    private void showAndReset(Alert.AlertType type, String message) {
        new Alert(type, message).showAndWait();
        board.initializeBoard();

        Button button;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String btnId = "#g" + (i * 3 + j + 1);
                button = (Button) grid.lookup(btnId);

                button.setText("");
            }
        }
    }
}

