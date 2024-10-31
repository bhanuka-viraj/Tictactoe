package com.assignment.tictactoe.service;

import java.util.Arrays;

public class BoardImpl implements Board {
    public Piece[][] pieces;
    public BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces = new Piece[3][3];
        initializeBoard();
    }
    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public void initializeBoard() {

        for (Piece[] piece : pieces) {
            Arrays.fill(piece, Piece.EMPTY);
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        return pieces[row][col] == Piece.EMPTY;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        if (piece==Piece.EMPTY || isLegalMove(row, col)) {
            pieces[row][col] = piece;
        }
    }

        @Override
        public Winner checkWinner() {
            boolean isDraw = false;
            //check diagonals
            if(pieces[0][0] != Piece.EMPTY &&pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]) {
                return new Winner(pieces[0][0],0,0,1,1,2,2);
            } else if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1]==pieces[2][0]) {
                return new Winner(pieces[0][2],0,2,1,1,2,0);
            }

            //check vertical and horizontal lines
            for (int i = 0; i < 3; i++) {
                //horizontal
                if (pieces[i][0] != Piece.EMPTY && pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2]) {
                    return new Winner(pieces[i][0],i,0,i,1,i,2);
                }

                //vertical
                if (pieces[0][i] != Piece.EMPTY && pieces[0][i]==pieces[1][i] && pieces[1][i]==pieces[2][i]) {
                    return new Winner(pieces[0][i],0,i,1,i,2,i);
                }

//                for (int j = 0; j < 3; j++) {
//                    if (!isLegalMove(i, j)) {
//                        isDraw = true;
//                    }
//                }
            }

//            if (isDraw) {
//                return new Winner(Piece.EMPTY,0,0,0,0,0,0);
//            }
            boolean isFull = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (pieces[i][j] == Piece.EMPTY) {
                        isFull = false;
                        break;
                    }
                }
            }

            // If the board is full and no winner, it's a draw
            if (isFull) {
                return new Winner(Piece.EMPTY, 0, 0, 0, 0, 0, 0); // Use Piece.EMPTY to indicate a draw
            }

            return null;
        }

    @Override
    public void printBoard() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                System.out.print(pieces[i][j] + " ");
            }
            System.out.println();

        }


    }

}
