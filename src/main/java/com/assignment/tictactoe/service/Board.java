package com.assignment.tictactoe.service;

public interface Board {
    public BoardUI getBoardUI();
    public void initializeBoard();
    public boolean isLegalMove(int row, int col);
    public void updateMove(int row, int col, Piece piece);
    public Winner checkWinner();
    public void printBoard();

}
