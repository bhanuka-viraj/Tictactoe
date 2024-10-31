package com.assignment.tictactoe.service;

public class Winner {
    public Piece winningPiece;
    public int row1;
    public int col1;
    public int row2;
    public int col2;
    public int row3;
    public int col3;


    public Winner(Piece winningPiece) {
        this.winningPiece = winningPiece;
    }

    public Winner(Piece winningPiece, int row1, int col1, int row2, int col2, int row3, int col3) {
        this.winningPiece = winningPiece;
        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;
        this.row3 = row3;
        this.col3 = col3;
    }
}
