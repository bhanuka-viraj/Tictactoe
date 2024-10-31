package com.assignment.tictactoe.service;

public interface BoardUI {
    public void update(int row, int col, boolean isHuman);
    public void notifyWinner();
}
